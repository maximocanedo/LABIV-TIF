<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entity.Administrador" %>
<%@page import="entity.Cliente" %>
<%@page import="logicImpl.MovimientoLogicImpl" %>
<%@page import="max.Response" %>
<%@page import="max.Dictionary" %>
<%@page import="java.util.ArrayList" %> 



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script type = "text/javascript" src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
<base href="/TPINT_GRUPO_3_LAB/" />
<link rel="stylesheet" href="./res/styles/init.css" />

</head>
<body>
	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>
	<main class="mdc-top-app-bar--fixed-adjust" style="width: 50%; display: grid; place-items: center; padding-bottom: 80px;">
		
	
	<div class="container">
        <h2 class="center-align">Informe de movimientos(Barras)</h2>
        
<div style="margin-bottom: 100px;">	
        <canvas id="graficoBarras" width="200" height="200"></canvas>
</div>				
				 
            
            <!-- Vista mensual, va dentro del script -->
            <!--  var datosTransacciones = {
            	    labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio"],
            	    datasets: [
            	        {
            	            label: "Alta de cuenta",
            	            data: [10, 15, 20, 25, 30, 35],
            	            backgroundColor: "rgba(255, 99, 132, 0.5)",
            	            borderColor: "rgba(255, 99, 132, 1)",
            	            borderWidth: 1
            	        },
            	        {
            	            label: "Alta de prestamo",
            	            data: [20, 25, 30, 35, 40, 45],
            	            backgroundColor: "rgba(54, 162, 235, 0.5)",
            	            borderColor: "rgba(54, 162, 235, 1)",
            	            borderWidth: 1
            	        },
            	        {
            	            label: "Pago de prestamo",
            	            data: [15, 20, 25, 30, 35, 40],
            	            backgroundColor: "rgba(255, 206, 86, 0.5)",
            	            borderColor: "rgba(255, 206, 86, 1)",
            	            borderWidth: 1
            	        },
            	        {
            	        	label:"Transferencia",
            	        	data: [28,28,28,28,28,3],
            	        	backgroundColor:"rgba(0, 255, 0, 0.5)",
            	        	borderColor:"rgba(0, 255, 0, 0.5)",
            	        	borderWidth: 1
            	        }
            	    ]
            	};-->
            	<%
            	//MovimientoLogicImpl logic = new MovimientoLogicImpl();
            	//ArrayList<Integer> data = logic.getInforme();
            	ArrayList<Integer> data = (ArrayList<Integer>) request.getAttribute("informe");   
            	int TP1 = 0;
            	int TP2 = 0;
            	int TP3 = 0;
            	int TP4 = 0;
            	if(data !=null && !data.isEmpty()){
            	TP1 = data.get(0);
            	TP2 = data.get(1);
            	TP3 = data.get(2);
            	TP4 = data.get(3);
            	}
            	%>
            	
        <script>
            // Datos de prueba, luego serán reemplazados por los datos obtenidos de la base de datos
          var datosTransacciones = { 
                labels: ["Altas de cuenta", "Altas de prestamo", "Pagos de prestamo", "Transferencias"], // Nombres de los tipos de transacciones
                datasets: [{
                    data: [<%= TP1 %>, <%= TP2 %>, <%= TP3 %>, <%= TP4 %>], // Cantidad de transacciones por cada tipo
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
        </div>
 <div class="container">
        <h2 class="center-align">Informe de movimientos(Torta)</h2>
<div style="margin-top: 100px;" >
       <canvas id="graficoTorta" width="100" height="100"></canvas>
</div>

<script>
    var ctx = document.getElementById('graficoTorta').getContext('2d');

    var datosTorta = {
        labels: ["Altas de cuenta", "Altas de prestamo", "Pagos de prestamo", "Transferencias"],
        datasets: [{
            label: 'Cantidad de Movimientos',
            data: [<%= TP1 %>, <%= TP2 %>, <%= TP3 %>, <%= TP4 %>],
            backgroundColor: ["rgba(255, 99, 132, 0.5)", "rgba(54, 162, 235, 0.5)", "rgba(255, 206, 86, 0.5)","rgba(0, 255, 0, 0.5)"],
            borderColor: ["rgba(255, 99, 132, 1)", "rgba(54, 162, 235, 1)", "rgba(255, 206, 86, 1)","rgba(0, 255, 0, 0.5)"],
            borderWidth: 1
        }]
    };

    var opcionesGrafico = {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    };

    var graficoTorta = new Chart(ctx, {
        type: 'pie',
        data: datosTorta,
        options: opcionesGrafico
    });
</script>

    </div>

    
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
	<script type="module" src="./res/controller/default.controller.js"></script>
</body>
</html>