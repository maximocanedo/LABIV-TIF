'use strict';
import * as clients from "./../data/clients.js";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";
import * as admins from "./../data/admins.js";

let openMyProfileDialog = () => {
	const dialog = material.showOtherDialog("#myProfileDialog");

};

(async () => {
	material.loadElements();
	let usuario = {};
	let role;
	const whoIam = await auth.whoIam();
	if(whoIam == null) {
	    // No hay usuario en sesión.
	    // Terminamos esta función con el return.
	    return;
	} else {
	    // Hay un usuario con sesión, ahora veremos qué rol tiene.
	    const data = whoIam.data;
	    role = data.message; // "ADMIN" o "CLIENT" son los dos valores posibles que debería tener.
	    usuario = data.objectReturned; // Datos del cliente/admin en sesión, como nombre, apellido, nombre de usuario, etc. 
	    switch(role) {
	        case "ADMIN":
	            console.log("Admin.");
	            break;
	        case "CLIENT":
	        	console.log("Client.");
	            break;
	    }
	}
	document.querySelectorAll(".__user_fullName").forEach(e => {
		e.innerText = usuario.nombre + " " + usuario.apellido;
	});
	document.querySelectorAll(".__user_name").forEach(e => {
		e.innerText = usuario.nombre;
	});

	document.querySelectorAll(".__user_role").forEach(e => {
		e.innerText = {"ADMIN": "Administrador", "CLIENT": "Cliente"}[role];
	});
	document.querySelectorAll(".__user_surname").forEach(e => {
		e.innerText = usuario.apellido;
	});
	document.querySelectorAll(".__user_username").forEach(e => {
		e.innerText = usuario.usuario;
	});
	document.querySelectorAll(".__user_dni").forEach(e => {
		e.innerText = usuario.usuario;
	});
	document.querySelectorAll(".__user_cuil").forEach(e => {
		e.innerText = usuario.usuario;
	});
	document.querySelector("#myProfileButton").addEventListener("click", openMyProfileDialog);
	document.querySelector("#logoutButton").addEventListener("click", e => {
		localStorage.removeItem("token");
		window.location = auth.LOGIN_PATH;
	});
})();