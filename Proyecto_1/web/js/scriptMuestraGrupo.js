
function initMuestra(){
    conmpruebaLogin();
    cargarGrupos();
   
}
 function cargarGrupos(){
   
    loadJSON(actualizGrupo,"ServicioMostrarGrupos");
   setTimeout(cargarGrupos, 1000); 
}
function actualizGrupo(textoJSON){
    var refDiv = document.getElementById("contents");
    var tabla = JSON.parse(textoJSON);
    if(refDiv){
         while (refDiv.hasChildNodes()){
            refDiv.removeChild(refDiv.firstChild);
        }
        for (var i = 0; i < tabla.datos.length; i++) {
            var newDiv = document.createElement("div");
            newDiv.setAttribute("class","divTablas");
            var tablaGrupo   = document.createElement("table");
            tablaGrupo.setAttribute("class","tablaGrupo");
            var tblBody = document.createElement("tbody");
            tblBody.setAttribute("class","tablasBody");
            var nuevaFila = tblBody.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.setAttribute("class","grupoTabla");
            nuevaCelda.setAttribute("COLSPAN","2");
            nuevaCelda.innerText = "Grupo "+tabla.datos[i].id;
      
            nuevaFila = tblBody.insertRow(-1);
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.setAttribute("class","nombreTabla");
            nuevaCelda.setAttribute("COLSPAN","2");
            nuevaCelda.innerText = tabla.datos[i].nombre;
            var lista = tabla.datos[i].estudiantes;
            for (var j = 0; j < lista.length;j++) {
                nuevaFila = tblBody.insertRow(-1);
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = lista[j].apellidos;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = lista[j].nombre;
            }
            tablaGrupo.appendChild(tblBody);
            newDiv.appendChild(tablaGrupo);
            refDiv.appendChild(newDiv);
        } 
    }
}