"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

(async () => {
	material.loadElements();
	const e = await auth.whoIam();
	console.log(auth);
	console.log(e);
	//auth.letAdminIn();
})();
