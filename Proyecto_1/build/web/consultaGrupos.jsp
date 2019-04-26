<%--
    colsultaGrupos.jsp

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
        <script src="js/scriptMuestraGrupo.js" type="text/javascript"></script>
       <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
       <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body onload="initMuestra()">
        <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menu.jsp" %>
            </header>
            <div id="contents">       
            </div>
        </div>
    </body>
</html>
