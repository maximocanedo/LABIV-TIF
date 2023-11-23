"use strict";
import { AUTH_HEADER } from "./auth.js";

const DNIExists = async (DNI) => {
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/client/dni/" +
            DNI,
            {
                method: "GET",
            }
        );

        return response.status;
    } catch (error) {
        console.error(error);
        return 500;
    }
};
const userExists = async (username) => {
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/client/u/" +
            username,
            {
                method: "GET",
            }
        );

        return response.status;
    } catch (error) {
        console.error(error);
        return 500;
    }
};
const CUILExists = async (CUIL) => {
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/client/cuil/" +
            CUIL,
            {
                method: "GET",
            }
        );

        return response.status;
    } catch (error) {
        console.error(error);
        return 500;
    }
};
const disable = async (username) => {
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/v2/clients/user/" + username,
            {
                method: "DELETE",
                headers: AUTH_HEADER
            }
        );
        const { status } = response;
        const data = await response.json();
        return { status, data };
    } catch(e) {
        return { status: 500, data: e };
        console.error(e);
    }
};
const enable = async (username) => {
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/v2/clients/user/" + username,
            {
                method: "POST",
                headers: AUTH_HEADER
            }
        );
        const { status } = response;
        const data = await response.json();
        return { status, data };
    } catch(e) {
        return { status: 500, data: e };
        console.error(e);
    }
};
export {DNIExists, userExists, CUILExists, disable, enable};
