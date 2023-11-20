"use strict";
import * as auth from './auth.js';

const getAccounts = async () => {
    try {
        const f = await fetch(
            'http://localhost:8080/TPINT_GRUPO_3_LAB/api/accounts',
            {
                method: "GET",
                headers: auth.AUTH_HEADER
            }
        );
        const status = f.status;
        const data = await f.json();
        return {status, data};
    } catch (err) {
        console.error(err);
    }


};

const getAccount = async (accountNo) => {
    try {
        const f = await fetch(
            'http://localhost:8080/TPINT_GRUPO_3_LAB/api/accounts/' + accountNo,
            {
                method: "GET",
                headers: auth.AUTH_HEADER
            }
        );
        const status = f.status;
        const data = await f.json();
        return {status, data};
    } catch (err) {
        console.error(err);
    }


};

const quickAccount = async (accountDetail, key) => {
    try {
        const f = await fetch(
            'http://localhost:8080/TPINT_GRUPO_3_LAB/api/quick/account/data/' + key + '/' + accountDetail, {
                method: 'GET'
            });
        const status = f.status;
        const data = await f.json();
        return {status, data};
    } catch (err) {
        console.error(err);
    }
}

export {
    getAccounts,
    getAccount,
    quickAccount
}