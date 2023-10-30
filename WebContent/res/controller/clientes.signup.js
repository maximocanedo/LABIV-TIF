import * as provinces from "./../data/provinces.js";
import * as localties from "./../data/localties.js";
import * as countries from "./../data/countries.js";
import * as material from "./../controller/mdc.controller.js";
import * as auth from "./../data/auth.js";

(async () => {
	//const loginResult = auth.login("Maria_12144165", "Ma#16%822$15*Gri");
	//const testResult = auth.testAccess(false);
	material.loadElements();
})();
