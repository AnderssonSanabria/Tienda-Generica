<%-- 
    Document   : vistaAdministrador
    Created on : 6/09/2021, 10:27:00 a. m.
    Author     : Carlos Beltrán
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@page import="package_modelo.modelo_user" %>
<!DOCTYPE html>
<html>

    <!-- ENCABEZADO DE DOCUMENTO -->
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../css/style_4.css">
        <title>Vista de Administrador</title>
    </head>

    <!-- CUERPO DE DOCUMENTO -->
    <body>

        <!-- ELEMENTOS JAVA -->
        <%
            modelo_user objusuario = new modelo_user();
            HttpSession objSesion = request.getSession();
            objusuario = (modelo_user) objSesion.getAttribute("objusuario");
        %>

        <!-- BARRA DE NAVEGACION -->
        <nav class="navbar navbar-expand-lg navbar-light bg-info">

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>


            <!-- ESPACIO DE BOTONES DE BARRA DE NAVEGACION -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <!-- CAJA DE NAVEGACION DE MODULOS -->
                <!-- OPCIONAL
                <ul class="navbar-nav mr-auto"> </ul>
                -->
                <ul class="menu">

                    <li class="ClassLiMenu">
                        <a class="active" href="vistaAdministrador.jsp">
                            <h2 style="color: black;">Home Ventas</h2>
                        </a>
                    </li>

                    <li class="ClassLiMenu">
                        <a target="myFrame"  href="../Controlador?menu=Usuarios&accion=Listar">
                            <h3>Usuarios</h3>
                        </a>
                    </li>

                    <li class="ClassLiMenu">
                        <a target="myFrame" href="#">
                            <h3>Clientes</h3>
                        </a>
                    </li>

                    <li class="ClassLiMenu">
                        <a target="myFrame" href="../Controlador?menu=Proveedor&accion=Listar">
                            <h3>Proveedores</h3>
                        </a>
                    </li>

                    <li class="ClassLiMenu">
                        <a target="myFrame" href="#">
                            <h3>Productos</h3>
                        </a>
                    </li>

                    <li class="ClassLiMenu">
                        <a target="myFrame" href="#">
                            <h3>Ventas</h3>
                        </a>
                    </li>

                    <li class="ClassLiMenu">
                        <a target="myFrame" href="#">
                            <h3>Reportes</h3>
                        </a>
                    </li>

                    <li class="slider"></li>

                </ul>

                <!-- ESTADO DE PERFIL -->
                <ul class="navbar-nav justify-content-end">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user-lock"></i>
                            Usuario: ${objusuario.getNombreUsuario()}
                            <br>
                            Tipo: ${objusuario.getTipoUsuario()}
                        </a>
                        <div class="dropdown-menu dropdown-menu-right text-center" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#"><img src="../img/user_1.png" height="50" width="50"></a>
                            <a class="dropdown-item" href="#">${objusuario.getTipoUsuario()}</a>
                            <a class="dropdown-item" href="#">${objusuario.getCorreo()}</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="cerrarSesion.jsp">Salir</a>
                        </div>
                    </li>
                </ul> <!-- ESTADO DE PERFIL -->

            </div> <!-- ESPACIO DE BOTONES DE BARRA DE NAVEGACION -->
        </nav> <!-- FIN DE BARRA DE NAVEGACION -->

        <div class="m-4" style="height: 550px;"> 
            <iframe name="myFrame" style="height: 100%; width:100%; border: none"></iframe>
        </div>

        <!-- ELEMENTOS SCRIPT -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
