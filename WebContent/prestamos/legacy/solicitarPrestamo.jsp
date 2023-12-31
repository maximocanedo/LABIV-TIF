<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="estiloPaginaCliente-Administrador.css">
    <link rel="stylesheet" type="text/css" href="estiloDatosCliente.css">

</head>
<body>
<nav class="indigo darken-2">
    <div class="nav-wrapper">
        <a class="brand-logo" href="#"><img
                src="https://cadastro.iqnear.com.br/company_logos/banco-empresa-teste-1618528973-992.png"
                style="width:65px;"/></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="#">Cuentas</a></li>
            <li><a href="#">Transferencias</a></li>
            <li><a href="#">Movimientos realizados</a></li>
            <li>
                <a href="#" class="dropdown-trigger" data-target="id_drop">Pr�stamos
                    <i class="material-symbols-outlined right">arrow_drop_down</i></a>
                <ul id="id_drop" class="dropdown-content">
                    <li><a href="#">Solicitar pr�stamo</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Pr�stamos otorgados</a></li>
                    <li class="divider"></li>
                    <li><a href="pagoPrestamo.jsp">Pago de pr�stamo</a></li>
                    <li class="divider"></li>
                </ul>
            </li>
            <li><a href="datospersonalesCliente.jsp">Registro personal</a></li>
            <li><a id="clienteBanco">Bienvenido, Mario Bustamante</a></li>
            <li>
                <a class="waves-effect waves-light btn" id="logout-button">
                    <i class="material-symbols-outlined left">exit_to_app</i>Cerrar Sesi�n</a>
            </li>
        </ul>
    </div>
</nav>

<div class="transparent-bg"></div>

<div class="center-align-container">
    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"> Solicitud de prestamo</span>
                    <p><strong>Nombre:</strong> Daniel Rodr�guez</p>
                    <p><strong>DNI:</strong> 30457881</p>
                    <p><strong>CUIL:</strong> 20-30457881-5</p>
                    <p><strong>Direcci�n:</strong> av. Entre R�os Piso:2 Dep:A</p>
                    <p><strong>Telefono:</strong> +54 3489 791496</p>
                    <p><strong>Email:</strong> Daniel@gmail.com</p>

                    <span class="card-title">Informaci�n de Pr�stamo</span>
                    <div class="input-field">
                        <input id="monto" type="text">
                        <label for="monto">Monto del Pr�stamo</label>
                    </div>
                    <div class="input-field">
                        <input id="plazo" type="text">
                        <label for="plazo">Plazo (en meses)</label>
                    </div>
                    <!-- Combobox para seleccionar la cuenta -->
                    <div class="input-field">
                        <select id="cuenta" required>
                            <option value="" disabled selected>Selecciona una cuenta</option>
                            <option value="cuenta1">Cuenta 1</option>
                            <option value="cuenta2">Cuenta 2</option>
                        </select>
                        <label for="cuenta">Cuenta a depositar el prestamo</label>
                    </div>
                    <div>
                        <a class="waves-effect waves-light btn" href="#">Solicitar Pr�stamo</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
<footer class="page-footer indigo darken-2">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Pie de p�gina Banco</h5>
                <p class="grey-text text-lighten-4">Informaci�n relativa al banco</p>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            � 2023 Copyright - Todos los derechos reservados para el Grupo N� 3
        </div>
    </div>
</footer>

<script>
    $(document).ready(function () {
        $(".dropdown-trigger").dropdown();
    });
</script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var elems = document.querySelectorAll('select');
        var instances = M.FormSelect.init(elems);
    });
</script>

</html>