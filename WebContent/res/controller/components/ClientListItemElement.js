'use strict';
import TwoLineListItemElement from "./TwoLineListItemElement.js";
export default class ClientListItemElement extends TwoLineListItemElement {
    static formatearDNI(dni)  {
            if (dni.length === 9) {
            return (
                dni.slice(0, 3) + "." + dni.slice(3, 6) + "." + dni.slice(6, 9)
        );
        } else if (dni.length === 8) {
            return (
                dni.slice(0, 2) + "." + dni.slice(2, 5) + "." + dni.slice(5, 8)
            );
        } else {
            return dni;
        }
    }
    static formatearCUIL(cuil) {
        const prefijo = cuil.substring(0, 2);
        const sufijo = cuil.substring(cuil.length - 1);
        const dni = cuil.substring(2, cuil.length - 1);
        return `${prefijo}-${ClientListItemElement.formatearDNI(dni)}-${sufijo}`;
    }
    constructor(data) {
        super(data.nombre + " " + data.apellido, ClientListItemElement.formatearCUIL(data.CUIL), 'person');
        this.payload = data;
    }
    handleClick(callback) {
        this.getElement().addEventListener('click', e => {
            callback({ ...e, payload: this.payload });
        })
    }
}