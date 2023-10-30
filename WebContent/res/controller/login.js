"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

const getFormData = () => {
	const txtUsuario = document.querySelector("#txtUsername");
	const txtClave = document.querySelector("#txtPassword");
	const rol = document.login.role;

	const tU = material.loadTxt(document.querySelector("#tU"));
	console.log(tU);

	return {
		usuario: txtUsuario.value,
		password: txtClave.value,
		role: rol.value,
	};
};

const setProgress = (pc) => {
	let lp = document.querySelector("#progressbar");
	let md = material.loadLinearProgressBar(lp);
	//console.log({ md });
	md.foundation.setDeterminate(false);
	if (pc) {
		md.foundation.open();
	} else md.foundation.close();
};

(async () => {
	//const loginResult = auth.login("Maria_12144165", "Ma#16%822$15*Gri");
	//const testResult = auth.testAccess(false);
	material.loadElements();
	getFormData();
	const form = document.login;
	form.addEventListener("submit", async (e) => {
		e.preventDefault();
		setProgress(true);
		const data = getFormData();
		const loginResult = await auth.login(
			data.usuario,
			data.password,
			data.role == "admin"
		);
		switch (loginResult.status) {
			case 200:
				window.showSnackbar("Se inició sesión con éxito!");
				break;
			case 400:
				window.showSnackbar("Los datos ingresados son inválidos. ");
				break;
			case 401:
				window.showSnackbar("Los datos ingresados son incorrectos. ");
				break;
			case 500:
				window.showSnackbar(
					"Hubo un error al intentar iniciar sesión. "
				);
				break;
		}
		setProgress(false);
	});
})();
