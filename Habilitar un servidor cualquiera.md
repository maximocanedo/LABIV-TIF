# Creá tu propio servidor remoto
## Identificá el puerto local de tu servidor
¿Qué puerto usa el programa/aplicación/servidor que estás desarrollando y del cuál querés acceder? 
El puerto es lo que va del lado derecho del dominio, después de los dos puntos (:).

Ejemplo: `http://localhost:8180/`, en esta URL, el puerto es **8180**. 
Si tu URL no viene con el puerto escrito, como en `http://localhost/`, entonces el puerto por defecto es **80**

## Crear una regla de firewall para tu puerto 
El firewall es como un guardaespaldas en tu PC que decide quién entra y quién sale al internet. 
Es muy útil para prevenir la mayoría de hackeos. Pero en este caso le queremos decir que le permita a nuestro puerto ser accedido desde nuestra red local.

Para eso, tenemos que crear una **regla de entrada** hacia el puerto deseado.
Las instrucciones para eso dependen del sistema operativo y el firewall que traiga.
### Windows CMD
```cmd
netsh advfirewall firewall add rule name="Regla para el puerto X" dir=in action=allow protocol=TCP localport=PUERTO_ACÁ enable=yes
```
### Windows gráfico
Buscá "Firewall de Windows", que te llevará a la ventana del panel de control del firewall. En el menú lateral, buscá la opción que diga "Opciones avanzadas". 

Una ventana se abrirá. Ahí buscá en el menú lateral "Reglas de entrada". Click ahí, y luego en el menú lateral derecho, habrá una opción que dice "Agregar regla".
Seguí los pasos y luego dale a "Finalizar".
### Linux
```bash
sudo iptables -A INPUT -p tcp --dport PUERTO_ACÁ -j ACCEPT
```
## Antes de continuar...
Vamos a descubrir qué IP local tenés.

En Windows, ejecutá `ipconfig` y buscá entre las interfaces de red la que usés para conectarte a internet. (Recomendado, ethernet).

Una vez identificada la interfaz, buscá la dirección IPv4, esa es tu dirección IP que te identifica dentro de tu red, y comienza con `192.168`.
Una vez hecho, hacé un ping a esa dirección y comprobá que te cargue.

Si no sabés/podés hacer ping a ese puerto, hacé como que vas a ejecutar tu app como siempre, pero reemplazá `localhost` o `127.0.0.1` por tu dirección IPv4 local.
Si te sigue apareciendo tu app, está todo bien y podés continuar.

## Habilitar el puerto en tu router.
En el mismo `ipconfig` buscá la dirección IP de Gateway, e ingresala en el navegador. Es algo como `192.168.0.1` o similar. 

Te aparecerá la página de configuración del router. Si te pide inicio de sesión y no lo sabés, consultá la etiqueta pegada al router.

Una vez dentro, buscá una opción que sea "Port Forwarding" o similar. 

Ahí el procedimiento es similar a cuando habilitaste el puerto en el firewall. Pero tenés que agregar la dirección local tuya (Dirección IPv4 de tu PC en la red), y el puerto local.

Guardá los cambios.

## Antes de terminar...
Vamos a ver si te funciona.
Entrá a https://whatsmyip.com/ y copiá tu dirección IP pública.

Hacé ping a tu dirección IP pública con tu puerto. Si tu dirección IP pública es 180.180.200.200, y tu puerto es 8080, sería: `ping 180.180.200.200:8080`.

Si no podés hacer ping, intentá acceder a tu aplicación pero reemplazando `localhost` o `127.0.0.1` por tu dirección IP pública.

Si aparece tu app sin problemas, es que tu servidor está conectado a la internet.

¡Listo!
