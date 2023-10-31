"use strict";
import * as mdce from "./material/material-components-web.js";

const mdSelectMenuItemSingleLine = (value, text) => {
	const li = document.createElement("li");
	li.classList.add("mdc-deprecated-list-item");
	li.setAttribute("data-value", value);
	li.setAttribute("role", "option");
	const rippleSpan = document.createElement("span");
	rippleSpan.classList.add("mdc-deprecated-list-item__ripple");
	const span = document.createElement("span");
	span.classList.add("mdc-deprecated-list-item__text");
	span.innerText = text;

	li.append(rippleSpan, span);
	return li;
};

const rippleIt = (element) => {
	const mdc = mdce.default.mdc;
	return mdc.ripple.MDCRipple.attachTo(element);
};

const loadSelect = (element) => {
	const mdc = mdce.default.mdc;
	return new mdc.select.MDCSelect(element);
};

const loadElements = () => {
	const mdc = mdce.default.mdc;
	console.log(mdc);
	window.temp2 = mdc;
	//console.log(mdc);
	// Instanciar todos los TextField
	document.querySelectorAll(".mdc-text-field").forEach((element) => {
		var textfield_mdc = new mdc.textField.MDCTextField(element);
	});
	document.querySelectorAll(".mdc-select").forEach((element) => {
		var select_mdc = loadSelect(element);
		console.log({ select_mdc });
	});
	document
		.querySelectorAll(".mdc-button")
		.forEach((element) => rippleIt(element));

	document.querySelectorAll(".mdc-snackbar").forEach((element) => {
		var md = new mdc.snackbar.MDCSnackbar(element);
		window.showSnackbar = (message) => {
			md.labelText = message;
			md.open();
		};
	});
};

const loadLinearProgressBar = (element) => {
	const mdc = mdce.default.mdc;
	let mdEl = new mdc.linearProgress.MDCLinearProgress(element);
	return mdEl;
};

const loadTxt = (element) => {
	const mdc = mdce.default.mdc;
	return new mdc.textField.MDCTextField(element);
};

export {
	loadElements,
	rippleIt,
	loadLinearProgressBar,
	loadTxt,
	mdSelectMenuItemSingleLine,
	loadSelect,
};
