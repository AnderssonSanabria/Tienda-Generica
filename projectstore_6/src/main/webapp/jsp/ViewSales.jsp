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
                    <!-- COLUMNA IZQUIERDA - INTERFAS DE MENU DE CLIENTE Y PRODUCTOS -->
                    <th align="left">

                        <!-- SECCION - BUSQUEDA DE CLIENTE -->
                        <div>
                            <!-- ACCION - FORMULARIO DE BUSQUEDA DE CLIENTE -->
                            <form action="Controlador?menu=AccionVentas" method="POST" novalidate>
                                <label>DATOS DE CLIENTE</label>
                                <div>
                                    Cedula: <input type="text" name="InputVentaCedula" class="" value="${clienteFactura.getCustomerId()}" placeholder="Cedula del Cliente">
                                </div>
                                <div>
                                    <button type="submit" name="accion" value="BuscarCliente" class="" >Buscar Cliente</button>
                                </div>
                                <div>
                                    Nombre: <input type="text" name="txtNombreCliente" class="" value="${clienteFactura.getCustomerNameFull()}" placeholder="Nombre del Cliente" readonly="">
                                </div>
                            </form>
                        </div> <!-- SECCION - BUSQUEDA DE CLIENTE -->
                        <br>

                        <!-- SECCION - BUSQUEDA DE  PRODUCTO-->
                        <div>
                            <form action="action">
                                <label>DATOS DE PRODUCTO</label>
                                <div>
                                    Codigo producto: <input placeholder="Codigo del producto">
                                </div>
                                <div>
                                    <button>Buscar Producto</button>
                                </div>
                                <div>
                                    Producto: <input placeholder="Codigo del producto">
                                </div>
                                <div>
                                    Valor: <input placeholder="Valor del producto">
                                </div>
                            </form>
                        </div> <!-- SECCION - BUSQUEDA DE  PRODUCTO-->
                        <br>

                        <!-- SECCION - COMPRA DE PRODUCTO -->
                        <div>
                            <form action="action">
                                <label>COMPRA DE PRODUCTO</label>
                                <div>
                                    Cantidad: <input placeholder="Cantidad del producto">
                                </div>
                                <div>
                                    <button>Agrear producto</button>
                                </div>
                            </form>
                        </div> <!-- SECCION - COMPRA DE PRODUCTO -->

                    </th> <!-- COLUMNA IZQUIERDA - INTERFAS DE MENU DE CLIENTE Y PRODUCTOS -->

                    <!-- COLUMNA DERECHA - REPORTE DE VENTA -->
                    <td>
                        REPORTE DE PRODUCTOS
                    </td> <!-- COLUMNA DERECHA - REPORTE DE VENTA -->
                    
                </tr> <!-- FILA DE CONTENIDO DE INTERFAS GRAFICA -->
            </tbody> <!-- CUERPO DE TABLA -->
        </table> <!-- TABLA DE CONTENIDO -->

    </body>

</html>
