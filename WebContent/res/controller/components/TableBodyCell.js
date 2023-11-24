'use strict';
export default class TableBodyCell {
    constructor(content, options = { isNumeric: false }) {
        const td = document.createElement("td");
        td.classList.add("mdc-data-table__cell");
        if(options.isNumeric) td.classList.add("mdc-data-table__cell--numeric");
        td.setAttribute("scope", "row");
        td.append(content);
        this.root = td;
    }
    getElement() {
        return this.root;
    }
};