<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../res/styles/init.css"/>
    <link rel="stylesheet" type="text/css" href="../res/styles/estiloPaginaCliente-Administrador.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/redux/4.0.5/redux.js"></script>

</head>
<body>
<%@ include file="../res/web/drawer.part.html" %>
<%@ include file="../res/web/header.part.html" %>
<main class="mdc-top-app-bar--fixed-adjust">
    Préstamos
    <br><br><br>
    <ul id="prestamosList">

    </ul>
    <input type="number" name="size" id="size" value="5" placeholder="Elementos por página">
    <button id="prevBtn">Anterior</button>
    <span id="textoPaginacion"></span>
    <button id="nextBtn">Siguiente</button>
</main>
<%@ include file="../res/web/dialog.part.html" %>
<%@ include file="../res/web/snackbar.part.html" %>
<script type="module" src="../res/controller/default.controller.js"></script>
<script type="module" src="../res/controller/admin.listarprestamos.js"></script>
</body>
</html>