'use strict';
import MDCDropdownList from "./MDCDropdownList.js";
import * as countries from "../../data/countries.js";

export default class CountriesDropdownList extends MDCDropdownList {
    constructor(labelText, defaultItemText = null) {
        super(labelText, []);
        this.defaultItemText = defaultItemText;
        (async () => {
            await this.loadOptions();
        })();
    }
    async loadOptions() {
        return countries.getCountries().then((paises) => {
            const countryItems = [];
            if(this.defaultItemText != null) countryItems.push({
                text: this.defaultItemText,
                value: -1
            });
            paises.map((opcion) => countryItems.push({
                text: opcion.nombre,
                value: opcion.codigo
            }));
            return this.addMultiple(countryItems);
        });
    }
}