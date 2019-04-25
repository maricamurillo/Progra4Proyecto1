/* global request */

function conmpruebaLogin(){
   cargarUsuario();
}

function cargarUsuario(){
    loadJSON(comprueba, "ServicioCompruebaLogin");
}

function comprueba(textoJSON){
    var usuario = textoJSON;
    if(usuario){
        request.getRequestDispatcher("index.html");
    }
    
}

