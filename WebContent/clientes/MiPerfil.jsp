<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio · Clientes</title>
<link rel="stylesheet" href="../res/styles/init.css" />
</head>
<body class="mdc-typography">
	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>
	<main class="mdc-top-app-bar--fixed-adjust">
	
    <h1 id="name"></h1><br>
	<form action="#" name="me" onsubmit="return false;">
		<input type="text" readonly placeholder="Nombre de usuario" name="usuario" />
		<br>
		<input type="text" name="nombre" required minlength="4" placeholder="Nombre" />
		<br>
		<input type="text" name="apellido" required minlength="4" placeholder="Apellido" />
		<br>
		<label>
			<input type="radio" name="sexo" required value="M" />
			Masculino
		</label>
		<label>
			<input type="radio" name="sexo" required value="F" />
			Femenino
		</label>
		<br>
		<input type="text" readonly placeholder="DNI" name="dni" />
		<br>
		<input type="text" readonly placeholder="CUIL" name="cuil" />
		<br>
		
		<br>
		<select name="provincias" id="provincias">
			<option selected disabled value="-1">Provincia</option>
		</select>
		<br>
		<select name="localidades" id="localidades">
			<option selected disabled value="-1">Localidad</option>
		</select>
		<br>
		<select name="pais" id="pais" >
			<option selected disabled value="-1">Nacionalidad</option>
		</select>

		<br>
		<input type="date" name="fechaNacimiento" placeholder="Fecha de nacimiento" required />
		<br>
		<input type="text" name="direccion" placeholder="Dirección personal." minlength="4" required>
		<br>
		<br>

		<button type="button" name="btnEnviar">Modificar datos</button>
	</form>

    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
	<script type="module" src="../res/controller/clientes.miperfil.js"></script>
</body>
</html>