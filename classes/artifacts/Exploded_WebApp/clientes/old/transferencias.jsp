<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@page import="entity.Administrador" %>
<%@page import="entity.Cliente" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Transferencias</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="estiloPaginaCliente-Administrador.css">
    <link rel="stylesheet" type="text/css" href="estiloDatosCliente.css">
    <link rel="stylesheet" type="text/css" href="estiloTablaTransferencia.css">
    <link rel="stylesheet" type="text/css" href="estilocuentaCliente.css">

</head>
<body class="mdc-typography">

<nav class="indigo darken-2">
    <div class="nav-wrapper">
        <a class="brand-logo" href="#"><img
                src="https://cadastro.iqnear.com.br/company_logos/banco-empresa-teste-1618528973-992.png"
                style="width:65px;"/></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="/TPINT_GRUPO_3_LAB/clientes/cuentasCliente.jsp">Cuentas</a></li>
            <li><a href="/TPINT_GRUPO_3_LAB/clientes/transferencias.jsp">Transferencias</a></li>
            <li><a href="/TPINT_GRUPO_3_LAB/MovimientosRealizados.jsp">Movimientos realizados</a></li>
            <li>
                <a href="#" class="dropdown-trigger" data-target="id_drop">Prestamos
                    <i class="material-symbols-outlined right">arrow_drop_down</i></a>
                <ul id="id_drop" class="dropdown-content">
                    <li><a href="/TPINT_GRUPO_3_LAB/prestamos/solicitarPrestamo.jsp">Solicitar prestamo</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Prestamos otorgados</a></li>
                    <li class="divider"></li>
                    <li><a href="/TPINT_GRUPO_3_LAB/prestamos/pagoPrestamo.jsp">Pago de prestamo</a></li>
                    <li class="divider"></li>
                </ul>
            </li>
            <li><a href="datospersonalesCliente.jsp">Registro personal</a></li>
            <li><a id="clienteBanco">Bienvenido,

                <%
                    String nombre = "";
                    if ((Administrador) session.getAttribute("admin") != null) {
                        Administrador admin = (Administrador) session.getAttribute("admin");
                        nombre = admin.getNombre();
                    } else if ((Cliente) session.getAttribute("cliente") != null) {
                        Cliente client = (Cliente) session.getAttribute("cliente");
                        nombre = client.getNombre();
                    }

                %>
                <%=nombre %>
            </a></li>
            <li>
                <a class="waves-effect waves-light btn" id="logout-button" href="inicioSesion.jsp">
                    <i class="material-symbols-outlined left">exit_to_app</i>Cerrar Sesion</a>
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
                    <span class="card-title">Realizar transferencia</span>
                    <!-- Combobox para seleccionar la cuenta -->
                    <div class="input-field">
                        <select id="cuenta" required>
                            <option value="" disabled selected>Seleccione una cuenta</option>
                            <option value="cuenta1">Cuenta 1</option>
                            <option value="cuenta2">Cuenta 2</option>
                            <option value="cuenta3">Cuenta 3</option>
                        </select>
                        <label for="prestamo">Cuenta a descontar monto</label>
                    </div>
                    <div>
                        <label for="CBUDestino">Ingrese el CBU del destinatario: </label>
                        <input type="text" id="CBUdestino" name="CBUdestino">
                    </div>
                    <div>
                        <label for="monto">Monto: </label>
                    </div>
                    <div class="input-field">
                        <input class="validate" type="number" step="0.01" pattern="\d+(\.\d{2})?" title="Formato: XX.XX"
                               required>
                    </div>
                    <div>
                        <a class="waves-effect waves-light btn" href="#">Transferir</a>
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
                <h5 class="white-text">Pie de pagina Banco</h5>
                <p class="grey-text text-lighten-4">Informacion relativa al banco</p>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            &copy 2023 Copyright - Todos los derechos reservados para el Grupo Nro 3
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