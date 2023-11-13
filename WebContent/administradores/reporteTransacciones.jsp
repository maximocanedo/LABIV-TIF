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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

</head>
<body>
	
	<nav class="indigo darken-2">
    <div class="nav-wrapper">
      <a class="brand-logo" href="#"><img src="https://cadastro.iqnear.com.br/company_logos/banco-empresa-teste-1618528973-992.png" style="width:65px;"/></a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="/TPINT_GRUPO_3_LAB/clientes/cuentasCliente.jsp">Cuentas</a></li>
        <li><a href="/TPINT_GRUPO_3_LAB/clientes/transferencias.jsp">Transferencias</a></li>
        <li><a href="/TPINT_GRUPO_3_LAB/MovimientosRealizados.jsp">Movimientos realizados</a></li>
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
	
	<div class="container">
        <h2 class="center-align">Informe de transacciones</h2>

        <canvas id="graficoBarras" width="400" height="400"></canvas>

        <script>
            // Datos de prueba, luego serán reemplazados por los datos obtenidos de la base de datos
            var datosTransacciones = {
                labels: ["Alta de cuenta", "Alta de prestamo", "Pago de prestamo", "Transferencia"], // Nombres de los tipos de transacciones
                datasets: [{
                    data: [10, 6, 18,28], // Cantidad de transacciones por cada tipo
                    backgroundColor: ["rgba(255, 99, 132, 0.5)", "rgba(54, 162, 235, 0.5)", "rgba(255, 206, 86, 0.5)","rgba(0, 255, 0, 0.5)"],
                    borderColor: ["rgba(255, 99, 132, 1)", "rgba(54, 162, 235, 1)", "rgba(255, 206, 86, 1)","rgba(0, 255, 0, 0.5)"],
                    borderWidth: 1
                }]
            };

            // Configuración del gráfico de barras
            var opcionesGrafico = {
            		plugins: {
            	        legend: {
            	            display: false
            	        }
            	    },
                scales: {
                    y: {
                        beginAtZero: true
                       
                    }
                }
            };

            // Obtener el contexto del canvas y dibujar el gráfico
            var ctx = document.getElementById('graficoBarras').getContext('2d');
            var graficoBarras = new Chart(ctx, {
                type: 'bar',
                data: datosTransacciones,
                options: opcionesGrafico
            });
        </script>

        <!-- Agrega aquí cualquier otro contenido o elementos que desees mostrar -->

    </div>

        <!-- Agrega aquí cualquier otro contenido o elementos que desees mostrar -->

    </div>
	
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

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