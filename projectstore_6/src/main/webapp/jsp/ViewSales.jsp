<%-- 
    Document   : ViewSale
    Created on : 8/10/2021, 10:41:11 p. m.
    Author     : fabia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es"> <!-- SE INDICA EL IDIOMA -->


    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Sales</title>
        <link rel="stylesheet" href="css/style_5.css">
    </head>

    <body>

        <!-- SECCION - TITULO DE VISTA -->
        <div align="center">
            <label>
                <h2>View Sale</h2>
                <img class="" src="Imagen/AnimacionCarroCompras_1.gif"  alt="" height="100px"/>
            </label>
        </div> <!-- SECCION - TITULO DE VISTA -->

        <!-- TABLA DE CONTENIDO -->
        <table>
            <!-- CUERPO DE TABLA -->
            <tbody>
                <!-- FILA DE CONTENIDO DE INTERFAS GRAFICA -->
                <tr>
                    <!-- COLUMNA IZQUIERDA - INTERFAS DE MENU DE CLIENTE Y PRODUCTOS
                    ---------------------------------------------------------------------------------------------------- -->
                    <th align="left">

                        <!-- SECCION - BUSQUEDA DE CLIENTE
                        ---------------------------------------------------------------------------------------------------- -->
                        <form action="="Controlador?menu=AccionVentas" method="POST" novalidate>

                            <!-- SECCION - BUSQUEDA DE CLIENTE
                            ---------------------------------------------------------------------------------------------------- -->
                            <div>
                                <label>DATOS DE CLIENTE</label>
                                <div>
                                    Cedula: <input type="text" name="InputVentaCedula" class="" placeholder="Cedula del Cliente"
                                                   value="${clienteFactura.getCustomerId()}">
                                </div>
                                <div>
                                    <button type="submit" name="accion" value="BuscarCliente" class="" >Buscar Cliente</button>
                                </div>
                                <div>
                                    Nombre: <input type="text" name="txtNombreCliente" class="" placeholder="Nombre del Cliente" readonly=""
                                                   value="${clienteFactura.getCustomerNameFull()}">
                                </div>
                            </div> <!-- SECCION - BUSQUEDA DE CLIENTE -->

                            <br>

                            <!-- SECCION - BUSQUEDA DE PRODUCTO
                            ---------------------------------------------------------------------------------------------------- -->
                            <div>
                                <label>DATOS DE PRODUCTO</label>
                                <div>
                                    <!-- InpuntProductoCodigo =  txtCodigo-->
                                    Codigo producto: <input type="text" name="InpuntProductoCodigo" placeholder="Codigo del producto"
                                                            value="${productoFactura.getCodigo_producto()}">
                                </div>
                                <div>
                                    <!-- AccionVentas_BuscarProducto =  buscarProducto-->
                                    <button type="submit" name="accion" value="AccionVentas_BuscarProducto" class="" >Buscar Producto</button>
                                </div>
                                <div>
                                    <!-- InputProductoNombre =  txtNombreProducto-->
                                    Producto: <input type="text" name="InputProductoNombre" placeholder="Nombre del producto" readonly="" 
                                                     value="${productoFactura.getNombre_producto()}">
                                </div>
                            </div> <!-- SECCION - BUSQUEDA DE  PRODUCTO-->

                            <br>

                            <!-- SECCION - COMPRA DE PRODUCTO
                            ---------------------------------------------------------------------------------------------------- -->
                            <div>
                                <label>COMPRA DE PRODUCTO</label>
                                <div>
                                    <!-- InputProductoCantidad =  txtCantidad-->
                                    Cantidad: <input type="number" name="InputProductoCantidad" class="" placeholder="1" style="text-align: right">
                                </div>
                                <div>
                                    <!-- InputProductoValor =  txtPrecioProducto-->
                                    Valor: <input type="text" name="InputProductoValor" placeholder="$ 0.00" readonly="" style="text-align:right"
                                                  value="${InputValueProductoFactura.getPrecio_venta()}">
                                </div>
                                <div>
                                    <!-- AgregarProducto =  AgregarProducto-->
                                    <button type="submit" name="accion" value="AgregarProducto" class="">Agrear producto</button>
                                </div>
                            </div> <!-- SECCION - COMPRA DE PRODUCTO -->
                        </form>
                    </th> <!-- COLUMNA IZQUIERDA - INTERFAS DE MENU DE CLIENTE Y PRODUCTOS -->

                    <!-- COLUMNA DERECHA - REPORTE DE VENTA
                    ---------------------------------------------------------------------------------------------------- -->
                    <td>
                        REPORTE DE PRODUCTOS
                    </td> <!-- COLUMNA DERECHA - REPORTE DE VENTA -->

                </tr> <!-- FILA DE CONTENIDO DE INTERFAS GRAFICA -->
            </tbody> <!-- CUERPO DE TABLA -->
        </table> <!-- TABLA DE CONTENIDO -->

    </body>

</html>
