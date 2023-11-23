<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../res/controller/material/material-components-web.css"/>
    <link rel="stylesheet" href="../res/styles/background.css">
    <link rel="stylesheet" href="index.css">
</head>
<body>

<body>
<div class="_root_background">
    <div class="circle c1"></div>
</div>
<main class="login__main">
    <form action="#" name="login" method="POST">
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
                    <span class="mdc-typography--headline5">Iniciá sesión</span>
                </div>
                <div class="_body">
                    <div class="flex-row full-width __login-selectRole">
                        <div class="mdc-form-field">
                            <div class="mdc-radio">
                                <input class="mdc-radio__native-control" type="radio" id="radioCliente" checked
                                       name="role"
                                       value="client">
                                <div class="mdc-radio__background">
                                    <div class="mdc-radio__outer-circle"></div>
                                    <div class="mdc-radio__inner-circle"></div>
                                </div>
                                <div class="mdc-radio__ripple"></div>
                            </div>
                            <label for="radioCliente">Cliente</label>
                        </div>
                        <div class="mdc-form-field">
                            <div class="mdc-radio">
                                <input class="mdc-radio__native-control" type="radio" id="radioAdministrador"
                                       name="role"
                                       value="admin">
                                <div class="mdc-radio__background">
                                    <div class="mdc-radio__outer-circle"></div>
                                    <div class="mdc-radio__inner-circle"></div>
                                </div>
                                <div class="mdc-radio__ripple"></div>
                            </div>
                            <label for="radioAdministrador">Administrador</label>
                        </div>
                    </div>
                    <label id="tU" class="mdc-text-field mdc-text-field--outlined">
              <span class="mdc-notched-outline">
                <span class="mdc-notched-outline__leading"></span>
                <span class="mdc-notched-outline__notch">
                  <span class="mdc-floating-label" id="lblUsername">Nombre de usuario</span>
                </span>
                <span class="mdc-notched-outline__trailing"></span>
              </span>
                        <input type="text" pattern="^[a-zA-Z0-9_]{4,20}$" minlength="4" maxlength="20" id="txtUsername"
                               class="mdc-text-field__input" required aria-labelledby="lblUsername">
                    </label>
                    <label id="tP" class="mdc-text-field mdc-text-field--outlined">
              <span class="mdc-notched-outline">
                <span class="mdc-notched-outline__leading"></span>
                <span class="mdc-notched-outline__notch">
                  <span class="mdc-floating-label" id="lblClave">Contraseña</span>
                </span>
                <span class="mdc-notched-outline__trailing"></span>
              </span>
                        <input type="password" required minlength="8" id="txtPassword" class="mdc-text-field__input"
                               aria-labelledby="lblClave">
                    </label>
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnEntrar" class="mdc-button mdc-button--touch mdc-button--raised" type="submit">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Ingresar</span>
                        </button>
                    </div>
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnCrearCuenta" class="mdc-button mdc-button--touch " type="button">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Crear cuenta</span>
                        </button>
                    </div>
                </div>
            </div>
            <div id="tab-ok" class="__login-card--tab">

                <div class="_header">
                    <span class="mdc-typography--headline5" id="successfulLoginSpanText">¡Hola!</span>
                </div>
                <div class="_body">
                    <span class="mdc-typography--body1">En un momento serás redirigido a la página de inicio. </span>
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
<script type="module" src="index.js"></script>
</body>
</html>