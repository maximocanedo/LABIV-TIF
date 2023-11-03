<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio · Clientes</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/styles/init.css" />
</head>
<body class="mdc-typography">
	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>
	<main class="mdc-top-app-bar--fixed-adjust">
		<br />
		<br />
		<a href="/Cuentas">Mis cuentas</a>
		<a href="/MiPerfil">Mi Perfil</a>
		<a href="/MisMovimientos">Historial de movimientos</a>
		<br />
		<br />

    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
	<script type="module" src="${pageContext.request.contextPath}/res/controller/clientes.inicio.js"></script>
</body>
</html>