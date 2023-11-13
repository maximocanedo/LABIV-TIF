import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as countries from "./../data/countries.js";
import * as clients from "./../data/clients.js";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

let state = {
	dniOK: false,
	cuilOK: false,
	userOK: false,
	clave1OK: false,
	clave2OK: false
};

let design = {
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
		document.querySelectorAll(".__signup-card--tab").forEach((tab) => {
			tab.classList.remove("_visible");
		});
		document
			.querySelector(".__signup-card--tab#" + id)
			.classList.add("_visible");
	},
	setLoadingStatus: (id) => {
		document
			.querySelector(".__signup-card--tab#" + id)
			.classList.add("_loading");
	},
	removeLoadingStatus: (id) => {
		document
			.querySelector(".__signup-card--tab#" + id)
			.classList.remove("_loading");
	},
};

let events = {
	btnNombreApellidoOK__click: (e) => {
		let nombre = material.doc.mdtxtNombre.foundation;
		let apellido = material.doc.mdtxtApellido.foundation;
		if(nombre.isValid() && apellido.isValid() && nombre.isNativeInputValid() && apellido.isNativeInputValid())
			design.switchTab("tab-documentos");
		else material.showSnackbar("Ingrese valores válidos. ");
	},
	btnDocumentosOK__click: (e) => {
		let dni = material.doc.mdtxtDNI.foundation;
		let cuil = material.doc.mdtxtCUIL.foundation;
		if(cuil.isValid() && cuil.isNativeInputValid() && dni.isValid() && dni.isNativeInputValid() && state.dniOK && state.cuilOK)	design.switchTab("tab-sexo");
		else material.showSnackbar("Ingresá un DNI y CUIL válidos. ");
	},
	btnSexOK__click: (e) => {
		if(document.signup.sex.value == "M" || document.signup.sex.value == "F")
			design.switchTab("tab-fecha-nacimiento");
		else material.showSnackbar("Seleccioná tu sexo. ");
	},
	btnBirthdayOK__click: async (e) => {
		const i = material.doc.mdtxtFechaNacimiento.foundation.isValid();
		if(!i) {
			showSnackbar("Ingresá una fecha válida. ");
			return;
		}
		design.switchTab("tab-direccion");
		const control = material.loadSelect(
			document.querySelector("#mdSelectProvincia")
		);
		await events.mdSelectProvincia__load(control);

	},
	btnAddressOK__click:  async (e) => {
		const pro = material.doc.mdSelectProvincia.foundation.adapter.getSelectedIndex() != -1;
		const loc = material.doc.mdSelectLocalidad.foundation.adapter.getSelectedIndex() != -1;
		const dir = material.doc.mdtxtDireccion.foundation.isValid();
		if(!dir) {
			material.showSnackbar("Ingresá una dirección. ");
			return;
		}
		if(!pro) {
			material.showSnackbar("Seleccioná una provincia. ");
			return;
		}
		if(!loc) {
			material.showSnackbar("Seleccioná una localidad. ");
			return;
		}
		design.switchTab("tab-nationality");
		const control = material.loadSelect(
			document.querySelector("#mdSelectNacionalidad")
		);
		events.mdSelectNacionalidad__load(control);
	},
	btnNacionalityOK__click: (e) => {
		const pro = material.doc.mdSelectNacionalidad.foundation.adapter.getSelectedIndex() != -1;
		if(pro) design.switchTab("tab-mail");
		else {
			material.showSnackbar("Elegí un país. ");
			return;
		}
	},
	btnMailOK__click: async (e) => {
		const v = material.doc.mdtxtMail.foundation.isValid();
		if(v) {
			design.switchTab("tab-processing");
			await saveAll();
		} else {
			material.showSnackbar("Ingresá un correo válido. ");
			return;
		}
	},
	btnNombreApellidoBack__click: (e) => {
		window.location = auth.LOGIN_PATH;
	},
	btnDocumentosBack__click: (e) => {
		design.switchTab("tab-nombres");
	},
	btnSexBack__click: (e) => {
		design.switchTab("tab-documentos");
	},
	btnBirthdayBack__click: (e) => {
		design.switchTab("tab-sexo");
	},
	btnAddressBack__click: (e) => {
		design.switchTab("tab-fecha-nacimiento");
	},
	btnNacionalityBack__click: (e) => {
		design.switchTab("tab-direccion");
	},
	btnMailBack__click: (e) => {
		design.switchTab("tab-nationality");
	},
	btnLogin__click: (e) => {
		console.log(auth.LOGIN_PATH);
		window.location = auth.LOGIN_PATH;
	},
	mdSelectProvincia__load: async (e) => {
		design.setLoadingStatus("tab-direccion");
		design.setProgress(true);
		const data = await provinces.getProvinces();
		data.map((province) => {
			const listItem = material.mdSelectMenuItemSingleLine(
				province.id,
				province.nombre
			);
			e.menuElement.querySelector("ul").append(listItem);
		});
		e.layoutOptions();
		design.removeLoadingStatus("tab-direccion");
		design.setProgress(false);
	},
	mdSelectLocalidad__load: async (e, p) => {
		e.menuElement.querySelector("ul").innerHTML = "";
		if (p != "") {
			design.setLoadingStatus("tab-direccion");
			design.setProgress(true);
			const data = await localties.getLocalties(p);
			e.disabled = false;
			data.map((localty) => {
				const listItem = material.mdSelectMenuItemSingleLine(
					localty.id,
					localty.nombre
				);
				e.menuElement.querySelector("ul").append(listItem);
			});
			e.layoutOptions();
			design.removeLoadingStatus("tab-direccion");
			design.setProgress(false);
		} else {
			e.setSelectedIndex(-1);
			e.disabled = true;
			e.layoutOptions();
			e.layout();
		}
	},
	mdSelectNacionalidad__load: async (e) => {
		design.switchTab("tab-nationality");
		material.showSnackbar("Obteniendo la lista de países... ");
		design.setProgress(true);
		design.setLoadingStatus("tab-nationality");
		e.disabled = true;
		const data = await countries.getCountries();
		data.map((country) => {
			const listItem = material.mdSelectMenuItemSingleLine(
				country.codigo,
				country.nombre
			);
			e.menuElement.querySelector("ul").append(listItem);
			e.layoutOptions();
		});
		e.disabled = false;
		design.setProgress(false);
		design.removeLoadingStatus("tab-nationality");
	},
	mdtxtDNI__change: async (e, control, btn) => {
		const value = e.srcElement.value;
		console.log(control);
		const status = await clients.DNIExists(value);
		if (status == 200) {
			control.foundation.setValid(false);
			control.foundation.setHelperTextContent(
				"Este DNI ya existe. Probá con otro. "
			);
			material.showSnackbar(
				"El DNI ingresado ya existe. Probá con otro. "
			);
			state.dniOK = false;
			//control.input.setCustomValidity("Este DNI ya está registrado. ");
		} else {
			state.dniOK = true;
			control.foundation.setValid(true);
			control.foundation.setHelperTextContent("");
			state.dniOK = true;
		}
	},
	mdtxtCUIL__change: async (e, control, btn) => {
		const value = e.srcElement.value;
		console.log(control);
		const status = await clients.CUILExists(value);
		console.log({ cuil: status });
		if (status == 200) {
			control.foundation.setValid(false);
			control.foundation.setHelperTextContent(
				"Este CUIL ya existe. Probá con otro. "
			);
			state.cuilOK = false;
			//control.input.setCustomValidity("Este CUIL ya está registrado. ");
			material.showSnackbar(
				"El CUIL ingresado ya existe. Probá con otro. "
			);
		} else {
			state.cuilOK = true;
			control.foundation.setValid(true);
			control.foundation.setHelperTextContent("");
			control.foundation.setValid(true);
			state.cuilOK = true;
		}
	},
};
let controls = {
	/* Buttons */
	btnNombreApellidoOK: (() => {
		const btn = document.querySelector("#btnNombreApellidoOK");
		btn.addEventListener("click", events.btnNombreApellidoOK__click);
		return btn;
	})(),
	btnDocumentosOK: (() => {
		const btn = document.querySelector("#btnDocumentosOK");
		btn.addEventListener("click", events.btnDocumentosOK__click);
		return btn;
	})(),
	btnSexOK: (() => {
		const btn = document.querySelector("#btnSexOK");
		btn.addEventListener("click", events.btnSexOK__click);
		return btn;
	})(),
	btnBirthdayOK: (() => {
		const btn = document.querySelector("#btnBirthdayOK");
		btn.addEventListener("click", events.btnBirthdayOK__click);
		return btn;
	})(),
	btnAddressOK: (() => {
		const btn = document.querySelector("#btnAddressOK");
		btn.addEventListener("click", events.btnAddressOK__click);
		return btn;
	})(),
	btnNacionalityOK: (() => {
		const btn = document.querySelector("#btnNacionalityOK");
		btn.addEventListener("click", events.btnNacionalityOK__click);
		return btn;
	})(),
	btnMailOK: (() => {
		const btn = document.querySelector("#btnMailOK");
		btn.addEventListener("click", events.btnMailOK__click);
		return btn;
	})(),
	btnNombreApellidoBack: (() => {
		const btn = document.querySelector("#btnNombreApellidoBack");
		btn.addEventListener("click", events.btnNombreApellidoBack__click);
		return btn;
	})(),
	btnDocumentosBack: (() => {
		const btn = document.querySelector("#btnDocumentosBack");
		btn.addEventListener("click", events.btnDocumentosBack__click);
		return btn;
	})(),
	btnSexBack: (() => {
		const btn = document.querySelector("#btnSexBack");
		btn.addEventListener("click", events.btnSexBack__click);
		return btn;
	})(),
	btnBirthdayBack: (() => {
		const btn = document.querySelector("#btnBirthdayBack");
		btn.addEventListener("click", events.btnBirthdayBack__click);
		return btn;
	})(),
	btnAddressBack: (() => {
		const btn = document.querySelector("#btnAddressBack");
		btn.addEventListener("click", events.btnAddressBack__click);
		return btn;
	})(),
	btnNacionalityBack: (() => {
		const btn = document.querySelector("#btnNacionalityBack");
		btn.addEventListener("click", events.btnNacionalityBack__click);
		return btn;
	})(),
	btnMailBack: (() => {
		const btn = document.querySelector("#btnMailBack");
		btn.addEventListener("click", events.btnMailBack__click);
		return btn;
	})(),
	/* Material TextFields */
	mdtxtNombre: (() => {
		const control = material.loadTxt(
			document.querySelector("#mdtxtNombre")
		);
		return control;
	})(),
	mdtxtApellido: (() => {
		const control = material.loadTxt(
			document.querySelector("#mdtxtApellido")
		);
		return control;
	})(),
	mdtxtDNI: (() => {
		const control = material.loadTxt(document.querySelector("#mdtxtDNI"));
		control.input.addEventListener("change", async (e) => {
			await events.mdtxtDNI__change(e, control, controls.btnDocumentosOK);
		});
		return control;
	})(),
	mdtxtCUIL: (() => {
		const control = material.loadTxt(document.querySelector("#mdtxtCUIL"));
		control.input.addEventListener("change", async (e) => {
			await events.mdtxtCUIL__change(
				e,
				control,
				controls.btnDocumentosOK
			);
		});
		return control;
	})(),
	mdtxtFechaNacimiento: (() => {
		const control = material.loadTxt(
			document.querySelector("#mdtxtFechaNacimiento")
		);
		return control;
	})(),
	mdtxtMail: (() => {
		const control = material.loadTxt(document.querySelector("#mdtxtMail"));
		return control;
	})(),
	mdtxtDireccion: (() => {
		const control = material.loadTxt(
			document.querySelector("#mdtxtDireccion")
		);
		return control;
	})(),
	mdSelectProvincia: (() => {
		const control = material.loadSelect(
			document.querySelector("#mdSelectProvincia")
		);
		//events.mdSelectProvincia__load(control);
		control.listen("MDCSelect:change", (e) => {
			events.mdSelectLocalidad__load(
				material.loadSelect(
					document.querySelector("#mdSelectLocalidad")
				),
				e.detail.value
			);
		});
		return control;
	})(),
	mdSelectLocalidad: (() => {
		const control = material.loadSelect(
			document.querySelector("#mdSelectLocalidad")
		);
		control.disabled = true;
		return control;
	})(),
	mdSelectNacionalidad: (() => {
		const control = material.loadSelect(
			document.querySelector("#mdSelectNacionalidad")
		);
		return control;
	})(),
	mdffSexo: (() => {
		console.log(material);
		const control = new material.mdc.radio.MDCRadio(
			document.querySelector("#mdffSexo")
		);
		return control;
	})(),
	mdtxtUser: (() => {
		const control = material.loadTxt(document.querySelector("#mdtxtUser"));
		return control;
	})(),
	mdtxtClave: (() => {
		const control = material.loadTxt(document.querySelector("#mdtxtClave"));
		return control;
	})(),
	mdbtnLogin: (() => {
		const control = document.querySelector("#btnLogin");
		control.addEventListener("click", (e) => {
			window.location = auth.LOGIN_PATH;
		});
	})(),
	errorDetails: document.querySelector("#errorDetails"),
};
(async () => {
	material.loadElements();
})();

