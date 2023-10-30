"use strict";

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
	}
};

const testAccess = async (isAdmin = false) => {
	var myHeaders = new Headers();
	myHeaders.append(
		"Authorization",
		`Bearer ${localStorage.getItem("token")}`
	);
	try {
		const response = await fetch(
			"http://localhost:8080/TPINT_GRUPO_3_LAB/api/" +
				(isAdmin ? "admin" : "client"),
			{
				headers: myHeaders,
				method: "GET",
			}
		);
		if (response.status == 200) {
			console.log("Autenticó con éxito. ");
		} else console.error("No se autenticó. ");
		console.log(await response.json());
	} catch (err) {
		console.error(err);
	}
};

export { login, testAccess };
