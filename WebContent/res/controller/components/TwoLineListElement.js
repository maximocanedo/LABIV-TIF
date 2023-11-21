'use strict';
import { mdc } from './../mdc.controller.js';
class TwoLineListElement {
    constructor() {
        this.element = document.createElement('ul');
        this.element.classList.add('mdc-deprecated-list', 'mdc-deprecated-list--two-line');
        // Agrega la lógica específica para tu lista si es necesario
    }
    getElement() {
        return this.element;
    }
    clear() {
        this.element.innerHTML = "";
    }
    add(twoLineListItemElement) {
        this.element.append(twoLineListItemElement.getElement());
    }
}

export default TwoLineListElement;