"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";
import * as accounts from "./../data/accounts.js";
import * as movements from "./../data/movements.js";

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

// Suscribirse al estado del store para actualizarse cuando cambie la paginación
store.subscribe(async () => {
  const { page, size } = store.getState();
  await loadTable();
  document.querySelector("#tablaMovimientos__textoPaginacion").innerText = `Página ${page}`;
  console.log({page, size});
});



let paginator = {
	page: 1,
	size: 10
};
const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', { style: 'currency', currency: 'ARS' });
    return numeroFormateado;
}
const logic = {
	formatearDNI: (dni) => {
		if (dni.length === 9) {
			return (
				dni.slice(0, 3) + "." + dni.slice(3, 6) + "." + dni.slice(6, 9)
			);
		} else if (dni.length === 8) {
			return (
				dni.slice(0, 2) + "." + dni.slice(2, 5) + "." + dni.slice(5, 8)
			);
		} else {
			return dni;
		}
	},
	formatearCUIL: (cuil) => {
		const prefijo = cuil.substring(0, 2);
		const sufijo = cuil.substring(cuil.length - 1);
		const dni = cuil.substring(2, cuil.length - 1);
		return `${prefijo}-${logic.formatearDNI(dni)}-${sufijo}`;
	},
};

/*const loadData = async() => {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noCuenta = urlParams.get('accountno');
	if(noCuenta == null) {
		material.showDialog("No se especificó un número de cuenta válido. ");
		return;
	}
	document.querySelectorAll(".account_card").forEach(e => e.classList.add("non-displayable"));
	const cuentasData = await accounts.getAccount(noCuenta);
	const cuentas = cuentasData.data.listReturned;
	for(let i = 0; i < 1; i++) {
		document.querySelectorAll(".account_1").forEach(e => e.classList.remove("non-displayable"));
		document.querySelectorAll(".__account_1_tipodesc").forEach(e => e.innerText = cuentas[i].tipo.descripcion);
		document.querySelectorAll(".__account_1_saldo").forEach(e => e.innerText = formatearComoDinero(cuentas[i].saldo));
		document.querySelectorAll(".__account_1_nc").forEach(e => e.innerText = formatearNumeroCuenta(cuentas[i].numero));
		document.querySelectorAll(".__account_1_cbu").forEach(e => e.innerText = cuentas[i].CBU);
		document.querySelectorAll(".__account_1_fechaCreacion").forEach(e => e.innerText = cuentas[i].fechaCreacion);	
		document.querySelectorAll(".__account_1_clname").forEach(e => e.innerText = cuentas[i].cliente.apellido + ", " + cuentas[i].cliente.nombre);	
		document.querySelectorAll(".__account_1_clcuil").forEach(e => e.innerText = logic.formatearCUIL(cuentas[i].cliente.CUIL));	
		document.querySelectorAll(".__account_1_clnac").forEach(e => e.innerText = cuentas[i].cliente.nacionalidad.nombre);			 
	}
	store.dispatch(setPage(paginator.page));
}; */

const loadData = () => {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noCuenta = urlParams.get('accountno');

	if (noCuenta == null) {
		material.showDialog("No se especificó un número de cuenta válido. ");
		return;
	}

	document.querySelectorAll(".account_card").forEach(e => e.classList.add("non-displayable"));

	accounts.getAccount(noCuenta)
		.then(cuentasData => {
			const cuentas = cuentasData.data.listReturned;
			for (let i = 0; i < 1; i++) {
				document.querySelectorAll(".account_1").forEach(e => e.classList.remove("non-displayable"));
				document.querySelectorAll(".__account_1_tipodesc").forEach(e => e.innerText = cuentas[i].tipo.descripcion);
				document.querySelectorAll(".__account_1_saldo").forEach(e => e.innerText = formatearComoDinero(cuentas[i].saldo));
				document.querySelectorAll(".__account_1_nc").forEach(e => e.innerText = formatearNumeroCuenta(cuentas[i].numero));
				document.querySelectorAll(".__account_1_cbu").forEach(e => e.innerText = cuentas[i].CBU);
				document.querySelectorAll(".__account_1_fechaCreacion").forEach(e => e.innerText = cuentas[i].fechaCreacion);
				document.querySelectorAll(".__account_1_clname").forEach(e => e.innerText = cuentas[i].cliente.apellido + ", " + cuentas[i].cliente.nombre);
				document.querySelectorAll(".__account_1_clcuil").forEach(e => e.innerText = logic.formatearCUIL(cuentas[i].cliente.CUIL));
				document.querySelectorAll(".__account_1_clnac").forEach(e => e.innerText = cuentas[i].cliente.nacionalidad.nombre);
			}
			store.dispatch(setPage(paginator.page));
		})
		.catch(error => {
			// Manejar el error si la obtención de datos falla
			console.error("Error al obtener los datos de la cuenta:", error);
		});
};

