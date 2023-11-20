<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cuentas Cliente</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="estiloPaginaCliente-Administrador.css">
    <link rel="stylesheet" type="text/css" href="estiloDatosCliente.css">
    <link rel="stylesheet" type="text/css" href="estiloTablaTransferencia.css">
    <link rel="stylesheet" type="text/css" href="estilocuentaCliente.css">
    <script src="htmlEnDiv.js"></script>
</head>
<body>

<div id="nav"></div>

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
<div id="footer"></div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>htmlEnDiv("Footer.html", "footer");</script>
<script>htmlEnDiv("NavCliente.html", "nav");</script>
</body>
</html>