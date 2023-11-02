# JSON Web Token
Un token JWT es como un pase virtual que te permite acceder a ciertos recursos de un sitio web o aplicación. Imaginalo como una credencial digital que te identifica. 
Está compuesto por tres partes: el encabezado, el cuerpo y la firma.

## Partes del token
  - **Encabezado**: Esta parte del token indica el tipo de token y el algoritmo utilizado para firmarlo. Es como la etiqueta en tu credencial que dice qué tipo de documento es.
  - **Cuerpo**: El cuerpo del token contiene información, llamada carga útil, que describe quién eres o qué puedes hacer. Por ejemplo, puede incluir tu nombre de usuario o tu ID de usuario.
  - **Firma**: La firma es como un sello que garantiza que el token es válido y no ha sido alterado. Se crea utilizando una clave secreta conocida solo por el servidor. Si alguien intenta cambiar el token, la firma ya no coincidirá, y el servidor lo rechazará.

## ¿Cómo se relaciona con el inicio de sesión?
  1. **Ingresar credenciales**: Cuando ingresás tu nombre de usuario y contraseña en una página de inicio de sesión, el servidor verifica si son correctos.
  2. **Generar un token JWT**: Si tus credenciales son válidas, el servidor crea un token JWT para vos. En la carga útil del token (Payload, body), se incluyen datos como tu nombre de usuario y tu rol (por ejemplo, "cliente" o "administrador").
  3. **Enviar el token al cliente**: El servidor envía este token de vuelta a tu navegador. Tu navegador lo almacena, generalmente en las cookies o en el almacenamiento local.
  4. **Usar el token en solicitudes posteriores**: Cuando quieras acceder a una parte protegida de la aplicación, como tu perfil, tu navegador envía el token JWT junto con la solicitud. El servidor verifica la firma del token para asegurarse de que sea válido y, si lo es, te permite acceder a los recursos protegidos.
  5. **Expiración y renovación**: Los tokens JWT de este trabajo tienen una fecha de vencimiento a una hora posterior a su emisión. Una vez que expira, tenés que iniciar sesión nuevamente para obtener un nuevo token. Esto es importante para la seguridad.

## Ejemplo
Si iniciás sesión como María Griselda (Cliente), el sistema generará un token para autenticarte que se verá así:
```jwt
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYXJpYV8xMjE0NDE2NSIsInJvbGUiOiJDTElFTlQiLCJpc3MiOiJHUlVQTzNMQUIiLCJpYXQiOjE2OTg1OTQyNDIsImV4cCI6MTY5ODU5Nzg0Mn0.rB0J8bseQCI5DZOkiHIQi0iqjv0XPvffvCU9wqJ4-G4
```
Este token contiene los siguientes datos:
**Cabecera**:
```json
{
  "alg": "HS256"
}
```
Contiene el algoritmo usado para encriptar el JSON.

**Cuerpo**:
```json
{
  "sub": "Maria_12144165",
  "role": "CLIENT",
  "iss": "GRUPO3LAB",
  "iat": 1698594242,
  "exp": 1698597842
}
```
Contiene el usuario logueado, el rol asignado, el emisor, y las fechas de emisión y expiración.

**Firma**: 
`rB0J8bseQCI5DZOkiHIQi0iqjv0XPvffvCU9wqJ4-G4` 
Es una firma con la cual se valida que el contenido anterior no se haya alterado.

## ¿Es posible alterar un token?
No. Porque la cabecera y el cuerpo del token están como "encriptados" con una firma hecha con una clave privada que el servidor renueva en cada reinicio.
Si se alterara la cabecera, por ejemplo, la firma ya no coincidiría y el token sería tachado como **inválido** o **corrupto**.

## ¿Cómo acceder y leer un token desde Postman?
### Primer paso: Iniciar sesión
Iniciá sesión con credenciales válidas, ya sea como administrador o como cliente.
![image](https://github.com/maximocanedo/LABIV-TIF/assets/103539894/37a7d141-81da-4c2c-84f1-7177a94451fa)

### Segundo paso: Buscar y copiar el token obtenido
Si las credenciales son correctas, y se devuelve un `HTTP 200`, fijate en la pestaña **Headers** y en la sección **Authorization**. Ahí está el token generado.
Copialo.
![image](https://github.com/maximocanedo/LABIV-TIF/assets/103539894/ba22c3ff-4f05-4052-ba0c-66e47a787d02)

### Tercer paso: Leer el token
Pegá el token generado en [jwt.io](https://jwt.io) y ahí podrás leer la información que lleva el token.

![image](https://github.com/maximocanedo/LABIV-TIF/assets/103539894/d09529f9-158e-40d0-85ec-218bb092f318)


