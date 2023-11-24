"use strict";

/**
 * Lista provincias
 * @returns lista de provincias, null si hubo error.
 */
const getProvinces = async () => {
    return fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/v2/provinces", {
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

export {getProvinces};
