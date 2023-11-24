'use strict';
import Formatter from './Formatter.js';
import {IconButtonView} from "./index.js";
export default class TableHeaderCell {
    constructor(textContent, options = { isNumeric: false, sortable: false }) {
        this.textContent = textContent;
        this.isNumeric = options.isNumeric?? false;
        this.sortable = options.sortable?? false;
        const cell = document.createElement('th');
        const id = `tableHeaderCell$${new Date().getMilliseconds()}$${Formatter.generateID(5)}`
        cell.classList.add('mdc-data-table__header-cell');
        cell.setAttribute('id', id);
        cell.setAttribute('data-column-id', id);
        cell.setAttribute('role', "columnheader");
        cell.setAttribute('scope', "col");
        if (this.isNumeric) {
            cell.classList.add('mdc-data-table__header-cell--numeric');
        }
        if(this.sortable) {
            cell.classList.add("mdc-data-table__header-cell--with-sort");
            cell.setAttribute("aria-sort", "none");
            const wrapper = document.createElement("div");
            wrapper.classList.add("mdc-data-table__header-cell-wrapper");
            cell.append(wrapper);
            const sortButton = document.createElement("button");
            sortButton.classList.add("mdc-icon-button", "material-symbols-outlined", "mdc-data-table__sort-icon-button");
            sortButton.setAttribute("aria-describedby", id + "$sl");
            sortButton.innerText = 'arrow_upward';
            const cellLabel = document.createElement("div");
            cellLabel.classList.add("mdc-data-table__header-cell-label");
            cellLabel.innerText = this.textContent;
            const statusLabel = document.createElement("div");
            statusLabel.classList.add("mdc-data-table__sort-status-label");
            statusLabel.setAttribute("aria-hidden", true);
            statusLabel.setAttribute("id", id + "$sl");
            wrapper.append(statusLabel, cellLabel, sortButton);
            cell.append(wrapper);
        } else cell.textContent = this.textContent;

        this.root = cell;
    }
    getElement() {
        return this.root;
    }
};