const saveAll = async () => {
	design.setProgress(true);
	design.setLoadingStatus("tab-processing");
	const data = {
		nombre: controls.mdtxtNombre.value,
		apellido: controls.mdtxtApellido.value,
		fechaNacimiento: controls.mdtxtFechaNacimiento.value,
		correo: controls.mdtxtMail.value,
		direccion: controls.mdtxtDireccion.value,
		localidad: parseInt(controls.mdSelectLocalidad.hiddenInput.value),
		provincia: parseInt(controls.mdSelectProvincia.hiddenInput.value),
		nacionalidad: controls.mdSelectNacionalidad.hiddenInput.value,
		cuil: controls.mdtxtCUIL.value,
		sexo: document.signup.sex.value,
		dni: controls.mdtxtDNI.value,
	};
	console.log(data);
	console.log(controls);
	//return;
	const result = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client",
		{
			method: "POST",
			body: JSON.stringify(data),
		}
	);
	const status = result.status;
	const res = await result.json();
	design.setProgress(false);
	if (result.status == 201) {
		design.switchTab("tab-done");
		console.log({
			user: res.objectReturned.usuario,
			key: res.objectReturned.contraseña,
		});
		controls.mdtxtUser.foundation.setValue(res.objectReturned.usuario);
		controls.mdtxtClave.foundation.setValue(res.objectReturned.contraseña);
	} else {
		design.switchTab("tab-error");
		errorDetails.innerText =
			"Hubo un error al intentar crear tu cuenta. \n\nDetalles: " +
			res.message;
	}
};
