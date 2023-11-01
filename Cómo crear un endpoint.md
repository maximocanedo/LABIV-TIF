# Cómo crear un endpoint para este TP
## Al crear la clase Servlet...
No heredamos directamente de `HttpServlet`, sino de `servlets.BaseServlet`, ya que este último es una clase base que hereda de `HttpServlet` y tiene algunas funcionalidades más.

Ejemplo
```java
public class MiServlet extends BaseServlet implements Servlet {
/* ... */
}
```
## Importante
Al hacer esto deberán implementar una función `getAllowedMethods()` que devuelve un array de String con todos los métodos que se usan en el Servlet.
Recomiendo actualizarlo cada vez que agreguen un nuevo endpoint, de lo contrario no les dejará entrar.
```java
@Override
protected String[] getAllowedMethods() {
  return new String[] { "GET", "POST", "DELETE" };
}
```

## Antes de crear un endpoint...
Decidir qué método HTTP van a implementar. 
Las opciones son:

  - `GET`: Generalmente se usa para *obtener información* de un recurso. Por ejemplo, una *lista de clientes*, *resultados de búsqueda*, etc.
  - `POST`: Se usa generalmente para *añadir recursos*, o para *recibir formularios extensos*. Por ejemplo, *crear un nuevo préstamo*, *añadir una cuenta*, *iniciar sesión*, etc.
  - `PUT`: "El editor". Se usa generalmente para *editar un recurso*. Por ejemplo, para *editar datos personales de un cliente*, *actualizar información sobre un recurso*, etc.
  - `PATCH`: "El editor ligero". Igual que el anterior, pero para *cambios ligeros y rápidos*. Por ejemplo, *cambiar contraseña*, *cambiar total de algo*, *cambiar un dato*, etc.
  - `DELETE`: Se usa generalmente para *eliminar un recurso*. Ejemplo, *deshabilitar una cuenta*, *eliminar un mensaje*, etc.

Luego deben definir qué URL va a usar.
Lo recomendado, es usar esta estructura: 

`/api/{clase_registro}/{acción/propiedad}/{valor}`.

Por ejemplo:

`/api/clientes`. Podría usarse para obtener listados de clientes, o hacer operaciones CRUD.

`/api/clientes/login`. Se usa para iniciar sesión. 

`/api/clientes/username/hector1984`. Se usa para obtener información de un cliente **@hector1984**, y hacer operaciones CRUD con esa cuenta.


## ¿Cómo crear un endpoint en el Servlet?
Una vez sepas qué URL tendrá el Servlet, y qué método querés usar, en la clase `MiServlet`, vas a crear un método `do{Método}` en base al que hayas elegido.

Por ejemplo, si vas a usar el método `GET`, tu método tendría que verse así:
```java
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /* Código que se ejecutará ante una Petición GET a la URL del servlet. */
  }
```

Si vas a hacer, en cambio, un método `POST`, por ejemplo, sólo tendrías que cambiar el nombre del método anterior, `doGet`, por `doPost`.
```java
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /* Código que se ejecutará ante una Petición POST a la URL del servlet. */
  }
```

Acordate siempre de agregar los métodos con los que funciona la API en la función `getAllowedMethods()`.

## ¿Cómo respondo a un endpoint?
Podés responder con un código `HTTP`, y, opcionalmente, un `JSON`, si tu endpoint devuelve datos, como por ejemplo, un listado, o información sobre un registro.

Atajo: [Códigos de respuesta HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)

**No es necesario** devolver un `JSON` por cada operación. Si por ejemplo, estás haciendo un endpoint que elimina registros, bastaría con devolver un código `HTTP` 200, ó 500.

**Siempre** que devuelvas un JSON, devolvelo mediante un `LogicResponse<TuClase>`. Esta clase tiene un método `toFinalJSON()` que deberás usar para devolver datos.

Ejemplo de uso para un endpoint **sin cuerpo** (Sin JSON, sólo código de respuesta.):
```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(inicioSesion) {
			response.setStatus(200);
		} else {
			response.setStatus(401);
		}
	}
```
Ejemplo de uso para un endpoint **que devuelve datos**:
```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogicResponse<Pais> res = logic.getAll(); // Hacés el llamado a lógica
		String json = res.toFinalJSON(); // Al resultado lo convertís en un JSON en formato String
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json); // ... Y lo devolvés.
	}
```

## Autenticar en un endpoint
Si tu endpoint necesita que haya un **administrador** en sesión para correr, usá este modelo:
```java
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  Administrador actualAdmin = AuthManager.getActualAdmin(request, response); // Esto devuelve el objeto Administrador con los datos del admin actual. 
  if(actualAdmin != null) { // Si hay un administrador en sesión actualmente...
    /** Código que se ejecutará tras autenticación exitosa **/
  } // No es necesario hacer un else, porque AuthManager ya devuelve un código de error en caso de fallo de autenticación.
  return;
}
```
Si tu endpoint necesita que haya un **cliente** en sesión para correr, usá este modelo de método:
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  Cliente cliente = AuthManager.getActualClient(request, response); // Esto devuelve el objeto Cliente con los datos del cliente actual.
  if(cliente != null) {
    /** Código que se ejecutará tras autenticación exitosa **/
  } // Tampoco es necesario hacer un else
  return;
}
```
Si en cambio, necesitás **saber** si alguien inició sesión:
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  TokenData td = AuthManager.readToken(request);
  if(td != null) {
    /* Alguien inició sesión. Pero no sabemos si es administrador o cliente. */
  }
  return;
}
```

Si necesitás saber qué **tipo de usuario** es el que inició sesión, por ejemplo, para ejecutar diferente código en base a eso:
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  /* Verificar si es cliente o admin */
  TokenData td = AuthManager.readToken(request);
  if(td != null) {
    switch(td.role) {
    case AuthManager.ADMIN:
      /* Código que se ejecutará si el usuario en sesión es un administrador. */
      break;
    case AuthManager.CLIENT:
      /* Código que se ejecutará si el usuario en sesión es un cliente. */
      break;
    default:
      response.setStatus(403); // Devolver HTTP 403 Forbidden si no es ninguno de los dos.
      break;
    }
  } else {
    response.setStatus(401); // Devolver HTTP 401 Unauthorized si nadie inició sesión.
  }
  return;
}
```

## Herramientas extra
### Obtener parámetros enviados mediante el cuerpo de la petición:
```java
Dictionary parameters = getParameters(request);

// Si no hay parámetros o están mal organizados, enviar un HTTP 400.
if(parameters == null) {
  die(response, false, 400, "Bad request");
  return;
}
```

### Obtener el parámetro especial enviado por URL.

Si tenés un servlet ```/api/registro/*```, y recibís una petición con un ID ```/api/registro/38``` y querés acceder a ese ID enviado en la URL:
```java
String id = getPathParameter(request);
print(id); // Imprime "38"
```


