"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

const controls = {
	txtUsuario: (() => {
		return material.loadTxt(document.querySelector("#tU"));
	})(),
	txtClave: (() => {
		return material.loadTxt(document.querySelector("#tP"));
	})(),
	btnEnviar: (() => {})(),
};

const getFormData = () => {
	const txtUsuario = document.querySelector("#txtUsername");
	const txtClave = document.querySelector("#txtPassword");
	const rol = document.login.role;

	const tU = material.loadTxt(document.querySelector("#tU"));
	// console.log(tU);

	return {
		usuario: txtUsuario.value,
		password: txtClave.value,
		role: rol.value,
	};
};

const design = {
	setProgress: (pc) => {
		let lp = document.querySelector("#progressbar");
		let md = material.loadLinearProgressBar(lp);
		//// console.log({ md });
		md.foundation.setDeterminate(false);
		if (pc) {
			md.foundation.open();
		} else md.foundation.close();
	},
	switchTab: (id) => {
		document.querySelectorAll(".__login-card--tab").forEach((tab) => {
			tab.classList.remove("_visible");
		});
		document
			.querySelector(".__login-card--tab#" + id)
			.classList.add("_visible");
	},
	setLoadingStatus: (id) => {
		document
			.querySelector(".__login-card--tab#" + id)
			.classList.add("_loading");
	},
	removeLoadingStatus: (id) => {
		document
			.querySelector(".__login-card--tab#" + id)
			.classList.remove("_loading");
	},
};

const HOME_PAGE = "./inicio.html";

const getURLNextValue = () => {
	var url = window.location.href;
	var urlObj = new URL(url);
	var parametroValor = urlObj.searchParams.get("next");
	return parametroValor == null ? HOME_PAGE : parametroValor;
};

const loginSuccessfulShowData = async (isAdmin = false) => {
	const userData = await auth.getActualUser(isAdmin);
	const user = userData.data;
	// console.log(userData);
	document.querySelector(
		"#successfulLoginSpanText"
	).innerText = `¡Hola, ${user.nombre}!`;
	setTimeout(() => {
		window.location = getURLNextValue();
	}, 1000);
};

(async () => {
	//const loginResult = auth.login("Maria_12144165", "Ma#16%822$15*Gri");
	//const testResult = auth.testAccess(false);
	material.loadElements();
	getFormData();
	const form = document.login;
	form.addEventListener("submit", async (e) => {
		e.preventDefault();
		design.setProgress(true);
		design.setLoadingStatus("tab-login");
		const data = getFormData();
		const loginResult = await auth.login(
			data.usuario,
			data.password,
			data.role == "admin"
		);
		design.setProgress(false);
		switch (loginResult.status) {
			case 200:
				await loginSuccessfulShowData(data.role == "admin");
				design.switchTab("tab-ok");
				design.setProgress(true);
				design.setLoadingStatus("tab-ok");
				break;
			case 400:
				design.removeLoadingStatus("tab-login");
				window.showSnackbar("Los datos ingresados son inválidos. ");
				break;
			case 401:
				design.removeLoadingStatus("tab-login");
				window.showSnackbar("Los datos ingresados son incorrectos. ");
				break;
			case 500:
				design.removeLoadingStatus("tab-login");
				window.showSnackbar(
					"Hubo un error al intentar iniciar sesión. "
				);
				break;
		}
		//setProgress(false);
	});
})();
