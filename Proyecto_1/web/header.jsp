<%--
    header.jsp

    EIF209 - Programación 4 – Proyecto #1 
    Abril 2019

    Autores:
            - 113030275 Mariela Cambronero
            - 111320128 Rodrigo Rodriguez
--%>

<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <script src="js/scriptCompruebaLogin.js" type="text/javascript"></script>
         <%
             String x = request.getRequestURL().toString(); 
             System.out.println("xxxxx"+x);
           
              if (request.getSession(true).getAttribute("usuario") == null  ) {
                    if(!x.contains("index.jsp")){request.getRequestDispatcher("errorIngreso.jsp").forward(request, response);}
                    
            }
        %>
    </head>
    <body>
        <div>
        <h3>EIF209 Programacion 4</h3>
        <h3>Proyecto#1 Formacion de grupos</h3>
        <h3>1er ciclo 2019</h3>     
    </div>
    </body>
</html>
