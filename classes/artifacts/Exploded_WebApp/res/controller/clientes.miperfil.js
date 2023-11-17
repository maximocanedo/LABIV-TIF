"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as countries from "./../data/countries.js";
import * as auth from "./../data/auth.js";

const logic = {
	formatearDNI: (dni) => {
		if (dni.length === 9) {
			return (
				dni.slice(0, 3) + "." + dni.slice(3, 6) + "." + dni.slice(6, 9)
			);
		} else if (dni.length === 8) {
			return (
				dni.slice(0, 2) + "." + dni.slice(2, 5) + "." + dni.slice(5, 8)
			);
		} else {
			return dni;
		}
	},
	formatearCUIL: (cuil) => {
		const prefijo = cuil.substring(0, 2);
		const sufijo = cuil.substring(cuil.length - 1);
		const dni = cuil.substring(2, cuil.length - 1);
		return `${prefijo}-${logic.formatearDNI(dni)}-${sufijo}`;
	},
};
const data = {
	provinces: [],
	localties: [],
	countries: [],
	client: {},
};
let handlerProvincias = (mdSelect, val) => {
	mdSelect.setValue(val);
};
const events = {
	provincias__onUpdate: async (newList, provObj) => {
		material.doc.mdSelectProvincia.menuElement.querySelector(
			"ul"
		).innerHTML = "";
		newList.forEach((opcion) => {
			const item = material.mdSelectMenuItemSingleLine(
				opcion.id,
				opcion.nombre
			);
			material.doc.mdSelectProvincia.menuElement
				.querySelector("ul")
				.append(item);
		});
		material.doc.mdSelectProvincia.initialize();
		material.doc.mdSelectProvincia.layoutOptions();
		console.log("PROV ID: " + provObj.id);
		material.doc.mdSelectProvincia.setValue(provObj.id);
		document.me.provincia.value = provObj.id;
	},
	localties__onUpdate: async (newList, provinceId) => {
		const mdSelectLocalidad = material.loadSelect(
			document.querySelector("#mdSelectLocalidad")
		);
		mdSelectLocalidad.foundation.setSelectedIndex(-1);
		mdSelectLocalidad.foundation.blur();
		mdSelectLocalidad.menuElement.querySelector("ul").innerHTML = "";
		newList.forEach((opcion) => {
			const item = material.mdSelectMenuItemSingleLine(
				opcion.id,
				opcion.nombre
			);
			mdSelectLocalidad.menuElement.querySelector("ul").append(item);
		});
		mdSelectLocalidad.initialize();
		mdSelectLocalidad.layoutOptions();
	},
	countries__onUpdate: async (newList, defVal) => {
		const mdSelectNacionalidad = material.loadSelect(
			document.querySelector("#mdSelectNacionalidad")
		);
		mdSelectNacionalidad.menuElement.querySelector("ul").innerHTML = "";
		newList.forEach((opcion) => {
			const item = material.mdSelectMenuItemSingleLine(
				opcion.codigo,
				opcion.nombre
			);
			mdSelectNacionalidad.menuElement.querySelector("ul").append(item);
		});
		mdSelectNacionalidad.initialize();
		mdSelectNacionalidad.layoutOptions();
		mdSelectNacionalidad.hiddenInput.value = defVal.codigo;
	},
	client__onLoad: async (client) => {
		console.log(client);
		document.me.usuario.value = client.usuario;
		material.doc.mdtxtNombre.foundation.setValue(client.nombre);
		material.doc.mdtxtApellido.foundation.setValue(client.apellido);
		document.me.sexo.value = client.sexo;
		material.doc.mdSelectProvincia.hiddenInput.value = client.provincia.id;
		material.doc.mdSelectLocalidad.hiddenInput.value = client.localidad.id;
		material.doc.mdSelectNacionalidad.hiddenInput.value =
			client.nacionalidad.codigo;
		material.doc.mdtxtFechaNacimiento.foundation.setValue(
			client.fechaNacimiento
		);
		material.doc.mdtxtDireccion.foundation.setValue(client.direccion);

		document
			.querySelectorAll(".cnt-nombreCompleto")
			.forEach(
				(el) => (el.innerText = client.nombre + " " + client.apellido)
			);
		document
			.querySelectorAll(".cnt-sexo")
			.forEach(
				(el) =>
					(el.innerText =
						client.sexo == "M"
							? "Masculino"
							: client.sexo == "F"
							? "Femenino"
							: "Otro")
			);
		document
			.querySelectorAll(".cnt-fechaNacimiento")
			.forEach((el) => (el.innerText = client.fechaNacimiento));
		document
			.querySelectorAll(".cnt-dni")
			.forEach((el) => (el.innerText = logic.formatearDNI(client.DNI)));
		document
			.querySelectorAll(".cnt-cuil")
			.forEach((el) => (el.innerText = logic.formatearCUIL(client.CUIL)));
		document
			.querySelectorAll(".cnt-usuario")
			.forEach((el) => (el.innerText = client.usuario));
		document
			.querySelectorAll(".cnt-direccion")
			.forEach((el) => (el.innerText = client.direccion));
		document
			.querySelectorAll(".cnt-nacionalidad-nombre")
			.forEach((el) => (el.innerText = client.nacionalidad.nombre));
		document
			.querySelectorAll(".cnt-pl")
			.forEach(
				(el) =>
					(el.innerText = `${client.localidad.nombre}, ${client.provincia.nombre}`)
			);

		await load.provincias(client.provincia);
		await load.countries(client.nacionalidad);
		material.doc.mdSelectProvincia.listen("MDCSelect:change", async () => {
			await load.localidades(material.doc.mdSelectProvincia.value);
		});
	},
};
const load = {
	provincias: async (provObj) => {
		const list = await provinces.getProvinces();
		if (list != null) {
			data.provinces = list;
			await events.provincias__onUpdate(list, provObj);
		} else {
			material.showSnackbar(
				"Hubo un error al intentar obtener la lista de provincias. "
			);
			return [];
		}
	},
	localidades: async (provinceId) => {
		const list = await localties.getLocalties(provinceId);
		if (list != null) {
			data.localties = list;
			await events.localties__onUpdate(list, provinceId);
		} else {
			material.showSnackbar(
				"Hubo un error al intentar obtener la lista de localidades. "
			);
			return [];
		}
	},
	countries: async (c) => {
		const list = await countries.getCountries();
		if (list != null) {
			data.countries = list;
			await events.countries__onUpdate(list, c);
		} else {
			material.showSnackbar(
				"Hubo un error al intentar obtener la lista de países. "
			);
			return [];
		}
	},
	actualUser: async () => {
		const client = await auth.allowClient({
			message: "Iniciá sesión para acceder a tu cuenta. ",
		});
		if (client != null) {
			data.client = client;
			events.client__onLoad(client);
		} else {
			material.showSnackbar("No se pudieron cargar tus datos. ");
		}
	},
};

(async () => {
	material.loadElements();
	console.log(material.doc);
	await load.actualUser();
	document.querySelector("#btnActualizar").addEventListener("click", () => {
		const dialog = material.showOtherDialog("#formDialog");
	});
	document.me.btnEnviar.addEventListener("click", async () => {
		//const dialog = await material.showDialog(
		//	"Se actualizarán tus datos personales. "
		//);
		//if (!dialog) return;
		const data = {
			nombre: document.me.nombre.value,
			apellido: document.me.apellido.value,
			sexo: document.me.sexo.value,
			provincia: parseInt(
				material.doc.mdSelectProvincia.hiddenInput.value
			),
			localidad: parseInt(
				material.doc.mdSelectLocalidad.hiddenInput.value
			),
			nacionalidad: material.doc.mdSelectNacionalidad.hiddenInput.value,
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

	document
		.querySelector("#btnDeshabilitar")
		.addEventListener("click", async () => {
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
