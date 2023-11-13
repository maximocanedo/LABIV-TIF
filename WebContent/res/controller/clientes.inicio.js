"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";
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

(async () => {
	// Instanciar componentes
	material.loadElements();
	console.log(material);
	// Obtener datos del cliente actual. Si no hay cliente redirige a la página de inicio de sesión.
	const actualUser = await auth.allowClient();
	document.querySelectorAll(".account_card").forEach(e => e.classList.add("non-displayable"));
	const cuentasData = await accounts.getAccounts();
	const cuentas = cuentasData.data.listReturned;
	for(let i = 0; i < cuentas.length; i++) {
		console.log(".__account_" + (i + 1) + "_tipodesc");
		console.log(cuentas[i]);
		document.querySelectorAll(".account_" + (i+1)).forEach(e => e.classList.remove("non-displayable"));
		document.querySelector(".__account_" + (i + 1) + "_tipodesc").innerText = cuentas[i].tipo.descripcion;
		document.querySelector(".__account_" + (i + 1) + "_saldo").innerText = formatearComoDinero(cuentas[i].saldo);
		document.querySelector(".__account_" + (i + 1) + "_nc").innerText = formatearNumeroCuenta(cuentas[i].numero);
		document.querySelector(".__account_" + (i + 1) + "_cbu").innerText = cuentas[i].CBU;
	}
	console.log(cuentas);
})();
