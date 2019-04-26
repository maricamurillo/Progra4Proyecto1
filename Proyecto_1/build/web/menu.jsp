<%--
    menu.jsp

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="formulario" uri="/WEB-INF/tlds/formulario" %>
<script src="js/scriptCompruebaLogin.js" type="text/javascript"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>Menu</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <table>
                    <tr>
                        <td>
                            <form action="formacion" method="POST">
                                ${formulario:campoBoton("Formación de grupos","botonFormacion","0")}
                            </form>
                        </td>
                        <td>
                            <form action="consulta" method="POST">
                                ${formulario:campoBoton("Consulta de grupos","botonConsulta","0")}
                            </form>
                        </td>
                        <td>
                            <form action="usuarios" method="POST">
                                ${formulario:campoBoton("Estudiantes","botonUsuario","0")}
                            </form>
                        </td>
                        <td>
                            <form action="cambiarclave" method="POST">
                                ${formulario:campoBoton("Cambiar clave","botonCambiaClave","0")}
                            </form>
                        </td>
                        <td>
                            <form action="salir" method="POST">
                                 ${formulario:campoBoton("Cerrar sesión","botonSalir","0")}
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>    
    </body>
</html>
