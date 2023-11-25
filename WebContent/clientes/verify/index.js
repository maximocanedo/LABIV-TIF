import * as provinces from "../../res/data/provinces.js";
import * as localties from "../../res/data/localties.js";
import * as countries from "../../res/data/countries.js";
import * as clients from "../../res/data/clients.js";
import * as material from "../../res/controller/mdc.controller.js";
import * as auth from "../../res/data/auth.js";


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
    btnMailOK__click: async (e) => {
        const v = material.doc.mdtxtMail.foundation.isValid();
        if (v) {
            design.switchTab("tab-codigo");
        } else {
            material.showSnackbar("Ingresá un correo válido. ");
            return;
        }
    },
    btnLogin__click: (e) => {
        window.location = '/TPINT_GRUPO_3_LAB/clientes/dashboard';
    },
    btnCodigoOK__click() {
        design.switchTab("tab-done");
    },
    btnCodigoBack__click() {
        design.switchTab("tab-mail");
    }
};
let controls = {
    btnMailOK: (() => {
        const btn = document.querySelector("#btnMailOK");
        btn.addEventListener("click", events.btnMailOK__click);
        return btn;
    })(),
    btnCodigoOK: (() => {
        const btn = document.querySelector("#btnCodigoOK");
        btn.addEventListener("click", events.btnCodigoOK__click);
        return btn;
    })(),
    btnCodigoBack: (() => {
        const btn = document.querySelector("#btnCodigoBack");
        btn.addEventListener("click", events.btnCodigoBack__click);
        return btn;
    })(),
    mdtxtMail: (() => {
        const control = material.loadTxt(document.querySelector("#mdtxtMail"));
        return control;
    })(),
    btnLogin: (() => {
        const btn = document.querySelector("#btnLogin");
        btn.addEventListener("click", events.btnLogin__click);
        return btn;
    })(),
};
(async () => {
    material.loadElements();
})();

