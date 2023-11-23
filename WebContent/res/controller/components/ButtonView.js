import { MDCRipple } from "../material/components.js";

export default class ButtonView {
    constructor(label, options = {
        iconLeading: false,
        outlined: false,
        raised: false,
    }) {
        this.label = label;
        this.options = options;

        const button = document.createElement('button');
        button.classList.add('mdc-button');

        if (this.options.iconLeading) {
            button.classList.add('mdc-button--icon-leading');
            const icon = document.createElement('i');
            icon.classList.add('material-symbols-outlined', 'mdc-button__icon');
            icon.setAttribute('aria-hidden', 'true');
            icon.textContent = this.options.iconLeading;
            button.appendChild(icon);
        }

        if (this.options.outlined) {
            button.classList.add('mdc-button--outlined');
        }

        if (this.options.raised) {
            button.classList.add('mdc-button--raised');
        }

        const ripple = document.createElement('span');
        ripple.classList.add('mdc-button__ripple');
        button.appendChild(ripple);

        const focusRing = document.createElement('span');
        focusRing.classList.add('mdc-button__focus-ring');
        button.appendChild(focusRing);

        const labelElement = document.createElement('span');
        labelElement.classList.add('mdc-button__label');
        labelElement.textContent = this.label;
        button.appendChild(labelElement);

        this.ripple = new MDCRipple(button);

        this.root = button;
    }
    getElement() {
        return this.root;
    }
    onClick(callback) {
        this.root.addEventListener('click', callback);
    }
};
