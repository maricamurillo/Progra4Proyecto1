<%--
    usuarios.jsp

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
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptsUsuarios.js" type="text/javascript"></script>
        <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
        <title>JSP Usuarios</title>
       
    </head>
    <body onload="initUsuarios()">
        <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menu.jsp" %>
            </header>
            <div id="contents">
                <form id="ingreso">
                    <table id="tablaUsuarios" class="tablaDatos">
                        <thead>
                            <tr>
                                <td id="OrdenaSecuencia" onclick="ordena()"># Grupo</td>
                                <td id="OrdenaGrupo" onclick="ordena()">Nombre del Grupo</td>
                                <td id="OrdenaNRC" onclick="ordena()">NRC</td>
                                <td id="OrdenaId" onclick="ordena()">Identificación</td>
                                <td id="OrdenaApelligos" onclick="ordena()" >Apellidos</td>
                                <td id="OrdenaNombre" onclick="ordena()">Nombre</td>
                                <td>Activo</td>
                            </tr>
                        </thead>
                        <tbody id="datosTablaUsuarios"></tbody>
                    </table>
                    <input type="hidden" id="control" name="control" value="1"/>
                </form>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
