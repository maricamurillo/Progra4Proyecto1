<%-- 
    Document   : consultaGrupos
    Created on : 19/04/2019, 03:48:24 AM
    Author     : gaspa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/loadJSON.js" type="text/javascript"></script>
        <script src="js/scriptMuestraGrupo.js" type="text/javascript"></script>
       <link href="css/tablas.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
      
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
