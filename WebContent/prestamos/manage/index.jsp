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
        <table class="mdc-data-table__table" aria-label="Dessert calories">
            <thead>
            <tr class="mdc-data-table__header-row">
                <th
                        class="mdc-data-table__header-cell mdc-data-table__header-cell--with-sort"
                        role="columnheader"
                        scope="col"
                        aria-sort="none"
                        data-column-id="dessert"
                >
                    <div class="mdc-data-table__header-cell-wrapper">
                        <div class="mdc-data-table__header-cell-label">
                            Dessert
                        </div>
                        <button class="mdc-icon-button material-symbols-outlined mdc-data-table__sort-icon-button"
                                aria-label="Sort by dessert" aria-describedby="dessert-status-label">arrow_upward</button>
                        <div class="mdc-data-table__sort-status-label" aria-hidden="true" id="dessert-status-label">
                        </div>
                    </div>
                </th>
                <th
                        class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric mdc-data-table__header-cell--with-sort mdc-data-table__header-cell--sorted"
                        role="columnheader"
                        scope="col"
                        aria-sort="ascending"
                        data-column-id="carbs"
                >
                    <div class="mdc-data-table__header-cell-wrapper">
                        <button class="mdc-icon-button material-symbols-outlined mdc-data-table__sort-icon-button"
                                aria-label="Sort by carbs" aria-describedby="carbs-status-label">arrow_upward</button>
                        <div class="mdc-data-table__header-cell-label">
                            Carbs (g)
                        </div>
                        <div class="mdc-data-table__sort-status-label" aria-hidden="true" id="carbs-status-label"></div>
                    </div>
                </th>
                <th
                        class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric mdc-data-table__header-cell--with-sort"
                        role="columnheader"
                        scope="col"
                        aria-sort="none"
                        data-column-id="protein"
                >
                    <div class="mdc-data-table__header-cell-wrapper">
                        <button class="mdc-icon-button material-symbols-outlined mdc-data-table__sort-icon-button"
                                aria-label="Sort by protein" aria-describedby="protein-status-label">arrow_upward</button>
                        <div class="mdc-data-table__header-cell-label">
                            Protein (g)
                        </div>
                        <div class="mdc-data-table__sort-status-label" aria-hidden="true" id="protein-status-label"></div>
                    </div>
                </th>
                <th
                        class="mdc-data-table__header-cell"
                        role="columnheader"
                        scope="col"
                        data-column-id="comments"
                >
                    Comments
                </th>
            </tr>
            </thead>
            <tbody class="mdc-data-table__content">
            <tr class="mdc-data-table__row">
                <td class="mdc-data-table__cell">Frozen yogurt</td>
                <td class="mdc-data-table__cell mdc-data-table__cell--numeric">
                    24
                </td>
                <td class="mdc-data-table__cell mdc-data-table__cell--numeric">
                    4.0
                </td>
                <td class="mdc-data-table__cell">Super tasty</td>
            </tr>
            </tbody>
        </table>
    </div>
    <br><br><br>
</main>
<%@ include file="../../res/web/dialog.part.html" %>
<%@ include file="../../res/web/snackbar.part.html" %>
<script type="module" src="../../res/controller/default.controller.js"></script>
<script type="module" src="index.js"></script>
</body>
</html>