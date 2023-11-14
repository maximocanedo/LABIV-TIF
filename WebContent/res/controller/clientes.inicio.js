"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";
import * as accounts from "./../data/accounts.js";
import * as loans from "./../data/loans.js";
import * as accountTypes from "./../data/accountTypes.js";

const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', { style: 'currency', currency: 'ARS' });
    return numeroFormateado;
}




(async () => {
	// Instanciar componentes
	material.loadElements();

		const tipoCuentaSelect_root = document.querySelector("#tipoCuentaSelect");
		const tipoCuentaSelect = new material.mdc.list.MDCList(tipoCuentaSelect_root);
	// Obtener datos del cliente actual. Si no hay cliente redirige a la página de inicio de sesión.
	const actualUser = await auth.allowClient();
	document.querySelectorAll(".account_card:not(.action__account)").forEach(e => {
		e.classList.add("non-displayable");
		material.rippleIt(e);
	});
	material.rippleIt(document.querySelector(".action__account"));
	const cuentasData = await accounts.getAccounts();
	console.log(cuentasData);
	const cuentas = cuentasData.data.listReturned;
	if(cuentas == undefined) return;
	for(let i = 0; i < cuentas.length; i++) {
		if(i == 2) {
			document.querySelector(".action__account").classList.add("non-displayable");
		}
		document.querySelectorAll(".account_" + (i+1)).forEach(e =>{ 
			e.classList.remove("non-displayable");
			e.addEventListener("click", event => {
				window.location = "http://localhost:8080/TPINT_GRUPO_3_LAB/clientes/MiCuenta.jsp?accountno=" + cuentas[i].numero;
			})
		});
		document.querySelector(".__account_" + (i + 1) + "_tipodesc").innerText = cuentas[i].tipo.descripcion;
		document.querySelector(".__account_" + (i + 1) + "_saldo").innerText = formatearComoDinero(cuentas[i].saldo);
		document.querySelector(".__account_" + (i + 1) + "_nc").innerText = formatearNumeroCuenta(cuentas[i].numero);
		document.querySelector(".__account_" + (i + 1) + "_cbu").innerText = cuentas[i].CBU;
	}


	document.querySelector(".action__account").addEventListener('click', 
	(async () => {
		const dialog = material.showOtherDialog("#crearCuentaBancaria");
		const tipoCuentaSelect_root = document.querySelector("#tipoCuentaSelect");
		const tipoCuentaSelect = new material.mdc.list.MDCList(tipoCuentaSelect_root);
		tipoCuentaSelect.layout();
		console.log(tipoCuentaSelect);
		const tipos_f = await accountTypes.getAll();
		const data = tipos_f.data.listReturned;
		if(data == undefined) return;
		tipoCuentaSelect_root.innerHTML = "";
		for(let i = 0; i < data.length; i++) {
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
		if(document.crearCuentaBancaria.tipoCuenta.value == '') {
			material.showSnackbar("No seleccionaste el tipo de cuenta. ");
		}
		let data = {
		    "Cod_TPCT_CxC": document.crearCuentaBancaria.tipoCuenta.value
		};
		let v = tipoCuentaSelect.foundation.adapter.getPrimaryTextAtIndex(tipoCuentaSelect.foundation.selectedIndex);
		let d = await material.showDialog("Vas a crear una " + v + ".\n¿Continuar?");
		if(!d) {
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
		if(f.status == 201 || f.status == 200) {
			material.showSnackbar("¡Tu cuenta se creó exitosamente!");
		} else {
			material.showSnackbar("Hubo un problema y tu cuenta no se creó. ");
		}
	});




})();


// Tabla SOLIICITUDES DE PRÉSTAMOS
(async () => {
	const paginationReducer = (state = { page: 1, size: 10 }, action) => {
	  switch (action.type) {
	    case 'SET_PAGE':
	      return { ...state, page: action.payload };
	    case 'NEXT_PAGE': 
	    	return {...state, page: state.page + 1};
	    case 'PREV_PAGE': 
	    	return {...state, page: state.page > 1 ? state.page - 1 : 1 }
	    case 'SET_SIZE':
	      return { ...state, size: action.payload };
	    default:
	      return state;
	  }
	};

	// Crear el store de Redux
	const store = Redux.createStore(paginationReducer);

	// Acciones para cambiar la página y el tamaño de la página
	const setPage = (page) => ({
	  type: 'SET_PAGE',
	  payload: page,
	});

	const nextPage = () => ({
	  type: 'NEXT_PAGE',
	  payload: 1,
	});

	const prevPage = () => ({
	  type: 'PREV_PAGE',
	  payload: 1,
	});

	const setSize = (size) => ({
	  type: 'SET_SIZE',
	  payload: size,
	});
	const fillLoanRequestsTable = async () => {
		const cliente = auth.allowClient();
		const ress = await loans.getMyLoanRequests(store.getState());
		const data = ress.list;
		document.querySelector("#tablaSolicitudesPrestamos__body").innerHTML = "";
		for(let i = 0; i < data.length; i++) {
			let html = `
			<tr class="mdc-data-table__row">
	          <th class="mdc-data-table__cell mdc-data-table__cell--numeric" scope="row">${data[i].codigo}</th>
	          <td class="mdc-data-table__cell">${formatearNumeroCuenta(data[i].cuenta.numero)}</td>
	          <td class="mdc-data-table__cell mdc-data-table__cell--numeric">${formatearComoDinero(data[i].montoPedido)}</td>
	          <td class="mdc-data-table__cell">${data[i].cantCuotas} &times; ${formatearComoDinero(data[i].montoPorCuota)}</td>
	        </tr>
			`;
			document.querySelector("#tablaSolicitudesPrestamos__body").innerHTML += html;
		}
	};

	// Suscribirse al estado del store para actualizarse cuando cambie la paginación
	store.subscribe(async () => {
	  const { page, size } = store.getState();
	  await fillLoanRequestsTable();
	  document.querySelector("#tablaSolicitudesPrestamos__textoPaginacion").innerText = `Página ${page}`;
	  console.log({page, size});
	});

	const paginator = {
		page: 1,
		size: 10
	};
	const mdSelectPaginator = material.loadSelect(document.querySelector("#mdSelectPaginator"));
	const nextBtn = document.querySelector("#tablaSolicitudesPrestamos__btnSiguiente");
	const prevBtn = document.querySelector("#tablaSolicitudesPrestamos__btnAnterior");
	
	mdSelectPaginator.root.addEventListener("MDCSelect:change", e => {
		let size = e.detail.value;
		store.dispatch(setSize(size));
	});
	nextBtn.addEventListener("click", e => store.dispatch(nextPage()));
	prevBtn.addEventListener("click", e => store.dispatch(prevPage()));
	store.dispatch(setPage(1));
})();