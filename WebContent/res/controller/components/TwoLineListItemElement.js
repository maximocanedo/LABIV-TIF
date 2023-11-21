"use strict";
class TwoLineListItemElement {
    constructor(firstLineText, secondLineText, icon) {
        this.element = document.createElement('li');
        this.element.classList.add('mdc-deprecated-list-item');
        this.element.setAttribute('tabindex', '0');

        const graphicSpan = document.createElement('span');
        graphicSpan.classList.add('mdc-deprecated-list-item__graphic', 'material-symbols-outlined');
        graphicSpan.textContent = icon;

        const rippleSpan = document.createElement('span');
        rippleSpan.classList.add('mdc-deprecated-list-item__ripple');

        const textSpan = document.createElement('span');
        textSpan.classList.add('mdc-deprecated-list-item__text');

        const primaryTextSpan = document.createElement('span');
        primaryTextSpan.classList.add('mdc-deprecated-list-item__primary-text');
        primaryTextSpan.textContent = firstLineText;

        const secondaryTextSpan = document.createElement('span');
        secondaryTextSpan.classList.add('mdc-deprecated-list-item__secondary-text');
        secondaryTextSpan.textContent = secondLineText;

        textSpan.appendChild(primaryTextSpan);
        textSpan.appendChild(secondaryTextSpan);

        this.element.appendChild(graphicSpan);
        this.element.appendChild(rippleSpan);
        this.element.appendChild(textSpan);
    }

    getElement() {
        return this.element;
    }
}

export default TwoLineListItemElement;
