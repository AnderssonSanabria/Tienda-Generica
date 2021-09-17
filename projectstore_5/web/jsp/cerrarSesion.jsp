<%-- 
    Document   : cerrarSesion
    Created on : 09-05-2021, 04:53:15 PM
    Author     : CABC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%  
    HttpSession objSesion = request.getSession();  
    objSesion.invalidate();
    out.print("<script>location.replace('../login.html');</script>");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CERRANDO SECION</title>
    </head>
    <body>
        <h1>CERRANDO SECION</h1>
    </body>
</html>
