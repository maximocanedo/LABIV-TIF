"use strict";
import * as material from "../../res/controller/mdc.controller.js";
import * as auth from "../../res/data/auth.js";
import * as accounts from "../../res/data/accounts.js";
import * as loans from "../../res/data/loans.js";
import * as accountTypes from "../../res/data/accountTypes.js";
import {DataTableView, TableBodyCell, TableBodyRow, TableHeaderCell} from "../../res/controller/components/index.js";

const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', {style: 'currency', currency: 'ARS'});
    return numeroFormateado;
}

const cargarCuentas = async () => {
    const actualUser = await auth.allowClient();
    document.querySelectorAll(".account_card:not(.action__account)").forEach(e => {
        e.classList.add("non-displayable");
        material.rippleIt(e);
    });
    material.rippleIt(document.querySelector(".action__account"));
    const cuentasData = await accounts.getAccounts();
    console.log(cuentasData);
    const cuentas = cuentasData.data.listReturned;
    if (cuentas == undefined) return;
    for (let i = 0; i < cuentas.length; i++) {
        if (i == 2) {
            document.querySelector(".action__account").classList.add("non-displayable");
        }
        document.querySelectorAll(".account_" + (i + 1)).forEach(e => {
            e.classList.remove("non-displayable");
            e.addEventListener("click", event => {
                window.location = "http://localhost:8080/TPINT_GRUPO_3_LAB/clientes/account?accountno=" + cuentas[i].numero;
            })
        });
        document.querySelector(".__account_" + (i + 1) + "_tipodesc").innerText = cuentas[i].tipo.descripcion;
        document.querySelector(".__account_" + (i + 1) + "_saldo").innerText = formatearComoDinero(cuentas[i].saldo);
        document.querySelector(".__account_" + (i + 1) + "_nc").innerText = formatearNumeroCuenta(cuentas[i].numero);
        document.querySelector(".__account_" + (i + 1) + "_cbu").innerText = cuentas[i].CBU;
    }
}


(async () => {
    // Instanciar componentes
    material.loadElements();

    const tipoCuentaSelect_root = document.querySelector("#tipoCuentaSelect");
    const tipoCuentaSelect = new material.mdc.list.MDCList(tipoCuentaSelect_root);
    // Obtener datos del cliente actual. Si no hay cliente redirige a la página de inicio de sesión.

    await cargarCuentas();

    document.querySelector(".action__account").addEventListener('click',
        (async () => {
            const dialog = material.showOtherDialog("#crearCuentaBancaria");
            const tipoCuentaSelect_root = document.querySelector("#tipoCuentaSelect");
            const tipoCuentaSelect = new material.mdc.list.MDCList(tipoCuentaSelect_root);
            tipoCuentaSelect.layout();
            console.log(tipoCuentaSelect);
            const tipos_f = await accountTypes.getAll();
            const data = tipos_f.data.listReturned;
            if (data == undefined) return;
            tipoCuentaSelect_root.innerHTML = "";
            for (let i = 0; i < data.length; i++) {
                let html = `
			  <li class="mdc-deprecated-list-item" role="radio" aria-checked="false">
			    <span class="mdc-deprecated-list-item__ripple"></span>
			    <span class="mdc-deprecated-list-item__graphic">
			      <div class="mdc-radio">
			        <input class="mdc-radio__native-control"
			              type="radio"
			              id="crearCuentaBancaria-${i}"
			              name="tipoCuenta"
			              value="${data[i].codigo}">
			        <div class="mdc-radio__background">
			          <div class="mdc-radio__outer-circle"></div>
			          <div class="mdc-radio__inner-circle"></div>
			        </div>
			      </div>
			    </span>
			    <label class="mdc-deprecated-list-item__text" for="crearCuentaBancaria-${i}">${data[i].descripcion}</label>
			  </li>
			`;
                tipoCuentaSelect_root.innerHTML += html;
            }
            tipoCuentaSelect.layout();
        }));


    document.querySelector("#crearCuentaBtn").addEventListener('click', async (e) => {
        const tipoCuentaSelect_root = document.querySelector("#tipoCuentaSelect");
        const tipoCuentaSelect = new material.mdc.list.MDCList(tipoCuentaSelect_root);
        if (document.crearCuentaBancaria.tipoCuenta.value == '') {
            material.showSnackbar("No seleccionaste el tipo de cuenta. ");
        }
        let data = {
            "Cod_TPCT_CxC": document.crearCuentaBancaria.tipoCuenta.value
        };
        let v = tipoCuentaSelect.foundation.adapter.getPrimaryTextAtIndex(tipoCuentaSelect.foundation.selectedIndex);
        let d = await material.showDialog("Vas a crear una " + v + ".\n¿Continuar?");
        if (!d) {
            material.showSnackbar("Cancelaste la operación. ");
            return;
        }
        let f = await fetch(
            'http://localhost:8080/TPINT_GRUPO_3_LAB/api/accounts', {
                method: 'POST',
                headers: auth.AUTH_HEADER,
                body: JSON.stringify(data)
            }
        );
        if (f.status == 201 || f.status == 200) {
            material.showSnackbar("¡Tu cuenta se creó exitosamente!");
            await cargarCuentas();
        } else {
            material.showSnackbar("Hubo un problema y tu cuenta no se creó. ");
        }
    });


})();


// Tabla SOLIICITUDES DE PRÉSTAMOS
(async () => {
    const tablaPrestamos = new DataTableView("Mis solicitudes de préstamos", {
        header: [
            new TableHeaderCell("ID", { isNumeric: true }),
            new TableHeaderCell("Cuenta"),
            new TableHeaderCell("Monto", { isNumeric: true }),
            new TableHeaderCell("Cuotas"),
            new TableHeaderCell("Estado")
        ],
        paginator: {
            default: 5,
            values: [5, 10, 15]
        }
    });
    document.querySelector("#myLoanRequests").append(tablaPrestamos.getElement());

    const fillLoanRequestsTable = async () => {
        tablaPrestamos.showProgress();
        const cliente = auth.allowClient();
        const ress = await loans.getMyLoanRequests(tablaPrestamos.getPaginatorDetails());
        const data = ress.list;
        tablaPrestamos.clear();
        for (let i = 0; i < data.length; i++) {
            const req = data[i];
            const leggendEl = document.createElement("span");
            leggendEl.classList.add("mdc-typography--button", `importe_${data[i].estado == 1 ? "plus" : (data[i].estado == -1 ? "less" : "none")}`);
            leggendEl.innerText = data[i].estado == 1 ? "Aprobado" : (data[i].estado == -1 ? "Rechazado" : "En espera");
            const row = new TableBodyRow([
                new TableBodyCell(req.codigo, { isNumeric: true }),
                new TableBodyCell(formatearNumeroCuenta(data[i].cuenta.numero)),
                new TableBodyCell(formatearComoDinero(data[i].montoPedido), { isNumeric: true }),
                new TableBodyCell(`${data[i].cantCuotas} × ${formatearComoDinero(data[i].montoPorCuota)}`),
                new TableBodyCell(leggendEl)
            ], req.codigo);
            tablaPrestamos.add(row);
        }
        tablaPrestamos.hideProgress();
    };
    tablaPrestamos.addEventListener('paginateChange', e => {
        (async () => {
            await fillLoanRequestsTable();
        })();
    });

    await fillLoanRequestsTable();
})();

