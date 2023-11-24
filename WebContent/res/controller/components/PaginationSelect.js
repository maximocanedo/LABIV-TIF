'use strict';
import {MDCSelect} from "../material/components.js";
export default class PaginationSelect {
    constructor(options) {
        this.options = options;
        options.default = options.default?? options.values[0];
        this.defaultIndex = options.values.indexOf(options.default);

        const selectContainer = document.createElement('div');
        selectContainer.classList.add(
            'mdc-select',
            'mdc-select--outlined',
            'mdc-select--no-label',
            'mdc-data-table__pagination-rows-per-page-select',
            'mdc-data-table__pagination-rows-per-page-select--outlined'
        );

        const selectAnchor = document.createElement('div');
        selectAnchor.classList.add('mdc-select__anchor');
        selectAnchor.setAttribute('role', 'button');
        selectAnchor.setAttribute('aria-haspopup', 'listbox');
        selectAnchor.setAttribute('aria-labelledby', 'demo-pagination-select');
        selectAnchor.setAttribute('tabindex', '0');

        const selectedTextContainer = document.createElement('span');
        selectedTextContainer.classList.add('mdc-select__selected-text-container');

        const selectedText = document.createElement('span');
        selectedText.id = 'demo-pagination-select';
        selectedText.classList.add('mdc-select__selected-text');
        selectedText.textContent = this.options.default || '10';

        selectedTextContainer.appendChild(selectedText);

        const dropdownIcon = document.createElement('span');
        dropdownIcon.classList.add('mdc-select__dropdown-icon');

        const dropdownIconSVG = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        dropdownIconSVG.classList.add('mdc-select__dropdown-icon-graphic');
        dropdownIconSVG.setAttribute('viewBox', '7 10 10 5');

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

        dropdownIconSVG.appendChild(inactivePolygon);
        dropdownIconSVG.appendChild(activePolygon);
        dropdownIcon.appendChild(dropdownIconSVG);

        const notchedOutline = document.createElement('span');
        notchedOutline.classList.add('mdc-notched-outline', 'mdc-notched-outline--notched');

        const leadingOutline = document.createElement('span');
        leadingOutline.classList.add('mdc-notched-outline__leading');

        const trailingOutline = document.createElement('span');
        trailingOutline.classList.add('mdc-notched-outline__trailing');

        notchedOutline.appendChild(leadingOutline);
        notchedOutline.appendChild(trailingOutline);

        selectAnchor.appendChild(selectedTextContainer);
        selectAnchor.appendChild(dropdownIcon);
        selectAnchor.appendChild(notchedOutline);

        const selectMenu = document.createElement('div');
        selectMenu.classList.add(
            'mdc-select__menu',
            'mdc-menu',
            'mdc-menu-surface',
            'mdc-menu-surface--fullwidth'
        );
        selectMenu.setAttribute('role', 'listbox');

        const list = document.createElement('ul');
        list.classList.add('mdc-list');

        this.options.values.forEach((value) => {
            const listItem = document.createElement('li');
            listItem.classList.add(
                'mdc-select__option',
                'mdc-select__one-line-option',
                'mdc-list-item',
                'mdc-list-item--with-one-line'
            );
            listItem.setAttribute('role', 'option');
            listItem.setAttribute('data-value', value);

            const listItemRipple = document.createElement('span');
            listItemRipple.classList.add('mdc-list-item__ripple');

            const listItemContent = document.createElement('span');
            listItemContent.classList.add('mdc-list-item__content');

            const primaryText = document.createElement('span');
            primaryText.classList.add('mdc-list-item__primary-text');
            primaryText.textContent = value;

            listItemContent.appendChild(primaryText);
            listItem.appendChild(listItemRipple);
            listItem.appendChild(listItemContent);
            list.appendChild(listItem);
        });

        selectMenu.appendChild(list);

        selectContainer.appendChild(selectAnchor);
        selectContainer.appendChild(selectMenu);

        this.root = selectContainer;
        this.materialElement = new MDCSelect(selectContainer);
        this.materialElement.setSelectedIndex(this.defaultIndex);
    }
    onChange(callback) {
        this.root.addEventListener("MDCSelect:change", e => {
            callback(this.getValue());
        });
    }
    getValue() {
        return parseInt(this.materialElement.menuItemValues[this.materialElement.selectedIndex]);
    }
    getElement() {
        return this.root;
    }
};
