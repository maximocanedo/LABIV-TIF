<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./../res/controller/material/material-components-web.css" />
    <link rel="stylesheet" href="./../res/styles/background.css">
    <link rel="stylesheet" href="./../res/styles/signup.css">
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
                <!-- Sección Nombre y Apellido -->
                <div id="tab-nombres" class="__signup-card--tab _visible">
                    <div class="_header">
                        <span class="mdc-typography--headline5">¿Cómo te llamás?</span>
                    </div>
                    <div class="_body">
                        <!-- Nombre -->
                        <label id="mdtxtNombre" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblNombre">Nombre</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                            <input type="text" pattern="^[a-zA-ZáàéèíìïäëöüÿòóùúçÇÁÉÍÓÚÀÈÌÒÙÄËÏÖÜ]{1,48}$" minlength="4"
                                maxlength="20" id="txtNombre" class="mdc-text-field__input" required
                                aria-labelledby="lblNombre">
                        </label>
                        <!-- Apellido -->
                        <label id="mdtxtApellido" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblApellido">Apellido</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                            <input type="text" pattern="^[a-zA-ZáàéèíìïäëöüÿòóùúçÇÁÉÍÓÚÀÈÌÒÙÄËÏÖÜ]{1,48}$" minlength="4"
                                maxlength="20" required id="txtApellido" class="mdc-text-field__input"
                                aria-labelledby="lblApellido">
                        </label>
                    </div>
                    <div class="_footer">
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnNombreApellidoOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Siguiente</span>
                            </button>
                        </div>
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnNombreApellidoBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Iniciar sesión</span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Sección DNI y CUIL -->
                <div id="tab-documentos" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">Ingresá tu DNI y CUIL</span>
                    </div>
                    <div class="_body">
                        <div class="grid-center">
                            <!-- DNI -->
                            <label id="mdtxtDNI" class="mdc-text-field mdc-text-field--outlined">
                                <span class="mdc-notched-outline">
                                    <span class="mdc-notched-outline__leading"></span>
                                    <span class="mdc-notched-outline__notch">
                                        <span class="mdc-floating-label" id="lblDNI">Número de documento</span>
                                    </span>
                                    <span class="mdc-notched-outline__trailing"></span>
                                </span>
                                <input type="number" min="10000000" id="txtDNI" class="mdc-text-field__input"
                                    aria-controls="DNI-helper" aria-describedby="DNI-helper" required
                                    aria-labelledby="lblDNI">
                            </label>
                            <div class="mdc-text-field-helper-line">
                                <div class="mdc-text-field-helper-text" id="DNI-helper" aria-hidden="true"></div>
                            </div>
                        </div>

                        <div class="grid-center">
                            <!-- CUIL -->
                            <label id="mdtxtCUIL" class="mdc-text-field mdc-text-field--outlined">
                                <span class="mdc-notched-outline">
                                    <span class="mdc-notched-outline__leading"></span>
                                    <span class="mdc-notched-outline__notch">
                                        <span class="mdc-floating-label" id="lblCUIL">Número de C.U.I.L.</span>
                                    </span>
                                    <span class="mdc-notched-outline__trailing"></span>
                                </span>
                                <input type="number" aria-controls="CUIL-helper" aria-describedby="CUIL-helper"
                                    min="10000000" required id="txtCUIL" class="mdc-text-field__input"
                                    aria-labelledby="lblCUIL">
                            </label>
                            <div class="mdc-text-field-helper-line">
                                <div class="mdc-text-field-helper-text" id="CUIL-helper" aria-hidden="true"></div>
                            </div>
                        </div>
                    </div>
                    <div class="_footer">
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnDocumentosOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Siguiente</span>
                            </button>
                        </div>
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnDocumentosBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Anterior</span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Sección Sexo -->
                <div id="tab-sexo" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">Ingresá tu género</span>
                    </div>
                    <div class="_body">
                        <!-- Sexo -->
                        <div class="mdc-form-field" id="mdffSexo">
                            <div class="mdc-radio">
                                <input class="mdc-radio__native-control" type="radio" id="radioSexoMasculino" name="sex" value="M"
                                    required>
                                <div class="mdc-radio__background">
                                    <div class="mdc-radio__outer-circle"></div>
                                    <div class="mdc-radio__inner-circle"></div>
                                </div>
                                <div class="mdc-radio__ripple"></div>
                                <div class="mdc-radio__focus-ring"></div>
                            </div>
                            <label for="radioSexoMasculino">Masculino</label>
                            <div class="mdc-radio">
                                <input class="mdc-radio__native-control" type="radio" id="radioSexoFemenino" name="sex" value="F"
                                    required>
                                <div class="mdc-radio__background">
                                    <div class="mdc-radio__outer-circle"></div>
                                    <div class="mdc-radio__inner-circle"></div>
                                </div>
                                <div class="mdc-radio__ripple"></div>
                                <div class="mdc-radio__focus-ring"></div>
                            </div>
                            <label for="radioSexoFemenino">Femenino</label>
                        </div>
                    </div>
                    <div class="_footer">
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnSexOK" class="mdc-button mdc-button--touch mdc-button--raised" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Siguiente</span>
                            </button>
                        </div>
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnSexBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Anterior</span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Sección Fecha de nacimiento -->
                <div id="tab-fecha-nacimiento" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">¿Cuándo naciste?</span>
                    </div>
                    <div class="_body">
                        <!-- Fecha de nacimiento -->
                        <label id="mdtxtFechaNacimiento" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblFechaNacimiento">Fecha de nacimiento</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                            <input type="date" id="txtFechaNacimiento" class="mdc-text-field__input" required
                                aria-labelledby="lblFechaNacimiento">
                        </label>

                    </div>
                    <div class="_footer">
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnBirthdayOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Siguiente</span>
                            </button>
                        </div>
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnBirthdayBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Anterior</span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Sección Dirección -->
                <div id="tab-direccion" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">¿Dónde vivís?</span>
                    </div>
                    <div class="_body">
                        <!-- Dirección -->
                        <label id="mdtxtDireccion" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblDireccion">Dirección</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                            <input type="text" id="txtDireccion" class="mdc-text-field__input" required
                                aria-labelledby="lblDireccion">
                        </label>
                        <!-- Provincia -->
                        <div id="mdSelectProvincia" class="mdc-select mdc-select--outlined">
                            <input type="hidden" name="demo-input">
                            <div class="mdc-select__anchor" aria-labelledby="outlined-select-label">
                                <span class="mdc-notched-outline">
                                    <span class="mdc-notched-outline__leading"></span>
                                    <span class="mdc-notched-outline__notch">
                                        <span id="outlined-select-label" class="mdc-floating-label">Provincia</span>
                                    </span>
                                    <span class="mdc-notched-outline__trailing"></span>
                                </span>
                                <span class="mdc-select__selected-text-container">
                                    <span id="demo-selected-text" class="mdc-select__selected-text"></span>
                                </span>
                                <span class="mdc-select__dropdown-icon">
                                    <svg class="mdc-select__dropdown-icon-graphic" viewBox="7 10 10 5"
                                        focusable="false">
                                        <polygon class="mdc-select__dropdown-icon-inactive" stroke="none"
                                            fill-rule="evenodd" points="7 10 12 15 17 10">
                                        </polygon>
                                        <polygon class="mdc-select__dropdown-icon-active" stroke="none"
                                            fill-rule="evenodd" points="7 15 12 10 17 15">
                                        </polygon>
                                    </svg>
                                </span>
                            </div>
                            <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menu-surface--fullwidth">
                                <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox"></ul>
                            </div>
                        </div>
                        <!-- Localidad -->
                        <div id="mdSelectLocalidad" class="mdc-select mdc-select--outlined">
                            <input type="hidden" name="demo-input">
                            <div class="mdc-select__anchor" aria-labelledby="outlined-select-label">
                                <span class="mdc-notched-outline">
                                    <span class="mdc-notched-outline__leading"></span>
                                    <span class="mdc-notched-outline__notch">
                                        <span id="outlined-select-label" class="mdc-floating-label">Localidad</span>
                                    </span>
                                    <span class="mdc-notched-outline__trailing"></span>
                                </span>
                                <span class="mdc-select__selected-text-container">
                                    <span id="demo-selected-text" class="mdc-select__selected-text"></span>
                                </span>
                                <span class="mdc-select__dropdown-icon">
                                    <svg class="mdc-select__dropdown-icon-graphic" viewBox="7 10 10 5"
                                        focusable="false">
                                        <polygon class="mdc-select__dropdown-icon-inactive" stroke="none"
                                            fill-rule="evenodd" points="7 10 12 15 17 10">
                                        </polygon>
                                        <polygon class="mdc-select__dropdown-icon-active" stroke="none"
                                            fill-rule="evenodd" points="7 15 12 10 17 15">
                                        </polygon>
                                    </svg>
                                </span>
                            </div>
                            <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menu-surface--fullwidth">
                                <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="_footer">
                        <!-- Botón siguiente -->
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnAddressOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Siguiente</span>
                            </button>
                        </div>

                        <div class="mdc-touch-target-wrapper">
                            <button id="btnAddressBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Anterior</span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Sección Nacionalidad -->
                <div id="tab-nationality" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">¿De dónde sos?</span>
                    </div>
                    <div class="_body">
                        <!-- Nacionalidad -->
                        <div id="mdSelectNacionalidad" class="mdc-select mdc-select--outlined">
                            <input type="hidden" name="demo-input">
                            <div class="mdc-select__anchor" aria-labelledby="outlined-select-label">
                                <span class="mdc-notched-outline">
                                    <span class="mdc-notched-outline__leading"></span>
                                    <span class="mdc-notched-outline__notch">
                                        <span id="outlined-select-label" class="mdc-floating-label">Nacionalidad</span>
                                    </span>
                                    <span class="mdc-notched-outline__trailing"></span>
                                </span>
                                <span class="mdc-select__selected-text-container">
                                    <span id="demo-selected-text" class="mdc-select__selected-text"></span>
                                </span>
                                <span class="mdc-select__dropdown-icon">
                                    <svg class="mdc-select__dropdown-icon-graphic" viewBox="7 10 10 5"
                                        focusable="false">
                                        <polygon class="mdc-select__dropdown-icon-inactive" stroke="none"
                                            fill-rule="evenodd" points="7 10 12 15 17 10">
                                        </polygon>
                                        <polygon class="mdc-select__dropdown-icon-active" stroke="none"
                                            fill-rule="evenodd" points="7 15 12 10 17 15">
                                        </polygon>
                                    </svg>
                                </span>
                            </div>
                            <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menu-surface--fullwidth">
                                <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox"></ul>
                            </div>
                        </div>
                    </div>

                    <div class="_footer">
                        <!-- Botón siguiente -->
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnNacionalityOK" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Siguiente</span>
                            </button>
                        </div>

                        <div class="mdc-touch-target-wrapper">
                            <button id="btnNacionalityBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Anterior</span>
                            </button>
                        </div>
                    </div>
                </div>
                <!-- Sección Correo -->
                <div id="tab-mail" class="__signup-card--tab">
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
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnMailBack" class="mdc-button mdc-button--touch" type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Anterior</span>
                            </button>
                        </div>

                    </div>
                </div>
                <!-- Sección Procesando -->
                <div id="tab-processing" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">Procesando información</span>
                    </div>
                    <div class="_body">
                    </div>
                    <!-- Botón siguiente -->
                    <div class="_footer">
                        </div>

                </div>
                <!-- Sección Listo -->
                <div id="tab-done" class="__signup-card--tab">
                    <div class="_header">
                        <span class="mdc-typography--headline5">¡Listo!</span>
                    </div>
                    <div class="_body">
                        <span class="mdc-typography--body2">Usá estas credenciales para iniciar sesión. </span>
                        <!-- Nombre -->
                        <label id="mdtxtUser" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblUser">Nombre de usuario</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                            <input type="text" readonly id="txtUser" class="mdc-text-field__input"
                                aria-labelledby="lblUser">
                        </label>
                        <!-- Apellido -->
                        <label id="mdtxtClave" class="mdc-text-field mdc-text-field--outlined">
                            <span class="mdc-notched-outline">
                                <span class="mdc-notched-outline__leading"></span>
                                <span class="mdc-notched-outline__notch">
                                    <span class="mdc-floating-label" id="lblClave">Contraseña</span>
                                </span>
                                <span class="mdc-notched-outline__trailing"></span>
                            </span>
                            <input type="text" style="font-family: Consolas, monospace;" readonly required id="txtClave" class="mdc-text-field__input"
                                aria-labelledby="lblClave">
                        </label>
                    </div>
                    <div class="_footer">
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnLogin" onclick="window.location = '/clientes/login.html';" class="mdc-button mdc-button--touch mdc-button--raised"
                                type="button">
                                <span class="mdc-button__ripple"></span>
                                <span class="mdc-button__touch"></span>
                                <span class="mdc-button__label">Iniciar sesión</span>
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
                        <span class="mdc-typography--body2" id="errorDetails"> </span>
                        
                    </div>
                    <div class="_footer">
                        <div class="mdc-touch-target-wrapper">
                            <button id="btnLogin2" onclick="window.location = '/clientes/login.html';" class="mdc-button mdc-button--touch mdc-button--raised"
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
    <script type="module" src="./../res/controller/clientes.signup.js"></script>
</body>
</html>