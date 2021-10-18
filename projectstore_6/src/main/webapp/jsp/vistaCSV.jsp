<%-- 
    Document   : vistaCSV
    Created on : 04-oct-2021, 16:12:07
    Author     : jsotto
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos del archivo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <h1 style="text-align: center"> Listado de Productos</h1>   
                <h1 style="text-align: center"> Contenido de Arreglo subid desde el CSV</h1>
                <br/><br/>
                <div class="col-sm-2">
                    <c:if test="${mensaje != null}" >
                        <div class="alert alert-success alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Resultado!</strong>${mensaje}
                        </div>
                    </c:if>
                    <c:if test="${aviso != null}" >
                        <div class="alert alert-danger alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>Error:</strong>${aviso}
                        </div>
                    </c:if>
                </div>
                <div class="col-sm-6">
                    <table borde="0" cellspacing="5" cellpadding="5" class="table table-striped">
                        
                        <thead>
                            <tr>
                                <th>CODIGO</th>
                                <th>IVA</th>
                                <th>PROVEEDOR</th>
                                <th>NOMBRE</th>
                                <th>PRECIO COMPRA</th>
                                <th>PRECIO VENTA</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <c:forEach items="${productos}" var="pro">
                                <tr>
                                    <td>${pro.codigo_producto}</td>
                                    <td>${pro.iva_compra}</td>
                                    <td>${pro.nit_proveedor}</td>
                                    <td>${pro.nombre_producto}</td>
                                    <td>${pro.precio_compra}</td>
                                    <td>${pro.precio_venta}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>
                    <a href="./jsp/productos.html" class="btn btn-success btn-lg">Regresar al Inicio</a>
                </div>
            </div>           
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>       
    </body>
</html>
