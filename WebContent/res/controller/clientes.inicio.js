"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

(async () => {
	// Instanciar componentes
	material.loadElements();
	console.log(material);
	// Obtener datos del cliente actual. Si no hay cliente redirige a la página de inicio de sesión.
	const actualUser = await auth.allowClient();
})();
