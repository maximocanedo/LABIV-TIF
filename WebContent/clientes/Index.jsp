<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio · Clientes</title>
<link rel="stylesheet" href="./../res/styles/init.css" />
<link rel="stylesheet" href="./../res/styles/clientes.dashboard.css" />
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
			        <div class="mdc-banner__text">
			          There was a problem processing a transaction on your credit card.
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
				<span class="mdc-layout-grid__cell--span-12 mdc-typography--subtitle6">Mis cuentas</span>
				<div class="mdc-layout-grid__cell 
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone
				mdc-card mdc-layout-grid account_card mdc-ripple account_1">
				<div class="mdc-card__ripple"></div>
					<div class="mdc-layout-grid__inner">
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_1_tipodesc"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--headline4 __account_1_saldo"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_1_nc"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_1_cbu"></span>

					</div>
				</div>


				<div class="mdc-layout-grid__cell 
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone
				mdc-card mdc-layout-grid account_card mdc-ripple account_2">
					<div class="mdc-layout-grid__inner">
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_2_tipodesc"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--headline4 __account_2_saldo"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_2_nc"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_2_cbu"></span>

					</div>
				</div>


				<div class="mdc-layout-grid__cell 
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone
				mdc-card mdc-layout-grid account_card mdc-ripple account_3">
					<div class="mdc-layout-grid__inner">
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_3_tipodesc"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--headline4 __account_3_saldo"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_3_nc"></span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_3_cbu"></span>

					</div>
				</div>

			</div>
		</div>

    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
    <script type="module" src="./../res/controller/default.controller.js"></script>
	<script type="module" src="./../res/controller/clientes.inicio.js"></script>
</body>
</html>