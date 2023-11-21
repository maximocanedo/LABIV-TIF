'use strict';
import MDCDropdownList from "./MDCDropdownList.js";
import * as provinces from "../../data/provinces.js";

export default class ProvinceDropdownList extends MDCDropdownList {
    constructor(labelText = "Seleccioná una provincia", defaultItemText = null) {
        super(labelText, []);
        this.defaultItemText = defaultItemText;
        (async () => { await this.loadOptions(); })();
    }
    async loadOptions() {
        provinces.getProvinces().then((provincias) => {
            const provinceItems = [];
            if(this.defaultItemText != null) provinceItems.push({
                text: this.defaultItemText,
                value: -1
            });
            provincias.map((opcion) => provinceItems.push({
                text: opcion.nombre,
                value: opcion.id
            }));
            return this.addMultiple(provinceItems);
        });
    }
}