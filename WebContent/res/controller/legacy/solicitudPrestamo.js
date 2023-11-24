"use strict";
import * as material from "../mdc.controller.js";
import * as solicitudes from "../../data/solicitudPrestamo.js";
import * as auth from "../../data/auth.js";

const whoIam = await auth.whoIam();
if (whoIam == null) {
    // No hay usuario en sesión.
    // Terminamos esta función con el return.
    return;
} else {
    // Hay un usuario con sesión, ahora veremos qué rol tiene.
    const data = whoIam.data;
    const role = data.message; // "ADMIN" o "CLIENT" son los dos valores posibles que debería tener.
    const usuario = data.objectReturned; // Datos del cliente/admin en sesión, como nombre, apellido, nombre de usuario, etc. 
    switch (role) {
        case "ADMIN":
            /* Código que se ejecutará si es admin. */
            //si es admin va a tener otro jsp con dos botones,
            //uno para aceptar y otro para rechazar

            //primero le debe mostrar la tabla, (con get)

            const req = await fetch(
                "http://localhost:8080/TPINT_GRUPO_3_LAB/api/solicitudprestamo", {
                    method: "GET",
                    headers: auth.AUTH_HEADER, // Adjuntamos el pase, para autenticarnos.
                });
            const code = req.status
            const json = await req.json();
            const listaDeSolicitudes = json.listReturned;

            //hacer tabla con listaDeSolicitudes

            // tengo q llamar a post si toca 1 de los dos botones
            // debe seleccionar la fila y obtener el codigo de solicitudPrestamo


            //alert("¡Sos un admin!");
            break;
        case "CLIENT":
            //fatores para el interes de forma que sea dinamico
            const factorMonto = 0.1;
            const factorCuotas = 0.25;

            const mPedido = parseDouble(document.querySelector("#montoPedido").value);
            const cCuotas = parseInt(document.querySelector("#cantCuotas").value);

            //formula de interes
            const interes = factorCuotas * cCuotas + mPedido * factorMonto;

            document.querySelector("#interes").value = interes;

            const mAPagar = montoPedido * (1 + (interes / 100));
            document.querySelector("#montoAPagar").value = montoAPagar;

            const montoPorCuota = mAPagar / cCuotas;
            document.querySelector("#montoPorCuota").value = montoPorCuota;
            document.querySelector("#plazoPago").value = cCuotas + 4; //margen de pago en meses

            const nuevosDatosAIngresar = {
                cliente: await auth.Allowclient() //document.querySelector("#cliente").value
                , fechaPedido: document.querySelector("#fechaPedido").value
                , montoPedido: document.querySelector("#montoPedido").value
                , montoAPagar: document.querySelector("#montoAPagar").value
                , plazoPago: document.querySelector("#plazoPago").value
                , cantCuotas: document.querySelector("#cantCuotas").value
                , montoPorCuota: document.querySelector("#montoPorCuota").value
                , interes: document.querySelector("#interes").value
                , cuenta: document.querySelector("#cuentaCBU").value

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
                const message = json.message; // "El registro se ingreso correctamente. "

            });


            break;
    }
}
