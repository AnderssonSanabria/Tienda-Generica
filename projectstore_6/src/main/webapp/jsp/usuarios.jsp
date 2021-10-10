<%-- 
    Document   : usuarios
    Created on : 6/09/2021, 8:44:17 p. m.
    Author     : Carlos Beltrán
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
        <div class="alert alert-warning alert-dismissible fade show" role="alert" id="alert" >
            <strong>${mensaje}</strong>
        </div>
        <div class="d-flex"> 
            <div class="card col-sm-4">
                <div class="card-body">

                    <!--MENSAJE -->
                    <form class="needs-validation" action="Controlador?menu=Usuarios" method="POST" novalidate>
                        <div class="alert alert-warning alert-dismissible fade show" role="alert" id="alert" >
                            <strong>${mensaje}</strong>
                        </div>
                        <div class="form-group" >
                            <label>Cédula Usuario</label>

                            <!--PROCESO JAVA -->
                            <c:if test="${usuarioEdit.cedulaUsuario!=0}">
                                <input  type="text" name="txtId" value="${usuarioEdit.cedulaUsuario}" readonly="" class="form-control">
                            </c:if>
                            <c:if test="${usuarioEdit.cedulaUsuario==0}">
                                <input  type="text" name="txtId" class="form-control" required="">
                                <div class="valid-feedback">Campo OK</div>
                                <div class="invalid-feedback">Complete los datos</div>
                            </c:if>

                        </div>
                        <div class="form-group" >
                            <label>Nombre</label>
                            <input  type="text" name="txtNombre" value="${usuarioEdit.nombreUsuario}" class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div>
                        <div class="form-group" >
                            <label>Clave</label>
                            <input  type="password" name="txtClave" value="${usuarioEdit.clave}" class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div>
                        <div class="form-group" >
                            <label>Correo</label>
                            <input  type="text" name="txtCorreo" value="${usuarioEdit.correo}" class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div>
                        <div class="form-group" >
                            <label>Tipo Usuario</label>
                            <select class="form-control" name="txtTipo" required="">
                                <c:if test="${empty categorias}">
                                    <option selected="" disabled=" " value="">Por favor Seleccione..</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Cliente">Cliente</option>
                                </c:if>
                                <c:forEach items="${categorias}" var="cate">
                                    <option value="${cate}" ${cate == usuarioEdit.tipoUsuario ? 'selected' :''}>
                                        <c:out value="${cate}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div><br>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info" >
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success" >
                    </form>
                    <form class="needs-validation" action="Controlador?menu=Usuarios" method="POST" novalidate>
                        <br>
                        <label>Buscar por Cédula Usuario</label>

                        <!--PROCESO JAVA -->
                        <c:if test="${usuarioEdit.cedulaUsuario!=0}">
                            <input  type="text" name="txtId" value="${usuarioEdit.cedulaUsuario}" readonly="" class="form-control">
                        </c:if>
                        <c:if test="${usuarioEdit.cedulaUsuario==0}">
                            <input  type="text" name="txtId" class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </c:if>

                        <br>
                        <input type="submit" name="accion" value="Buscar" class="btn btn-warning" >                        

                    </form>                
                </div>
            </div>
            
            <!-- ESPACIO PARA TABLA -->
            <div class="col-sm-8">
                <!-- TABLA DE VALORES -->
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CEDULA</th>
                            <th>NOMBRE</th>
                            <th>CLAVE</th>
                            <th>CORREO</th>
                            <th>TIPO USUARIO</th>
                            <th>ACCIONES</th>
                    </thead>
                    <tbody>
                        <c:forEach var="e" items="${usuarios}">
                            <tr>
                                <td>${e.cedulaUsuario}</td>
                                <td>${e.nombreUsuario}</td>
                                <td>${e.clave}</td>
                                <td>${e.correo}</td>
                                <td>${e.tipoUsuario}</td>
                                <td>
                                    <a href="Controlador?menu=Usuarios&accion=Editar&id=${e.cedulaUsuario}" 
                                       class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>
                                    <!--<a href="Controlador?menu=Usuarios&accion=Eliminar&id=${e.cedulaUsuario}" 
                                       class="btn btn-danger btn-sm"><i class="fa fa-trash-alt"></i></a>-->
                                    <!-- Modal -->
                                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#exampleModal" data-whatever=${e.cedulaUsuario}>
                                        <i class="fa fa-trash-alt"></i>
                                    </button>
                                    <!-- Modal -->
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div> <!-- ESPACIO PARA TABLA -->
        </div>
        <br>    


        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="modal-body">
                            <p>¿Seguro que quieres borrar este elemento? </p>
                            <p class="text-warning"><small>Si lo borras, nunca podrás recuperarlo.</small></p>
                        </div>
                        <div class="modal-footer">
                            <button id="btncancelar" type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                            <button id="btneliminar" type="button" class="btn btn-danger">Eliminar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>        

        <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Alerta</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="modal-body">
                            <p>La cédula buscada no existe</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>      

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                        .forEach(function (form) {
                            form.addEventListener('submit', function (event) {
                                if (!form.checkValidity()) {
                                    event.preventDefault()
                                    event.stopPropagation()
                                }
                                form.classList.add('was-validated')
                            }, false)
                        })
            })()
        </script>
        <script src="jquery/jquery-3.3.1.min.js"></script>	 	
        <script src="popper/popper.min.js"></script>
        <script src="bootstrap4/js/bootstrap.min.js"></script>
        <script>
            //Autoclose
            window.setTimeout(function () {
                $(".alert").fadeTo(1500, 0).slideDown(1000, function () {
                    $(this).remove();
                });
            }, 3000); //2 segundos y desaparece
        </script> 
        <script>
            $(document).ready(function () {
                $("#alert").hide();
            })
        </script>
        <script>
            $('#exampleModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget)
                var recipient = button.data('whatever')
                var modal = $(this)
                modal.find('.modal-title').text('Esta seguro que desea eliminar el id: ' + recipient)
                $('#btneliminar').click(function () {
                    alert('Se ha eliminado el usuario con id: ' + recipient);
                    window.location.href = "Controlador?menu=Usuarios&accion=Eliminar&id=" + recipient;
                });
            });
            function Clean() {
                $('#txtId').value = ""
                $('#txtNombre').value = ""
                $('#txtClave').value = ""
                $('#txtCorreo').value = ""
                $('#txttipo').value = ""
            }
        </script>
        <c:if test="${cedula==0}">
            <script>
                $('#exampleModal1').modal('show');
            </script>       
        </c:if>

    </body>
</html>