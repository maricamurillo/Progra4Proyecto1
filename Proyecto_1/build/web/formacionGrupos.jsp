<%--
    formacionGrupos.jsp

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptGrupos.js" type="text/javascript"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>Formacion de grupos</title>
    </head>
    <body onload="initGrupos()">
        <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menu.jsp" %>
            </header>
            
            <%
                int status = 0;
                try {
                    status = Integer.parseInt(request.getParameter("status"));
                } catch (NullPointerException | NumberFormatException ex) {

                }
                if (status != 0) {
                    StringBuilder r = new StringBuilder();
                    switch (status) {
                        case 1:
                            r.append("Grupo creado.");
                            out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                        case 2:
                            r.append("Ocurrio un error durante la creacion del grupo. Verifique el nombre del grupo");
                            out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                        case 3:
                            r.append("Unido al grupo.");
                            out.println(String.format("<div class=\"alert success\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                        case 4:
                            r.append("Ya se encuentra unido a un grupo.");
                            out.println(String.format("<div class=\"alert warning\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                        case 5:
                            r.append("Salio del grupo.");
                            out.println(String.format("<div class=\"alert info\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                        case 6:
                            r.append("Ocurrio un error durante la salida del grupo.");
                            out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                        case 7:
                            r.append("Ocurrio un error al unirse al grupo.");
                            out.println(String.format("<div class=\"alert\"><span class=\"closebtn\">&times;</span><strong>%s</strong></div>", r.toString()));
                            break;
                    }
                }
            %>
          
            <div id="contents">
                <form id="grupo" action="CrearGrupo" method="POST">
                    <fieldset>
                        <legend>Crear grupo</legend>
                    <table>
                        ${formulario:campoEtiquetado("Nombre del grupo:","nombreGrupo","0")}
                        ${formulario:campoBoton("Agregar","botonAgregar","1")}
                    </table>
                    </fieldset>
                </form>
                <table id="tablaGruposDisponibles" class="tablaDatos">
                    <caption>Grupos disponibles </caption>
                    <thead>
                        <tr>
                            <td id="Nombre">Nombre</td>
                            <td id="Cupo">Cupo</td>
                            <td>Accion</td>
                        </tr>
                    </thead>
                    <tbody id="datosTablaGruposDisponibles"></tbody>
                </table>
                <table id="tablaMisGrupos" class="tablaDatos">
                    <caption>Mi grupo </caption>
                    <thead>
                        <tr>
                            <td id="Nombre">Nombre</td>
                            <td id="Cupo">Cupo</td>
                            <td>Accion</td>
                        </tr>
                    </thead>
                    <tbody id="datosTablaMisGrupos"></tbody>
                </table>
            </div>
        </div>           
        <script>
            var close = document.getElementsByClassName("closebtn");
            var i;
            for (i = 0; i < close.length; i++) {
              close[i].onclick = function(){
                var div = this.parentElement;
                div.style.opacity = "0";
                setTimeout(function(){ div.style.display = "none"; }, 600);
            }
        }
        </script>
        <%@include file="footer.jsp" %>
    </body>
</html>