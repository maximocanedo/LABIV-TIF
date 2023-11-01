<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Movimientos</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css'>
    <link rel='stylesheet' href='https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="estiloPaginaCliente-Administrador.css">
    <script src="htmlEnDiv.js"></script>
  </head>
  <body >
    <div id="nav"></div>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="95%">
      <thead>
        <tr>
          <th>Cuenta</th>
          <th>Tipo de movimiento</th>
          <th>Detalle</th>
          <th>Fecha</th>
          <th>Importe $</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Cuenta 1</td>
          <td>Deposito</td>
          <td>Detalle 61</td>
          <td>2011-04-25</td>
          <td>$320,800</td>
        </tr>
        <tr>
          <td>Cuenta 2</td>
          <td>Retiro</td>
          <td>Detalle 63</td>
          <td>2011-07-25</td>
          <td>$170,750</td>
        </tr>
        <tr>
          <td>Cuenta 3</td>
          <td>Retiro</td>
          <td>Detalle 66</td>
          <td>2009-01-12</td>
          <td>$86,000</td>
        </tr>
        <tr>
          <td>Cuenta 4</td>
          <td>Retiro</td>
          <td>Detalle 22</td>
          <td>2012-03-29</td>
          <td>$433,060</td>
        </tr>
        <tr>
          <td>Cuenta 5</td>
          <td>Deposito</td>
          <td>Detalle 33</td>
          <td>2008-11-28</td>
          <td>$162,700</td>
        </tr>
        <tr>
          <td>Cuenta 6</td>
          <td>Retiro</td>
          <td>Detalle 61</td>
          <td>2012-12-02</td>
          <td>$372,000</td>
        </tr>
        <tr>
          <td>Cuenta 7</td>
          <td>Deposito</td>
          <td>Detalle 59</td>
          <td>2012-08-06</td>
          <td>$137,500</td>
        </tr>
        <tr>
          <td>Cuenta 8</td>
          <td>Deposito</td>
          <td>Detalle 55</td>
          <td>2010-10-14</td>
          <td>$327,900</td>
        </tr>
        <tr>
          <td>Cuenta 9</td>
          <td>Deposito</td>
          <td>Detalle 39</td>
          <td>2009-09-15</td>
          <td>$205,500</td>
        </tr>
        <tr>
          <td>Cuenta 10</td>
          <td>Deposito</td>
          <td>Detalle 23</td>
          <td>2008-12-13</td>
          <td>$103,600</td>
        </tr>
        <tr>
          <td>Cuenta 11</td>
          <td>Retiro</td>
          <td>Detalle 30</td>
          <td>2008-12-19</td>
          <td>$90,560</td>
        </tr>
        <tr>
          <td>Cuenta 12</td>
          <td>Retiro</td>
          <td>Detalle 22</td>
          <td>2013-03-03</td>
          <td>$342,000</td>
        </tr>
        <tr>
          <td>Cuenta 13</td>
          <td>Deposito</td>
          <td>Detalle 36</td>
          <td>2008-10-16</td>
          <td>$470,600</td>
        </tr>
        <tr>
          <td>Cuenta 14</td>
          <td>Retiro</td>
          <td>Detalle 43</td>
          <td>2012-12-18</td>
          <td>$313,500</td>
        </tr>
        <tr>
          <td>Cuenta 15</td>
          <td>Retiro</td>
          <td>Detalle 19</td>
          <td>2010-03-17</td>
          <td>$385,750</td>
        </tr>
        <tr>
          <td>Cuenta 16</td>
          <td>Retiro</td>
          <td>Detalle 86</td>
          <td>2012-11-27</td>
          <td>$198,500</td>
        </tr>
        <tr>
          <td>Cuenta 17</td>
          <td>Retiro</td>
          <td>Detalle 99</td>
          <td>2010-06-09</td>
          <td>$725,000</td>
        </tr>
        <tr>
          <td>Cuenta 18</td>
          <td>Retiro</td>
          <td>Detalle 59</td>
          <td>2009-04-10</td>
          <td>$237,500</td>
        </tr>
        <tr>
          <td>Cuenta 19</td>
          <td>Retiro</td>
          <td>Detalle 88</td>
          <td>2001-09-01</td>
          <td>$132,000</td>
        </tr>
        <tr>
          <td>Cuenta 20</td>
          <td>Deposito</td>
          <td>Detalle 11</td>
          <td>2012-09-26</td>
          <td>$217,500</td>
        </tr>
        <tr>
          <td>Cuenta 21</td>
          <td>Deposito</td>
          <td>Detalle 30</td>
          <td>2011-09-03</td>
          <td>$345,000</td>
        </tr>
        <tr>
          <td>Cuenta 22</td>
          <td>Deposito</td>
          <td>Detalle 40</td>
          <td>2009-06-25</td>
          <td>$675,000</td>
        </tr>
        <tr>
          <td>Cuenta 23</td>
          <td>Deposito</td>
          <td>Detalle 23</td>
          <td>2011-12-12</td>
          <td>$106,450</td>
        </tr>
        <tr>
          <td>Cuenta 24</td>
          <td>Deposito</td>
          <td>Detalle 73</td>
          <td>2010-09-20</td>
          <td>$85,600</td>
        </tr>
        <tr>
          <td>Cuenta 25</td>
          <td>Deposito</td>
          <td>Detalle 47</td>
          <td>2009-10-09</td>
          <td>$1,200,000</td>
        </tr>
        <tr>
          <td>Cuenta 26</td>
          <td>Deposito</td>
          <td>Detalle 42</td>
          <td>2010-12-22</td>
          <td>$92,575</td>
        </tr>
        <tr>
          <td>Cuenta 27</td>
          <td>Retiro</td>
          <td>Detalle 28</td>
          <td>2010-11-14</td>
          <td>$357,650</td>
        </tr>
        <tr>
          <td>Cuenta 28</td>
          <td>Retiro</td>
          <td>Detalle 28</td>
          <td>2011-06-07</td>
          <td>$206,850</td>
        </tr>
        <tr>
          <td>Cuenta 29</td>
          <td>Retiro</td>
          <td>Detalle 48</td>
          <td>2010-03-11</td>
          <td>$850,000</td>
        </tr>
        <tr>
          <td>Cuenta 30</td>
          <td>Retiro</td>
          <td>Detalle 20</td>
          <td>2011-08-14</td>
          <td>$163,000</td>
        </tr>
        <tr>
          <td>Cuenta 31</td>
          <td>Retiro</td>
          <td>Detalle 999</td>
          <td>2011-06-02</td>
          <td>$95,400</td>
        </tr>
        <tr>
          <td>Cuenta 32</td>
          <td>Retiro</td>
          <td>Detalle 22</td>
          <td>2009-10-22</td>
          <td>$114,500</td>
        </tr>
      </tbody>
    </table>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
    <script src='https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.2.2/js/buttons.colVis.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js'></script>
    <script src='https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js'></script>
    <script src='https://cdn.datatables.net/buttons/1.2.2/js/buttons.bootstrap.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js'></script>
    <script src='https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js'></script>
    <script src='https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js'></script>
    <script id="rendered-js" >
      $(document).ready(function () {
        document.title = 'Movimientos';
        $('#example').DataTable(
        {
          "dom": '<"dt-buttons"Bf><"clear">lirtp',
          "paging": true,
          "autoWidth": true,
          "responsive": true,
          "buttons": [
          'colvis',
          'copy',
          'csv',
          'excel',
          'print'] });
      });
    </script>
    <div id="footer"></div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script>htmlEnDiv("Footer.html", "footer");</script>
    <script>htmlEnDiv("NavCliente.html", "nav");</script>
  </body>
</html>
