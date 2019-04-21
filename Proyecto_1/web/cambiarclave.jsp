<%-- 
    cambiarclave.jsp


    EIF209 - Programacion 4 - Proyecto #1
    Abril 2019
    Autores: 
                -113030275  Mariela Cambronero Murillo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar clave</title>
    </head>
    <body>
         <div id="wrapper">
            <header id="header">
                 <%@include file="header.jsp" %> 
                 <%@include file="menu.jsp" %>
            </header>
            <div id="contents">
                <form name="formulario" action="ServicioCambiarClave" method="POST">
                    <table class="parametros">
                        <tr>
                            <td>Nueva clave:&nbsp;</td>
                            <td>
                                <input type="password" name="clave" required>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit">Enviar</button>
                            </td>
                        </tr>
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
    </body>
</html>