'use strict';

const getAll = async () => {
    const f = await fetch(
        "http://localhost:8080/TPINT_GRUPO_3_LAB/api/accountTypes",
        {method: "GET"}
    );
    const status = f.status;
    const data = await f.json();
    return {status, data};
};

export {getAll};