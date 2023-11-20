<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inicio · Clientes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" href="../res/styles/init.css"/>
    <link rel="stylesheet" href="../res/styles/clientes.miPerfil.css"/>
</head>

<body style="background: rgb(0,0,0,0.04)" class="mdc-typography">

<%@ include file="../res/web/drawer.part.html" %>
<%@ include file="../res/web/header.part.html" %>
<main class="mdc-top-app-bar--fixed-adjust">
    <div id="principal-grid" class="mdc-layout-grid mdc-layout-grid__cell--align-middle mdc-layout-grid-margin-12">
        <div class="mdc-layout-grid__inner">
            <div class="mdc-layout-grid__cell
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone
				mdc-card mdc-layout-grid">
                <div class="mdc-layout-grid__inner">
                    <div class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle2 card-title">
                        Datos básicos
                    </div>
                    <div class="mdc-layout-grid__cell--span-12">
                        <ul
                                class="mdc-deprecated-list mdc-deprecated-list--two-line mdc-deprecated-list--icon-list">
                            <li class="mdc-deprecated-list-item" tabindex="0">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">alternate_email</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span class="mdc-deprecated-list-item__primary-text cnt-usuario"
                                              id="iusuario">usuario</span>
										<span class="mdc-deprecated-list-item__secondary-text">Nombre de usuario</span>
									</span>
                            </li>
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">person</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span
                                                class="mdc-deprecated-list-item__primary-text cnt-nombreCompleto">Nombre</span>
										<span class="mdc-deprecated-list-item__secondary-text">Nombre completo</span>
									</span>
                            </li>
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">wc</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span class="mdc-deprecated-list-item__primary-text cnt-sexo"></span>
										<span class="mdc-deprecated-list-item__secondary-text">Sexo</span>
									</span>
                            </li>
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">cake</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span class="mdc-deprecated-list-item__primary-text cnt-fechaNacimiento"></span>
										<span class="mdc-deprecated-list-item__secondary-text">Fecha de
											nacimiento</span>
									</span>
                            </li>

                        </ul>
                    </div>
                </div>
            </div>
            <div class="mdc-layout-grid__cell
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone  mdc-card mdc-layout-grid">
                <div class="mdc-layout-grid__inner">
                    <div class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle2 card-title">
                        Domicilio y documentos
                    </div>
                    <div class="mdc-layout-grid__cell--span-12">
                        <ul
                                class="mdc-deprecated-list mdc-deprecated-list--two-line mdc-deprecated-list--icon-list">
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">badge</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span class="mdc-deprecated-list-item__primary-text cnt-dni"></span>
										<span class="mdc-deprecated-list-item__secondary-text">Número de
											documento</span>
									</span>
                            </li>
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">badge</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span class="mdc-deprecated-list-item__primary-text cnt-cuil"></span>
										<span class="mdc-deprecated-list-item__secondary-text">Número de CUIL</span>
									</span>
                            </li>
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">place</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span class="mdc-deprecated-list-item__primary-text cnt-direccion"></span>
										<span class="mdc-deprecated-list-item__secondary-text cnt-pl"></span>
									</span>
                            </li>
                            <li class="mdc-deprecated-list-item">
                                <span class="mdc-deprecated-list-item__ripple"></span>
                                <span class="mdc-deprecated-list-item__graphic">
										<span class="material-symbols-outlined">flag</span>
									</span>
                                <span class="mdc-deprecated-list-item__text">
										<span
                                                class="mdc-deprecated-list-item__primary-text cnt-nacionalidad-nombre"></span>
										<span class="mdc-deprecated-list-item__secondary-text">Nacionalidad</span>
									</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <button id="btnActualizar" class="mdc-layout-grid__cell
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone mdc-button mdc-button--raised mdc-button--icon-leading">
                <span class="mdc-button__ripple"></span>
                <span class="mdc-button__focus-ring"></span>
                <i class="material-symbols-outlined mdc-button__icon" aria-hidden="true"
                >edit</i
                >
                <span class="mdc-button__label">Actualizar datos personales</span>
            </button>
            <button id="btnDeshabilitar" class="mdc-layout-grid__cell
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone mdc-button mdc-button--raised mdc-button--icon-leading">
                <span class="mdc-button__ripple"></span>
                <span class="mdc-button__focus-ring"></span>
                <i class="material-symbols-outlined mdc-button__icon" aria-hidden="true"
                >delete</i
                >
                <span class="mdc-button__label">Deshabilitar tu cuenta</span>
            </button>

        </div>
    </div>

    <h1 id="name"></h1><br>


