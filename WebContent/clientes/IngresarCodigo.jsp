<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Verificacion | Codigo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./../res/styles/estiloPaginaCliente-Administrador.css">
    <link rel="stylesheet" type="text/css" href="./../res/styles/estiloDatosCliente.css">
    <link rel="stylesheet" href="./../res/controller/material/material-components-web.css"/>
    <link rel="stylesheet" href="./../res/styles/background.css">
    <link rel="stylesheet" href="../login/index.css">
    <link rel="stylesheet" type="text/css" href="./../res/styles/estiloPaginaCliente-Administrador.css">
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
                <a href="#" class="dropdown-trigger" data-target="id_drop">Préstamos
                    <i class="material-symbols-outlined right">arrow_drop_down</i></a>
                <ul id="id_drop" class="dropdown-content">
                    <li><a href="#">Solicitar préstamo</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Préstamos otorgados</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Pago de préstamo</a></li>
                    <li class="divider"></li>
                </ul>
            </li>
            <li><a href="datospersonalesCliente.jsp">Registro personal</a></li>
            <li><a id="clienteBanco">Bienvenido, Mario Bustamante</a></li>
            <li>
                <a class="waves-effect waves-light btn" id="logout-button">
                    <i class="material-symbols-outlined left">exit_to_app</i>Cerrar Sesión</a>
            </li>
        </ul>
    </div>
</nav>

<div class="transparent-bg"></div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
<div class="_root_background">
    <div class="circle c1"></div>
</div>
<main class="login__main">
    <form action="/TPINT_GRUPO_3_LAB/api/client/validateMail" name="verificationCode" method="GET">
        <div class="mdc-card __login-card">
            <div role="progressbar" id="progressbar" class="mdc-linear-progress mdc-linear-progress--closed"
                 aria-label="Example Progress Bar" aria-valuemin="0" aria-valuemax="1" aria-valuenow="0">
                <div class="mdc-linear-progress__buffer">
                    <div class="mdc-linear-progress__buffer-bar"></div>
                    <div class="mdc-linear-progress__buffer-dots"></div>
                </div>
                <div class="mdc-linear-progress__bar mdc-linear-progress__primary-bar">
                    <span class="mdc-linear-progress__bar-inner"></span>
                </div>
                <div class="mdc-linear-progress__bar mdc-linear-progress__secondary-bar">
                    <span class="mdc-linear-progress__bar-inner"></span>
                </div>
            </div>
            <div id="tab-login" class="__login-card--tab _visible">
                <div class="_header">
                    <span class="mdc-typography--headline5">Ingresa el codigo que enviamos a tu correo</span>
                </div>
                <div class="_body">

                    <label id="tU" class="mdc-text-field mdc-text-field--outlined">
              <span class="mdc-notched-outline">
                <span class="mdc-notched-outline__leading"></span>
                <span class="mdc-notched-outline__notch">
                  <span class="mdc-floating-label" id="lblUsername">Codigo</span>
                </span>
                <span class="mdc-notched-outline__trailing"></span>
              </span>
                        <input type="text" id="code" name="code" class="mdc-text-field__input" required
                               aria-labelledby="lblUsername">
                    </label>

                    <div class="mdc-touch-target-wrapper">
                        <button name="btnEntrar" id="btnEntrar" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="submit">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Validar</span>
                        </button>
                    </div>
                </div>
            </div>
            <div id="tab-ok" class="__login-card--tab">

                <div class="_header">
                    <span class="mdc-typography--headline5" id="successfulLoginSpanText">¡Correo verificado!</span>
                </div>
                <div class="_body">
                    <span class="mdc-typography--body1">En un momento seras redirigido a la pagina de inicio. </span>
                    <div class="mdc-touch-target-wrapper">

                    </div>
                </div>
            </div>
        </div>
    </form>
</main>

<aside class="mdc-snackbar">
    <div class="mdc-snackbar__surface" role="status" aria-relevant="additions">
        <div class="mdc-snackbar__label" aria-atomic="false"></div>
    </div>
</aside>
<script type="module" src="./../res/controller/IngresarCodigo.js"></script>

<script>
    $(document).ready(function () {
        $(".dropdown-trigger").dropdown();
    });
</script>
</html>