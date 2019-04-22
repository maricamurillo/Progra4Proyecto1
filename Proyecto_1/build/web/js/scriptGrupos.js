/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function initGrupos(){
    cargarGrupos();
}

function cargarGrupos(){
    loadJSON(solicitarTablaGrupos, "ServicioListarGrupos");
}

function solicitarTablaGrupos(textoJSON){
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaGrupos");
    
    if(refTabla){
        while (refTabla.rows.length>0){
            refTabla.deleteRow(0);
        }
        for (var i = 0; i < tabla.datos.length; i++) {
            var nuevaFila = refTabla.insertRow(-1);
            var nuevaCelda;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].nombre;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerText = tabla.datos[i].cupo;
            nuevaCelda = nuevaFila.insertCell(-1);
            nuevaCelda.innerHTML = "<a href=\"ServicioUnirseGrupo?grupoid=" + tabla.datos[i].id + "\">Unirse</a>";
        }
    }
}