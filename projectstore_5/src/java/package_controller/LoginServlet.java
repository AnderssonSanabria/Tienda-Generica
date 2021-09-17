package package_controller;

// IMPORTACION DE ELEMENTOS
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import package_modelo.modelo_user;
import package_dao.dao_user;

// PROCESOS DE CONEXION
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\n\n>> >> >> LOGINSERVLET / INICIO\n");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("btnAcceder") != null) {
            System.out.println("\n\n>> >> >> LOGINSERVLET / INGRESO DE USUARIO\n");
            modelo_user usu = new modelo_user();
            String user = request.getParameter("txtUsuario");
            String clave = request.getParameter("txtPassword");
            usu.setNombreUsuario(user);
            usu.setClave(clave);
            dao_user login = new dao_user();
            String estado, correo;
            try {
                System.out.println("\n\n>> >> >> LOGINSERVLET / VALIDACION TIPO DE USUARIO\n");
                estado = login.login(usu);
                if ("true".equals(estado)) {
                    System.out.println("\n\n>> >> >> LOGINSERVLET / USUARIO CORRECTO\n");
                    HttpSession objSesion = request.getSession();
                    if (usu.getTipoUsuario().equals("Administrador")) {
                        System.out.println("\n\n>> >> >> LOGINSERVLET / USUARIO IDENTIFICADO COMO ADMINISTRADOR\n");
                        objSesion.setAttribute("usuario", user);
                        objSesion.setAttribute("objusuario", usu);
                        objSesion.setAttribute("nivel", "Administrador");
                        response.sendRedirect("jsp/vistaAdministrador.jsp");
//                        response.sendRedirect("jsp/vistaAdmin.jsp");
                    } else if (usu.getTipoUsuario().equals("Cliente")) {
                        System.out.println("\n\n>> >> >> LOGINSERVLET / USUARIO IDENTIFICADO COMO CLIENTE\n");
                        objSesion.setAttribute("usuario", user);
                        objSesion.setAttribute("objusuario", usu);
                        objSesion.setAttribute("nivel", "Cliente");
                        response.sendRedirect("jsp/vistaCliente.jsp");
                    }
                } // if ("true".equals(estado)) {}
                else {
                    System.out.println("\n\n>> >> >> LOGINSERVLET / ELSE / ERROR DE USUARIO O CONTRASEÑA\n");
                    response.sendRedirect("jsp/error.html");
                } // else {}
            } // try{}
            catch (Exception ex) {
                System.out.println("\n\n>> >> >> LOGINSERVLET / CATCH / ERROR DE USUARIO O CONTRASEÑA\n");
                response.sendRedirect("jsp/error.html");
            }
        }
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
