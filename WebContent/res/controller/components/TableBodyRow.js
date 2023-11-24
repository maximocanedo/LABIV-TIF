'use strict';
import {Formatter} from "./index.js";

export default class TableBodyRow {
    constructor(arr = [], id = null) {
        const tr = document.createElement("tr");
        tr.classList.add("mdc-data-table__row");
        this.root = tr;
        if(id == null) {
            id = Formatter.generateID(6);
        }
        this.id = id;
        tr.setAttribute("data-row-id", this.id);
        this.setRows(arr);
    }
    clear() {
        this.root.innerHTML = '';
    }
    setRows(arr = []) {
        this.clear();

        arr.map(td => {
            console.log(td);
            this.addRow(td);
        });
    }
    addRow(row) {
        this.root.append(row.getElement());
    }
    prependRow(row) {
        this.root.prepend(row.getElement());
    }
    getElement(selectable = false) {
        if(selectable) {
            const chk = new TableBodyRow.CheckBoxCell(this.id);
            this.prependRow(chk);
        }
        return this.root;
    }
    static CheckBoxCell = class {
        constructor(rowId) {
            this.root = document.createElement('td');
            this.root.classList.add('mdc-data-table__cell', 'mdc-data-table__cell--checkbox');

            const checkboxContainer = document.createElement('div');
            checkboxContainer.classList.add('mdc-checkbox', 'mdc-data-table__row-checkbox');

            const checkboxInput = document.createElement('input');
            checkboxInput.setAttribute('type', 'checkbox');
            checkboxInput.classList.add('mdc-checkbox__native-control');
            checkboxInput.setAttribute('aria-labelledby', rowId);

            const checkboxBackground = document.createElement('div');
            checkboxBackground.classList.add('mdc-checkbox__background');

            const checkboxSVG = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
            checkboxSVG.classList.add('mdc-checkbox__checkmark');
            checkboxSVG.setAttribute('viewBox', '0 0 24 24');

            const checkboxPath = document.createElementNS('http://www.w3.org/2000/svg', 'path');
            checkboxPath.classList.add('mdc-checkbox__checkmark-path');
            checkboxPath.setAttribute('fill', 'none');
            checkboxPath.setAttribute('d', 'M1.73,12.91 8.1,19.28 22.79,4.59');

            checkboxSVG.appendChild(checkboxPath);

            const checkboxMixedMark = document.createElement('div');
            checkboxMixedMark.classList.add('mdc-checkbox__mixedmark');

            const checkboxRipple = document.createElement('div');
            checkboxRipple.classList.add('mdc-checkbox__ripple');

            checkboxBackground.appendChild(checkboxSVG);
            checkboxBackground.appendChild(checkboxMixedMark);

            checkboxContainer.appendChild(checkboxInput);
            checkboxContainer.appendChild(checkboxBackground);
            checkboxContainer.appendChild(checkboxRipple);

            this.root.appendChild(checkboxContainer);
        }

        getElement() {
            return this.root;
        }
    }
}