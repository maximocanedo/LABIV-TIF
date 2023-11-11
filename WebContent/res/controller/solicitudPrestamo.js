"use strict";
import * as material from "./../controller/mdc.controller.js";
import * as solicitudes from "./../data/solicitudPrestamo.js";
import * as auth from "./../data/auth.js";

const whoIam = await auth.whoIam();
if(whoIam == null) {
    // No hay usuario en sesión.
    // Terminamos esta función con el return.
    return;
} else {
    // Hay un usuario con sesión, ahora veremos qué rol tiene.
    const data = whoIam.data;
    const role = data.message; // "ADMIN" o "CLIENT" son los dos valores posibles que debería tener.
    const usuario = data.objectReturned; // Datos del cliente/admin en sesión, como nombre, apellido, nombre de usuario, etc. 
    switch(role) {
        case "ADMIN":
            /* Código que se ejecutará si es admin. */
            //alert("¡Sos un admin!");
            break;
        case "CLIENT":
            const nuevosDatosAIngresar = {
				codigo: document.querySelector("#codigo")
				,cliente: document.querySelector("#cliente")
				,fechaPedido: document.querySelector("#fechaPedido")
				,montoPedido: document.querySelector("#montoPedido")
				,montoAPagar: document.querySelector("#montoAPagar")
				,plazoPago: document.querySelector("#plazoPago")
				,cantCuotas: document.querySelector("#cantCuotas")
				,montoPorCuota: document.querySelector("#montoPorCuota")
				,interes: document.querySelector("#interes")
				,estado: document.querySelector("#estado")
				,cuenta: document.querySelector("#cuentaCBU")
			
			};
			
			const BtnEnviar = document.querySelector("#btnSolicitar");
			BtnEnviar.addEventListener("click", async (e) => {
				const req = await fetch(
					"http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo", {
					method: "POST", 
					headers: auth.AUTH_HEADER, // Adjuntamos el pase, para autenticarnos.
					body: JSON.stringify(nuevosDatosAIngresar) // Adjuntamos el body con los parámetros a enviar.
				});
				const code = req.status; // 200, 401, 404, ...
				const json = await req.json();
				const message = json.message; // "El registro se modificó correctamente. "
				
			});
			
			
            break;
    }
}
/*
try {    
    const res = await fetch(
        "http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo",
        {
			method: "GET",
 			headers: auth.AUTH_HEADER // Adjuntamos el pase, para autenticarnos.
        }
    );
    const codigo = res.status;
    const data = await res.json();
	const listaDeSolicitudes = json.listReturned;
} catch(err) {
    console.log(err);
    return;
}
*/
