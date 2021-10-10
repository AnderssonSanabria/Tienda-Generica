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
                            <br> 
                            Cedula: <input type="text" name="InputVentaCedula" class="" value="${clienteFactura.getCustomerId()}" placeholder="Cedula del Cliente">
                            <button type="submit" name="accion" value="BuscarCliente" class="" >Buscar Cliente</button>
                            <br>
                            Nombre: <input type="text" name="txtNombreCliente" class="" value="${clienteFactura.getCustomerNameFull()}" placeholder="Nombre del Cliente" readonly="">
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
                            <img class="ClassLogMinTic" src="img/LogoMinTic_001.png" alt=""/>
                        </div>
                    </th>
                    <td>REPORTE DE PRODUCTOS</td>
                </tr>
            </tbody>
        </table>

    </body>

</html>
