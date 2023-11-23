"use strict";

const AUTH_HEADER = (() => {
    let _headers = new Headers();
    _headers.append("Authorization", `Bearer ${localStorage.getItem("token")}`);
    return _headers;
})();

const LOGIN_PATH = "/TPINT_GRUPO_3_LAB/login";

const buildLoginUrl = (
    {message, role, next} = {
        message: "Iniciá sesión para continuar. ",
        role: "client",
        next: window.location.href,
    }
) => {
    let url = LOGIN_PATH;
    const data = {
        message,
        role,
        next,
    };
    url += "?";
    if (data != null) {
        const filteredData = Object.fromEntries(
            Object.entries(data).filter(([key, value]) => value !== null)
        );
        url += new URLSearchParams(filteredData).toString();
    }
    return url;
};

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

const allowClient = async (
    {message} = {message: "Iniciá sesión como cliente para continuar. "}
) => {
    const data = await whoIam();
    if (data == null) {
        const actualURL = window.location.href;
        window.location = buildLoginUrl({
            message,
            role: "client",
            next: actualURL,
        });
    } else {
        if (data.data.message != "CLIENT") {
            const actualURL = window.location.href;
            window.location = buildLoginUrl({
                message,
                role: "admin",
                next: actualURL,
            });
        } else {
            return data.data.objectReturned;
        }
    }
};

const allowAdmin = async (
    {message} = {
        message: "Iniciá sesión como administrador para continuar. ",
    }
) => {
    const data = await whoIam();
    if (data == null) {
        const actualURL = window.location.href;
        window.location = buildLoginUrl({
            message,
            role: "admin",
            next: actualURL,
        });
    } else {
        if (data.data.message != "ADMIN") {
            const actualURL = window.location.href;
            window.location = buildLoginUrl({
                message,
                role: "admin",
                next: actualURL,
            });
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
    let status = 500
        , data = {};
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/" +
            (isAdmin ? "admin" : "client"),
            {
                headers: AUTH_HEADER,
                method: "GET",
            }
        );
        status = response.status;
        data = await response.json();
    } catch (err) {
        console.error(err);
    }
    return {status, data};
};

export {
    login,
    testAccess,
    AUTH_HEADER,
    getActualUser,
    whoIam,
    allowAdmin,
    allowClient,
    LOGIN_PATH
};
