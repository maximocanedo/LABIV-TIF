"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

(async () => {
	material.loadElements();
	const actualUser = await auth.allowAdmin();
	console.log({ actualUser });
})();
