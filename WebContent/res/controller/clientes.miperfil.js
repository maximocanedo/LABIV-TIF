"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as countries from "./../data/countries.js";
import * as auth from "./../data/auth.js";

(async () => {
	material.loadElements();
	// Cargar controles:
	const provincias = await provinces.getProvinces();
	console.log({ provincias });
	provincias.forEach((opcion) => {
		const option = document.createElement("option");
		option.text = opcion.nombre;
		option.value = opcion.id;
		document.me.provincias.appendChild(option);
	});
	const paises = await countries.getCountries();
	console.log({ paises });
	paises.forEach((opcion) => {
		const option = document.createElement("option");
		option.text = opcion.nombre;
		option.value = opcion.codigo;
		document.me.pais.appendChild(option);
	});
	const loadLocalties = async (event) => {
		const provinceId = document.me.provincias.value;
		const localidades = await localties.getLocalties(provinceId);
		document.me.localidades.innerHTML = "";
		const defOp = document.createElement("option");
		defOp.text = "Seleccioná una localidad";
		defOp.value = -1;
		document.me.localidades.appendChild(defOp);
		localidades.forEach((opcion) => {
			const option = document.createElement("option");
			option.text = opcion.nombre;
			option.value = opcion.id;
			document.me.localidades.appendChild(option);
		});
	};
	document.me.provincias.addEventListener("change", async (e) => {
		await loadLocalties(e);
	});

	const client = await auth.allowClient({
		message: "Iniciá sesión para acceder a tu cuenta. ",
	});
	console.log(client);
	document.me.nombre.value = client.nombre;
	document.me.apellido.value = client.apellido;
	document.me.sexo.value = client.sexo;
	document.me.provincias.value = client.provincia.id;
	await loadLocalties(null);
	document.me.localidades.value = client.localidad.id;
	document.me.pais.value = client.nacionalidad.codigo;
	document.me.fechaNacimiento.value = client.fechaNacimiento;
	document.me.direccion.value = client.direccion;
	document.me.dni.value = client.DNI;
	document.me.cuil.value = client.CUIL;
	document.me.usuario.value = client.usuario;

	document.me.btnEnviar.addEventListener("click", async () => {
		const dialog = await material.showDialog(
			"Se actualizarán tus datos personales. "
		);
		if (!dialog) return;
		const data = {
			nombre: document.me.nombre.value,
			apellido: document.me.apellido.value,
			sexo: document.me.sexo.value,
			provincia: parseInt(document.me.provincias.value),
			localidad: parseInt(document.me.localidades.value),
			nacionalidad: document.me.pais.value,
			fechaNacimiento: document.me.fechaNacimiento.value,
			direccion: document.me.direccion.value,
		};
		const result = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client",
			{
				method: "PUT",
				headers: auth.AUTH_HEADER,
				body: JSON.stringify(data),
			}
		);
		const status = result.status;
		const json = await result.json();
		if (status == 200) {
			material.showSnackbar(
				"Los datos fueron modificados exitosamente. "
			);
		} else {
			material.showSnackbar(
				"Error al intentar modificar: " + json.message
			);
		}
	});

	document.me.btnDisable.addEventListener("click", async () => {
		const dialog = await material.showDialog(
			"¿Seguro de eliminar tu cuenta?"
		);
		if (!dialog) return;
		const res = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client",
			{
				method: "DELETE",
				headers: auth.AUTH_HEADER,
			}
		);
		const status = res.status;
		const json = await res.json();
		if (status == 200) {
			material.showSnackbar("Eliminaste tu cuenta exitosamente. ");
			localStorage.clear();
		} else {
			material.showSnackbar(
				"No se pudo eliminar tu cuenta. Detalles: " + json.message
			);
		}
	});
})();
