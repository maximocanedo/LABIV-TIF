"use strict";
const getCountries = () =>
    fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/v2/countries", {
        method: "GET",
    })
        .then((raw) => raw.json())
        .then((json) => {
            return json.listReturned;
        })
        .catch((err) => console.error(err));

export {getCountries};
