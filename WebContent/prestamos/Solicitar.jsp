<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../res/styles/init.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/redux/4.0.5/redux.js"></script>
    <link rel="stylesheet" type="text/css" href="../res/styles/prestamos.solicitar.css">

</head>
<body>
<%@ include file="../res/web/drawer.part.html" %>
<%@ include file="../res/web/header.part.html" %>
<main class="mdc-top-app-bar--fixed-adjust">
    <form action="#" onsubmit="return false; " name="solicitud">
        <span class="mdc-typography--headline5">Solicitar un préstamo</span>
        <br><br>
        <div id="cuentaSelect" class="mdc-select mdc-select--filled demo-width-class">
            <div class="mdc-select__anchor"
                 role="button"
                 aria-haspopup="listbox"
                 aria-expanded="false"
                 aria-labelledby="demo-label demo-selected-text">
                <span class="mdc-select__ripple"></span>
                <span id="demo-label" class="mdc-floating-label">Cuenta</span>
                <span class="mdc-select__selected-text-container">
			      <span id="demo-selected-text" class="mdc-select__selected-text"></span>
			    </span>
                <span class="mdc-select__dropdown-icon">
			      <svg
                          class="mdc-select__dropdown-icon-graphic"
                          viewBox="7 10 10 5" focusable="false">
			        <polygon
                            class="mdc-select__dropdown-icon-inactive"
                            stroke="none"
                            fill-rule="evenodd"
                            points="7 10 12 15 17 10">
			        </polygon>
			        <polygon
                            class="mdc-select__dropdown-icon-active"
                            stroke="none"
                            fill-rule="evenodd"
                            points="7 15 12 10 17 15">
			        </polygon>
			      </svg>
			    </span>
                <span class="mdc-line-ripple"></span>
            </div>

            <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menus-surface--fullwidth">
                <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox">

                </ul>
            </div>
        </div>
        <br>
        <br>
        <label class="mdc-text-field mdc-text-field--filled">
            <span class="mdc-text-field__ripple"></span>
            <span class="mdc-floating-label" id="montoAPagar">Monto</span>
            <span class="mdc-text-field__affix mdc-text-field__affix--prefix">$</span>
            <input class="mdc-text-field__input" type="text" name="montoAPagar" aria-labelledby="montoAPagar">
            <span class="mdc-line-ripple"></span>
        </label>
        <br>
        <br>
        <div class="mdc-slider mdc-slider--discrete mdc-slider--tick-marks">
            <div class="mdc-slider__track">
                <div class="mdc-slider__track--inactive"></div>
                <div class="mdc-slider__track--active">
                    <div class="mdc-slider__track--active_fill"></div>
                </div>
                <div class="mdc-slider__tick-marks">
                    <div class="mdc-slider__tick-mark--active"></div>
                    <div class="mdc-slider__tick-mark--active"></div>
                    <div class="mdc-slider__tick-mark--active"></div>
                    <div class="mdc-slider__tick-mark--active"></div>
                    <div class="mdc-slider__tick-mark--active"></div>
                    <div class="mdc-slider__tick-mark--active"></div>
                    <div class="mdc-slider__tick-mark--inactive"></div>
                    <div class="mdc-slider__tick-mark--inactive"></div>
                    <div class="mdc-slider__tick-mark--inactive"></div>
                    <div class="mdc-slider__tick-mark--inactive"></div>
                    <div class="mdc-slider__tick-mark--inactive"></div>
                </div>
            </div>
            <div class="mdc-slider__thumb">
                <div class="mdc-slider__value-indicator-container" aria-hidden="true">
                    <div class="mdc-slider__value-indicator">
			        <span class="mdc-slider__value-indicator-text">
			          50
			        </span>
                    </div>
                </div>
                <div class="mdc-slider__thumb-knob"></div>
                <input class="mdc-slider__input" type="range" min="4" max="24" value="8" name="cantCuotas" step="2"
                       aria-label="Discrete slider with tick marks demo">
            </div>
        </div>
        <br><br><br>
        <div class="mdc-card mdc-layout-grid">
            <div class="mdc-layout-grid__inner">
                <div class="mdc-layout-grid__cell">
                    <span class="_chd">Interés</span>
                    <span class="mdc-typography--headline6" id="_interes"></span>
                </div>
                <div class="mdc-layout-grid__cell">
                    <span class="_chd">A pagar</span>
                    <span class="mdc-typography--headline6" id="_apagar"></span>
                </div>
                <div class="mdc-layout-grid__cell">
                    <span class="_chd">Cuota mensual</span>
                    <span class="mdc-typography--headline6" id="_cuota"></span>
                </div>
            </div>
        </div>
        <button type="button" id="pedirPrestamo">Pedir préstamo</button>
    </form>
</main>
<%@ include file="../res/web/dialog.part.html" %>
<%@ include file="../res/web/snackbar.part.html" %>
<script type="module" src="../res/controller/default.controller.js"></script>
<script type="module" src="../res/controller/prestamos.solicitar.js"></script>
</body>
</html>