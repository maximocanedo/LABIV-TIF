<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio · Clientes</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/redux/4.0.5/redux.js"></script>
<link rel="stylesheet" href="./../res/styles/init.css" />
<link rel="stylesheet" href="./../res/styles/clientes.micuenta.css" />
</head>
<body class="mdc-typography">
	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>

	<main class="mdc-top-app-bar--fixed-adjust">
			<div class="mdc-banner" role="banner">
			  <div class="mdc-banner__fixed">
			    <div class="mdc-banner__content"
			         role="alertdialog"
			         aria-live="assertive">
			      <div class="mdc-banner__graphic-text-wrapper">
			        <div class="mdc-banner__text">gfd
			        </div>
			      </div>
			      <div class="mdc-banner__actions">
			        <button type="button" class="mdc-button mdc-banner__primary-action">
			          <div class="mdc-button__ripple"></div>
			          <div class="mdc-button__label">Fix it</div>
			        </button>
			      </div>
			    </div>
			  </div>
			</div>
		<div id="principal-grid" class="mdc-layout-grid mdc-layout-grid__cell--align-middle mdc-layout-grid-margin-12">
			<div class="mdc-layout-grid__inner">

				<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button centered-text">
					<span class="__account_1_tipodesc"></span> <span class="__account_1_nc"></span>
				</span>
				<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--headline4 __account_1_saldo centered-text"></span>
				<div class="mdc-layout-grid__cell 
					mdc-layout-grid__cell--span-6
					mdc-layout-grid__cell--span-12-phone
					mdc-card account_card mdc-ripple account_1">
						<ul class="mdc-deprecated-list mdc-deprecated-list--two-line">
						  <li class="mdc-deprecated-list-item" tabindex="0">
						    <span class="mdc-deprecated-list-item__ripple"></span>
						    <span class="mdc-deprecated-list-item__text">
						      <span class="mdc-deprecated-list-item__primary-text __account_1_nc"></span>
						      <span class="mdc-deprecated-list-item__secondary-text">Número de cuenta</span>
						    </span>
						  </li>
						  <li class="mdc-deprecated-list-item">
						    <span class="mdc-deprecated-list-item__ripple"></span>
						    <span class="mdc-deprecated-list-item__text">
						      <span class="mdc-deprecated-list-item__primary-text __account_1_cbu"></span>
						      <span class="mdc-deprecated-list-item__secondary-text">CBU</span>
						    </span>
						  </li>
						  <li class="mdc-deprecated-list-item">
						    <span class="mdc-deprecated-list-item__ripple"></span>
						    <span class="mdc-deprecated-list-item__text">
						      <span class="mdc-deprecated-list-item__primary-text __account_1_fechaCreacion"></span>
						      <span class="mdc-deprecated-list-item__secondary-text">Fecha de creación</span>
						    </span>
						  </li>
						</ul>
				</div>
				<div class="mdc-layout-grid__cell 
					mdc-layout-grid__cell--span-6
					mdc-layout-grid__cell--span-12-phone
					mdc-card account_card mdc-ripple account_1">
					<ul class="mdc-deprecated-list mdc-deprecated-list--two-line">
					  <li class="mdc-deprecated-list-item" tabindex="0">
					    <span class="mdc-deprecated-list-item__ripple"></span>
					    <span class="mdc-deprecated-list-item__text">
					      <span class="mdc-deprecated-list-item__primary-text __account_1_clname"></span>
					      <span class="mdc-deprecated-list-item__secondary-text">Nombre del propietario</span>
					    </span>
					  </li>
					  <li class="mdc-deprecated-list-item">
					    <span class="mdc-deprecated-list-item__ripple"></span>
					    <span class="mdc-deprecated-list-item__text">
					      <span class="mdc-deprecated-list-item__primary-text __account_1_clcuil"></span>
					      <span class="mdc-deprecated-list-item__secondary-text">CUIL</span>
					    </span>
					  </li>
					  <li class="mdc-deprecated-list-item">
					    <span class="mdc-deprecated-list-item__ripple"></span>
					    <span class="mdc-deprecated-list-item__text">
					      <span class="mdc-deprecated-list-item__primary-text __account_1_clnac"></span>
					      <span class="mdc-deprecated-list-item__secondary-text">Nacionalidad</span>
					    </span>
					  </li>
					</ul>
				</div>
				<div id="tablaMovimientos" class="mdc-data-table mdc-layout-grid__cell--span-12">
				  <div class="mdc-data-table__table-container">
				    <table class="mdc-data-table__table" aria-label="Dessert calories">
				      <thead>
				        <tr class="mdc-data-table__header-row">
				          <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric" role="columnheader" scope="col">ID</th>
				          <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Concepto</th>
				          <th class="mdc-data-table__header-cell" role="columnheader" scope="col">Tipo</th>

				          <th class="mdc-data-table__header-cell mdc-data-table__header-cell--numeric" role="columnheader" scope="col">Importe</th>
				        </tr>
				      </thead>
				      <tbody class="mdc-data-table__content" id="tablaMovimientos__body">
				      </tbody>
				    </table>
				  </div>
						<div class="mdc-data-table__progress-indicator">
						    <div class="mdc-data-table__scrim"></div>
						    <div class="mdc-linear-progress mdc-linear-progress--indeterminate mdc-data-table__linear-progress" role="progressbar" aria-label="Data is being loaded...">
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
						  </div>
				  <div class="mdc-data-table__pagination">
				    <div class="mdc-data-table__pagination-trailing">
				      <div class="mdc-data-table__pagination-rows-per-page">
				        <div class="mdc-data-table__pagination-rows-per-page-label">
				          Elementos por página
				        </div>

				        <div class="mdc-select mdc-select--outlined mdc-select--no-label mdc-data-table__pagination-rows-per-page-select mdc-data-table__pagination-rows-per-page-select--outlined"  id="mdSelectPaginator">
				          <input type="hidden" name="paginatorInput">
				          <div class="mdc-select__anchor" role="button" aria-haspopup="listbox"
				                aria-labelledby="demo-pagination-select" tabindex="0">
				            <span class="mdc-select__selected-text-container">
				              <span id="demo-pagination-select" class="mdc-select__selected-text">10</span>
				            </span>
				            <span class="mdc-select__dropdown-icon">
				              <svg
				                  class="mdc-select__dropdown-icon-graphic"
				                  viewBox="7 10 10 5">
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
				            <span class="mdc-notched-outline mdc-notched-outline--notched">
				              <span class="mdc-notched-outline__leading"></span>
				              <span class="mdc-notched-outline__trailing"></span>
				            </span>
				          </div>

				          <div class="mdc-select__menu mdc-menu mdc-menu-surface mdc-menu-surface--fullwidth" role="listbox">
				            <ul class="mdc-list">
				              <li class="mdc-select__option mdc-select__one-line-option mdc-list-item mdc-list-item--selected mdc-list-item--with-one-line"
				                  aria-selected="true" role="option" data-value="10">
				                <span class="mdc-list-item__ripple"></span>
				                <span class="mdc-list-item__content">
				                  <span class="mdc-list-item__primary-text">10</span>
				                </span>
				              </li>
				              <li class="mdc-select__option mdc-select__one-line-option mdc-list-item mdc-list-item--with-one-line"
				                  role="option" data-value="25">
				                <span class="mdc-list-item__ripple"></span>
				                <span class="mdc-list-item__content">
				                  <span class="mdc-list-item__primary-text">25</span>
				                </span>
				              </li>
				              <li class="mdc-select__option mdc-select__one-line-option mdc-list-item mdc-list-item--with-one-line"
				                  role="option" data-value="100">
				                <span class="mdc-list-item__ripple"></span>
				                <span class="mdc-list-item__content">
				                  <span class="mdc-list-item__primary-text">100</span>
				                </span>
				              </li>
				            </ul>
				          </div>
				        </div>
				      </div>
      <div class="mdc-data-table__pagination-navigation">
        <div class="mdc-data-table__pagination-total" id="tablaMovimientos__textoPaginacion">
          
        </div>
        <button class="mdc-icon-button material-icons mdc-data-table__pagination-button" data-prev-page="true" disabled id="tablaMovimientos__btnAnterior">
          <div class="mdc-button__icon">chevron_left</div>
        </button>
        <button id="tablaMovimientos__btnSiguiente" class="mdc-icon-button material-icons mdc-data-table__pagination-button" data-next-page="true">
          <div class="mdc-button__icon">chevron_right</div>
        </button>      </div>
    </div>
				</div>

			</div>
		</div>

    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
    <script type="module" src="./../res/controller/default.controller.js"></script>
	<script type="module" src="./../res/controller/clientes.micuenta.js"></script>
</body>
</html>