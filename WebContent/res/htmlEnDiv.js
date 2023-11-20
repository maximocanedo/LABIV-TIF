function htmlEnDiv(file, elementId) {
    /*Esto permite que una página web actualice
    solo una parte de una página sin interrumpir lo que el usuario está haciendo.
    https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest
    Es asincrona*/

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange/*se ejecuta cuando cambia estado de solicitud*/

        = function () {
        //4. solicitud completa
        //200. respuesta exitosa del servidor
        if (this.readyState == 4 && this.status == 200) {
            //cargo el div
            document.getElementById(elementId).innerHTML = this.responseText;
            $(document).ready(function () {
                $(".dropdown-trigger").dropdown();
            });
        }
    };
    xhttp.open("GET", file, true);//para abrir solicitud
    xhttp.send();//para enviar solicitud
}