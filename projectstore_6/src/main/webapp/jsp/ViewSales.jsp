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

        <label><h1>View Sale</h1></label>

        <table>
            <tbody>
                <tr>
                    <th>

                        <div>
                            <label>DATOS DE CLIENTE</label>
                            <form action="Controlador?menu=AccionVentas" method="POST" novalidate>
                                <br>
                                Cedula: <input type="text" name="InputVentaCedula" class="" value="${clienteFactura.getCustomerId()}" placeholder="Cedula del Cliente">
                                <button type="submit" name="accion" value="BuscarCliente" class="" >Buscar Cliente</button>
                                <br>
                                Nombre: <input type="text" name="txtNombreCliente" class="" value="${clienteFactura.getCustomerNameFull()}" placeholder="Nombre del Cliente" readonly="">
                            </form>

                            <form class="needs-validation" action="Controlador?menu=Clientes" method="POST" novalidate>
                                <br>
                                <label>Buscar por cedula cliente</label>

                                <!--PROCESO JAVA -->
                                <c:if test="${clienteEdit.getCustomerId()!=0}">
                                    <input  type="text" name="txtIdc" value="${clienteEdit.getCustomerId()}" readonly="" class="form-control">
                                </c:if>
                                <c:if test="${clienteEdit.getCustomerId()==0}">
                                    <input  type="text" name="txtIdc" class="form-control" required="">
                                    <div class="valid-feedback">Campo OK</div>
                                    <div class="invalid-feedback">Complete los datos</div>
                                </c:if>
                                <br>
                                <input type="submit" name="accion" value="Buscar" class="btn btn-warning" >                        

                            </form>  

                        </div>
                        <br>
                        <div>
                            <label>DATOS DE PRODUCTO</label>
                            <br>
                            Codigo producto: <input placeholder="Codigo del producto">
                            <button>Buscar Producto</button>
                            <br>
                            Producto: <input placeholder="Codigo del producto">
                            <br>
                            Valor: <input placeholder="Valor del producto">
                            <br>
                            Cantidad: <input placeholder="Valor del producto">
                            <button>Agregar Productos</button>
                        </div>
                        <div>
                            <img class="ClassLogMinTic" src="Imagen/AnimacionCarroCompras_1" alt=""/>
                        </div>
                    </th>
                    <td>REPORTE DE PRODUCTOS</td>
                </tr>
            </tbody>
        </table>

    </body>

</html>
