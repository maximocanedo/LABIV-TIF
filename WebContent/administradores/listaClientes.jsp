<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entity.Administrador" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clientes · Administradores</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/styles/init.css" />-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./../../estiloPaginaCliente-Administrador.css">
    
    
	
</head>
<body class="mdc-typography">
	
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
        	<li><a href="WebContent/prestamos/solicitarPrestamo.jsp">Solicitar prestamo</a></li>
        	<li class="divider"></li>
        	<li><a href="#">Prestamos otorgados</a></li>
        	<li class="divider"></li>
        	<li><a href="#">Pago de prestamo</a></li>
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

	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>
	<main class="mdc-top-app-bar--fixed-adjust">
    <h3>Clientes</h3>
        <form action="#" onsubmit="return false;" name="searchForm">
            <input type="search" name="q" placeholder="Buscá por nombre, apellido, DNI, CUIL, etc." />
            <hr>
            <label for="showInactiveRadio">
                
            <input type="checkbox" name="showInactive" id="showInactiveRadio" />
            Mostrar usuarios deshabilitados
            </label>
            <br>
            <select name="sexo" id="sexo">
                <option value="A" selected>Todos los géneros</option>
                <option value="F">Femenino</option>
                <option value="M">Masculino</option>
            </select>
            <br>
            <select name="provincias" id="provincias">
                <option selected value="-1">Filtrar por provincia</option>
            </select>
            <select name="localidades" id="localidades">
                <option selected value="-1">Filtrar por localidad</option>
            </select>
            <select name="pais" id="pais">
                <option selected value="-1">Filtrar por nacionalidad</option>
            </select>
            <br>


            <br>

            <button type="button" id="eee" name="searchBtn">Buscar</button>
            <br>
        </form>
       <ul id="listaClientes">

       </ul>
    </main>
    
	<!--  <%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
	<script type="module" src="../res/controller/admin.listarClientes.js"></script>-->
	
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
</html>