<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entity.Administrador" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pestaña Consulta</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type = "text/javascript" src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>             
<link rel="stylesheet" type="text/css" href="estiloPaginaCliente-Administrador.css">
<link rel="stylesheet" type="text/css" href="estiloGraficosConsulta.css">
<script src="htmlEnDiv.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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

				
</a></li>
        <li>
        	<a class="waves-effect waves-light btn" id="logout-button" href="inicioSesion.jsp">
        	<i class="material-icons left">exit_to_app</i>Cerrar Sesion</a>
        </li>       
      </ul>      
    </div>
 </nav>
  
 <div class="transparent-bg"></div>
    <div id="nav"></div>

<div class="wrapper">    
    <div class="container"> 
    	<div class="row">
      		<div class="col s12 m8 offset-m2">
        		<div class="form-container">
        			<h5>Registro de préstamos por cliente</h5>
          			<form>    	
      					<div class="row">
        					<div class="input-field col s12 m6 offset-m3">
          						<label for="dni">Ingrese DNI del cliente</label>
          						<input id="dni" type="text" class="validate">
        					</div>
        					<div class="input-field col s12 m6 offset-m3">
          						<label for="start-date">Fecha de Inicio:</label>
          						<input id="start-date" type="text" class="datepicker">
        					</div>
        					<div class="input-field col s12 m6 offset-m3">
          						<label for="end-date">Fecha de Final:</label>
          						<input id="end-date" type="text" class="datepicker">
        					</div>
        					<div class="col s12 m6 offset-m3 center-align">
          						<button class="btn waves-effect waves-light" type="submit" name="action">Buscar
          						<i class="material-icons right">search</i>
        						</button>
        					</div>
      					</div>
    				</form>
        		</div>
     		</div>
   		</div>
	</div>
</div>


  
<div class="charts-container">
    <div class="chart">
      <canvas id="cuotasChart" width="400" height="400"></canvas>
    </div>
    <div class="chart">
      <canvas id="montosChart" width="400" height="400"></canvas>
    </div>
</div>
  
    <div id="footer"></div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <script>htmlEnDiv("Footer.html", "footer");</script>

    <script>htmlEnDiv("NavAdmin.html", "nav");</script>

</html>
<script>
    // Datos de prueba, luego serán reemplazados
    // por los datos obtenidos del servlet (JSON)
    var cuotasData = {
      labels: ["Préstamo 1", "Préstamo 2", "Préstamo 3", "Préstamo 4"],
      datasets: [
        {
          label: "Cantidad de Cuotas Pagadas",
          data: [12, 8, 10, 15],
          backgroundColor: ["#64B5F6", "#81C784", "#FFD54F", "#FF8A65"],
        },
      ],
    };

    var montosData = {
      labels: ["Préstamo 1", "Préstamo 2", "Préstamo 3", "Préstamo 4"],
      datasets: [
        {
          label: "Montos Otorgados",
          data: [30000, 45000, 60000, 75000],
          backgroundColor: ["#64B5F6", "#81C784", "#FFD54F", "#FF8A65"],
        },
      ],
    };

    // Aquí se configura un gráfico de barra y un gráfico tipo torta
    var cuotasChart = new Chart(document.getElementById("cuotasChart"), {
      type: "bar",
      data: cuotasData,
    });

    var montosChart = new Chart(document.getElementById("montosChart"), {
      type: "pie",
      data: montosData,
    });
</script>
<script>
    M.AutoInit(); 
</script>


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