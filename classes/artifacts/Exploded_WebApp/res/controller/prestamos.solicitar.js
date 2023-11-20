"use strict";
import * as auth from "./../data/auth.js";
import * as accounts from "./../data/accounts.js";
import * as material from "./mdc.controller.js";

const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', {style: 'currency', currency: 'ARS'});
    return numeroFormateado;
}

const initialState = {
    monto: 0,
    cuotas: 4,
    cbu: '',
};

const cambiarMonto = (nuevoMonto) => ({
    type: 'CAMBIAR_MONTO',
    payload: nuevoMonto,
});

const cambiarCuotas = (nuevaCantidadCuotas) => ({
    type: 'CAMBIAR_CUOTAS',
    payload: nuevaCantidadCuotas,
});

const cambiarCBU = (nuevoCBU) => ({
    type: 'CAMBIAR_CBU',
    payload: nuevoCBU,
});

// Definir el reducer
const reducer = (state = initialState, action) => {
    switch (action.type) {
        case 'CAMBIAR_MONTO':
            return {...state, monto: action.payload};
        case 'CAMBIAR_CUOTAS':
            return {...state, cuotas: action.payload};
        case 'CAMBIAR_CBU':
            return {...state, cbu: action.payload};
        default:
            return state;
    }
};

const store = Redux.createStore(reducer);
const calcInteres = () => {
    const monto = store.getState().monto;
    const cuotas = store.getState().cuotas;
    return parseFloat(((5 + (cuotas * 0.1)) / 100).toFixed(4));
};
const calcMontoAPagar = () => {
    const interes = calcInteres();
    const monto = store.getState().monto;
    return (monto * (1 + interes));
};
const calcCuota = () => {
    const montoAPagar = calcMontoAPagar();
    const cuotas = store.getState().cuotas;
    return (montoAPagar / cuotas);
};
store.subscribe(() => {
    console.log('Estado actualizado:', store.getState());


    document.querySelector("#_interes").innerText = ((calcInteres() * 100).toFixed(2)) + "%";
    document.querySelector("#_apagar").innerText = formatearComoDinero(calcMontoAPagar());
    document.querySelector("#_cuota").innerText = formatearComoDinero(calcCuota());

});
/*
// Dispatch de acciones
store.dispatch(cambiarMonto(1000));
store.dispatch(cambiarCuotas(3));
store.dispatch(cambiarCBU('123456789'));*/


const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
(async () => {
    const cliente = auth.allowClient({
        message: "Iniciá sesión para poder pedir un préstamo. "
    });
    material.loadElements();
    const montoPedidoInput = document.solicitud.montoAPagar;
    montoPedidoInput.addEventListener("change", () => {
        store.dispatch(cambiarMonto(parseFloat(montoPedidoInput.value)));
    });
    const slider_root = document.querySelector(".mdc-slider");
    const slider = new material.mdc.slider.MDCSlider(slider_root);
    slider_root.addEventListener("MDCSlider:change", (e) => {
        store.dispatch(cambiarCuotas(parseInt(document.solicitud.cantCuotas.value)));
    });
    const cuentasSelect = material.loadSelect(document.querySelector("#cuentaSelect"));
    console.log(cuentasSelect);
    (async () => {
        const misCuentasData = await accounts.getAccounts();
        if (misCuentasData.status != 200) {
            material.showSnackbar("Hubo un error al intentar obtener tus cuentas. ");
            return;
        }
        const cuentas = misCuentasData.data.listReturned;
        cuentasSelect.root.querySelector("ul").innerHTML = "";
        for (let i = 0; i < cuentas.length; i++) {
            const cuenta = cuentas[i];
            let name = cuenta.tipo.descripcion + " " + formatearNumeroCuenta(cuenta.numero);
            let item = material.mdSelectMenuItemSingleLine(cuenta.CBU, name);
            cuentasSelect.root.querySelector("ul").append(item);
        }
        cuentasSelect.layoutOptions();
        cuentasSelect.root.addEventListener('MDCSelect:change', e => {
            store.dispatch(cambiarCBU(e.detail.value));
        });
    })();

    document.querySelector("#pedirPrestamo").addEventListener("click", async (e) => {
        let data = {
            "montoPedido_Sol": store.getState().monto,
            "cantCuotas_Sol": store.getState().cuotas,
            "CBU_CxC": store.getState().cbu
        };
        let cnfrm = await material.showDialog("Estás a punto de solicitar un préstamo de "
            + formatearComoDinero(store.getState().monto) + " a pagar en " + store.getState().cuotas + " cuotas de " + formatearComoDinero(calcCuota()) + ". \n¿Continuar?");
        if (!cnfrm) {
            material.showSnackbar("Cancelaste la solicitud. ");
            return;
        }
        const f = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo", {
                method: "POST",
                headers: auth.AUTH_HEADER,
                body: JSON.stringify(data)
            });
        const status = f.status;
        const res = await f.json();
        console.log({status, res});
        if (status == 200 || status == 201) {
            material.showSnackbar("¡Se envió con éxito tu solicitud de préstamo! En la página de inicio verás el estado. ");
            return;
        } else {
            material.showSnackbar("Hubo un problema al intentar enviar tu solicitud. Intentá de nuevo más tarde. ");
        }
    });
})();

