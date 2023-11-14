"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";
import * as accounts from "./../data/accounts.js";
import * as movements from "./../data/movements.js";
import * as concepts from "./../data/concepts.js";

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



(async () => {
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noCuenta = urlParams.get('accountno');

	const tipusDestinoSegmentedButton_root = document.querySelector("#tipusDestinoSegmentedButton");
	const tipusDestinoSegmentedButton = new material.mdc.segmentedButton.MDCSegmentedButton(tipusDestinoSegmentedButton_root);
	const tipusValue = ["CBU", "No"][tipusDestinoSegmentedButton.foundation.getSelectedSegments()[0].index];
	const conceptoSelect = material.loadSelect(document.querySelector("#conceptoSelect")); // Cargar:
	console.log(tipusDestinoSegmentedButton);
	let data = {
		monto: document.transfer.montoTotal.value,
		cuenta: noCuenta,
		cuentaDestino: {
			id: tipusValue,
			value: document.transfer.idCuentaDestino.value
		}
	};
	(async () => {
		const conceptos_data = await concepts.getConcepts();
		if(conceptos_data.status != 200) {
			material.showSnackbar("Hubo un problema al intentar cargar los conceptos. ");
			return;
		}
		const conceptos = conceptos_data.data.listReturned;
		conceptoSelect.root.querySelector("ul").innerHTML = "";
		for(let i = 0; i < conceptos.length; i++) {
			let item = material.mdSelectMenuItemSingleLine(conceptos[i].codigo, conceptos[i].descripcion);
			conceptoSelect.root.querySelector("ul").append(item);
		}
		conceptoSelect.layoutOptions();
	})();
	window.transferir = async () => {
		const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noCuenta = urlParams.get('accountno');

	const tipusDestinoSegmentedButton_root = document.querySelector("#tipusDestinoSegmentedButton");
	const tipusDestinoSegmentedButton = new material.mdc.segmentedButton.MDCSegmentedButton(tipusDestinoSegmentedButton_root);
	const tipusValue = ["CBU", "No"][tipusDestinoSegmentedButton.foundation.getSelectedSegments()[0].index];
	const conceptoSelect = material.loadSelect(document.querySelector("#conceptoSelect")); // Cargar:
	console.log(tipusDestinoSegmentedButton);
	let data = {
		monto: document.transfer.montoTotal.value,
		cuenta: noCuenta,
		cuentaDestino: {
			id: tipusValue,
			value: document.transfer.idCuentaDestino.value
		}
	};
		console.log(33);
		// Validar monto
		let miCuenta = await accounts.getAccount(noCuenta); // TODO: Implementar Redux acá
		if(miCuenta.status != 200) {
			material.showSnackbar("Hubo un error al validar los datos. ");
			return false;
		}
		console.log(34);
		let saldo = miCuenta.data.listReturned[0].saldo;
		let miCBU = miCuenta.data.listReturned[0].CBU;
		console.log({saldo});
		console.log(35);
		if(data.monto > saldo) {
			material.showSnackbar("No tenés fondos suficientes para realizar esta transferencia. ");
			return false;
		}
		// Validar cuenta destino
		console.log(36);
		const key = data.cuentaDestino.id;
		const id = data.cuentaDestino.value;
		console.log({key, id});
		const accountDetails = await accounts.quickAccount(id, key);
		const ek = ["CBU", "número de cuenta"][tipusDestinoSegmentedButton.foundation.getSelectedSegments()[0].index];
		
		console.log(37);
		if(accountDetails.status != 200) {
			material.showSnackbar("El " + ek + " ingresado no corresponde con ninguna cuenta. ");
			return false;
		}
		console.log(accountDetails);
		// Validar select
		if(conceptoSelect.value == "") {
			material.showSnackbar("Seleccioná un concepto. ");
			return false;
		}
		console.log(38);

		console.log({data});

		console.log(39);
		let decision = await material.showDialog(
			"Vas a transferir $ " + formatearComoDinero(data.monto)
			+ " a: \n" + accountDetails.data.tipo.descripcion + "\n"
			+ formatearNumeroCuenta(accountDetails.data.numero) + "\n"
			+ "CBU: " + accountDetails.data.CBU + "\n" 
			+ "Titular: " + accountDetails.data.cliente.apellido + ", " + accountDetails.data.cliente.nombre + "\n\n"
			+ "¿Continuar?"
		);
		if(!decision) {
			material.showSnackbar("Cancelaste la transferencia. ");
			return false;
		}

		console.log(40);
		const transferData = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client/transferencia", {
				method: "POST",
				headers: auth.AUTH_HEADER,
				body: JSON.stringify({
				    "CBUOrigen": miCBU,
				    "CBUDestino" : accountDetails.data.CBU,
				    "montoTransf": data.monto,
				    "tipoConcep": conceptoSelect.value
				})
			});
		const status = transferData.status;
		const edata = await transferData.json();
		const res = {status, edata};
		console.log(res);
		if(status == 200 || status == 201) {
			material.showSnackbar("La transferencia se realizó con éxito!");
			await loadData();
			await loadTable();
			return;
		} else {
			material.showSnackbar("Hubo un problema al realizar la transferencia. ");
			return;
		}


	};
    document.querySelector("#btnTransferir").addEventListener('click', async (e) => {
        console.log(32);
        await transferir();
    });
})();