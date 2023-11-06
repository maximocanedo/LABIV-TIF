<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clientes · Administradores</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/styles/init.css" />
</head>
<body class="mdc-typography">
	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>
	<main class="mdc-top-app-bar--fixed-adjust">
    <h3>Clientes</h3>
        <form action="#" onsubmit="return false;" name="searchForm">
            <input type="search" name="q" placeholder="Buscá por nombre, apellido, DNI, CUIL, etc." />
            <hr>
            <label for="showInactiveRadio">
                
            <input type="checkbox" name="showInactive" id="showInactiveRadio" />
            Mostrar usuarios deshabilitados
            </label>
            <br>
            <select name="sexo" id="sexo">
                <option value="A" selected>Todos los géneros</option>
                <option value="F">Femenino</option>
                <option value="M">Masculino</option>
            </select>
            <br>
            <select name="provincias" id="provincias">
                <option selected value="-1">Filtrar por provincia</option>
            </select>
            <select name="localidades" id="localidades">
                <option selected value="-1">Filtrar por localidad</option>
            </select>
            <select name="pais" id="pais">
                <option selected value="-1">Filtrar por nacionalidad</option>
            </select>
            <br>


            <br>

            <button type="button" id="eee" name="searchBtn">Buscar</button>
            <br>
        </form>
       <ul id="listaClientes">

       </ul>
    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
	<script type="module" src="../res/controller/admin.listarClientes.js"></script>
</body>
</html>