<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inicio · Clientes</title>
    <link rel="stylesheet" href="../../res/styles/init.css"/>
    <link rel="stylesheet" href="index.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/redux/4.0.5/redux.js"></script>
</head>
<body class="mdc-typography">
<%@ include file="../../res/web/drawer.part.html" %>
<%@ include file="../../res/web/header.part.html" %>

<main class="mdc-top-app-bar--fixed-adjust">
    <%@ include file="../../res/web/banner.part.html" %>
    <div id="principal-grid" class="mdc-layout-grid mdc-layout-grid__cell--align-middle mdc-layout-grid-margin-12">
        <div class="mdc-layout-grid__inner" id="mainGrid">

            <span class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle6 header-aligned">
                <span class="title">Mis cuentas</span>
                <div class="space"></div>
                <div class="mdc-touch-target-wrapper">
                  <button class="mdc-icon-button" id="crearCuentaBtn">
                    <div class="mdc-icon-button__ripple"></div>
                    <span class="mdc-icon-button__focus-ring"></span>
                    <i class="material-symbols-outlined">add</i>
                    <div class="mdc-icon-button__touch"></div>
                  </button>
                </div>
                <div class="mdc-touch-target-wrapper">
                  <button class="mdc-icon-button" id="refreshAccountSectionBtn">
                    <div class="mdc-icon-button__ripple"></div>
                    <span class="mdc-icon-button__focus-ring"></span>
                    <i class="material-symbols-outlined">refresh</i>
                    <div class="mdc-icon-button__touch"></div>
                  </button>
                </div>
                <button id="verSaldoBtn"
                        class="mdc-icon-button"
                        aria-label="Ver saldo"
                        aria-pressed="false">
                   <div class="mdc-icon-button__ripple"></div>
                   <span class="mdc-icon-button__focus-ring"></span>
                   <i class="material-symbols-outlined mdc-icon-button__icon">visibility</i>
                   <i class="material-symbols-outlined mdc-icon-button__icon mdc-icon-button__icon--on">visibility_off</i>
                </button>
            </span>
            <div class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-layout-grid" style="padding: 0px">
                <div class="mdc-layout-grid__inner" id="accountsGrid">

                </div>
            </div>
            <span class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle6 header-aligned">
                <span class="title">Mis solicitudes de préstamo</span>
                <div class="space"></div>
                <div class="mdc-touch-target-wrapper">
                  <button class="mdc-icon-button" id="refreshLoanRequestsSectionBtn">
                    <div class="mdc-icon-button__ripple"></div>
                    <span class="mdc-icon-button__focus-ring"></span>
                    <i class="material-symbols-outlined">refresh</i>
                    <div class="mdc-icon-button__touch"></div>
                  </button>
                </div>
            </span>
            <div id="myLoanRequests" class="mdc-data-table mdc-layout-grid__cell--span-12">
            </div>
            <span class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle6"><br/></span>
            <span class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle6"><br/></span>
        </div>
    </div>
    <button class="mdc-fab mdc-fab--extended _fab"
            onclick="window.location = '../../prestamos/request/';">
        <div class="mdc-fab__ripple"></div>
        <span class="material-symbols-outlined mdc-fab__icon">add</span>
        <span class="mdc-fab__label">Pedir un préstamo</span>
    </button>
</main>
<form class="mdc-dialog mdc-dialog__open" name="crearCuentaBancaria" id="crearCuentaBancaria">
    <div class="mdc-dialog__container">
        <div class="mdc-dialog__surface" role="alertdialog" aria-modal="true" aria-labelledby="my-dialog-title"
             aria-describedby="my-dialog-content" tabindex="-1">
            <div class="mdc-dialog__content">
                <span class="mdc-typography--headline6">Crear una nueva cuenta</span>
                <div class="select-container">
                    <ul class="mdc-deprecated-list" id="tipoCuentaSelect" role="radiogroup"></ul>
                </div>
            </div>
            <div class="mdc-dialog__actions">
                <button type="button" class="mdc-button mdc-dialog__button" data-mdc-dialog-action="cancel">
                    <div class="mdc-button__ripple"></div>
                    <span class="mdc-button__label">Cancelar</span>
                </button>
                <button type="button" id="crearCuentaBtn" class="mdc-button mdc-dialog__button"
                        data-mdc-dialog-action="discard">
                    <div class="mdc-button__ripple"></div>
                    <span class="mdc-button__label">Crear</span>
                </button>
            </div>
        </div>
    </div>
    <div class="mdc-dialog__scrim"></div>
</form>
<%@ include file="../../res/web/dialog.part.html" %>
<%@ include file="../../res/web/snackbar.part.html" %>
<script type="module" src="../../res/controller/default.controller.js"></script>
<script type="module" src="./index.js"></script>
</body>
</html>