</main>
<form action="#" name="me" onsubmit="return false;">
    <div id="formDialog" class="mdc-dialog mdc-dialog--fullscreen">
        <div class="mdc-dialog__container">
            <div class="mdc-dialog__surface" role="dialog" aria-modal="true" aria-labelledby="my-dialog-title"
                 aria-describedby="my-dialog-content" tabindex="-1">
                <div class="mdc-dialog__header">
                    <h2 class="mdc-dialog__title" id="my-dialog-title">
                        Modificar datos personales
                    </h2>
                    <button class="mdc-icon-button material-symbols-outlined mdc-dialog__close"
                            data-mdc-dialog-action="close">
                        close
                    </button>
                </div>
                <div class="mdc-dialog__content mdc-layout-grid" style="padding-top: 12px" id="my-dialog-content">
                    <div class="mdc-layout-grid__inner">
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <label id="mdtxtNombre" class="mdc-text-field mdc-text-field--outlined di-control">
									<span class="mdc-notched-outline">
										<span class="mdc-notched-outline__leading"></span>
										<span class="mdc-notched-outline__notch">
											<span class="mdc-floating-label" id="lblNombre">Nombre</span>
										</span>
										<span class="mdc-notched-outline__trailing"></span>
									</span>
                                <input type="text" type="text" name="nombre" required minlength="4"
                                       class="mdc-text-field__input" aria-labelledby="lblNombre">

                            </label>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <label id="mdtxtApellido" class="mdc-text-field mdc-text-field--outlined di-control">
									<span class="mdc-notched-outline">
										<span class="mdc-notched-outline__leading"></span>
										<span class="mdc-notched-outline__notch">
											<span class="mdc-floating-label" id="lblApellido">Apellido</span>
										</span>
										<span class="mdc-notched-outline__trailing"></span>
									</span>
                                <input type="text" type="text" name="apellido" required minlength="4"
                                       class="mdc-text-field__input" aria-labelledby="lblApellido">
                            </label>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <div class="mdc-form-field di-control">
                                <div class="mdc-radio">
                                    <input required class="mdc-radio__native-control" type="radio"
                                           id="radioMasculino" name="sexo" value="M">
                                    <div class="mdc-radio__background">
                                        <div class="mdc-radio__outer-circle"></div>
                                        <div class="mdc-radio__inner-circle"></div>
                                    </div>
                                    <div class="mdc-radio__ripple"></div>
                                    <div class="mdc-radio__focus-ring"></div>
                                </div>
                                <label for="radioMasculino">Masculino</label>
                                <div class="mdc-radio">
                                    <input required class="mdc-radio__native-control" type="radio"
                                           id="radioFemenino" name="sexo" value="F">
                                    <div class="mdc-radio__background">
                                        <div class="mdc-radio__outer-circle"></div>
                                        <div class="mdc-radio__inner-circle"></div>
                                    </div>
                                    <div class="mdc-radio__ripple"></div>
                                    <div class="mdc-radio__focus-ring"></div>
                                </div>
                                <label for="radioFemenino">Femenino</label>
                            </div>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <label id="mdtxtFechaNacimiento" class="mdc-text-field mdc-text-field--outlined di-control">
									<span class="mdc-notched-outline">
										<span class="mdc-notched-outline__leading"></span>
										<span class="mdc-notched-outline__notch">
											<span class="mdc-floating-label" id="lblFechaNacimiento">Fecha de
												Nacimiento</span>
										</span>
										<span class="mdc-notched-outline__trailing"></span>
									</span><i
                                    class="material-symbols-outlined mdc-text-field__icon mdc-text-field__icon--leading"
                                    tabindex="0" role="button">event</i>
                                <input type="date" name="fechaNacimiento" required class="mdc-text-field__input"
                                       aria-labelledby="lblFechaNacimiento">
                            </label>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <div id="mdSelectProvincia"
                                 class="mdc-select mdc-select--outlined di-control mdc-select--required mdc-select--with-leading-icon">
                                <input type="hidden" minlength="1" name="provincia">
                                <div class="mdc-select__anchor" aria-required="true"
                                     aria-labelledby="outlined-select-label">
										<span class="mdc-notched-outline">
											<span class="mdc-notched-outline__leading"></span>
											<span class="mdc-notched-outline__notch">
												<span id="outlined-select-label"
                                                      class="mdc-floating-label">Provincia</span>
											</span>
											<span class="mdc-notched-outline__trailing"></span>
										</span>
                                    <i class="material-symbols-outlined mdc-select__icon" tabindex="0" role="button">place</i>
                                    <span class="mdc-select__selected-text-container">
											<span id="selectedTextProvincia" class="mdc-select__selected-text"></span>
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
                                    <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <div id="mdSelectLocalidad"
                                 class="mdc-select mdc-select--outlined di-control mdc-select--required mdc-select--with-leading-icon">
                                <input type="hidden" minlength="1" name="localidad">
                                <div class="mdc-select__anchor" aria-required="true"
                                     aria-labelledby="outlined-select-label">
										<span class="mdc-notched-outline">
											<span class="mdc-notched-outline__leading"></span>
											<span class="mdc-notched-outline__notch">
												<span id="outlined-select-label"
                                                      class="mdc-floating-label">Localidad</span>
											</span>
											<span class="mdc-notched-outline__trailing"></span>
										</span>
                                    <i class="material-symbols-outlined mdc-select__icon" tabindex="0" role="button">location_city</i>
                                    <span class="mdc-select__selected-text-container">
											<span id="selectedTextNacionalidadLocalidad"
                                                  class="mdc-select__selected-text"></span>
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
                                    <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <div id="mdSelectNacionalidad"
                                 class="mdc-select mdc-select--outlined di-control mdc-select--required mdc-select--with-leading-icon">
                                <input type="hidden" required minlength="1" name="nacionalidad">
                                <div class="mdc-select__anchor" aria-required="true"
                                     aria-labelledby="outlined-select-label">
										<span class="mdc-notched-outline">
											<span class="mdc-notched-outline__leading"></span>
											<span class="mdc-notched-outline__notch">
												<span id="outlined-select-label"
                                                      class="mdc-floating-label">Nacionalidad</span>
											</span>
											<span class="mdc-notched-outline__trailing"></span>
										</span>
                                    <i class="material-symbols-outlined mdc-select__icon" tabindex="0" role="button">flag</i>
                                    <span class="mdc-select__selected-text-container">
											<span id="selectedTextNacionalidad"
                                                  class="mdc-select__selected-text"></span>
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
                                    <ul class="mdc-deprecated-list" role="listbox" aria-label="Food picker listbox">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div
                                class="mdc-layout-grid__cell mdc-layout-grid__cell--span-6-desktop mdc-layout-grid__cell--span-6-phone">
                            <label id="mdtxtDireccion" class="mdc-text-field mdc-text-field--outlined di-control">
									<span class="mdc-notched-outline">
										<span class="mdc-notched-outline__leading"></span>
										<span class="mdc-notched-outline__notch">
											<span class="mdc-floating-label" id="lblDireccion">Dirección</span>
										</span>
										<span class="mdc-notched-outline__trailing"></span>
									</span><i
                                    class="material-symbols-outlined mdc-text-field__icon mdc-text-field__icon--leading"
                                    tabindex="0" role="button">home</i>
                                <input type="text" type="text" name="direccion" required minlength="4"
                                       class="mdc-text-field__input" aria-labelledby="lblDireccion">
                            </label>
                        </div>

                        <input type="hidden" readonly name="usuario"/>
                        <input type="hidden" readonly placeholder="DNI" name="dni"/>
                        <input type="hidden" readonly placeholder="CUIL" name="cuil"/>


                        <button type="button" name="btnDisable"
                                style="display: none; background: rgb(150,0,0);color: white;">Deshabilitar mi
                            cuenta
                        </button>

                    </div>
                </div>
                <div class="mdc-dialog__actions">
                    <button type="button" name="btnEnviar" class="mdc-button mdc-dialog__button"
                            data-mdc-dialog-action="ok">
                        <div class="mdc-button__ripple"></div>
                        <span class="mdc-button__label">OK</span>
                    </button>
                </div>
            </div>
        </div>
        <div class="mdc-dialog__scrim"></div>
    </div>
</form>

<%@ include file="../res/web/dialog.part.html" %>
<%@ include file="../res/web/snackbar.part.html" %>
<script type="module" src="../res/controller/clientes.miperfil.js"></script>
</body>

</html>