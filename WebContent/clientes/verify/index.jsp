<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../res/controller/material/material-components-web.css"/>
    <link rel="stylesheet" href="../../res/styles/background.css">
    <link rel="stylesheet" href="../../res/styles/signup.css">
</head>

<body>
<div class="_root_background">
    <div class="circle c1"></div>
</div>
<main class="signup__main">
    <form action="#" name="signup" method="POST">
        <div class="mdc-card __signup-card">
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

            <!-- Sección Correo -->
            <div id="tab-mail" class="__signup-card--tab _visible">
                <div class="_header">
                    <span class="mdc-typography--headline5">¿Cuál es tu correo?</span>
                </div>
                <div class="_body">
                    <!-- Correo electrónico -->
                    <label id="mdtxtMail" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblMail">Correo electrónico</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                        <input type="text" id="txtMail" class="mdc-text-field__input" required
                               aria-labelledby="lblMail">
                    </label>
                </div>
                <!-- Botón siguiente -->
                <div class="_footer">
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnMailOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Siguiente</span>
                        </button>
                    </div>

                </div>
            </div>
            <!-- Sección Ingresá el código que te enviamos -->
            <div id="tab-codigo" class="__signup-card--tab ">
                <div class="_header">
                    <span class="mdc-typography--headline5">Ingresá el código que te enviamos</span>
                </div>
                <div class="_body">
                    <!-- Código -->
                    <label id="mdtxtNombre" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblCodigo">Código</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                        <input type="text" pattern="\d{6}" id="txtCodigo" class="mdc-text-field__input" required
                               aria-labelledby="lblCodigo">
                    </label>
                </div>
                <div class="_footer">
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnCodigoOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Siguiente</span>
                        </button>
                    </div>
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnCodigoBack" class="mdc-button mdc-button--touch" type="button">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Cambiar correo</span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Sección Listo -->
            <div id="tab-done" class="__signup-card--tab">
                <div class="_header">
                    <span class="mdc-typography--headline5">¡Listo!</span>
                </div>
                <div class="_body">
                    <span class="mdc-typography--body2">¡Ya podés disfrutar de toda la funcionalidad de TIF Bank HOMEBANKING!</span>

                </div>
                <div class="_footer">
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnLogin" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Volver a inicio</span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- Sección Error -->
            <div id="tab-error" class="__signup-card--tab">
                <div class="_header">
                    <span class="mdc-typography--headline5">Error</span>
                </div>
                <div class="_body">
                    <span class="mdc-typography--body2" id="errorDetails"></span>
                </div>
                <div class="_footer">
                    <div class="mdc-touch-target-wrapper">
                        <button id="btnLogin2" onclick="window.location = '/clientes/login.html';"
                                class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                            <span class="mdc-button__ripple"></span>
                            <span class="mdc-button__touch"></span>
                            <span class="mdc-button__label">Iniciar sesión</span>
                        </button>
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