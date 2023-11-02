<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script src="htmlEnDiv.js"></script>

</head>
<body>
<div id="nav"></div>
 

 <div class="transparent-bg"></div>
 
<div class="container">
	<div class = "row"> 
		<div class="col s12">
			<div class="card">
				<div class="card-content">
					<span class="card-title"> Listado de Préstamos solicitados</span>
        <!-- <h4>Listado de Préstamos solicitados</h4>      -->
        <table class="striped">
            <thead>
                <tr>
                    <th>Cliente</th>
                    <th>Monto</th>
                    <th>Tasa de Interés</th>
                    <th>Plazo</th>
                    <th>Pago por mes</th>
                    <th>Fecha de Solicitud</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Juan Pérez</td>
                    <td>$5,000.000</td>
                    <td>5%</td>
                    <td>12 meses</td>
                    
                    <td>$416.000</td>
                    <td>2023-10-15</td>
                    <td>
                        <button class="waves-effect waves-light btn">Aceptar</button>
                        <button class="waves-effect waves-light btn red">Rechazar</button>
                    </td>
                </tr>
                <tr>
                    <td>María Gómez</td>
                    <td>$10,000.000</td>
                    <td>4.5%</td>
                    <td>24 meses</td>
                    
                    <td>$416.000</td>
                    <td>2023-10-20</td>
                    <td>
                        <button class="waves-effect waves-light btn">Aceptar</button>
                        <button class="waves-effect waves-light btn red">Rechazar</button>
                    </td>
                </tr>
                <tr>
                	<td>Mario Bustamante</td>
                	<td>$6,000,000</td>
                	<td>4,5%</td>
                	<td>12 meses</td>
                	
                	<td>$500.000</td>
                	<td>2023-10-19</td>
                	<td>
                        <button class="waves-effect waves-light btn">Aceptar</button>
                        <button class="waves-effect waves-light btn red">Rechazar</button>
                    </td>
                </tr>
                <tr>
                	<td>Alejandro Peña</td>
                	<td>$7,000,000</td>
                	<td>4,5%</td>
                	<td>12 meses</td>
                	
                	<td>$583.000</td>
                	<td>2023-10-19</td>
                	<td>
                        <button class="waves-effect waves-light btn">Aceptar</button>
                        <button class="waves-effect waves-light btn red">Rechazar</button>
                    </td>
                </tr>             
            </tbody>
        </table>
        
        			</div>
          		</div>
          </div>
     </div>
</div>



 
 
    <div id="footer"></div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

    <script>htmlEnDiv("Footer.html", "footer");</script>

    <script>htmlEnDiv("NavCliente.html", "nav");</script>



</body>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('select');
        var instances = M.FormSelect.init(elems);
    });
</script>

</html>