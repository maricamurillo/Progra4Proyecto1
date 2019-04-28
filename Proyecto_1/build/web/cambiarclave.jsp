<%-- 
    cambiarclave.jsp


    EIF209 - Programacion 4 - Proyecto #1
    Abril 2019
    Autores: 
                - 113030275  Mariela Cambronero Murillo
                - 111320128 Rodrigo Rodriguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <jsp:useBean id="login" scope="session" type="String" class="java.lang.String" />
        <title>Cambiar clave</title>
    </head>
    <body>
         <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menu.jsp" %>
            </header>
            <div id="contents">
                <form id="ingreso" action="ServicioCambiarClave" method="POST">
                    <table >
                        ${formulario:campoEtiquetado("Nueva clave:","clave","1")}
                        ${formulario:campoBoton("Actualizar","botonActualizar","1")}
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
                                r.append("Su clave fue cambiada exitosamente");
                                break;
                            case 2:
                                r.append("Error actualizando clave");
                                break;
                        }
                        out.println(String.format("<p class=\"mensajeError\">%s</p>", r.toString()));
                    }
                %>
            </div>
         </div>
         <%@include file="footer.jsp" %>
    </body>
</html>
