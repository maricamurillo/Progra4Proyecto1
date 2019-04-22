<%--
    formacionGrupos.jsp

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptGrupos.js" type="text/javascript"></script>
        <title>Formacion de grupos</title>
    </head>
    <body onload="initGrupos()">
        <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menu.jsp" %>
            </header>
            <h2>Crear grupo</h2>
            <div id="contents">
                <form id="grupo" action="CrearGrupo" method="POST">
                    <table>
                        ${formulario:campoEtiquetado("Nombre del grupo:","nombreGrupo","0")}
                        ${formulario:campoBoton("Agregar","botonAgregar","1")}
                    </table>
                </form>
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
                                r.append("Grupo agregado");
                                break;
                            case 2:
                                r.append("Ocurrio un error durante la creacion del grupo");
                                break;
                        }
                        out.println(String.format("<p class=\"mensajeError\">%s</p>", r.toString()));
                    }
                %>
            </div>
            <h2>Unirse a grupo</h2>
            <table id="tablaGrupos" class="tablaDatos">
                <thead>
                    <tr>
                        <td id="Nombre">Nombre</td>
                        <td id="Cupo">Cupo</td>
                        <td>Accion</td>
                    </tr>
                </thead>
                <tbody id="datosTablaGrupos"></tbody>
            </table>
        </div>
    </body>
</html>
