/* 
/*
    scriptGrupos.java

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
*/


function initGrupos(){
    cargarGruposDisponibles();
    cargarMisGrupos();
    //setTim(actualizarPersona(), 3000);
}

function cargarGruposDisponibles(){
    loadJSON(solicitarTablaGruposDisponibles, "ServicioListarGrupos?listar=disponibles");
}

function solicitarTablaGruposDisponibles(textoJSON){
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaGruposDisponibles");
    
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
            nuevaCelda.innerHTML = "<a href=\"GestionarGrupo?accion=unir&idgrupo=" + tabla.datos[i].id + "\">Unir</a>";
        }
    }
}

function cargarMisGrupos(){
    loadJSON(solicitarTablaMisGrupos, "ServicioListarGrupos?listar=idEstudiante");
}

function solicitarTablaMisGrupos(textoJSON){
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaMisGrupos");
    
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
            nuevaCelda.innerHTML = "<a href=\"GestionarGrupo?accion=salir&idgrupo=" + tabla.datos[i].id + "\">Salir</a>";
        }
    }
}