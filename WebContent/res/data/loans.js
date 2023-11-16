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
const getById = async (id) => {
	const res = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/loans/" + id, {
		method: "GET",
		headers: auth.AUTH_HEADER
	});
	const status = res.status;
	const data = await res.json();
	return { status, data };
};
const pagarCuota = async (k) => {
	const res = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client/pagarcuota", {
		method: "POST",
		headers: auth.AUTH_HEADER,
		body: JSON.stringify(k)
	});
	const status = res.status;
	const data = await res.json();
	return { status, data };
};

const getPaidInstallments = async (requestId) => {
	const r = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/client/pagarcuota?cod_Sol=" + requestId,
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

const approve = async (requestId) => {
	const res = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo", {
		method: "PUT",
		headers: auth.AUTH_HEADER,
		body: JSON.stringify({ "cod_Sol": requestId })
	});
	const status = res.status;
	return { status };

};
const reject = async (requestId) => {
	const res = await fetch(
		"http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo", {
		method: "DELETE",
		headers: auth.AUTH_HEADER,
		body: JSON.stringify({ "cod_Sol": requestId })
	});
	const status = res.status;
	return { status };

};

export {
	getMyLoanRequests,
	getById,
	pagarCuota,
	getPaidInstallments,
	approve,
	reject
};