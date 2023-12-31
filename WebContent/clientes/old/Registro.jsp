<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Registro Banco</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="Registro.js"></script>
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>

    <link href="Registro.css" rel="stylesheet">
</head>

<body>
<script>
    cargarProvincias();
</script>
<div class="container">
    <h2 style="color:#1E90FF;font-family:Arial;font-size:30px;" class="center-align">Registro</h2>
    <div class="register-container">
        <div class="row">
            <div class="col s12 m6 offset-m3">

                <form id="formAgregar" method="post">

                    <!-- Correo Electr�nico -->
                    <div class="input-field">
                        <input class="validate" type="email" id="e-mail" name="email" required>
                        <label for="e-mail">E-Mail</label>
                    </div>
                    <!-- Contrase�a -->
                    <div class="input-field">
                        <input class="validate" type="password" id="password" name="password" required>
                        <label for="password">Clave</label>
                    </div>
                    <div class="input-field">
                        <input class="validate" type="password" id="Confirm_password" name="confirmPassword" required>
                        <label for="Confirm_password">Confirmar Clave</label>
                    </div>
                    <!-- Direccion -->
                    <div class="input-field">
                        <input class="validate" type="text" id="direccion" name="direccion" required>
                        <label for="direccion">Direccion</label>
                    </div>
                    <div class="input-field">
                        <input type="text" class="datepicker" id="fechaNacimiento" name="fechaNacimiento" required>
                        <label for="fechaNacimiento">Fecha de Nacimiento</label>
                    </div>
                    <!-- Provincia -->
                    <div class="input-field">
                        <select id="provincia" name="provincia" required>

                        </select>
                        <label for="provincia">Provincia</label>
                    </div>
                    <!-- Localidad -->
                    <div class="input-field">
                        <select id="localidad" name="Localidad" required>
                            <option value="" disabled selected>Elija una localidad</option>
                            <option value="localidad1">Localidad 1</option>
                            <option value="localidad2">Localidad 2</option>
                            <!-- Agregar m�s opciones de localidades aqu� -->
                        </select>
                        <label for="localidad">Localidad</label>
                    </div>
                    <!-- Sexo  -->
                    <div class="input-field">
                        <select id="sexo" name="sexo" required>
                            <option value="" disabled selected>Elija su sexo</option>
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femenino</option>
                            <option value="otro">Otro</option>
                        </select>
                        <label for="sexo">Sexo</label>
                    </div>
                    <!-- Apellido -->
                    <div class="input-field">
                        <input class="validate" type="text" id="apellido" name="apellido" required>
                        <label for="apellido">Apellido</label>
                    </div>
                    <!-- Nombre -->
                    <div class="input-field">
                        <input class="validate" type="text" id="nombre" name="nombre" required>
                        <label for="nombre">Nombre</label>
                    </div>
                    <!-- DNI -->
                    <div class="input-field">
                        <input class="validate" type="text" id="dni" name="dni" required>
                        <label for="dni">DNI</label>
                    </div>
                    <!-- CUIL -->
                    <div class="input-field">
                        <input class="validate" type="text" id="cuil" name="cuil" required>
                        <label for="cuil">CUIL</label>
                    </div>
                    <div class="center-align">
                        <button class="btn waves-effect waves-light" type="submit">Enviar</button>
                    </div>
                    <br/>
                    <div class="center-align">
                        <a href="inicioSesion.jsp">Ya tengo cuenta</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('.datepicker');
        M.Datepicker.init(elems, {});
    });
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var provinciaSelect = document.getElementById('provincia');
        M.FormSelect.init(provinciaSelect);

        var localidadSelect = document.getElementById('localidad');
        M.FormSelect.init(localidadSelect);

        var sexoSelect = document.getElementById('sexo');
        M.FormSelect.init(sexoSelect);
    });
</script>
</body>
</html>