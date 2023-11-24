<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../../res/styles/init.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="../../res/styles/estiloPaginaCliente-Administrador.css">

</head>
<body>
<%@ include file="../../res/web/drawer.part.html" %>
<%@ include file="../../res/web/header.part.html" %>
<main class="mdc-top-app-bar--fixed-adjust center-horizontally">
    <h3 class="mdc-typography--headline4 text-centered">Solicitudes pendientes</h3>
</main>
<%@ include file="../../res/web/dialog.part.html" %>
<%@ include file="../../res/web/snackbar.part.html" %>
<script type="module" src="../../res/controller/default.controller.js"></script>
<script type="module" src="index.js"></script>
</body>
</html>