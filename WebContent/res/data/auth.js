"use strict";

const AUTH_HEADER = (() => {
	let _headers = new Headers();
	_headers.append("Authorization", `Bearer ${localStorage.getItem("token")}`);
	return _headers;
})();

const LOGIN_PATH = "/TPINT_GRUPO_3_LAB/clientes/login.html";

const whoIam = async () => {
	try {
		const response = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/whoami",
			{
				headers: AUTH_HEADER,
				method: "GET",
			}
		);
		if (response.status != 200) return null;
		return {
			status: response.status,
			data: await response.json(),
		};
	} catch (err) {
		console.error(err);
	}
};

const allowClient = async () => {
	const data = await whoIam();
	if (data == null) {
		window.location = LOGIN_PATH;
	} else {
		if (data.data.message != "CLIENT") {
			window.location = LOGIN_PATH;
		} else {
			return data.data.objectReturned;
		}
	}
};

const allowAdmin = async () => {
	const data = await whoIam();
	if (data == null) {
		window.location = LOGIN_PATH;
	} else {
		if (data.data.message != "ADMIN") {
			window.location = LOGIN_PATH;
		} else {
			return data.data.objectReturned;
		}
	}
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

export {
	login,
	testAccess,
	AUTH_HEADER,
	getActualUser,
	whoIam,
	allowAdmin,
	allowClient,
};
