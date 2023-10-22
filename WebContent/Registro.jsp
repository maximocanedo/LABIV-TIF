<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Registro.css" rel="stylesheet">
<title>Registro</title>
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <script src="Registro.js"></script>
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>	
</head>
<body style="display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0;">
<div class="mdl-card mdl-shadow--2dp">
        <div class="mdl-card__title">
            <h2 class="mdl-card__title-text">Registro</h2>
        </div>
        <div class="mdl-card__supporting-text">
            <form class="mdl-grid">
        <!-- Correo Electrónico -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--12-col">
            <input class="mdl-textfield__input" type="email" id="email">
            <label class="mdl-textfield__label" for="email">E-Mail</label>
        </div>
        <!-- Contraseña -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="password" id="password">
            <label class="mdl-textfield__label" for="password">Clave</label>
        </div>
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="password" id="Confirm_password">
            <label class="mdl-textfield__label" for="Confirm_password">Confirmar Clave</label>
        </div>
        <!-- Provincia -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <select class="mdl-select__input" id="provincia">
                <option value="" disabled selected>Elija una provincia</option>
                <option value="provincia1">Provincia 1</option>
                <option value="provincia2">Provincia 2</option>
                <!-- Agregar más opciones aquí -->
            </select>
            
        </div>
        <!-- Localidad -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <select class="mdl-select__input" id="localidad">
                <option value="" disabled selected>Elija una localidad</option>
                <option value="localidad1">Localidad 1</option>
                <option value="localidad2">Localidad 2</option>
            </select>
            
        </div>
        <!-- Dirección -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="text" id="direccion">
            <label class="mdl-textfield__label" for="direccion">Direccion</label>
        </div>
       <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
		  <input class="mdl-textfield__input" type="date" id="fechaNacimiento">
		  <label class="mdl-textfield__label" for="fechaNacimiento">Fecha de Nacimiento</label>
		  <span class="mdl-textfield__error">Ingrese una fecha válida</span>
	   </div>
        <!-- Nacionalidad -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="text" id="nacionalidad">
            <label class="mdl-textfield__label" for="nacionalidad">Nacionalidad</label>
        </div>
        <!-- Sexo (desplegable) -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <select class="mdl-select__input" id="sexo">
                <option value="" disabled selected>Elija su sexo</option>
                <option value="masculino">Masculino</option>
                <option value="femenino">Femenino</option>
                <option value="otro">Otro</option>
            </select>
        </div>
        <!-- Apellido -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="text" id="apellido">
            <label class="mdl-textfield__label" for="apellido">Apellido</label>
        </div>
        <!-- Nombre -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="text" id="nombre">
            <label class="mdl-textfield__label" for="nombre">Nombre</label>
        </div>
        <!-- DNI -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="text" id="dni">
            <label class="mdl-textfield__label" for="dni">DNI</label>
        </div>
        <!-- CUIL -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label mdl-cell mdl-cell--6-col">
            <input class="mdl-textfield__input" type="text" id="cuil">
            <label class="mdl-textfield__label" for="cuil">CUIL</label>
        </div>
        <div class="mdl-cell mdl-cell--12-col">
            <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Enviar</button>
        </div>
    </form>
    </div>    
</body>
</html>