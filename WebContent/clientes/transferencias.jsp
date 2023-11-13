<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entity.Administrador" %>
<%@page import="entity.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type = "text/javascript" src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>       
<link rel="stylesheet" type="text/css" href="estiloPaginaCliente-Administrador.css">
<link rel="stylesheet" type="text/css" href="estiloDatosCliente.css">
<link rel="stylesheet" type="text/css" href="estiloTablaTransferencia.css">
<link rel="stylesheet" type="text/css" href="estilocuentaCliente.css">

</head>
<body class="mdc-typography">
	
	<nav class="indigo darken-2">
    <div class="nav-wrapper">
      <a class="brand-logo" href="#"><img src="https://cadastro.iqnear.com.br/company_logos/banco-empresa-teste-1618528973-992.png" style="width:65px;"/></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="/TPINT_GRUPO_3_LAB/clientes/cuentasCliente.jsp">Cuentas</a></li>
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


<div class="wrapper"> 
<div class="container">
    <h4>Tus Cuentas Bancarias</h4>
    <table class="striped highlight centered">
      <thead>
        <tr>
          <th>N.º Cuenta</th>
          <th>Tipo de Cuenta</th>
          <th>Saldo</th>
          <th>CBU</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1234671110</td>
          <td>Cuenta Corriente</td>
          <td>$5,000.00</td>
          <td>4050600712346711105401</td>
        </tr>
        <tr>
          <td>1234671113</td>
          <td>Caja de Ahorro</td>
          <td>$10,000.00</td>
          <td>4050600712346711135401</td>
        </tr>
        <tr>
          <td>1234671115</td>
          <td>Caja de Ahorro</td>
          <td>$50,000.00</td>
          <td>4050600712346711155401</td>
        </tr>
      </tbody>
    </table>
  </div>
</div>



</body>
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
</html>