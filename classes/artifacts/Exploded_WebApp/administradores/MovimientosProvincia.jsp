<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entity.Administrador" %>
<%@page import="entity.Cliente" %>
<%@page import="entity.Provincia" %>
<%@page import="logicImpl.MovimientoLogicImpl" %>
<%@page import="logicImpl.ProvinciaLogicImpl" %>
<%@page import="max.Response" %>
<%@page import="max.Dictionary" %>
<%@page import="java.util.ArrayList" %> 



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movimientos por Provincia</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script type = "text/javascript" src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

<link rel="stylesheet" href="../res/styles/init.css" />

</head>
<body>
	<%@ include file="../res/web/drawer.part.html" %>
	<%@ include file="../res/web/header.part.html" %>
	<main class="mdc-top-app-bar--fixed-adjust">
		
	
<div class="wrapper">    
    <div class="container"> 
    	<div class="row">
      		<div class="col s12 m8 offset-m2">
        		<div class="form-container">
        			<h5>Ver movimientos por provincia</h5>
          			<form>    	
      					<div class="row">
							<div class="input-field col s12" >
    							<select id="provincias" name="selectProv" onchange="obtainSelectedValue()">
        							<option value="" disabled selected>Selecciona una provincia</option>
        							<%
        							ProvinciaLogicImpl logic = new ProvinciaLogicImpl();
        			            	Response<Provincia> data = logic.getAll();//luego hacer el logic en servlets
                                    if(data.listReturned != null) {
                                        for (int i = 0; i < data.listReturned.size(); i++) {%>                                    
                                      <option value="<%=data.listReturned.get(i).getId()%>"><%=data.listReturned.get(i).getNombre() %></option>
                                     <%}
                                    }%>
        							
        							
       
    							</select>
   	 							<label for="provincias">Provincias</label>
							</div>
      					</div>
    				</form>
        		</div>
     		</div>
   		</div>
	</div>
</div>

<script>
    function obtainSelectedValue() {
            var selectedValue = document.getElementById("provincias").value;

            // Hacer una solicitud al servlet usando XMLHttpRequest o Fetch API
            // Por ejemplo, usando Fetch API:
            fetch('/informe58?selectProv=' + selectedValue, {
                method: 'POST' // O 'POST' si es una solicitud POST
                // Otros parámetros como headers, body, etc., si es necesario
            }).then(response => {
                // Lógica para manejar la respuesta del servlet
            }).catch(error => {
                console.error('Error:', error);
            });
    }
</script>

	
	<div class="container">
        <h2 class="center-align">Informe de movimientos por provincia</h2>
        
<div style="margin-bottom: 100px;">	
        <canvas id="graficoBarras" width="200" height="200"></canvas>
</div>				
				         
           
            	<%
            	MovimientoLogicImpl logic2 = new MovimientoLogicImpl();
            	
            	//int selectedValue = Integer.parseInt(request.getParameter("selectProv"));
            	//int selectedValue = 0; 
            	//if (selectedValueParam != null && !selectedValueParam.isEmpty()) {
            	//    selectedValue = Integer.parseInt(selectedValueParam);
            	//}
     			//int selectedValue= obtainSelectedValue();
            	//ArrayList<Integer> data2 = logic2.getInformeProvincia(14);
            	ArrayList<Integer> data2 = (ArrayList<Integer>) request.getAttribute("informe");   
            	int TP1 = 1;
            	int TP2 = 1;
            	int TP3 = 1;
            	int TP4 = 1;
            	if(data2 !=null && !data2.isEmpty()){
            	TP1 = data2.get(0);
            	TP2 = data2.get(1);
            	TP3 = data2.get(2);
            	TP4 = data2.get(3);
            	}
            	%>
            	
        <script>
            // Datos de prueba, luego seran reemplazados por los datos obtenidos de la base de datos
          var datosTransacciones = { 
                labels: ["Altas de cuenta", "Altas de prestamo", "Pagos de prestamo", "Transferencias"], // Nombres de los tipos de transacciones
                datasets: [{
                    data: [<%= TP1 %>, <%= TP2 %>, <%= TP3 %>, <%= TP4 %>], // Cantidad de transacciones por cada tipo
                    backgroundColor: ["rgba(255, 99, 132, 0.5)", "rgba(54, 162, 235, 0.5)", "rgba(255, 206, 86, 0.5)","rgba(0, 255, 0, 0.5)"],
                    borderColor: ["rgba(255, 99, 132, 1)", "rgba(54, 162, 235, 1)", "rgba(255, 206, 86, 1)","rgba(0, 255, 0, 0.5)"],
                    borderWidth: 1
                }]
            };
           
            // Config del grafico de barras
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

            // contexto del canvas y grafico
            var ctx = document.getElementById('graficoBarras').getContext('2d');
            var graficoBarras = new Chart(ctx, {
                type: 'bar',
                data: datosTransacciones,
                options: opcionesGrafico
            });
        </script>
        </div>

   	
	
		
    </main>
	<%@ include file="../res/web/dialog.part.html" %>
	<%@ include file="../res/web/snackbar.part.html" %>
	<script type="module" src="./../res/controller/default.controller.js"></script>
</body>
</html>