'use strict';
import * as auth from "./auth.js";

const getMyLoanRequests = async (paginator) => {
	const r = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo?p=" + paginator.page + "&s=" + paginator.size,
		{
			method: "GET",
			headers: auth.AUTH_HEADER
		}
	);
	const status = r.status;
	const data = await r.json();
	const list = data.listReturned;
	return {status, list};
};

export {
	getMyLoanRequests
};