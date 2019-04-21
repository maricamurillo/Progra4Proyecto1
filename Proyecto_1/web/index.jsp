<%-- 
    Document   : registro
    Created on : 18/04/2019, 03:10:32 PM
    Author     : gaspa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="formulario" uri="/WEB-INF/tlds/formulario" %>
<%@page import="servicios.ServicioIngreso"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="wrapper">
            <header>
                 <%@include file="header.jsp" %> 
            </header>
            <div id="contents">
                <form id="ingreso" action="ServicioIngreso" method="POST">
                    <table class="tablaFormulario">
                        ${formulario:campoEtiquetado("Id usuario:","campoId","0")}
                        ${formulario:campoEtiquetado("Clave:","campoClave","1")}
                        ${formulario:campoBoton("Ingresar","botonIngreso","1")}
                    </table>
                </form>
            </div>
            <footer></footer>
        </div>
    </body>
</html>
