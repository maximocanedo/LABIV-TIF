'use strict';
import { mdc } from './../mdc.controller.js';

class MDCDropdownList {

    constructor(labelText, options, id = null) {
        this.labelText = labelText;
        this.options = options;
        const selectContainer = document.createElement('div');
        selectContainer.classList.add('mdc-select', 'mdc-select--outlined');
        if(id == null) {
            let ln = document.querySelectorAll(".mdc-select--outlined").length;
            let mil = new Date().getMilliseconds() ** 2;
            selectContainer.setAttribute("id", "MDCDropdownList$" + mil + '' + ln);
        }

        this.root = selectContainer;

        const selectAnchor = document.createElement('div');
        selectAnchor.classList.add('mdc-select__anchor');
        selectAnchor.setAttribute('aria-labelledby', 'outlined-select-label');

        const notchOutline = document.createElement('span');
        notchOutline.classList.add('mdc-notched-outline');

        const leadingNotch = document.createElement('span');
        leadingNotch.classList.add('mdc-notched-outline__leading');
        notchOutline.appendChild(leadingNotch);

        const notch = document.createElement('span');
        notch.classList.add('mdc-notched-outline__notch');
        notchOutline.appendChild(notch);

        const label = document.createElement('span');
        label.id = 'outlined-select-label';
        label.classList.add('mdc-floating-label');
        label.textContent = this.labelText;
        notch.appendChild(label);

        const trailingNotch = document.createElement('span');
        trailingNotch.classList.add('mdc-notched-outline__trailing');
        notchOutline.appendChild(trailingNotch);

        const selectedTextContainer = document.createElement('span');
        selectedTextContainer.classList.add('mdc-select__selected-text-container');

        this.selectedText = document.createElement('span');
        this.selectedText.id = 'demo-selected-text';
        this.selectedText.classList.add('mdc-select__selected-text');
        selectedTextContainer.appendChild(this.selectedText);

        const dropdownIcon = document.createElement('span');
        dropdownIcon.classList.add('mdc-select__dropdown-icon');

        const svgIcon = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        svgIcon.classList.add('mdc-select__dropdown-icon-graphic');
        svgIcon.setAttribute('viewBox', '7 10 10 5');
        svgIcon.setAttribute('focusable', 'false');

        const inactivePolygon = document.createElementNS('http://www.w3.org/2000/svg', 'polygon');
        inactivePolygon.classList.add('mdc-select__dropdown-icon-inactive');
        inactivePolygon.setAttribute('stroke', 'none');
        inactivePolygon.setAttribute('fill-rule', 'evenodd');
        inactivePolygon.setAttribute('points', '7 10 12 15 17 10');

        const activePolygon = document.createElementNS('http://www.w3.org/2000/svg', 'polygon');
        activePolygon.classList.add('mdc-select__dropdown-icon-active');
        activePolygon.setAttribute('stroke', 'none');
        activePolygon.setAttribute('fill-rule', 'evenodd');
        activePolygon.setAttribute('points', '7 15 12 10 17 15');

        svgIcon.appendChild(inactivePolygon);
        svgIcon.appendChild(activePolygon);
        dropdownIcon.appendChild(svgIcon);

        selectAnchor.appendChild(notchOutline);
        selectAnchor.appendChild(selectedTextContainer);
        selectAnchor.appendChild(dropdownIcon);
        selectContainer.appendChild(selectAnchor);

        const selectMenu = document.createElement('div');
        selectMenu.classList.add('mdc-select__menu', 'mdc-menu', 'mdc-menu-surface', 'mdc-menu-surface--fullwidth');

        const ul = document.createElement('ul');
        ul.classList.add('mdc-deprecated-list');
        ul.setAttribute('role', 'listbox');
        ul.setAttribute('aria-label', '');
        this.list = ul;


        selectMenu.appendChild(ul);
        selectContainer.appendChild(selectMenu);

        this.options.forEach(data => this.add(data));

        this.materialElement = new mdc.select.MDCSelect(selectContainer);
        this.materialElement.getValue = () => this.labelText;
        this.materialElement.foundation.getValue = () => this.labelText;
        this.materialElement.layoutOptions();
    }

    add(data) {
        this.options.push(data);
        const option = { text: "", value: "", selected: false, disabled: false, ...data};
        const li = document.createElement('li');
        li.classList.add('mdc-deprecated-list-item');
        li.setAttribute('role', 'option');
        li.setAttribute('data-value', option.value);

        if (option.selected) {
            li.classList.add('mdc-deprecated-list-item--selected');
            li.setAttribute('aria-selected', 'true');
            this.selectedText.textContent = option.text;
        } else {
            li.setAttribute('aria-selected', 'false');
        }

        if (option.disabled) {
            li.classList.add('mdc-deprecated-list-item--disabled');
            li.setAttribute('aria-disabled', 'true');
        }

        const rippleSpan = document.createElement('span');
        rippleSpan.classList.add('mdc-deprecated-list-item__ripple');
        li.appendChild(rippleSpan);

        const textSpan = document.createElement('span');
        textSpan.classList.add('mdc-deprecated-list-item__text');
        textSpan.textContent = option.text;
        li.appendChild(textSpan);

        this.list.appendChild(li);

    }
    addMultiple(arr) {
        arr.map(obj => this.add(obj));
    }
    clear() {
        this.list.innerHTML = "";
        this.options = [];
    }
    onChange(callback) {
        this.root.addEventListener("MDCSelect:change", e => {
            const options = {
                text: (this.materialElement.selectedText.innerText),
                value: (e.detail.value),
                index: (e.detail.index)
            };
            callback(e, options);
        });
    }
    getElement() {
        return this.root;
    }
}
export default MDCDropdownList;


