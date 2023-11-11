# Conectando con servlets
Ya teniendo los servlets listos, ahora es tiempo de conectarlos con las páginas web.

# Creando y vinculando un JS 
No saltarse esta parte. Dado que trabajamos con módulos no se incluyen los archivos de la misma forma.

Al final de la página, dentro del `<body>` deben incluir un `<script type="module" src=" [ Ruta del JS ] "></script>`.
Les debe quedar así:
```html
<html>
  <head> { ... } </head>
  <body>
    { ... }  
    <script type="module" src="./../res/controller/mipagina.controller.js"></script>
  </body>
</html>
```

# Importar y exportar
Primero van a tener que importar la librería `auth`. 
Esta se encuentra en `/WebContents/res/data/auth.js`. 

También querrán usar la librería `material` que incluye funciones y acceso a controles MD que hice con el fin de hacerlo más práctico.
Esta se encuentra en `/WebContents/controller/mdc.controller.js`

A la hora de importar tienen que tener en cuenta qué pueden importar. En el caso de los últimos dos módulos, y para menos problemas, permití que puedan importar todo de una.
Para importar se incluye la palabra clave `import`, lo que se va a importar, seguido de un `from` y la ruta **relativa** al JS. 
Significa que si estás en `/WebContents/res/controller/mipagina.controller.js` y querés importar la lirería `auth`, vas a tener que usar `./../data/auth.js`

Te debería quedar así:
```js
"use strict";
import * as auth from "./../data/auth.js"; // Cambiar dependiendo de dónde te encuentres.
import * as material from "./../controller/mdc.controller.js";
```

Por lo general los scripts de tipo módulo no se ejecutan bien automáticamente. 
Para hacer eso, hay que agregar lo que sería el equivalente al método **main** en otros lenguajes, de la siguiente manera:
```js
(async () => {
    /* Tu código acá */
    /* Esto se ejecutará apenas se cargue la página */
})();
```
A esta función la llamaremos "función principal" de ahora en más. 
## Autenticación
Algunos endpoints requieren autenticación previa por parte de un administrador o cliente para funcionar bien. 
A continuación, formas de saber quién es el admin/cliente actual.

### Clientes
La función `allowClient` verifica si un cliente tiene iniciada la sesión. En caso de **no tener iniciada** la sesión, lo redirige a la página de inicio de sesión, la cual está definida en la librería `auth`. 
```js
const cliente = await auth.allowClient();
```
También podés redirigirlo y enviarle un mensaje personalizado, de la siguiente manera:
```js
const cliente = await auth.allowClient({
    message: "Iniciá sesión como cliente para acceder a este recurso. "
});
```
### Administradores
La función `allowAdmin` verifica si un administrador tiene iniciada la sesión. En caso de **no tener iniciada** la sesión, lo redirige a la página de inicio de sesión, la cual está definida en la librería `auth`. 
```js
const admin = await auth.allowAdmin();
```
También podés enviarle un mensaje, de paso:
```js
const admin = await auth.allowAdmin({
    message: "Iniciá sesión como administrador para acceder a este recurso. "
});
```
### ¿Ambos?
Si en tu página por ejemplo tenés que ejecutar código diferente en función del rol del usuario, la función `whoIam` te será de ayuda.
```js
const whoIam = await auth.whoIam();
if(whoIam == null) {
    // No hay usuario en sesión.
    // Terminamos esta función con el return.
    return;
} else {
    // Hay un usuario con sesión, ahora veremos qué rol tiene.
    const data = whoIam.data;
    const role = data.message; // "ADMIN" o "CLIENT" son los dos valores posibles que debería tener.
    const usuario = data.objectReturned; // Datos del cliente/admin en sesión, como nombre, apellido, nombre de usuario, etc. 
    switch(role) {
        case "ADMIN":
            /* Código que se ejecutará si es admin. */
            alert("¡Sos un admin!");
            break;
        case "CLIENT":
            /* Código que se ejecutará si es cliente. */
            alert("¡Sos un cliente!");
            break;
    }
}
```
### Obtener el pase digital (Token)
Antes de entrar en el tema `fetch`, acordate que la API se vale de un pase digital (aka Token JWT). Vas a necesitar usarlo para hacer peticiones que requieran autenticar.
Cómo acceder: 
```js
const pase = auth.AUTH_HEADER;
```

## Fetch
Ahora que sabés cómo autenticar en la página, queda lo más importante, ¡Hacer peticiones!
### Estructura básica
#### Comenzando el fetch
```js
const res = await fetch(
    "http://localhost:8080/TPINT_GRUPO_3_LAB/api/provinces/list",
    {
        method: "GET"
    }
);
```
#### Obteniendo el código de respuesta
Podés obtener el código de respuesta de la petición (200, 404, 401, 500, etc.) de la siguiente forma:
```js
const codigo = res.status;
```
#### Obteniendo el cuerpo de la respuesta
El cuerpo de la respuesta es todo el JSON que la mayoría de los endpoints del TP devuelven. Se accede de la siguiente forma:
```js
const data = await res.json();
```
#### Manejar errores
Para esto podés usar un `try-catch` de toda la vida, rodeando al fetch.
```js
try {    
    const res = await fetch(
        "http://localhost:8080/TPINT_GRUPO_3_LAB/api/provinces/list",
        {
            method: "GET"
        }
    );
    const codigo = res.status;
    const data = await res.json();
} catch(err) {
    console.log(err);
    return;
}
```
## Ejemplos
### Obtener lista de provincias
**No requiere autenticación.** Ejemplo de cómo hacer esto:
```js
const req = await fetch("http://localhost:8080/TPINT_GRUPO_3_LAB/api/provinces/list", {
  method: "GET",
});
const code = req.status
const json = await req.json();
const listaDeProvincias = json.listReturned;
```
### Obtener lista de clientes
**Sólo administradores**. Asegurate primero de que un admin haya iniciado sesión. 
```js
const req = await fetch(
  "http://localhost:8080/TPINT_GRUPO_3_LAB/api/clients?q=&p=1&s=3", {
  method: "GET",
  headers: auth.AUTH_HEADER // Adjuntamos el pase, para autenticarnos.
});
const code = req.status
const json = await req.json();
const listaDeClientes = json.listReturned;
```
### Cambiar datos del cliente actual (Ejemplo de PUT)
**Sólo clientes**. Ejemplo de petición con un body.
```js
const nuevosDatosAIngresar = {
    direccion: "Avenida Siempreviva 783"
};
const req = await fetch(
    "http://localhost:8080/TPINT_GRUPO_3_LAB/api/client", {
    method: "PUT", // Ya que estamos editando datos personales
    headers: auth.AUTH_HEADER, // Adjuntamos el pase, para autenticarnos.
    body: JSON.stringify(nuevosDatosAIngresar) // Adjuntamos el body con los parámetros a enviar.
});
const code = req.status; // 200, 401, 404, ...
const json = await req.json();
const message = json.message; // "El registro se modificó correctamente. "
```
## Material tricks
### Mostrar un snackbar con mensaje
```js
material.showSnackbar("Hola! Soy un snackbar!");
```
### Mostrar un diálogo de confirmación
```js
const dialog = await material.showDialog(
	"Se actualizarán tus datos personales. "
);
if (!dialog) {
    // El usuario presionó "CANCELAR"
    material.showSnackbar("Cancelaste la operación. ");
} else {
    // El usuario presionó "ACEPTAR".
    material.showSnackbar("Aprobaste la operación. ");
}
```
