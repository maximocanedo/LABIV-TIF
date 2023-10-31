import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as countries from "./../data/countries.js";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

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
		design.switchTab("tab-documentos");
	},
	btnDocumentosOK__click: (e) => {
		design.switchTab("tab-sexo");
	},
	btnSexOK__click: (e) => {
		design.switchTab("tab-fecha-nacimiento");
	},
	btnBirthdayOK__click: (e) => {
		design.switchTab("tab-direccion");
	},
	btnAddressOK__click: (e) => {
		design.switchTab("tab-mail");
	},
	btnMailOK__click: (e) => {
		design.switchTab("tab-mailValidation");
		alert(32);
	},
	btnNombreApellidoBack__click: (e) => {
		design.switchTab("tab-documentos");
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
	btnMailBack__click: (e) => {
		design.switchTab("tab-direccion");
	},
	mdSelectProvincia__load: async (e) => {
		const data = await provinces.getProvinces();
		data.map((province) => {
			const listItem = material.mdSelectMenuItemSingleLine(
				province.id,
				province.nombre
			);
			e.menuElement.querySelector("ul").append(listItem);
		});
		e.layoutOptions();
	},
	mdSelectLocalidad__load: async (e, p) => {
		e.menuElement.querySelector("ul").innerHTML = "";
		if (p != "") {
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
		} else {
			e.setSelectedIndex(-1);
			e.disabled = true;
			e.layoutOptions();
			e.layout();
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
		return control;
	})(),
	mdtxtCUIL: (() => {
		const control = material.loadTxt(document.querySelector("#mdtxtCUIL"));
		return control;
	})(),
	mdtxtFechaNacimiento: (() => {
		const control = material.loadTxt(
			document.querySelector("#mdtxtFechaNacimiento")
		);
		return control;
	})(),
	mdSelectProvincia: (() => {
		const control = material.loadSelect(
			document.querySelector("#mdSelectProvincia")
		);
		events.mdSelectProvincia__load(control);
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
};
(async () => {
	material.loadElements();
})();