/*
const loadTable = async () => {
	const actualUser = await auth.allowClient();
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noCuenta = urlParams.get('accountno');
	
	const movimientos = await movements.getMovementsFromAccount(noCuenta, store.getState());
	for(let i = 0; i < movimientos.data.listReturned.length; i++) {
		let mv = movimientos.data.listReturned[i];
		let row = document.createElement("tr");
		row.classList.add("mdc-data-table__row");
		let html = `
	          <td class="mdc-data-table__cell mdc-data-table__cell--numeric">${mv.id}</td>
	          <th class="mdc-data-table__cell" scope="row">${mv.concepto.descripcion}</th>
	          <td class="mdc-data-table__cell">${mv.tipo.descripcion}</td>
	          <td class="mdc-data-table__cell mdc-data-table__cell--numeric mdc-typography--button ${mv.importe > 0 ? "importe_plus" : "importe_less"}">
	          	${mv.importe > 0 ? "+" : "-"} ${formatearComoDinero(mv.importe)}
	          </td>
		`;
		row.innerHTML = html;
		row.addEventListener("click", () => {
			const dialog = material.showDialog(`ID: ${mv.id}\nCuenta: ${formatearNumeroCuenta(mv.cuenta.numero)}\nConcepto: ${mv.concepto.descripcion}\nTipo: ${mv.tipo.descripcion}\nImporte: ${formatearComoDinero(mv.importe)}`)
		});
		document.querySelector("#tablaMovimientos__body").append(row);
	}
	console.log(movimientos);
}; */

const loadTable = () => {
	auth.allowClient()
		.then(actualUser => {
			const queryString = window.location.search;
			const urlParams = new URLSearchParams(queryString);
			const noCuenta = urlParams.get('accountno');
			
			return movements.getMovementsFromAccount(noCuenta, store.getState());
		})
		.then(movimientos => {
				document.querySelector("#tablaMovimientos__body").innerHTML = "";
			movimientos.data.listReturned.forEach(mv => {
				let row = document.createElement("tr");
				row.classList.add("mdc-data-table__row");
				let html = `
					<td class="mdc-data-table__cell mdc-data-table__cell--numeric">${mv.id}</td>
					<th class="mdc-data-table__cell" scope="row">${mv.concepto.descripcion}</th>
					<td class="mdc-data-table__cell">${mv.tipo.descripcion}</td>
					<td class="mdc-data-table__cell mdc-data-table__cell--numeric mdc-typography--button ${mv.importe > 0 ? "importe_plus" : "importe_less"}">
						${mv.importe > 0 ? "+" : "-"} ${formatearComoDinero(mv.importe)}
					</td>
				`;
				row.innerHTML = html;
				row.addEventListener("click", () => {
					const dialog = material.showDialog(`ID: ${mv.id}\nCuenta: ${formatearNumeroCuenta(mv.cuenta.numero)}\nConcepto: ${mv.concepto.descripcion}\nTipo: ${mv.tipo.descripcion}\nImporte: ${formatearComoDinero(mv.importe)}`);
				});
				document.querySelector("#tablaMovimientos__body").append(row);
			});
			console.log(movimientos);
		})
		.catch(error => {
			// Manejar el error si la obtención de datos falla
			console.error("Error al cargar la tabla de movimientos:", error);
		});
};


(async () => {
	// Instanciar componentes
	material.loadElements();

	console.log({material});

	const mdSelectPaginator = material.loadSelect(document.querySelector("#mdSelectPaginator"));
	const nextBtn = document.querySelector("#tablaMovimientos__btnSiguiente");
	const prevBtn = document.querySelector("#tablaMovimientos__btnAnterior");
	
	mdSelectPaginator.root.addEventListener("MDCSelect:change", e => {
		let size = e.detail.value;
		store.dispatch(setSize(size));
	});
	nextBtn.addEventListener("click", e => store.dispatch(nextPage()));
	prevBtn.addEventListener("click", e => store.dispatch(prevPage()));

	await loadData();
	await loadTable();

	
})();
