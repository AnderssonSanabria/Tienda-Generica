<%-- 
    Document   : usuarios
    Created on : 6/09/2021, 8:44:17 p. m.
    Author     : Carlos Beltrán
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- DOCUMENTO HTML CLIENTES -->
<html>

    <!-- ENCABEZADO -->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>

    <!-- CUERPO DE HTML -->
    <body>
        <h1>VISTA CLIENTES P7</h1>
        <div class="col-sm-8">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>CEDULA</th>
                        <th>CLIENTE</th>
                        <th>DIRECCION</th>
                        <th>TELEFONO</th>
                        <th>CORREO</th>
                        <th>ACCIONES</th>
                </thead>
                <tbody>
                    <c:forEach var="e" items="${ListCustomer}">
                        <tr>
                            <td>${e.CustomerId}</td>
                            <td>${e.CustomerNameFull}</td>
                            <td>${e.CustomerAddress}</td>
                            <td>${e.CustomerPhone}</td>
                            <td>${e.CustomerEmail}</td>
                            <td>
                                <a href="Controlador?menu=Usuarios&accion=Editar&id=${e.CustomerId}" <!-- PENDIENTE POR CREAR LA ACCION DE ELIMINAR EN EL CONTROLADOR -->
                                   class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>
                                <!--<a href="Controlador?menu=Customer&accion=Eliminar&id=${e.CustomerId}"  <!-- PENDIENTE POR CREAR LA ACCION DE ELIMINAR EN EL CONTROLADOR -->
                                class="btn btn-danger btn-sm"><i class="fa fa-trash-alt"></i></a>-->
                                <!-- Modal -->
                                <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#exampleModal" data-whatever=${e.CustomerId}>
                                    <i class="fa fa-trash-alt"></i>
                                </button>
                                <!-- Modal -->
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>