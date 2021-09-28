package package_controller;

// IMPORTACION DE ELEMENTOS
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
// IMPORTACION DE MODELOS
import package_modelo.modelo_user;
import package_modelo.modelo_supplier;
// IMPORTACION DE DAO PROCESOS
import package_dao.dao_user;
import package_dao.dao_supplier;

// CUERPO DE PROCESOS
public class Controlador extends HttpServlet {

    // IMPORTACION DE MODELOS
    modelo_user usuario = new modelo_user();
    modelo_supplier proveedor = new modelo_supplier();

    // IMPORTACION DE DAO
    dao_user usuarioDao = new dao_user();
    dao_supplier proveedorDao = new dao_supplier();

    // CRUD DE USUARIO
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // DEFINICION DE ACCIONES PARA LA BARRA DE MENU
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        String mensaje = "";

        // ESTE ELEMENTO DEFINE LLA SUB-PANTALLA DE ACCIONES, CORRESPONDE A LAS ACCIONES DE LA BARRA DE MENU
        // REALIZA ACCION DE ENTRADA DE VISTA usuario.jsp
        if (menu.equals("Usuarios")) {
            switch (accion) {

                case "Listar":
                    String tipos[] = {"Administrador", "Cliente"};
                    request.setAttribute("usuarios", usuarioDao.getUsuarios());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarioEdit", new modelo_user());
                    break;

                case "Buscar":
                    int CedulaB = Integer.valueOf(request.getParameter("txtId"));
                    modelo_user busu = new modelo_user();
                    String[] categoriasb = {"Administrador", "Cliente"};
                    busu = usuarioDao.getUsuarioCedula(CedulaB);
                    request.setAttribute("usuarioEdit", busu);
                    request.setAttribute("categorias", categoriasb);
                    request.setAttribute("cedula", busu.getCedulaUsuario()); // **** LUIS, ESTE ELEMENTO ES NUEVO
                    break;

                case "Agregar":
                    int CdUsuario = Integer.parseInt(request.getParameter("txtId"));
                    String clave = request.getParameter("txtClave");
                    String nombreUsuario = request.getParameter("txtNombre");
                    String correo = request.getParameter("txtCorreo");
                    String tipoUsuario = request.getParameter("txtTipo");
                    usuario.setCedulaUsuario(CdUsuario);
                    usuario.setClave(clave);
                    usuario.setCorreo(correo);
                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setTipoUsuario(tipoUsuario);
                    boolean creado = usuarioDao.agregarUsuario(usuario);
                    if (creado) {
                        mensaje = "Usuario Creado";
                    } else {
                        mensaje = "Faltan Datos del Usuario";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    int CdU = Integer.valueOf(request.getParameter("id"));
                    modelo_user usu = new modelo_user();
                    String[] categorias = {"Administrador", "Cliente"};
                    usu = usuarioDao.getUsuarioCedula(CdU);
                    request.setAttribute("usuarioEdit", usu);
                    request.setAttribute("categorias", categorias);
                    break;

                case "Actualizar":
                    int cdUsuarioa = Integer.parseInt(request.getParameter("txtId"));
                    String clavea = request.getParameter("txtClave");
                    String nombreUsuarioa = request.getParameter("txtNombre");
                    String correoa = request.getParameter("txtCorreo");
                    String tipoUsuarioa = request.getParameter("txtTipo");
                    usuario.setCedulaUsuario(cdUsuarioa);
                    usuario.setClave(clavea);
                    usuario.setCorreo(correoa);
                    usuario.setNombreUsuario(nombreUsuarioa);
                    usuario.setTipoUsuario(tipoUsuarioa);
                    usuarioDao.actualizarUsuario(usuario);
                    boolean editado = usuarioDao.actualizarUsuario(usuario);
                    if (editado) {
                        mensaje = "Usuario Actualizado";
                    } else {
                        mensaje = "Faltan datos";
                    }
                    request.setAttribute("mensaje: ", mensaje);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    int cdUsuarioa2 = Integer.valueOf(request.getParameter("id"));
                    usuarioDao.eliminarUsuario(cdUsuarioa2);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/usuarios.jsp").forward(request, response);
        } // if (menu.equals("Usuarios")) {}
        
        
        // REALIZA ACCION DE ENTRADA DE VISTA proveedor.jsp
        else if (menu.equals("Proveedor")) {
            switch (accion) {

                /* **** DE LOS ELEMENTOS A CONTINUACION
                1. ESTA PARTE DE CODIGO ESTA TRABAJANDO CON EL MODELO DE USUARIO, SE DEBERA CAMBIAR POR PROVEEDOR
                2. MODIFICAR LAS PUESTAS DE ENTRADA Y SALIDA (SET Y GET DE MODELO)
                3. HAY PARTES DEL CODIGO DONDE SE IMPORTA EL MODELO USUARIO, SE DEBERA CAMBIAR
                4. HAY MODIFICACIONES ADICIONALES QUE EN ANTERIORES VERSIONES NO TENIA, SE DEBERAN AGREGAR, EJEMPLO case "Listar"
                 */
                case "Listar":
                    request.setAttribute("proveedor", proveedorDao.getProveedor());
                    request.setAttribute("proveedorEdit", new modelo_supplier());
                    break;

                case "Buscar":
                    int idp = Integer.valueOf(request.getParameter("txtId"));
                    modelo_supplier bpro = new modelo_supplier();
                    String[] categoriasb = {"Administrador", "Cliente"};
                    bpro = proveedorDao.getProveedorId(idp);
                    request.setAttribute("proveedorEdit", bpro);
                    request.setAttribute("categorias", categoriasb);
                    break;

                case "Agregar":

                    // IMORTANTE, ESTE CAMBIO CAMBIA DRASTICAMENTE, SE DEBERA GUARDAR EL ORDEN SEGUN LA TABLA DE BASE DE DATOS
                    // REVISAR LA DEFINICION DE TIPOS DE VARIABLES
                    // TENER EN CUENTA LOS NOMBRES DE LAS ENTRADAS SI ES QUE TAMBIEN SE CAMIA LOS BONOTES EN EL HTML O VISTA PROVEEDORES
                    int nit = Integer.parseInt(request.getParameter("txtId"));
                    String clave = request.getParameter("txtClave");
                    String nombreUsuario = request.getParameter("txtNombre");
                    String correo = request.getParameter("txtCorreo");
                    String tipoUsuario = request.getParameter("txtTipo");
                    proveedor.setSupplierNit(nit);
                    proveedor.setSupplierName(clave);
                    proveedor.setSupplierAddress(correo);
                    proveedor.setSupplierPhone(nit);
                    proveedor.setSupplierCity(tipoUsuario);
                    boolean creado = proveedorDao.agregarProveedor(proveedor);
                    if (creado) {
                        mensaje = "Usuario Creado";
                    } else {
                        mensaje = "Faltan Datos del Usuario";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    int ideu = Integer.valueOf(request.getParameter("id"));
                    modelo_user usu = new modelo_user(); // SE DEBE CAMBIAR LA IMPORTACION DEL MODEL POR EL DE modelu_supplier
                    String[] categorias = {"Administrador", "Cliente"};
                    usu = usuarioDao.getUsuarioCedula(ideu); // SE DEBE MODIFICAR ESTE ELEMENTO POR EL MODEL DE PROVEEDOR
                    request.setAttribute("usuarioEdit", usu);
                    request.setAttribute("categorias", categorias);
                    break;

                case "Actualizar":
                    int idUsuarioa = Integer.parseInt(request.getParameter("txtId"));
                    String clavea = request.getParameter("txtClave");
                    String nombreUsuarioa = request.getParameter("txtNombre");
                    String correoa = request.getParameter("txtCorreo");
                    String tipoUsuarioa = request.getParameter("txtTipo");
                    usuario.setCedulaUsuario(idUsuarioa);
                    usuario.setClave(clavea);
                    usuario.setCorreo(correoa);
                    usuario.setNombreUsuario(nombreUsuarioa);
                    usuario.setTipoUsuario(tipoUsuarioa);
                    usuarioDao.actualizarUsuario(usuario);
                    request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    int idUsuarioe = Integer.valueOf(request.getParameter("id"));
                    usuarioDao.eliminarUsuario(idUsuarioe);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/proveedor.jsp").forward(request, response);
        } // else if (menu.equals("Proveedor")) {}

    } // protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
