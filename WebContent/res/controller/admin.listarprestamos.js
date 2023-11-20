'use strict';
import * as auth from "./../data/auth.js";
import * as loans from "./../data/loans.js";
import * as material from "./mdc.controller.js";


const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', {style: 'currency', currency: 'ARS'});
    return numeroFormateado;
}

const paginationReducer = (state = {page: 1, size: 10}, action) => {
    switch (action.type) {
        case 'SET_PAGE':
            return {...state, page: action.payload};
        case 'NEXT_PAGE':
            return {...state, page: state.page + 1};
        case 'PREV_PAGE':
            return {...state, page: state.page > 1 ? state.page - 1 : 1}
        case 'SET_SIZE':
            return {...state, size: action.payload};
        default:
            return state;
    }
};

// Crear el store de Redux
const store = Redux.createStore(paginationReducer);


const prestamosList = document.querySelector("#prestamosList");

const loadRequests = async () => {
    const {status, list} = await loans.getMyLoanRequests(store.getState());
    if (status != 200 && status != 201) {
        material.showSnackbar("Hubo un error al intentar obtener los préstamos. ");
        return;
    }
    prestamosList.innerHTML = "";
    list.map(loan => {
        console.log(loan);
        const li = document.createElement("li");
        li.innerHTML = `#${loan.codigo} (${loan.cliente.apellido}, ${loan.cliente.nombre}). `
            + `<b>${formatearComoDinero(loan.montoPedido)} a ${loan.cantCuotas} cuotas. </b>`
            + `<button id="aprobarPrestamo${loan.codigo}">Aprobar</button>`
            + `<button id="rechazarPrestamo${loan.codigo}">Rechazar</button>`;


        if (loan.estado == 0) {
            prestamosList.append(li);
            document.querySelector(`#aprobarPrestamo${loan.codigo}`).addEventListener('click', async (e) => {
                if (confirm("¿Aprobar préstamo #" + loan.codigo + "?")) {
                    const op = await loans.approve(loan.codigo);
                    if (op.status == 200 || op.status == 201) {
                        material.showSnackbar("Se aprobó el préstamo #" + loan.codigo + " con éxito. ");
                        return;
                    }
                    material.showSnackbar("No se aprobó el préstamo debido a un error. ");
                    return;
                }
            });
            document.querySelector(`#rechazarPrestamo${loan.codigo}`).addEventListener('click', async (e) => {
                if (confirm("Rechazar préstamo #" + loan.codigo + "?")) {
                    const op = await loans.reject(loan.codigo);
                    if (op.status == 200 || op.status == 201) {
                        material.showSnackbar("Se rechazó el préstamo #" + loan.codigo + " con éxito. ");
                        return;
                    }
                    material.showSnackbar("No se rechazó el préstamo debido a un error. ");
                    return;
                }
            });
        }
    });
};


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
    const {page, size} = store.getState();
    await loadRequests();
    document.querySelector("#textoPaginacion").innerText = `Página ${page}`;
    console.log({page, size});
});


let paginator = {
    page: 1,
    size: 10
};


(async () => {
    const admin = await auth.allowAdmin({
        message: "Iniciá sesión como administrador para revisar y aprobar préstamos. "
    });
    store.dispatch(setSize(parseInt(document.querySelector("#size").value)));
    document.querySelector("#size").addEventListener('change', () => {
        store.dispatch(setSize(parseInt(document.querySelector("#size").value)));
    });
    document.querySelector("#prevBtn").addEventListener('click', () => {
        store.dispatch(prevPage());
    });
    document.querySelector("#nextBtn").addEventListener('click', () => {
        store.dispatch(nextPage());
    });
})();