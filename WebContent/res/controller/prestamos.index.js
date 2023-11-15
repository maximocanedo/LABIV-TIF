'use strict';
import * as auth from "./../data/auth.js";
import * as material from "./mdc.controller.js";
import * as loans from "./../data/loans.js";

const fillLoan = async (loan) => {

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
};

(async () => {
	material.loadElements();
	const cliente = auth.allowClient({
		message: "Iniciá sesión para ver el estado de tus préstamos. "
	});
	const queryString = window.location.search;
	const urlParams = new URLSearchParams(queryString);
	const noLoan = urlParams.get('id');
	if (noLoan == null) {
		material.showDialog("No se especificó un código de préstamo válido. ");
		return;
	}

	await loadData(cliente, loanId);
})();