<%-- 
    Document   : productos
    Created on : 10/10/2021, 10:00:01 p. m.
    Author     : luisj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

    <!-- DOCUMENTO HTML -  ENCABEZADO
    ---------------------------------------------------------------------------------------------------- -->
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
        <title>View Sales</title>
    </head>

    <!-- DOCUMENTO HTML -  CUERPO
        ---------------------------------------------------------------------------------------------------- -->
    <body>

        <!-- SECCION - TITULO DE VISTA
        ---------------------------------------------------------------------------------------------------- -->
        <div class="jumbotron text-center" align="center">
            <h1>Factura de venta</h1>
            <img class="" src="Imagen/AnimacionCarroCompras_1.gif"  alt="" height="150px"/>
        </div>

        <!-- SECCION - MENU DE VISTA
        ---------------------------------------------------------------------------------------------------- -->
        <div class="d-flex">
            <div class="row">


                <!-- SECCION - MENU DE VENTAS
                ---------------------------------------------------------------------------------------------------- -->
                <div class="col-sm-5">
                    <div class="card">
                        <div class="card-body">

                            <div class="form-group">
                                <label>Datos del Cliente</label>
                                <br>
                            </div>

                            <!-- FORMULARIO - BUSCAR CLIENTE
                            ---------------------------------------------------------------------------------------------------- -->
                            <form action="Controlador?menu=AccionVentas" method="POST" novalidate>
                                <div class="form-group d-flex">
                                    <div class="col-sm-4">
                                        <!-- INPUT - CEDULA DE CLIENTE -->
                                        <input type="text" name="txtCedula" class="form-control" placeholder="Cédula del Cliente" 
                                               value="${ClienteFactura.getCustomerId()}">
                                    </div>
                                    <div class="col-sm-2">
                                        <!-- BUTTON - BUSCAR CLIENTE -->
                                        <button type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-success"> Buscar Cliente</button>
                                    </div>
                                    <div class="col-sm-6">
                                        <!-- INPUT - NOMBRE DE CLIENTE -->
                                        <input type="text" name="txtNombreCliente" class="form-control" placeholder="Nombre del Cliente" readonly=""
                                               value="${ClienteFactura.getCustomerNameFull()}">
                                    </div>
                                </div>
                            </form> <!-- FORMULARIO - BUSCAR CLIENTE -->

                            <div class="form-group">
                                <label>Datos del Producto</label>
                                <br>
                            </div>

                            <!-- FORMULARIO - BUSCAR PRODUCTO
                            ---------------------------------------------------------------------------------------------------- -->
                            <form action="Controlador?menu=AccionVentas" method="POST" novalidate>
                                <div class="form-group d-flex">
                                    <div class="col-sm-4">
                                        <!-- INPUT - CODIGO DEL PRODUCTO -->
                                        <input type="text" name="txtCodigo" class="form-control" placeholder="Código del Producto" 
                                               value="${ProductoFactura.getCodigo_producto()}"> 
                                    </div>
                                    <div class="col-sm-2">
                                        <!-- BUTTON - BUSCAR PRODUCTO -->
                                        <button type="submit" name="accion" value="AccionVentas_BuscarProducto" class="btn btn-outline-info"> Buscar</button>
                                    </div>
                                    <div class="col-sm-6">
                                        <!-- INPUT - NOMBRE DEL PRODUCTO -->
                                        <input type="text" name="txtNombreProducto" class="form-control" placeholder="Nombre del Producto" 
                                               value="${ProductoFactura.getNombre_producto()}"> 
                                    </div>
                                </div>
                            </form> <!-- FORMULARIO - BUSCAR PRODUCTO -->

                            <!-- FORMULARIO - AGREGAR PRODUCTO
