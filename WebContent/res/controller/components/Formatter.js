'use strict';
export default class Formatter {
    constructor() {
    }

    static money(str) {

    }

    static DNI(dni) {
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

    static CUIL(cuil) {
        const prefix = cuil.substring(0, 2);
        const suffix = cuil.substring(cuil.length - 1);
        const dni = cuil.substring(2, cuil.length - 1);
        return `${prefix}-${Formatter.DNI(dni)}-${suffix}`;
    }
}