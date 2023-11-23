<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../../res/styles/init.css"/>
    <link rel="stylesheet" type="text/css" href="../../res/styles/estiloPaginaCliente-Administrador.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/redux/4.0.5/redux.js"></script>

</head>
<body>
<%@ include file="../../res/web/drawer.part.html" %>
<%@ include file="../../res/web/header.part.html" %>
<main class="mdc-top-app-bar--fixed-adjust">
    Préstamos
    <br><br><br>
    <ul id="prestamosList">

    </ul>
    <input type="number" name="size" id="size" value="5" placeholder="Elementos por página">
    <button id="prevBtn">Anterior</button>
    <span id="textoPaginacion"></span>
    <button id="nextBtn">Siguiente</button>
    <br><br><br>
    <div class="mdc-data-table">
        <div class="mdc-data-table__table-container">
            <table class="mdc-data-table__table" aria-label="Dessert calories">
                <thead>
                <tr class="mdc-data-table__header-row">
                    <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Dessert</th>
                    <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric" role="columnheader" scope="col">Carbs (g)</th>
                    <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric" role="columnheader" scope="col">Protein (g)</th>
                    <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Comments</th>
                </tr>
                </thead>
                <tbody class="mdc-data-table__content">
                <tr class="mdc-data-table__row">
                    <th class="mdc-data-table__cell" scope="row">Frozen yogurt</th>
                    <td class="mdc-data-table__cell mdc-data-table__cell--numeric">24</td>
                    <td class="mdc-data-table__cell mdc-data-table__cell--numeric">4.0</td>
                    <td class="mdc-data-table__cell">Super tasty</td>
                </tr>
                <tr class="mdc-data-table__row">
                    <th class="mdc-data-table__cell" scope="row">Ice cream sandwich</th>
                    <td class="mdc-data-table__cell mdc-data-table__cell--numeric">37</td>
                    <td class="mdc-data-table__cell mdc-data-table__cell--numeric">4.33333333333</td>
                    <td class="mdc-data-table__cell">I like ice cream more</td>
                </tr>
                <tr class="mdc-data-table__row">
                    <th class="mdc-data-table__cell" scope="row">Eclair</th>
                    <td class="mdc-data-table__cell mdc-data-table__cell--numeric">24</td>
                    <td class="mdc-data-table__cell mdc-data-table__cell--numeric">6.0</td>
                    <td class="mdc-data-table__cell">New filing flavor</td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="mdc-data-table__pagination">
            <div class="mdc-data-table__pagination-trailing">
                <div class="mdc-data-table__pagination-rows-per-page">
                    <div class="mdc-data-table__pagination-rows-per-page-label">
                        Rows per page
                    </div>

                    <div class="mdc-select mdc-select--outlined mdc-select--no-label mdc-data-table__pagination-rows-per-page-select mdc-data-table__pagination-rows-per-page-select--outlined">
                        <div class="mdc-select__anchor" role="button" aria-haspopup="listbox"
                             aria-labelledby="demo-pagination-select" tabindex="0">
            <span class="mdc-select__selected-text-container">
              <span id="demo-pagination-select" class="mdc-select__selected-text">10</span>
            </span>
                            <span class="mdc-select__dropdown-icon">
              <svg
                      class="mdc-select__dropdown-icon-graphic"
                      viewBox="7 10 10 5">
                <polygon
                        class="mdc-select__dropdown-icon-inactive"
                        stroke="none"
                        fill-rule="evenodd"
                        points="7 10 12 15 17 10">
                </polygon>
                <polygon
                        class="mdc-select__dropdown-icon-active"
                        stroke="none"
                        fill-rule="evenodd"
                        points="7 15 12 10 17 15">
                </polygon>
              </svg>
            </span>
                            <span class="mdc-notched-outline mdc-notched-outline--notched">
              <span class="mdc-notched-outline__leading"></span>
              <span class="mdc-notched-outline__trailing"></span>
            </span>
                        </div>

                        <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menu-surface--fullwidth" role="listbox">
                            <ul class="mdc-list">
                                <li class="mdc-select__option mdc-select__one-line-option mdc-list-item mdc-list-item--selected mdc-list-item--with-one-line"
                                    aria-selected="true" role="option" data-value="10">
                                    <span class="mdc-list-item__ripple"></span>
                                    <span class="mdc-list-item__content">
                  <span class="mdc-list-item__primary-text">10</span>
                </span>
                                </li>
                                <li class="mdc-select__option mdc-select__one-line-option mdc-list-item mdc-list-item--with-one-line"
                                    role="option" data-value="25">
                                    <span class="mdc-list-item__ripple"></span>
                                    <span class="mdc-list-item__content">
                  <span class="mdc-list-item__primary-text">25</span>
                </span>
                                </li>
                                <li class="mdc-select__option mdc-select__one-line-option mdc-list-item mdc-list-item--with-one-line"
                                    role="option" data-value="100">
                                    <span class="mdc-list-item__ripple"></span>
                                    <span class="mdc-list-item__content">
                  <span class="mdc-list-item__primary-text">100</span>
                </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="mdc-data-table__pagination-navigation">
                    <div class="mdc-data-table__pagination-total">
                        1‑10 of 100
                    </div>
                    <button class="mdc-icon-button material-symbols-outlined mdc-data-table__pagination-button" data-first-page="true" disabled>
                        <div class="mdc-button__icon">first_page</div>
                    </button>
                    <button class="mdc-icon-button material-symbols-outlined mdc-data-table__pagination-button" data-prev-page="true" disabled>
                        <div class="mdc-button__icon">chevron_left</div>
                    </button>
                    <button class="mdc-icon-button material-symbols-outlined mdc-data-table__pagination-button" data-next-page="true">
                        <div class="mdc-button__icon">chevron_right</div>
                    </button>
                    <button class="mdc-icon-button material-symbols-outlined mdc-data-table__pagination-button" data-last-page="true">
                        <div class="mdc-button__icon">last_page</div>
                    </button>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../../res/web/dialog.part.html" %>
<%@ include file="../../res/web/snackbar.part.html" %>
<script type="module" src="../../res/controller/default.controller.js"></script>
<script type="module" src="index.js"></script>
</body>
</html>