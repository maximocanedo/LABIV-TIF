"use strict";
import * as mdce from "./material/material-components-web.js";

const mdc = mdce.default.mdc;

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

const loadDrawer = (element) => {
	const mdc = mdce.default.mdc;
	const drawer = new mdc.drawer.MDCDrawer(element);
	document
		.querySelector(".btn--open-drawer")
		.addEventListener("click", (e) => (drawer.open = true));
	document
		.querySelectorAll(".mdc-drawer .mdc-deprecated-list")
		.forEach((listEl) => {
			listEl.addEventListener("click", (event) => {
				drawer.open = false;
			});
		});
	return drawer;
};

const loadDialog = () => {
	const dialogElement = document.querySelector(".mdc-dialog");
	return new mdc.dialog.MDCDialog(dialogElement);
};

const loadSnackbar = () => {
	const snackbarElement = document.querySelector(".mdc-snackbar");
	return new mdc.snackbar.MDCSnackbar(snackbarElement);
};

const showSnackbar = (text) => {
	const snackbar = loadSnackbar();
	snackbar.labelText = text;
	snackbar.open();
};

const showDialog = async (question) => {
	const dialog = loadDialog();
	console.log(dialog);
	let status = false;
	dialog.root.querySelector("#dialog--question").innerText = question;
	const buttonPromise = new Promise((resolve) => {
		const oA = (e) => {
			status = true;
			resolve(status);
		};
		const oC = (e) => {
			status = false;
			resolve(status);
		};

		dialog.buttons[1].addEventListener("click", oA); // Quita el paréntesis aquí
		dialog.buttons[0].addEventListener("click", oC); // Quita el paréntesis aquí
	});

	dialog.buttons[0].innerText = "Cancelar";
	dialog.buttons[1].innerText = "Aceptar";

	dialog.open();

	return await buttonPromise;
};

const loadElements = () => {
	const mdc = mdce.default.mdc;
	console.log(mdc);
	window.temp2 = mdc;
	console.log(mdc);
	// Instanciar todos los MDCDrawer
	document
		.querySelectorAll(".mdc-drawer")
		.forEach((element) => loadDrawer(element));

	// Instanciar todos los TextField
	document.querySelectorAll(".mdc-text-field").forEach((element) => {
		var textfield_mdc = new mdc.textField.MDCTextField(element);
	});
	document.querySelectorAll(".mdc-select").forEach((element) => {
		var select_mdc = loadSelect(element);
		console.log({ select_mdc });
	});
	document.querySelectorAll(".mdc-top-app-bar").forEach((element) => {
		var topAppBar = new mdc.topAppBar.MDCTopAppBar(element);
		console.log({ topAppBar });
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
	loadDialog,
	showDialog,
	loadSnackbar,
	showSnackbar,
};
