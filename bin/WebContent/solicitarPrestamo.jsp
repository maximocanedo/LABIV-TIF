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
							<span class="card-title"> Solicitud de prestamo</span>
							<p><strong>Nombre:</strong> Daniel Rodríguez</p>
                  		    <p><strong>DNI:</strong> 30457881</p>
                    		<p><strong>CUIL:</strong> 20-30457881-5</p>
                    		<p><strong>Dirección:</strong> av. Entre Ríos Piso:2 Dep:A</p>
                    		<p><strong>Telefono:</strong> +54 3489 791496</p>
                    		<p><strong>Email:</strong> Daniel@gmail.com</p>
                    		
                    	 	<span class="card-title">Información de Préstamo</span>
                    	 	  <div class="input-field">
                        <input id="monto" type="text">
                        <label for="monto">Monto del Préstamo</label>
                    </div>
                    <div class="input-field">
                        <input id="plazo" type="text">
                        <label for="plazo">Plazo (en meses)</label>
                    </div>
                    <!-- Combobox para seleccionar la cuenta -->
                    <div class="input-field">
                        <select id="cuenta" required>
                            <option value="" disabled selected>Selecciona una cuenta</option>
                            <option value="cuenta1">Cuenta 1</option>
                            <option value="cuenta2">Cuenta 2</option>
                        </select>
                        <label for="cuenta">Cuenta a depositar el prestamo</label>
                    </div>
                    <div>
                    <a class="waves-effect waves-light btn" href="#">Solicitar Préstamo</a>
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

</html>