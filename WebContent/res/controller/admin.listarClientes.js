"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as auth from "./../data/auth.js";

const getData = async () => {
	return fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/api/clients", {
		headers: auth.AUTH_HEADER,
		method: "GET",
	})
		.then((raw) => {
			if (raw.status != 200) {
				material.showSnackbar(
					"Hubo un problema al intentar cargar los datos. "
				);
			}
			return raw.json();
		})
		.then((json) => {
			return json;
		})
		.catch((err) => {
			console.error(err);
			material.showSnackbar(err);
		});
};

(async () => {
	material.loadElements();
	const actualUser = await auth.allowAdmin();
	const data = await getData();
	// Cargar controles:
	const provincias = await provinces.getProvinces();
	console.log({ provincias });
	provincias.forEach((opcion) => {
		const option = document.createElement("option");
		option.text = opcion.nombre;
		option.value = opcion.id;
		document.searchForm.provincias.appendChild(option);
	});
	document.searchForm.provincias.addEventListener("change", async (event) => {
		const provinceId = event.srcElement.value;
		const localidades = await localties.getLocalties(provinceId);
		document.searchForm.localidades.innerHTML = "";
		const defOp = document.createElement("option");
		defOp.text = "Seleccioná una localidad";
		defOp.value = -1;
		document.searchForm.localidades.appendChild(defOp);
		localidades.forEach((opcion) => {
			const option = document.createElement("option");
			option.text = opcion.nombre;
			option.value = opcion.id;
			document.searchForm.localidades.appendChild(option);
		});
	});

	// Diseñar luego:
	data.listReturned.forEach((element) => {
		const item = document.createElement("li");
		const a = document.createElement("a");
		a.setAttribute("href", "/clientes/perfil.html?user=" + element.usuario);
		a.innerText =
			element.nombre + " " + element.apellido + " (" + element.DNI + ") ";
		item.append(a);
		document.querySelector("ul#listaClientes").append(item);
	});
})();
