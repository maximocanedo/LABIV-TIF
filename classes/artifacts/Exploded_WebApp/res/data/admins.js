"use strict";
const DNIExists = async (DNI) => {
    try {
        const response = await fetch(
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/admin/dni/" +
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
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/admin/u/" +
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
            "http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/admin/cuil/" +
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
export {DNIExists, userExists, CUILExists};
