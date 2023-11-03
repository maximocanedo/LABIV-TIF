"use strict";

const AUTH_HEADER = (() => {
	let _headers = new Headers();
	_headers.append("Authorization", `Bearer ${localStorage.getItem("token")}`);
	return _headers;
})();

const whoIam = async () => {
	var requestOptions = {
		method: "GET",
		headers: AUTH_HEADER,
		redirect: "follow",
	};

	fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/api/whoami", requestOptions)
		.then((response) => response.text())
		.then((result) => console.log(result))
		.catch((error) => console.log("error", error));
};

const login = async (user, password, isAdmin = false) => {
	try {
		const response = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/" +
				(isAdmin ? "admin" : "client") +
				"/login",
			{
				method: "POST",
				body: JSON.stringify({
					usuario: user,
					password: password,
					usuario_admin: user,
					password_admin: password,
				}),
			}
		);
		let message = "";
		if (response.status === 200) {
			const token = response.headers.get("Authorization");
			localStorage.setItem("token", token);
			message = "Se inició sesión correctamente. ";
		} else {
			console.error("Error en la respuesta:", response.status);
			message = "Error al intentar iniciar sesión. "; // TODO: Ampliar.
		}
		return {
			status: response.status,
			message: message,
		};
	} catch (error) {
		console.error(error);
		return {
			status: 500,
			message: "Error interno del servidor. ",
		};
	}
};

const testAccess = async (isAdmin = false) => {
	try {
		const response = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/" +
				(isAdmin ? "admin" : "client"),
			{
				headers: AUTH_HEADER,
				method: "GET",
			}
		);
	} catch (err) {
		console.error(err);
	}
};

const getActualUser = async (isAdmin = false) => {
	try {
		const response = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/" +
				(isAdmin ? "admin" : "client"),
			{
				headers: AUTH_HEADER,
				method: "GET",
			}
		);
		const status = response.status;
		const data = await response.json();
		return { status, data };
	} catch (err) {
		console.error(err);
	}
};

export { login, testAccess, AUTH_HEADER, getActualUser, whoIam };
