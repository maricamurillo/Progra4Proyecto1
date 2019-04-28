/* global request */

function conmpruebaLogin(){
   cargarUsuario();
}

function cargarUsuario(){
    loadJSON(comprueba, "ServicioCompruebaLogin");
}

function comprueba(textoJSON){
    var usuario = textoJSON;
    if(usuario===-1){
        window.location.replace("index.jsp");
    }
    
}
