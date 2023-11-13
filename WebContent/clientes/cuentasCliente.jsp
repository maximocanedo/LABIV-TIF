<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entity.Administrador" %>
<%@page import="entity.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas · Clientes</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./../../estiloPaginaCliente-Administrador.css">
    
    <link rel="stylesheet" href="./../res/styles/signup.css">
    
</head>
<body>


	<nav class="indigo darken-2">
    <div class="nav-wrapper">
      <a class="brand-logo" href="#"><img src="https://cadastro.iqnear.com.br/company_logos/banco-empresa-teste-1618528973-992.png" style="width:65px;"/></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="cuentaCliente.jsp">Cuentas</a></li>
        <li><a href="#">Transferencias</a></li>
        <li><a href="MovimientosRealizados.jsp">Movimientos realizados</a></li>
        <li>
        	<a href="#" class="dropdown-trigger" data-target="id_drop">Prestamos
        	<i class="material-icons right">arrow_drop_down</i></a>
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
					String nombre="";
				if((Administrador)session.getAttribute("admin")!=null){
					Administrador admin= (Administrador)session.getAttribute("admin");
					nombre= admin.getNombre();
				}
				else if((Cliente)session.getAttribute("cliente")!=null){
					Cliente client= (Cliente)session.getAttribute("cliente");
					nombre= client.getNombre();
				}
					
				%>
				<%=nombre %>
</a></li>
        <li>
        	<a class="waves-effect waves-light btn" id="logout-button" href="inicioSesion.jsp">
        	<i class="material-icons left">exit_to_app</i>Cerrar Sesion</a>
        </li>       
      </ul>      
    </div>
 </nav>
  
 <div class="transparent-bg"></div>
 
 

<!-- <main class="mdc-top-app-bar--fixed-adjust">
		<div id="principal-grid" class="mdc-layout-grid mdc-layout-grid__cell--align-middle mdc-layout-grid-margin-12">
			<div class="mdc-layout-grid__inner">
				<div class="mdc-layout-grid__cell 
				mdc-layout-grid__cell--span-6
				mdc-layout-grid__cell--span-12-phone
				mdc-card mdc-layout-grid account_card">
					<div class="mdc-layout-grid__inner">
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_1_tipodesc">Caja de ahorro</span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--headline4 __account_1_saldo">$ 3.237,78</span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_1_saldo"># 1792-455-837</span>
						<span class="mdc-layout-grid__cell mdc-layout-grid__cell--span-12 mdc-typography--button __account_1_saldo">00000730001135623345</span>
						<button class="mdc-button mdc-button--icon-leading mdc-layout-grid__cell--span-12">
						  <span class="mdc-button__ripple"></span>
						  <span class="mdc-button__focus-ring"></span>
						  <i class="material-icons mdc-button__icon" aria-hidden="true"
						    >arrow_left</i
						  >
						  <span class="mdc-button__label">Ver más</span>
						</button>

					</div>
				</div>
			</div>
		</div>-->
<div style="width: 300px; margin: auto; border: 1px solid #ccc; padding: 20px; margin-top: 50px;">
    <h2>Cuentas</h2>

    <form action="servlet" method="post">
        <label for="tipoCuenta">Tipo de Cuenta:</label>
        <input type="text" id="tipoCuenta" name="tipoCuenta" value='Caja ahorro' readonly />

        <label for="numeroCuenta">Número de Cuenta:</label>
        <input type="text" id="numeroCuenta" name="numeroCuenta" value="111111111" readonly />

        <label for="dniCuenta">DNI:</label>
        <input type="text" id="dniCuenta" name="dniCuenta" value="12345678" readonly />

        <label for="cbu">CBU:</label>
        <input type="text" id="cbu" name="cbu" value="00000487484646" readonly />

        <label for="fechaCreacion">Fecha de Creación:</label>
        <input type="text" id="fechaCreacion" name="fechaCreacion" value="18/8/2008" readonly />

        <label for="saldo">Saldo:</label>
        <input type="text" id="saldo" name="saldo" value="11,235" readonly />

    </form>
</div>



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
</body>
<script>
$(document).ready(function(){
	$(".dropdown-trigger").dropdown();
  });
</script>

</html>