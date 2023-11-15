'use strict';
import * as auth from "./../data/auth.js";
import * as material from "./mdc.controller.js";
import * as loans from "./../data/loans.js";
import * as accounts from "./../data/accounts.js";

const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', { style: 'currency', currency: 'ARS' });
    return numeroFormateado;
}


const fillLoan = async (loan) => {
	const f = (sel, text) => {
		document.querySelectorAll(sel).forEach(e => e.innerText = text);
	}
	f("._title", "Préstamo #" + loan.id);
	f("._otorgadoEl", `Otorgado el ${loan.fechaOtorgado}, a pagar en ${loan.plazoPago} meses. `);
	f("._estadoCuotas", `${loan.cantCuotas} cuotas. `);
	f("._estadoCuotasPagas", `${loan.cuotasRestantes} cuotas restantes. `);
};


const loadData = async (cliente, loanId) => {
	const f = await loans.getById(loanId);
	if(f.status != 200) {
		material.showSnackbar("No se encontró un préstamo con el código especificado. ");
		return;
	}
	const loan = f.data.listReturned[0];
	if(loan == null) {
		material.showSnackbar("Hubo un error al intentar procesar los detalles del préstamo. ");
		return;
	}
	await fillLoan(loan);
	document.querySelector("#pagarCuota").addEventListener("click", async (e) => {
		await cargarCuentas(cliente, loan);
		material.showOtherDialog("#pagarCuotaDialog");
	});
};

const cargarCuentas = async (cliente, loan) => {
	const cuentaRadioSelect_root = document.querySelector("#cuentaRadioSelect");
	const cuentaRadioSelect = new material.mdc.list.MDCList(cuentaRadioSelect_root);
	document.querySelector("#continuarBtn").disabled = true;
	cuentaRadioSelect_root.innerHTML = "";
	const misCuentas = await accounts.getAccounts();
	if(misCuentas.status != 200) {
		material.showSnackbar("Hubo un problema al cargar tus cuentas. ");
		return;
	}
	const data = misCuentas.data.listReturned;
	const canPay = [];
	for(let i = 0; i < data.length; i++) {
		let disabled = loan.montoPorCuota > data[i].saldo;
		canPay.push(!disabled);
		let html = `
		  <li class="mdc-deprecated-list-item ${disabled ? "mdc-deprecated-list-item--disabled" : ""}" role="radio" aria-checked="false">
		    <span class="mdc-deprecated-list-item__ripple"></span>
		    <span class="mdc-deprecated-list-item__graphic">
		      <div class="mdc-radio">
		        <input class="mdc-radio__native-control"
		              type="radio"
		              ${disabled ? " disabled " : ""}
		              id="crearCuentaBancaria-${i}"
		              name="cuenta"
		              value="${i}">
		        <div class="mdc-radio__background">
		          <div class="mdc-radio__outer-circle"></div>
		          <div class="mdc-radio__inner-circle"></div>
		        </div>
		      </div>
		    </span>
		    <label class="mdc-deprecated-list-item__text" for="crearCuentaBancaria-${i}">
		      <span class="mdc-deprecated-list-item__primary-text">${data[i].tipo.descripcion} ${formatearNumeroCuenta(data[i].numero)}</span>
		      <span class="mdc-deprecated-list-item__secondary-text">${formatearComoDinero(data[i].saldo)}<br /></span>
		    </label>
		  </li>
		`;
		cuentaRadioSelect_root.innerHTML += html;
	}
	cuentaRadioSelect.layout();
	const noneCanPay = canPay.every(el => el == false);
	if(!noneCanPay) {
		document.querySelector("#continuarBtn").disabled = false;
	}
	document.querySelector("#continuarBtn").addEventListener("click", async (e) => {
		if(document.pay.cuenta.value == '') {
			material.showSnackbar("Seleccioná una cuenta. ");
			return;
		}
		const v = parseInt(document.pay.cuenta.value);
		const cuenta = data[v];
		await pagarCuota(cliente, loan, cuenta);
	});
};

const pagarCuota = async (cliente, loan, cuenta) => {
	const confirmation = await material.showDialog(
		`Vas a pagar una cuota de ${formatearComoDinero(loan.montoPorCuota)} del préstamo #${loan.id}, con tu ${cuenta.tipo.descripcion} ${formatearNumeroCuenta(cuenta.numero)}. \n¿Continuar?`
	);
	if(!confirmation) {
		material.showSnackbar("Cancelaste el pago de la cuota. ");
		return;
	}
	const res = await loans.pagarCuota({
		"cod_Sol": loan.solicitud.codigo,
		"CBU_Sol": cuenta.CBU
	});
	if(res.status == 200 || res.status == 201) {
		material.showSnackbar("El pago se realizó con éxito. ");
		await loadData(cliente, loan.id);
		return;
	} else {
		material.showSnackbar("Hubo un error al procesar el pago. ");
		return;
	}

};

(async () => {
	material.loadElements();
	const cliente = await auth.allowClient({
		message: "Iniciá sesión para ver el estado de tus préstamos. "
	});
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noLoan = urlParams.get('id');
	if (noLoan == null) {
		material.showDialog("No se especificó un código de préstamo válido. ");
		return;
	}
	await loadData(cliente, noLoan);
})();


// Tabla CUOTAS PAGADAS
(async () => {

	const fillLoanQuotas = async () => {
		return;
		const cliente = auth.allowClient();
		const ress = await loans.getMyLoanRequests(store.getState());
		const data = ress.list;
		document.querySelector("#tablaCuotasPagadas__body").innerHTML = "";
		for(let i = 0; i < data.length; i++) {
			let leggend = `<span class="mdc-typography--button importe_${data[i].estado ? "plus" : "less"}">${data[i].estado ? "Aprobado" : "Sin aprobar / En espera"}</span>`
			let html = `
			<tr class="mdc-data-table__row">
	          <th class="mdc-data-table__cell mdc-data-table__cell--numeric" scope="row">${data[i].codigo}</th>
	          <td class="mdc-data-table__cell">${formatearNumeroCuenta(data[i].cuenta.numero)}</td>
	        </tr>
			`;
			document.querySelector("#tablaCuotasPagadas__body").innerHTML += html;
		}
	};
	await fillLoanQuotas();

})();

