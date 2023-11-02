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
 
<div class="center-align-container">
			<div class="row">
				<div class="col s12">
					<div class="card">
						<div class="card-content">
							<span class="card-title">PAGO DE PRESTAMO</span>
                    		<!-- Combobox para seleccionar la cuota -->
                    		<div class="input-field">
                       				 <select id="prestamo" required>
                           			 <option value="" disabled selected>Seleccione una cuota</option>
                          			  <option value="prestamo1">Cuota 1</option>
                          			  <option value="prestamo2">Cuota 2</option>
                          			  <option value="prestamo3">Cuota 3</option>
                          			  <option value="prestamo4">Cuota 4</option>
                          			  <option value="prestamo5">Cuota 5</option>
                          			  <option value="prestamo6">Cuota 6</option>
                     		   		</select>
                       			 <label for="prestamo">Cuenta a depositar el prestamo</label>
                    		</div>
                    		<!-- Combobox para seleccionar cuenta de pago -->
                    		<div class="input-field">
                        			<select id="cuenta" required>
                            			<option value="" disabled selected>Seleccione una cuenta</option>
                            			<option value="cuenta1">Cuenta 1</option>
                            			<option value="cuenta2">Cuenta 2</option>
                        			</select>
                        			<label for="cuenta">Cuenta a usar para pagar</label>
                    		</div>
                    		<div>
                    			<label for="fecha-Actual">Fecha y Hora actual: </label>
                    		</div>
                    		<div class="input-field">
                    			<input class="validate" type="text" id="fechaActual" readonly>
                    		</div>
                    	<div>
                    		<a class="waves-effect waves-light btn" href="#">Pagar</a>
                    	</div>
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

<script>
    
    var fechaActual = new Date();
    
    var options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
    document.getElementById("fechaActual").value = fechaActual.toLocaleString('es-AR', options);
</script>

</html>