---------------------------------------------------------------------------------------------------- -->
                            <form action="Controlador?menu=AccionVentas" method="POST" novalidate>
                                <div class="form-group d-flex">
                                    <div class="col-sm-6">
                                        <button type="submit" name="accion" value="AgregarProducto" class="btn btn-outline-primary"> Agregar producto</button>
                                    </div>
                                    <div class="col-sm-2">
                                        <input type="number" name="txtCantidad" class="form-control" value="1" style="text-align: right"> 
                                    </div>
                                    <div class="col-sm-4">
                                        <input type="text" name="txtPrecioProducto" style="text-align: right" class="form-control" placeholder="$/ 0,000.00"
                                               value="${ProductoFactura.getPrecio_venta()}">
                                    </div>
                                </div>
                            </form> <!-- FORMULARIO - AGREGAR PRODUCTO -->
                            
                            
                        </div>
                        <!-- SECCION - ADICIONAL -->
                        <div class="form-group d-flex">
                            <div class="col-sm-4">
                                <c:if test="${Mensaje != null}">
                                    <div class="alert alert-success alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                                        <strong>Resultado !</strong>${Mensaje}
                                    </div>
                                </c:if>
                                <c:if test="${Aviso != null}">
                                    <div class="alert alert-danger alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                                        <strong>Cuidado !</strong>${Aviso}
                                    </div>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div> <!-- SECCION - MENU DE VISTA -->

                <!-- SECCION - TABLA DE REPORTE DE VENTAS
                ---------------------------------------------------------------------------------------------------- -->
                <div class="col-sm-7">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex col-sm-4 ml-auto">
                                <label style="width: 20">Factura N°: </label>
                                <input type="text" name="txtNumeroFactura" style="text-align: right" class="form-control" disabled="" readonly=""
                                       value="${IdVenta}">
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="col-sm-12 ml-auto">
                                <table border="0" class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Nro</th>
                                            <th>Codigo</th>
                                            <th>Descripción</th>
                                            <th>Precio</th>
                                            <th>Cantidad</th>
                                            <th>Subtotal</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${DetalleVentas}" var="Listar">
                                            <tr>
                                                <td>${Listar.getCodigo_detalle_venta()}</td>
                                                <td>${Listar.getCodigo_producto()}</td>
                                                <td>${Listar.getVentaDetalleDescripcion()}</td>
                                                <td>${Listar.getPrecioVentaDetalle()}</td>
                                                <td>${Listar.getCantidas_producto()}</td>
                                                <td>${Listar.getValor_total()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card-footer">
                            <div class="form-group d-flex">
                                <table border="0" class="table table-hover">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <button type="submit" name="accion" value="guardarFactura" class="btn btn-success"> Generar Factura</button>
                                            </td>
                                            <td>
                                                <button type="submit" name="accion" value="agregarProducto" class="btn btn-danger"> Cancelar Registro</button>
                                            </td>
                                            <td>
                                                <label>Subtotal: </label>
                                            </td>
                                            <td>
                                                <input type="text" name="txtSubtotalFactura" style="text-align: right" class="form-control" placeholder="$/ 0,000.00" size="20" readonly="" 
                                                       value="${Subtotal}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            </td>
                                            <td>
                                            </td>
                                            <td>
                                                <label>Valor Iva: </label>
                                            </td>
                                            <td>
                                                <input type="text" name="txtTotalIva" style="text-align: right" class="form-control" placeholder="$/ 0,000.00" size="20" readonly="" 
                                                       value="${TotalIva}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            </td>
                                            <td>
                                            </td>
                                            <td>
                                                <label>Total con Iva: </label>
                                            </td>
                                            <td>
                                                <input type="text" name="txtTotalConIva" style="text-align: right" class="form-control" placeholder="$/ 0,000.00" size="20" readonly="" 
                                                       value="${TotalFactura}">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div> <!-- SECCION - TABLA DE REPORTE DE VENTAS -->

            </div>
        </div> <!-- SECCION - MENU DE VISTA -->


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

    </body>
</html>
