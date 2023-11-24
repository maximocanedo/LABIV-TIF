'use strict';
import * as auth from "../../res/data/auth.js";
import * as loans from "../../res/data/loans.js";
import * as material from "../../res/controller/mdc.controller.js";
import {
    ButtonView,
    DataTableView,
    TableBodyCell,
    TableBodyRow,
    TableHeaderCell
} from "../../res/controller/components/index.js";


const formatearNumeroCuenta = (nc) => {
    const cleaned = ('' + nc).replace(/\D/g, '');
    const formatted = cleaned.replace(/(\d{4})(\d{3})(\d{3})/, '$1-$2-$3');
    return "# " + formatted;
}
const formatearComoDinero = (numero) => {
    const numeroFormateado = numero.toLocaleString('es-AR', {style: 'currency', currency: 'ARS'});
    return numeroFormateado;
}


const tablaPrestamos = new DataTableView("Solicitudes de préstamos", {
    header: [
        new TableHeaderCell("ID", { sortable: true }),
        new TableHeaderCell("Cliente"),
        new TableHeaderCell("Detalles"),
        new TableHeaderCell("Acciones")
    ],
    selectable: true
});
document.body.append(tablaPrestamos.getElement());


const loadRequests = async (paginator) => {
    tablaPrestamos.showProgress();
    tablaPrestamos.clear();
    const {status, list} = await loans.getMyLoanRequests(paginator);
    tablaPrestamos.hideProgress();
    if (status != 200 && status != 201) {
        material.showSnackbar("Hubo un error al intentar obtener los préstamos. ");
        return;
    }
    list.map((loan, index) => {
        const btnCliente = new ButtonView(
            loan.cliente.nombre + " " + loan.cliente.apellido, {
                iconLeading: "person"
            });
        btnCliente.getElement().addEventListener('click', () => {
            console.log(loan.cliente);
        });
        const dv = document.createElement("div");
        const btnAprobar = new ButtonView("Aprobar");
        const btnRechazar = new ButtonView("Rechazar");
        dv.append(btnAprobar.getElement(), btnRechazar.getElement());
        const row = new TableBodyRow([
            new TableBodyCell(loan.codigo),
            new TableBodyCell(btnCliente.getElement()),
            new TableBodyCell(`${formatearComoDinero(loan.montoPedido)} a ${loan.cantCuotas} cuotas.`),
            new TableBodyCell(dv)
        ], loan.codigo);
        if (loan.estado == 0) {
            btnAprobar.getElement().addEventListener('click', async (e) => {
                if (await material.showDialog("¿Aprobar préstamo #" + loan.codigo + "?")) {
                    const op = await loans.approve(loan.codigo);
                    if (op.status == 200 || op.status == 201) {
                        material.showSnackbar("Se aprobó el préstamo #" + loan.codigo + " con éxito. ");
                        return;
                    }
                    material.showSnackbar("No se aprobó el préstamo debido a un error. ");
                    return;
                }
            });
            btnRechazar.getElement().addEventListener('click', async (e) => {
                if (await material.showDialog("Rechazar préstamo #" + loan.codigo + "?")) {
                    const op = await loans.reject(loan.codigo);
                    if (op.status == 200 || op.status == 201) {
                        material.showSnackbar("Se rechazó el préstamo #" + loan.codigo + " con éxito. ");
                        return;
                    }
                    material.showSnackbar("No se rechazó el préstamo debido a un error. ");
                    return;
                }
            });
            tablaPrestamos.add(row);
        }
    });
};

(async () => {
    const admin = await auth.allowAdmin({
        message: "Iniciá sesión como administrador para revisar y aprobar préstamos. "
    });
    await loadRequests({page: 1, size: 10});
    tablaPrestamos.addEventListener("paginateChange", e => {
        (async () => {
            await loadRequests(e.detail.paginator);
        })();
    });
})();