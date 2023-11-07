"use strict";

const getProvinces = async () => {
	return fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/api/provinces/list", {
		method: "GET",
	})
		.then((raw) => raw.json())
		.then((json) => {
			return json.listReturned;
		})
		.catch((err) => {
			console.error(err);
			return null;
		});
};

export { getProvinces };
