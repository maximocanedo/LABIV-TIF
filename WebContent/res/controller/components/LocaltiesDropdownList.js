'use strict';
import MDCDropdownList from "./MDCDropdownList.js";
import * as localties from "../../data/localties.js";

export default class LocaltiesDropdownList extends MDCDropdownList {
    constructor(labelText, provincesDDL, defaultLocaltyText = null) {
        super(labelText, []);
        this.defaultLocaltyText = defaultLocaltyText;
        provincesDDL.onChange((e, d) => {
            (async () => {
                console.log({e, d});
                if(typeof d.value !== 'undefined') await this.loadOptions(d.value);
            })();
        });
    }
    async loadOptions(provinceId) {
        if(provinceId === -1 || provinceId === '-1') return;
        const localidades = await localties.getLocalties(provinceId);
        this.clear();
        if(this.defaultLocaltyText != null) {
            this.add(this.defaultLocaltyText, -1);
        }
        if(typeof localidades === 'undefined') return;
        localidades.forEach(option => {
           this.add({ text: option.nombre, value: option.id });
        });
        this.materialElement.layoutOptions();
    }
}