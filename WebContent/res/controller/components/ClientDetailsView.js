'use strict';

import {ButtonView, TwoLineListElement, TwoLineListItemElement, Formatter, IconButtonView} from "./index.js";
import * as material from './../mdc.controller.js';
import * as clients from './../../data/clients.js';

export default class ClientDetailsView {
    constructor(data) {
        this.root = document.createElement("div");
        this.root.classList.add("mdc-layout-grid__inner");

        const closeBtn = new IconButtonView("close");
        closeBtn.onClick(e => {
            this.close();
        });
        const header = document.createElement("div");
        header.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-2", "cdv-header");
        header.append(closeBtn.getElement());

        const spanFullName = document.createElement("span");
        spanFullName.classList.add("mdc-layout-grid__cell", "cdv-header", "mdc-layout-grid__cell--span-10", "mdc-typography--headline4");
        spanFullName.innerText = data.nombre + " " + data.apellido;

        const spanUsername = document.createElement("span");
        spanUsername.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12", "mdc-typography--body1");
        spanUsername.innerText = "@" + data.usuario;

        let sx = (data.sexo != null && typeof data.sexo !== "undefined") ? {"M": "Masculino", "F": "Femenino"}[data.sexo] : "Valor inválido";


        const listPersonalData = new TwoLineListElement();
        const dataItems = [
            new TwoLineListItemElement(Formatter.DNI(data.DNI), "D.N.I.", "badge"),
            new TwoLineListItemElement(Formatter.CUIL(data.CUIL), "C.U.I.L.", "badge"),
            new TwoLineListItemElement(sx, "Sexo", "wc"),
            new TwoLineListItemElement(data.direccion, data.localidad.nombre + ", " + data.provincia.nombre, "place"),
            new TwoLineListItemElement(data.fechaNacimiento, "Fecha de nacimiento", "cake"),
            new TwoLineListItemElement(data.correo, (data.correoVerificado ? "Dirección de correo electrónico" : "Dirección de correo electrónico sin confirmar"), "mail")
        ];
        dataItems.forEach(item => listPersonalData.add(item));
        listPersonalData.element.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12");

        const btnDeshabilitar = new ButtonView("Deshabilitar", {outlined: true, iconLeading: "delete"});
        btnDeshabilitar.root.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12", "danger-action-button");
        btnDeshabilitar.onClick(e => this.disableUser(data.usuario));

        const btnHabilitar = new ButtonView("Habilitar", {outlined: true, iconLeading: "enable"});
        btnHabilitar.root.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12");
        btnHabilitar.onClick(e => this.enableUser(data.usuario));


        this.root.append(spanFullName, header, spanUsername, listPersonalData.getElement(), (data.estado ? btnDeshabilitar : btnHabilitar).getElement());

    }
    disableUser(user) {
        (async () => {
            if(await material.showDialog("¿Deshabilitar este usuario?")) {
                const res = await clients.disable(user);
                if(res.status == 200) {
                    material.showBanner("Este usuario se deshabilitó con éxito. ");
                } else {
                    material.showBanner("Hubo un problema al deshabilitar el usuario. Código de error: " + res.status, );
                }
            } else {
                material.showSnackbar("Cancelaste la operación. ");
            }
        })();
    }
    enableUser(user) {
        (async () => {
            if(await material.showDialog("¿Habilitar este usuario?")) {
                const res = await clients.enable(user);
                if(res.status == 200) {
                    material.showBanner("Este usuario se habilitó con éxito. ");
                } else {
                    material.showBanner("Hubo un problema al habilitar el usuario. Código de error: " + res.status, );
                }
            } else {
                material.showSnackbar("Cancelaste la operación. ");
            }
        })();
    }
    close() {
        this.root.remove();
    }
    getElement() {
        return this.root;
    }

}