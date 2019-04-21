var ordenar =1;
function initUsuarios(){
    actualizarPersona();
    console.log("Aplicación inicializada..");
     
}
function actualizarPersona() {
    console.log("Actualizando..");
    cargarEstudiantes();
    //setTimeout(actualizarPersona(), 3000);
    
}
function cargarEstudiantes(){
    loadJSON(solicitarTabla,"ServicioEstudiante?ordenar=" + String(ordenar));
    
}


function solicitarTabla(textoJSON){
    var tabla = JSON.parse(textoJSON);
    var refTabla = document.getElementById("datosTablaUsuarios");
     if(refTabla){
            while (refTabla.rows.length>0){
                refTabla.deleteRow(0);
            }
            for (var i = 0; i < tabla.datos.length; i++) {
                var nuevaFila = refTabla.insertRow(-1);
                var nuevaCelda;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].secuencia;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].grupo;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].nrc;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].id;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].apellidos;
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.innerText = tabla.datos[i].nombre;
                var b = tabla.datos[i].activo;
                if(!b){
                    nuevaCelda.setAttribute("class","especial2");
                }
                else {  nuevaCelda.setAttribute("class","especial1");}
                nuevaCelda = nuevaFila.insertCell(-1);
                nuevaCelda.setAttribute("class", b ? "especial1" : "especial2");
                nuevaCelda.innerHTML = b ? "&#10004;" : "&#10006;";
            }
        }
}

function ordena(){
    var datos = event.target;
    var id = datos.id;
    var celda = document.getElementById(id);
    if(celda.getAttribute("id")==="OrdenaNRC"){ordenar =1;}
    if(celda.getAttribute("id")==="OrdenaId"){ordenar =2;}
    if(celda.getAttribute("id")==="OrdenaApelligos"){ordenar =3;}
    if(celda.getAttribute("id")=== "OrdenaNombre"){ordenar = 4;}
     if(celda.getAttribute("id")=== "OrdenaGrupo"){ordenar = 5;}
    cargarEstudiantes();
}



