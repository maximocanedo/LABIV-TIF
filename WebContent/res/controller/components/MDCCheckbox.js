'use strict';
import { mdc } from './../mdc.controller.js';
class MDCCheckbox {
    constructor(labelText, id = null, selected = false) {
        if(id == null) {
            let ln = document.querySelectorAll(".mdc-checkbox").length;
            let mil = new Date().getMilliseconds() ** 2;
            id = "MDCCheckbox$" + mil + '' + ln;
        }
        this.id = id;
        this.labelText = labelText;

        const divFormField = document.createElement('div');
        divFormField.classList.add('mdc-form-field');
        this.formField = divFormField;

        const divCheckbox = document.createElement('div');
        divCheckbox.classList.add('mdc-checkbox');

        this.root = divCheckbox

        const inputCheckbox = document.createElement('input');
        inputCheckbox.type = 'checkbox';
        inputCheckbox.classList.add('mdc-checkbox__native-control');
        inputCheckbox.id = this.id;

        const divCheckboxBackground = document.createElement('div');
        divCheckboxBackground.classList.add('mdc-checkbox__background');

        const svgCheckmark = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        svgCheckmark.classList.add('mdc-checkbox__checkmark');
        svgCheckmark.setAttribute('viewBox', '0 0 24 24');

        const pathCheckmark = document.createElementNS('http://www.w3.org/2000/svg', 'path');
        pathCheckmark.classList.add('mdc-checkbox__checkmark-path');
        pathCheckmark.setAttribute('fill', 'none');
        pathCheckmark.setAttribute('d', 'M1.73,12.91 8.1,19.28 22.79,4.59');

        svgCheckmark.appendChild(pathCheckmark);

        const divMixedMark = document.createElement('div');
        divMixedMark.classList.add('mdc-checkbox__mixedmark');

        const divRipple = document.createElement('div');
        divRipple.classList.add('mdc-checkbox__ripple');

        const divFocusRing = document.createElement('div');
        divFocusRing.classList.add('mdc-checkbox__focus-ring');

        divCheckboxBackground.appendChild(svgCheckmark);
        divCheckboxBackground.appendChild(divMixedMark);

        divCheckbox.appendChild(inputCheckbox);
        divCheckbox.appendChild(divCheckboxBackground);
        divCheckbox.appendChild(divRipple);
        divCheckbox.appendChild(divFocusRing);

        divFormField.appendChild(divCheckbox);

        const label = document.createElement('label');
        label.setAttribute('for', this.id);
        label.textContent = this.labelText;

        divFormField.appendChild(label);
        this.materialFormElement = new mdc.formField.MDCFormField(divFormField);
        this.materialElement = new mdc.checkbox.MDCCheckbox(this.root);

    }
    getElement() {
        return this.formField;
    }
}
export default MDCCheckbox;
