"use strict";
import * as material from "./mdc.controller.js";

const controls = {
	email: (() => {
		return material.loadTxt(document.querySelector("#tU"));
	})(),
	btnEnviar: (() => {})(),
};

const getFormData = () => {
	const email = document.querySelector("#email");

	const tU = material.loadTxt(document.querySelector("#tU"));

	return {
		correo: email.value,
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

const INGRESAR_CODIGO = "./IngresarCodigo.jsp";

const getURLNextValue = () => {
	var url = window.location.href;
	var urlObj = new URL(url);
	var parametroValor = urlObj.searchParams.get("next");
	return parametroValor == null ? INGRESAR_CODIGO : parametroValor;
};
const data= {
		correo: controls.email.value
}
const BtnEnviar = document.querySelector("#btnEnviar");
BtnEnviar.addEventListener("click", async (e) => {
	const result= await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client/validateMail",
			{
				method: "POST",
				body: JSON.stringify(data),
			}
			
		);
        if(result.status==200){
            window.location = "http://localhost:8080/TPINT_GRUPO_3_LAB/clientes/IngresarCodigo.jsp";

        }
});



