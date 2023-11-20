"use strict";
const getLocalties = (provinceId) =>
    fetch(
        "http://localhost:8080/TPINT_GRUPO_3_LAB/api/locality/list/" +
        provinceId,
        {
            method: "GET",
        }
    )
        .then((raw) => raw.json())
        .then((json) => {
            return json.listReturned;
        })
        .catch((err) => console.error(err));

export {getLocalties};
