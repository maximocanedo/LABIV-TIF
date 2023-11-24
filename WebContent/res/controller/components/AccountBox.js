'use strict';
import { Formatter } from "./index.js";
import { MDCRipple } from "../material/components.js";

export default class AccountBox {
    constructor(data) {
// Crear el div principal
        const mainDiv = document.createElement('div');
        mainDiv.classList.add(
            'mdc-layout-grid__cell',
            'mdc-layout-grid__cell--span-4',
            'mdc-layout-grid__cell--span-12-phone',
            'mdc-card',
            'mdc-layout-grid',
            'account_card',
            'mdc-ripple-surface',
            'mdc-ripple-surface--accent'
        );
        mainDiv.addEventListener('click', e => {
            window.location = '/TPINT_GRUPO_3_LAB/clientes/account/?accountno=' + data.numero;
        })

        const innerDiv = document.createElement('div');
        innerDiv.classList.add('mdc-layout-grid__inner');

        const spanTipoCuenta = document.createElement("span");
        spanTipoCuenta.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12", "mdc-typography--button");
        spanTipoCuenta.innerText = data.tipo.descripcion;
        innerDiv.append(spanTipoCuenta);

        const spanSaldo = document.createElement("span");
        spanSaldo.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12", "mdc-typography--headline5");
        spanSaldo.innerText = Formatter.money(data.saldo);
        innerDiv.append(spanSaldo);
        this.hideSensitiveData = () => {
            spanSaldo.innerText = "$ ***";
        };
        this.showSensitiveData = () => {
            spanSaldo.innerText = Formatter.money(data.saldo);
        };

        const spanNumeroCuenta = document.createElement("span");
        spanNumeroCuenta.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12", "mdc-typography--button");
        spanNumeroCuenta.innerText = Formatter.accountNumber(data.numero);
        innerDiv.append(spanNumeroCuenta);

        const spanCBU = document.createElement("span");
        spanCBU.classList.add("mdc-layout-grid__cell", "mdc-layout-grid__cell--span-12", "mdc-typography--button");
        spanCBU.innerText = data.CBU;
        innerDiv.append(spanCBU);

        mainDiv.appendChild(innerDiv);

        this.root = mainDiv;
        this.materialElement = new MDCRipple(mainDiv);

    }
    getElement() {
        return this.root;
    }
    static RequestNewAccountButton = class {
        constructor() {
            const mainDiv = document.createElement('div');
            mainDiv.classList.add(
                'mdc-layout-grid__cell',
                'mdc-layout-grid__cell--span-6',
                'mdc-layout-grid__cell--span-12-phone',
                'mdc-card',
                'mdc-layout-grid',
                'account_card',
                'action__account',
                'mdc-ripple-surface',
                'mdc-ripple-surface--primary'
            );

            const innerDiv = document.createElement('div');
            innerDiv.classList.add('mdc-layout-grid__inner');

            const iconContainer = document.createElement('div');
            iconContainer.classList.add('super-icon-container', 'mdc-layout-grid__cell--span-12');

            const icon = document.createElement('i');
            icon.classList.add('material-symbols-outlined');
            icon.textContent = 'add';

            iconContainer.appendChild(icon);

            const spanText = document.createElement('span');
            spanText.classList.add('mdc-layout-grid__cell--span-12', 'mdc-typography--body1', 'centered-text');
            spanText.textContent = 'Crear cuenta';

            innerDiv.appendChild(iconContainer);
            innerDiv.appendChild(spanText);

            mainDiv.appendChild(innerDiv);

            this.root = mainDiv;

        }
        getElement() {
            return this.root;
        }
    }
};