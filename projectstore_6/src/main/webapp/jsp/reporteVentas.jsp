<%-- 
    Document   : reporteVentas.jsp
    Created on : 17/10/2021, 10:51:23 a. m.
    Author     : luisj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte Ventas por Clientes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <div class="alert alert-warning alert-dismissible fade show" role="alert" id="alert" >
                <strong>${mensaje}</strong>
            </div>
            <div class="col-sm-8">
                <table border="1" class="table table-hover">
                    <thead>
                        <tr>
                            <th>Cedula</th>
                            <th>Nombre</th>
                            <th>Valor Total Ventas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reporteVentas}" var="repv">
                            <tr>
                                <td>${repv.getCedulaCliente()}</td>
                                <td>${repv.getNombreCliente()}</td>
                                <td>${repv.getVentaTotal()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="d-flex">
                    <label>Total Ventas: $</label>
                    <input type="text" readonly="" value="${granTotalVentas}" class="form-control" style="text-align: right">
                </div>

            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
