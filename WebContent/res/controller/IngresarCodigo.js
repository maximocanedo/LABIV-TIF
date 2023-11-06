"use strict";
import * as material from "./mdc.controller.js";

const controls = {
	code: (() => {
		return material.loadTxt(document.querySelector("#tU"));
	})(),
	btnEnviar: (() => {})(),
};

const getFormData = () => {
	const code = document.querySelector("#code");
	const tU = material.loadTxt(document.querySelector("#tU"));

	return {
		code: code.value,
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

const BtnEnviar = document.querySelector("#btnEntrar");
BtnEnviar.addEventListener("click", async (e) => {
	const result= await fetch(
		"http://localhost:8080/api/client/validateMail?code=" +
		+code.value+
		"btnEntrar=",
		{
			method: "GET",
		}
		
	);
    if(result.status==200){
        window.location = "http://localhost:8080/TPINT_GRUPO_3_LAB/clientes/Index.jsp";

    }
});
