'use strict';
import {MDCDataTable} from "../material/components.js";
import PaginationSelect from "./PaginationSelect.js";
import PaginationButton from "./PaginationButton.js";

export default class DataTableView {
    constructor(label, data = { header: [], paginator: {default: 10, values: [10, 15, 20]}, selectable: false }) {
        this.selectable = data.selectable?? false;
        this.paginator = data.paginator?? { default: 10, values: [10, 15, 20] };
        const root = document.createElement("div");
        root.classList.add("mdc-data-table");

        const container = document.createElement("div");
        container.classList.add("mdc-data-table__table-container");
        root.append(container);

        const table = document.createElement("table");
        table.classList.add("mdc-data-table__table");
        table.setAttribute("aria-label", label);
        container.append(table);

        const tableHead = document.createElement("thead");
        table.append(tableHead);

        const tableHeadRow = document.createElement("tr");
        tableHeadRow.classList.add("mdc-data-table__header-row");
        tableHead.append(tableHeadRow);
        if(this.selectable) {
            tableHeadRow.append(new DataTableView.HeaderCheckboxCell().getElement());
        }
        data.header.map(cell => {
            tableHeadRow.append(cell.getElement());
        });

        const tableBody = document.createElement("tbody");
        tableBody.classList.add("mdc-data-table__content");
        this.tbody = tableBody;
        table.append(tableBody);

        // Progress Indicator
        const progressIndicator = new DataTableView.ProgressIndicator();
        root.append(progressIndicator.getElement());

        // Pagination stuff
        const mainPagination = document.createElement("div");
        mainPagination.classList.add("mdc-data-table__pagination");
        root.append(mainPagination);

        const paginationTrailing = document.createElement("div");
        paginationTrailing.classList.add("mdc-data-table__pagination-trailing");
        mainPagination.append(paginationTrailing);

        const rowsPerPage = document.createElement("div");
        rowsPerPage.classList.add("mdc-data-table__pagination-rows-per-page");
        paginationTrailing.append(rowsPerPage);

        const rowsPerPageLabel = document.createElement("div");
        rowsPerPageLabel.classList.add("mdc-data-table__pagination-rows-per-page-label");
        rowsPerPageLabel.innerText = "Rows per page: ";
        rowsPerPage.append(rowsPerPageLabel);
        this.page = 1;
        this.paginatorSelect = new PaginationSelect(this.paginator);
        rowsPerPage.append(this.paginatorSelect.getElement());

        const paginatorNavigation = document.createElement("div");
        paginatorNavigation.classList.add("mdc-data-table__pagination-navigation");
        paginationTrailing.append(paginatorNavigation);

        this.paginatorTotalSpan = document.createElement("div");
        this.paginatorTotalSpan.classList.add("mdc-data-table__pagination-total");
        this.paginatorTotalSpan.innerText = `Page ${this.page}`;
        paginatorNavigation.append(this.paginatorTotalSpan);

        this.prevPageButton = new PaginationButton("chevron_left");
        this.prevPageButton.getElement().disabled = (this.page == 1);
        this.prevPageButton.getElement().addEventListener('click', (e) => {
            if(this.page > 1) this.page--;
            this.prevPageButton.getElement().disabled = (this.page == 1);
            this.dispatchPaginateChangeEvent('prevPage');
        });
        paginatorNavigation.append(this.prevPageButton.getElement());

        this.nextPageButton = new PaginationButton("chevron_right");
        this.nextPageButton.getElement().addEventListener('click', (e) => {
            this.page++;
            this.dispatchPaginateChangeEvent('nextPage');
        });

        this.paginatorSelect.onChange(val => {
            this.dispatchPaginateChangeEvent('paginatorSelect');
        });


        paginatorNavigation.append(this.nextPageButton.getElement());

        this.root = root;
        this.materialElement = new MDCDataTable(this.root);
        if(this.selectable) this.materialElement.layout();

    }
    showProgress() {
        this.materialElement.showProgress();
    }
    hideProgress() {
        this.materialElement.hideProgress();
    }
    getSelectedRowIds() {
        return this.materialElement.getSelectedRowIds();
    }
    setSelectedRowIds(str = []) {
        return this.materialElement.setSelectedRowIds(str);
    }
    addEventListener(type, listener) {
        this.root.addEventListener(type, listener);
    }
    dispatchPaginateChangeEvent(action) {
        this.paginatorTotalSpan.innerText = `Page ${this.page}`;
        const event = new CustomEvent('paginateChange', { detail: { action, paginator: this.getPaginatorDetails() } });
        this.root.dispatchEvent(event);
        this.nextPageButton.getElement().disabled = (this.tbody.childElementCount < this.getPaginatorDetails().size);
    }
    getPaginatorDetails() {
        return {
            page: this.page,
            size: parseInt(this.paginatorSelect.getValue())
        }
    }
    clear() {
        this.tbody.innerHTML = "";
    }
    add(row) {
        this.tbody.append(row.getElement(this.selectable));
        if(this.selectable) this.materialElement.layout();
    }
    fill(rows = []) {
        this.clear();
        rows.forEach(row => {
            this.add(row);
        });
        if(this.selectable) this.materialElement.layout();
    }
    getElement() {
        return this.root;
    }
    static HeaderCheckboxCell = class HeaderCheckboxCell {
        constructor() {
            this.root = document.createElement('th');
            this.root.classList.add('mdc-data-table__header-cell', 'mdc-data-table__header-cell--checkbox');
            this.root.setAttribute('role', 'columnheader');
            this.root.setAttribute('scope', 'col');

            const checkboxContainer = document.createElement('div');
            checkboxContainer.classList.add('mdc-checkbox', 'mdc-data-table__header-row-checkbox', 'mdc-checkbox--selected');

            const checkboxInput = document.createElement('input');
            checkboxInput.setAttribute('type', 'checkbox');
            checkboxInput.classList.add('mdc-checkbox__native-control');
            checkboxInput.setAttribute('aria-label', 'Toggle all rows');

            const checkboxBackground = document.createElement('div');
            checkboxBackground.classList.add('mdc-checkbox__background');

            const checkboxSVG = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
            checkboxSVG.classList.add('mdc-checkbox__checkmark');
            checkboxSVG.setAttribute('viewBox', '0 0 24 24');

            const checkboxPath = document.createElementNS('http://www.w3.org/2000/svg', 'path');
            checkboxPath.classList.add('mdc-checkbox__checkmark-path');
            checkboxPath.setAttribute('fill', 'none');
            checkboxPath.setAttribute('d', 'M1.73,12.91 8.1,19.28 22.79,4.59');

            checkboxSVG.appendChild(checkboxPath);

            const checkboxMixedMark = document.createElement('div');
            checkboxMixedMark.classList.add('mdc-checkbox__mixedmark');

            const checkboxRipple = document.createElement('div');
            checkboxRipple.classList.add('mdc-checkbox__ripple');

            checkboxBackground.appendChild(checkboxSVG);
            checkboxBackground.appendChild(checkboxMixedMark);

            checkboxContainer.appendChild(checkboxInput);
            checkboxContainer.appendChild(checkboxBackground);
            checkboxContainer.appendChild(checkboxRipple);

            this.root.appendChild(checkboxContainer);
        }

        getElement() {
            return this.root;
        }
    }
    static ProgressIndicator = class {
        constructor() {
            this.root = document.createElement('div');
            this.root.classList.add('mdc-data-table__progress-indicator');

            const scrim = document.createElement('div');
            scrim.classList.add('mdc-data-table__scrim');

            const linearProgress = document.createElement('div');
            linearProgress.classList.add('mdc-linear-progress', 'mdc-linear-progress--indeterminate', 'mdc-data-table__linear-progress');
            linearProgress.setAttribute('role', 'progressbar');
            linearProgress.setAttribute('aria-label', 'Data is being loaded...');

            const buffer = document.createElement('div');
            buffer.classList.add('mdc-linear-progress__buffer');

            const bufferBar = document.createElement('div');
            bufferBar.classList.add('mdc-linear-progress__buffer-bar');

            const bufferDots = document.createElement('div');
            bufferDots.classList.add('mdc-linear-progress__buffer-dots');

            buffer.appendChild(bufferBar);
            buffer.appendChild(bufferDots);

            const primaryBar = document.createElement('div');
            primaryBar.classList.add('mdc-linear-progress__bar', 'mdc-linear-progress__primary-bar');

            const primaryBarInner = document.createElement('span');
            primaryBarInner.classList.add('mdc-linear-progress__bar-inner');

            primaryBar.appendChild(primaryBarInner);

            const secondaryBar = document.createElement('div');
            secondaryBar.classList.add('mdc-linear-progress__bar', 'mdc-linear-progress__secondary-bar');

            const secondaryBarInner = document.createElement('span');
            secondaryBarInner.classList.add('mdc-linear-progress__bar-inner');

            secondaryBar.appendChild(secondaryBarInner);

            linearProgress.appendChild(buffer);
            linearProgress.appendChild(primaryBar);
            linearProgress.appendChild(secondaryBar);

            this.root.appendChild(scrim);
            this.root.appendChild(linearProgress);
        }

        getElement() {
            return this.root;
        }
    }

}