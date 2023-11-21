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
<main class="mdc-top-app-bar--fixed-adjust mdc-layout-grid">
    <div class="mdc-layout-grid__inner">
        <div class="mdc-layout-grid__cell mdc-layout-grid__cell--span-4" style="overflow-x: hidden">

            <form action="#" onsubmit="return false;" name="searchForm">
                <div class="mdc-card principal_search_card">
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
                    <div id="selects"></div>
                </div>
            </form>
            <div class="client-list-container"></div>

        </div>
        <div class="mdc-layout-grid__cell mdc-layout-grid__cell--span-8">
<span class="mdc-deprecated-chip" role="row" id="c2">
  <span class="mdc-deprecated-chip__cell mdc-deprecated-chip__cell--primary" role="gridcell">
    <button class="mdc-deprecated-chip__action mdc-deprecated-chip__action--primary" type="button" tabindex="0">
      <span class="mdc-deprecated-chip__ripple mdc-deprecated-chip__ripple--primary"></span>
      <span class="mdc-deprecated-chip__text-label">Chip foo</span>
    </button>
  </span>
  <span class="mdc-deprecated-chip__cell mdc-deprecated-chip__cell--trailing" role="gridcell">
    <button class="mdc-deprecated-chip__action mdc-deprecated-chip__action--trailing" type="button" tabindex="-1" data-mdc-deletable="true" aria-label="Remove chip foo">
      <span class="mdc-deprecated-chip__ripple mdc-deprecated-chip__ripple--trailing"></span>
      <span class="mdc-deprecated-chip__icon mdc-deprecated-chip__icon--trailing">close</span>
    </button>
  </span>
</span>
        </div>
    </div>
</main>
<%@ include file="../res/web/dialog.part.html" %>
<%@ include file="../res/web/snackbar.part.html" %>
<script type="module" src="../res/controller/admin.listarClientes.js"></script>
</body>
</html>