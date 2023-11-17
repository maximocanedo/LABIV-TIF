"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as countries from "./../data/countries.js";
import * as auth from "./../data/auth.js";

const getData = async (data = null) => {
	let ue = "?";
	if (data != null) {
		// Filtrar propiedades con valores no nulos
		const filteredData = Object.fromEntries(
			Object.entries(data).filter(([key, value]) => value !== null)
		);

		// Convertir a cadena de consulta
		ue += new URLSearchParams(filteredData).toString();
	}
	return fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/api/clients" + ue, {
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

const searchEvent = async (e) => {
	const q = document.searchForm.q.value;
	const status = !document.searchForm.showInactive.checked;
	const province =
		document.searchForm.provincias.value == "-1"
			? null
			: document.searchForm.provincias.value;
	const localty =
		document.searchForm.localidades.value == "-1"
			? null
			: document.searchForm.localidades.value;
	const sex =
		document.searchForm.sexo.value == "M" ||
		document.searchForm.sexo.value == "F"
			? document.searchForm.sexo.value
			: null;
	const country =
		document.searchForm.pais.value == "-1"
			? null
			: document.searchForm.pais.value;
	const data = {
		q,
		status,
		province,
		localty,
		sex,
		country,
	};
	const fetchedData = await getData(data);
	fillData(fetchedData);
};

const fillData = (data) => {
	// Diseñar luego:
	document.querySelector("ul#listaClientes").innerHTML = "";
	data.listReturned.forEach((element) => {
		const item = document.createElement("li");
		const a = document.createElement("a");
		a.setAttribute("href", "/clientes/perfil.html?user=" + element.usuario);
		a.innerText =
			element.nombre + " " + element.apellido + " (" + element.DNI + ") ";
		item.append(a);
		document.querySelector("ul#listaClientes").append(item);
	});
};

(async () => {
	material.loadElements();
	const actualUser = await auth.allowAdmin();
	const data = await getData();
	document.searchForm.searchBtn.addEventListener("click", async () => {
		await searchEvent();
	});
	// Cargar controles:
	const provincias = await provinces.getProvinces();
	console.log({ provincias });
	provincias.forEach((opcion) => {
		const option = document.createElement("option");
		option.text = opcion.nombre;
		option.value = opcion.id;
		document.searchForm.provincias.appendChild(option);
	});

	const paises = await countries.getCountries();
	console.log({ paises });
	paises.forEach((opcion) => {
		const option = document.createElement("option");
		option.text = opcion.nombre;
		option.value = opcion.codigo;
		document.searchForm.pais.appendChild(option);
	});

	document.searchForm.provincias.addEventListener("change", async (event) => {
		const provinceId = document.searchForm.provincias.value;
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

	fillData(data);
})();
