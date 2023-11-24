'use strict';
import {IconButtonView} from "./index.js";

export default class PaginationButton extends IconButtonView {
    constructor(iconName) {
        super(iconName);
        this.getElement().classList.add("mdc-data-table__pagination-button");
    }
}