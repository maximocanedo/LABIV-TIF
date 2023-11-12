# Estructura de páginas de Clientes. 
Las flechas punteadas simbolizan funciones que **aún no están disponibles**.

```mermaid
graph LR;
    Clientes-->Login;
    Clientes-..->logout[Cerrar sesión];
    Login-->id1[Iniciar sesión];
    Login-->Registrarse;
    Clientes-.->Dashboard;
    Clientes-->me[Mi Perfil];
    me-->id3[Modificar mis datos];
    me-->id4[Ver mis datos];
    me-.->cambiarclave[Solicitar cambio de clave]

```

# Estructura de páginas de Administradores. 
Las flechas punteadas simbolizan funciones que **aún no están disponibles**.

```mermaid
graph LR;
    Administradores-->Login;
    Administradores-..->logout[Cerrar sesión];
    Login-->id1[Iniciar sesión];
    Login-->Registrarse;
    Administradores-.->Dashboard;
    Administradores-.->me[Mi Perfil];
    me-.->id3[Modificar mis datos];
    me-.->id4[Ver mis datos];
    me-.->cambiarclave[Solicitar cambio de clave];
    Administradores -. A terminar .->listarClientes[Listar clientes];
    listarClientes -. A rediseñar y paginar .->buscarClientes[Buscar clientes];
    Administradores-.->verCliente[Perfil de un cliente];
    verCliente-.->verCliente1[Ver datos de un cliente];
    verCliente-.->modificarDatosCliente[Modificar datos de un cliente];
    Administradores-.->listarc01[Ver solicitudes de cambio de clave sin aprobar];
    Administradores-.->verc01[Ver solicitud de cambio de clave];
    verc01-.->aprobarc01[Aprobar solicitud];
    verc01-.->desaprobarc01[Desaprobar solicitud];
    

```
