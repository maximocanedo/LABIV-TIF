<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Clientes · Administradores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/styles/init.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/styles/listarClientes.css"/>
</head>
<body class="mdc-typography">
<%@ include file="../res/web/drawer.part.html" %>
<%@ include file="../res/web/header.part.html" %>
<main class="mdc-top-app-bar--fixed-adjust ">
    <%@ include file="../res/web/banner.part.html" %>
            <div class="navBusqueda" style="overflow-x: hidden">

                <form action="#" onsubmit="return false;" name="searchForm">
                    <div class="principal_search_card">
                        <input type="search" class="pcSearch" name="q" placeholder="Buscá por nombre, apellido, DNI, CUIL, etc." aria-label="Label" />
                        <span id="filterDetails"></span>
                        <div class="btns">
                            <button id="mostrarFiltrosBtn" name="filtrarBtn" class="mdc-icon-button material-symbols-outlined">
                                <div class="mdc-icon-button__ripple"></div>
                                filter_alt
                            </button>
                            <button id="eee" name="searchBtn"  class="mdc-button mdc-button--raised mdc-button--leading">
                                <span class="mdc-button__ripple"></span>
                                <i class="material-symbols-outlined mdc-button__icon" aria-hidden="true">search</i>
                                <span class="mdc-button__label">Buscar</span>
                            </button>
                        </div>
                        <div id="selects" class="hidden"></div>
                    </div>
                </form>
                <div class="client-list-container">

                </div>

            </div>
            <div class="navCliente" id="clientDetailViewContainer">
            </div>
        </div>
    </div>

</main>
<%@ include file="../res/web/dialog.part.html" %>
<%@ include file="../res/web/snackbar.part.html" %>
<script type="module" src="../res/controller/default.controller.js"></script>
<script type="module" src="../res/controller/admin.listarClientes.js"></script>
</body>
</html>