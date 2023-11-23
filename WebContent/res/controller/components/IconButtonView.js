'use strict';
import { MDCRipple } from "../material/components.js";

export default class IconButtonView {
    constructor(iconName) {
        this.iconName = iconName;

        const wrapper = document.createElement('div');
        wrapper.classList.add('mdc-touch-target-wrapper');

        const button = document.createElement('button');
        button.classList.add('mdc-icon-button');
        this.button = button;
        const ripple = document.createElement('div');
        ripple.classList.add('mdc-icon-button__ripple');
        button.appendChild(ripple);

        const focusRing = document.createElement('span');
        focusRing.classList.add('mdc-icon-button__focus-ring');
        button.appendChild(focusRing);

        const icon = document.createElement('i');
        icon.classList.add('material-symbols-outlined');
        icon.textContent = this.iconName;
        button.appendChild(icon);

        const touch = document.createElement('div');
        touch.classList.add('mdc-icon-button__touch');
        button.appendChild(touch);

        const iconButtonRipple = new MDCRipple(button);
        iconButtonRipple.unbounded = true;

        wrapper.appendChild(button);

        this.wrapper =  wrapper;
    }
    getElement() {
        return this.wrapper;
    }
    onClick(callback) {
        this.button.addEventListener('click', callback);
    }
}

