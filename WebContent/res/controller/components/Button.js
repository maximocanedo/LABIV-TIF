"use strict";
import * as material from "./../mdc.controller.js";

class MDButton extends HTMLElement {
	constructor() {
		super();

		// Crear un shadow DOM para tu elemento
		//const shadow = this.attachShadow({ mode: "open" });

		// Crear los elementos internos
		this = document.createElement("button");
		this.classList.add("mdc-button");

		const ripple = document.createElement("span");
		ripple.classList.add("mdc-button__ripple");

		const focusRing = document.createElement("span");
		focusRing.classList.add("mdc-button__focus-ring");

		const span = document.createElement("span");
		span.classList.add("mdc-button__label");

		// Obtener el texto del contenido
		const buttonText = this.textContent.trim();
		span.textContent = buttonText;

		const componentRipple = material.rippleIt(this);

		// Agregar los elementos al shadow DOM
		this.appendChild(ripple);
		this.appendChild(focusRing);
		this.appendChild(span);
		//shadow.appendChild(button);
	}
}

// Definir el custom element
customElements.define("md-button", MDButton);

export default MDButton;
