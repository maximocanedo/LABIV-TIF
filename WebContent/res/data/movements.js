"use strict";
import * as auth from './auth.js';



const getMovementsFromAccount = async (accountNo, paginator) => {
	try {
			const f = await fetch(
			'http://localhost:8080/TPINT_GRUPO_3_LAB/api/movements/account/' + accountNo + `?p=${paginator.page}&s=${paginator.size}`,
			{
				method: "GET",
				headers: auth.AUTH_HEADER
			}
			);
		const status = f.status;
		const data = await f.json();
		return {status, data};
	} catch(err) {
		console.error(err);
	}


};

export {
	getMovementsFromAccount
}