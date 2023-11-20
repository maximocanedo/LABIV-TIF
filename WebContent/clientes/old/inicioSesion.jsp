<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Inicio Sesión Banco</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" type="text/css" href="estiloLogin.css">
</head>
<body>

<div class="container">
    <h2 style="color:#1E90FF;font-family:Arial;font-size:30px;" class="center-align">¡Bienvenido a tu banco!</h2>

    <div class="login-container">
        <div class="row">
            <div class="col s12 m6 offset-m3">
                <p class="radio">
                    <label style="color: #000;">
                        <input name="user-type" type="radio" value="cliente"/>
                        <span>Cliente</span>
                    </label>
                </p>
                <p class="radio">
                    <label style="color: #000;">
                        <input name="user-type" type="radio" value="administrador"/>
                        <span>Administrador</span>
                    </label>
                </p>
            </div>
        </div>

        <div class="row">
            <div class="col s12 m6 offset-m3">
                <form method="post" action="Servlet">
                    <div class="input-field">
                        <input name="inputUsuario" id="username" type="text" class="validate" required>
                        <label for="username">Nombre de usuario</label>
                    </div>
                    <div class="input-field">
                        <input name="inputClave" id="password" type="password" class="validate" required>
                        <label for="password">Contraseña</label>
                    </div>
                    <div class="center-align">
                        <button class="btn waves-effect waves-light" type="submit">Iniciar Sesión</button>
                    </div>
                    <div class="center-align">
                        <a href="Registro.jsp">No tengo cuenta</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>