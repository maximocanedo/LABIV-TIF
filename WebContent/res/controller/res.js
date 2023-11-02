"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

(() => {
	material.loadElements();
	auth.letClientIn();
	//auth.letAdminIn();
})();
