package package_controller;

// IMPORTACION DE ELEMENTOS
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// IMPORTACION DE MODELOS
import package_modelo.modelo_user;
import package_modelo.modelo_supplier;
import package_modelo.modelo_customer;
import package_modelo.modelo_producto;
import package_modelo.modelo_ventas;
import package_modelo.modelo_detalle_ventas;
import package_modelo.modelo_reporte_ventas;

// IMPORTACION DE DAO PROCESOS
import package_dao.dao_user;
import package_dao.dao_supplier;
import package_dao.dao_customer;
import package_dao.dao_producto;
import package_dao.dao_ventas;
import package_dao.dao_delalle_ventas;

// ______________________________________________________________________________
// CUERPO DE PROCESOS
public class Controlador extends HttpServlet {

    // ______________________________________________________________________________
    //  ASIGNACION DE MODELO A VARIABLES
    modelo_user usuario = new modelo_user();
    modelo_supplier proveedor = new modelo_supplier();
    modelo_customer cliente = new modelo_customer();
    modelo_producto ModelProducto = new modelo_producto();
    modelo_ventas ModeloVentas = new modelo_ventas();
    modelo_detalle_ventas ModeloDetalleVenta = new modelo_detalle_ventas();
    modelo_reporte_ventas ModeloReporteVentas = new modelo_reporte_ventas();

    // ASIGNACION DE DAO A VARIABLES
    dao_user usuarioDao = new dao_user();
    dao_supplier proveedorDao = new dao_supplier();
    dao_customer clienteDao = new dao_customer();
    dao_producto DaoProducto = new dao_producto();
    dao_ventas DaoVentas = new dao_ventas();
    dao_delalle_ventas DaoDetalleVenta = new dao_delalle_ventas();

    // ______________________________________________________________________________
    // CRUD DE USUARIO
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // DEFINICION DE ACCIONES PARA LA BARRA DE MENU
        List<modelo_detalle_ventas> ListDetalleVentas = new ArrayList<modelo_detalle_ventas>(); // detalleVentas
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        // VARIABLES Y ELEMENTOS DE CONTROLADOR
        String mensaje = "";
        String MensajeAviso = null;
        String MensajeReporte = null;
        String Descripcion = null;
        int NumeroFactura = 0;
        int Operacion = 0;
        int CedulaCliente = 0;
        int CedulaUsuario = 0;
        int CodigoProducto = 0;
        int Item = 0;
        int Cantidad = 0;
        double Total = 0;
        double SubTotal = 0;
        double TotalIva = 0;
        double TotalFactura = 0;
        double PrecioVenta = 0;
        int granTotalVentas = 0;

        /* 
        **ERROR EN SECCION DE CODIGO
        **SE PRESENTA UN ERROR DE CODIGO, EN EL USO DE LA LINEA [[ModeloVentas = (usuario) objSesionVentas.getAttribute("objusuario");]] >> SECCION ESPECIFICA [[(usuario)]]
        **SE UTILIZA UNA VARIABLE CON VALOR ASIGNADO PARA QUE PUEDA REALIZAR EL PROCESO EL PROGRAMA
        
        HttpSession objSesionVentas = (HttpSession) request.getSession();
        ModeloVentas = (usuario) objSesionVentas.getAttribute("objusuario");
        CedulaUsuario = ModeloVentas.getCedula_usuario();
        System.out.println("USUARIO INICIAL: "+ModeloVentas.toString());
         */
        //
        // ______________________________________________________________________________
        // REALIZA ACCION DE ENTRADA DE VISTA usuario.jsp
        if (menu.equals("Usuarios")) {
            System.out.println("\n\n>> >> >> Controlador / Usuarios / INICIO");
            switch (accion) {

                case "Listar":
                    System.out.println("\n\n>> >> >> Controlador / Usuarios / accion / Listar / INICIO");
                    String tipos[] = {"Administrador", "Cliente"};
                    request.setAttribute("usuarios", usuarioDao.getUsuarios());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarioEdit", new modelo_user());
                    System.out.println(">> >> >> Controlador / Usuarios / accion / Listar / FIN");
                    break;

                case "Buscar":
                    System.out.println("\n\n>> >> >> Controlador / Usuarios / accion / Buscar / INICIO");
                    int CedulaB = Integer.valueOf(request.getParameter("txtId"));
                    modelo_user busu = new modelo_user();
                    String[] categoriasb = {"Administrador", "Cliente"};
                    busu = usuarioDao.getUsuarioCedula(CedulaB);
                    request.setAttribute("usuarioEdit", busu);
                    request.setAttribute("categorias", categoriasb);
                    request.setAttribute("cedula", busu.getCedulaUsuario()); // **** LUIS, ESTE ELEMENTO ES NUEVO
                    System.out.println(">> >> >> Controlador / Usuarios / accion / Buscar / FIN");
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
        //
        // ______________________________________________________________________________
        // REALIZA ACCION DE ENTRADA DE VISTA proveedor.jsp
        else if (menu.equals("Proveedores")) {

            int nit;
            String nombre_proveedor;
            String direccion_proveedor;
            int telefono_proveedor;
            String ciudad_proveedor;

            switch (accion) {

                case "Listar":
                    request.setAttribute("proveedor", proveedorDao.getProveedor());
                    request.setAttribute("proveedorEdit", new modelo_supplier());
                    break;

                case "Buscar":
                    modelo_supplier pro = new modelo_supplier();
                    nit = Integer.valueOf(request.getParameter("txtIdp"));
                    pro = proveedorDao.getProveedorId(nit);
                    request.setAttribute("proveedorEdit", pro);
                    request.setAttribute("nit", pro.getSupplierNit());
                    break;

                case "Agregar":
                    nit = Integer.parseInt(request.getParameter("txtIdp"));
                    nombre_proveedor = request.getParameter("txtNombrep");
                    direccion_proveedor = request.getParameter("txtDireccionp");
                    telefono_proveedor = Integer.parseInt(request.getParameter("txtTelefonop"));
                    ciudad_proveedor = request.getParameter("txtCiudadp");
                    proveedor.setSupplierNit(nit);
                    proveedor.setSupplierName(nombre_proveedor);
                    proveedor.setSupplierAddress(direccion_proveedor);
                    proveedor.setSupplierPhone(telefono_proveedor);
                    proveedor.setSupplierCity(ciudad_proveedor);
                    boolean creado = proveedorDao.agregarProveedor(proveedor);
                    if (creado) {
                        mensaje = "Usuario Creado";
                    } else {
                        mensaje = "Faltan Datos del Proveedor";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    nit = Integer.valueOf(request.getParameter("txtIdp"));
                    request.setAttribute("proveedorEdit", proveedorDao.getProveedorId(nit));
                    break;

                case "Actualizar":
                    nit = Integer.parseInt(request.getParameter("txtIdp"));
                    nombre_proveedor = request.getParameter("txtNombrep");
                    direccion_proveedor = request.getParameter("txtDireccionp");
                    telefono_proveedor = Integer.parseInt(request.getParameter("txtTelefonop"));
                    ciudad_proveedor = request.getParameter("txtCiudadp");
                    proveedor.setSupplierNit(nit);
                    proveedor.setSupplierName(nombre_proveedor);
                    proveedor.setSupplierAddress(direccion_proveedor);
                    proveedor.setSupplierPhone(telefono_proveedor);
                    proveedor.setSupplierCity(ciudad_proveedor);
                    proveedorDao.actualizarProveedor(proveedor);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    nit = Integer.valueOf(request.getParameter("txtIdp"));
                    proveedorDao.eliminarProveedor(nit);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/proveedor.jsp").forward(request, response);
        } // else if (menu.equals("Proveedor")) {}
        //
        // ______________________________________________________________________________
        // REALIZA ACCION DE ENTRADA DE VISTA proveedor.jsp
        else if (menu.equals("Clientes")) {
            int cedula_cliente;
            String nombre_cliente;
            String direccion_cliente;
            int telefono_cliente;
            String email_cliente;

            switch (accion) {

                case "Listar":
                    request.setAttribute("cliente", clienteDao.getCliente());
                    request.setAttribute("clienteEdit", new modelo_customer());
                    break;

                case "Buscar":
                    modelo_customer cli = new modelo_customer();
                    cedula_cliente = Integer.valueOf(request.getParameter("txtIdc"));
                    cli = clienteDao.getClienteId(cedula_cliente);
                    request.setAttribute("clienteEdit", cli);
                    request.setAttribute("cedula_cliente", cli.getCustomerId());
                    break;

                case "Agregar":

                    cedula_cliente = Integer.parseInt(request.getParameter("txtIdc"));
                    nombre_cliente = request.getParameter("txtNombrec");
                    direccion_cliente = request.getParameter("txtDireccionc");
                    telefono_cliente = Integer.parseInt(request.getParameter("txtTelefonoc"));
                    email_cliente = request.getParameter("txtEmailc");
                    cliente.setCustomerId(cedula_cliente);
                    cliente.setCustomerNameFull(nombre_cliente);
                    cliente.setCustomerAddress(direccion_cliente);
                    cliente.setCustomerPhone(telefono_cliente);
                    cliente.setCustomerEmail(email_cliente);
                    boolean creado = clienteDao.agregarCliente(cliente);
                    if (creado) {
                        mensaje = "Cliente Creado";
                    } else {
                        mensaje = "Faltan Datos del Cliente";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    cedula_cliente = Integer.valueOf(request.getParameter("txtIdc"));
                    request.setAttribute("clienteEdit", clienteDao.getClienteId(cedula_cliente));
                    break;

                case "Actualizar":
                    cedula_cliente = Integer.parseInt(request.getParameter("txtIdc"));
                    nombre_cliente = request.getParameter("txtNombrec");
                    direccion_cliente = request.getParameter("txtDireccionc");
                    telefono_cliente = Integer.parseInt(request.getParameter("txtTelefonoc"));
                    email_cliente = request.getParameter("txtEmailc");
                    cliente.setCustomerId(cedula_cliente);
                    cliente.setCustomerNameFull(nombre_cliente);
                    cliente.setCustomerAddress(direccion_cliente);
                    cliente.setCustomerPhone(telefono_cliente);
                    cliente.setCustomerEmail(email_cliente);
                    clienteDao.actualizarCliente(cliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    cedula_cliente = Integer.valueOf(request.getParameter("txtIdc"));
                    clienteDao.eliminarCliente(cedula_cliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            } // switch (accion){}
            request.getRequestDispatcher("jsp/cliente.jsp").forward(request, response);
        } // else if (menu.equals("clientes")) {}
        //
        //
        // ______________________________________________________________________________
        // REALIZA ACCION DE ENTRADA DE VISTA registrarVenta.jsp
        else if (menu.equals("AccionVentas")) {
            System.out.println("\n\n>> >> >> Controlador / AccionVentas / INICO");
            int BuscarCedulaCliente;
            int BuscarProductoCodigo;

            // VARIABLE DE VENTAS
            switch (accion) {

                case "BuscarCliente":
                    System.out.println("\n\n>> >> >> Controlador / AccionVentas / accion / BuscarCliente / INICIO");
                    if (request.getParameter("txtCedula").isEmpty()) {
                        MensajeAviso = "Cedula en blanco";
                        MensajeReporte = null;
                    } else {
                        System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / Parametre [Integer.parseInt(request.getParameter(\"txtCedula\"))]: [" + Integer.parseInt(request.getParameter("txtCedula")) + "]");
                        BuscarCedulaCliente = Integer.parseInt(request.getParameter("txtCedula"));
                        System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / Int BuscarCedulaCliente: [" + BuscarCedulaCliente + "]");
                        cliente.setCustomerId(BuscarCedulaCliente);
                        cliente = clienteDao.getClienteId(BuscarCedulaCliente);
                        System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / Parametre clienteDao.getClienteId(BuscarCedulaCliente): [" + clienteDao.getClienteId(BuscarCedulaCliente) + "]");
                        System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / Model cliente: [" + cliente + "]");
                        if (cliente.getCustomerNameFull() == null) {
                            MensajeAviso = "Cedula no encontrada.";
                            MensajeReporte = null;
                        } else {
                            MensajeAviso = null;
                            MensajeReporte = "Cliente encontrado.";
                        }
                    }
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / cliente: [" + cliente + "]");
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / cliente.toString(): [" + cliente.toString() + "]");
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / cliente.getCustomerNameFull(): [" + cliente.getCustomerNameFull() + "]");
                    request.setAttribute("IdVenta", NumeroFactura);
                    request.setAttribute("Aviso", MensajeAviso);
                    request.setAttribute("Mensaje", MensajeReporte);
                    request.setAttribute("ClienteFactura", cliente);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / BuscarCliente / FIN");
                    break;

                case "AccionVentas_BuscarProducto":
                    System.out.println("\n\n>> >> >> Controlador / AccionVentas / accion / AccionVentas_BuscarProducto / INICIO");
                    BuscarProductoCodigo = Integer.parseInt(request.getParameter("txtCodigo"));
                    ModelProducto.setCodigo_producto(BuscarProductoCodigo);
                    ModelProducto = DaoProducto.BuscarProductoPorCodigo(BuscarProductoCodigo);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / AccionVentas_BuscarProducto / PRODUCTO: " + ModelProducto.toString());
                    request.setAttribute("IdVenta", NumeroFactura); // ESTA LINEA NO ES DEL TODO NECESARIA
                    request.setAttribute("ClienteFactura", cliente); // ESTA LINEA NO ES DEL TODO NECESARIA
                    request.setAttribute("ProductoFactura", ModelProducto);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / AccionVentas_BuscarProducto / FIN");
                    break;

                case "Listar":
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / INICIO");
                    NumeroFactura = DaoVentas.CalcularIdVenta();
                    NumeroFactura += 1;
                    System.out.println("Numero de Factura: " + NumeroFactura);
                    request.setAttribute("IdVenta", NumeroFactura);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / FIN");
                    break;

                case "AgregarProducto":
                    System.out.println("\n\n>> >> >> Controlador / AccionVentas / accion / AgregarProducto / INICIO");
                    Item += 1;
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / Item: [" + Item + "]");
                    /* METODO ORIGINAL
                    NumeroFactura = Integer.parseInt(request.getParameter("txtNumeroFactura"));
                     */
                    // METODO ALTERNATIVO Y FUNCIONAL
                    NumeroFactura = DaoVentas.CalcularIdVenta();
                    NumeroFactura += 1;
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / NumeroFactura: [" + NumeroFactura + "]");
                    Descripcion = request.getParameter("txtNombreProducto");
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / Descripcion: [" + Descripcion + "]");
                    PrecioVenta = Double.parseDouble(request.getParameter("txtPrecipProducto"));
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / PrecioVenta: [" + PrecioVenta + "]");
                    Cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / Cantidad: [" + Cantidad + "]");
                    Total = PrecioVenta * Cantidad;
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / Total: [" + Total + "]");
                    SubTotal += Total;
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / SubTotal: [" + SubTotal + "]");
                    // OPERACION DE IVA SE DEBE REALIZAR CON DECIMALES [ 0.19 ]
                    TotalIva += Math.round(Total * ModelProducto.getIva_compra()); // OPERACION DE IVA - EL IVA SE DEBE VERIFICAR EL ESTADO DE ESTA VARIABLE
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / TotalIva: [" + TotalIva + "]");
                    TotalFactura = SubTotal + TotalIva;
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / TotalFactura: [" + TotalFactura + "]");
                    ModeloDetalleVenta = new modelo_detalle_ventas();
                    ModeloDetalleVenta.setCodigo_detalle_venta(Item);
                    ModeloDetalleVenta.setCodigo_venta(NumeroFactura);
                    ModeloDetalleVenta.setCodigo_producto(CodigoProducto);
                    ModeloDetalleVenta.setVentaDetalleDescripcion(Descripcion);
                    ModeloDetalleVenta.setValor_venta(PrecioVenta);
                    ModeloDetalleVenta.setCantidas_producto(Cantidad);
                    ModeloDetalleVenta.setValor_total(Total);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / ModeloDetalleVenta: [" + ModeloDetalleVenta + "]");
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / ModeloDetalleVenta.toString: [" + ModeloDetalleVenta.toString() + "]");

                    ListDetalleVentas.add(ModeloDetalleVenta);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / ListDetalleVentas: [" + ListDetalleVentas + "]");
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / Listar / ListDetalleVentas.toString: [" + ListDetalleVentas.toString() + "]");

                    request.setAttribute("Subtotal", SubTotal);
                    request.setAttribute("TotalIva", TotalIva);
                    request.setAttribute("TotalFactura", TotalFactura);
                    request.setAttribute("DetalleVentas", ListDetalleVentas);
                    request.setAttribute("IdVenta", NumeroFactura);
                    request.setAttribute("ClienteFactura", cliente);
                    request.setAttribute("ProductoFactura", ModelProducto);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / AgregarProducto / FIN");
                    break;

                case "CancelarFactura":
                    System.out.println("\n\n>> >> >> Controlador / AccionVentas / accion / CancelarFactura / INICIO");
                    request.setAttribute("Aviso", "NOS RETIRAMOS POR LO PRONTO");
                    request.setAttribute("Mensaje", "NO SE BRABA NADA");
                    // USO ALTERNATIVO DE LINEA, CUADNO SE REALIZA LA ACCION DE CANCELAR, DEBE REALIZAR ACCION DE NUEVO LISTADO, ESTE CODIGO ES ALTERNATIVO
                    // request.getRequestDispatcher("Controlador?menu=AccionVentas&accion=Listar").forward(request, response);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / CancelarFactura / FIN");
                    break;

                case "GenerarFactura":
                    System.out.println("\n\n>> >> >> Controlador / AccionVentas / accion / GenerarFactura / INICIO");
                    NumeroFactura = Integer.parseInt(request.getParameter("txtNumeroFactura"));
                    CedulaCliente = Integer.parseInt(request.getParameter("txtCedula"));
                    TotalIva = Integer.parseInt(request.getParameter("txtTotalIva"));
                    SubTotal = Integer.parseInt(request.getParameter("txtSubtotalFactura"));
                    TotalFactura = Integer.parseInt(request.getParameter("txtTotalConIva"));
                    // POR ERROR EN CODIGO EN LA PRIMERA PARTE, SE DEFINE EL USUARIO [101] COMO PREDETERMINADO
                    // USUARIO DE VENTAS PREDETERMINADO
                    int Venta_UsuarioVenta = 101;
                    System.out.println("IDENTIFICACION DE USUARIO PREDEFINIDO: [" + Venta_UsuarioVenta + "]");

                    ModeloVentas.setCodigo_venta(NumeroFactura);
                    ModeloVentas.setCedula_cliente(CedulaCliente);
                    ModeloVentas.setIva_venta(TotalIva);
                    ModeloVentas.setValor_venta(SubTotal);
                    ModeloVentas.setTotal_venta(TotalFactura);
                    ModeloVentas.setCedula_usuario(Venta_UsuarioVenta);

                    boolean CreadoVenta = DaoVentas.agregarventa(ModeloVentas);
                    if (CreadoVenta) {
                        MensajeAviso = null;
                        MensajeReporte = "Registro de venta creada";
                    } else {
                        MensajeAviso = "No se puede registrar la venta";
                        MensajeReporte = null;
                    }
                    for (modelo_detalle_ventas ModeloDetalleVenta : ListDetalleVentas) {
                        System.out.println("modelo_detalle_ventas: " + ModeloDetalleVenta.toString());
                    }
                    for (modelo_detalle_ventas ModeloDetalleVenta : ListDetalleVentas) {
                        System.out.println("modelo_detalle_ventas: " + ModeloDetalleVenta.toString());
                        DaoDetalleVenta.agregarDetalleVentas(ModeloDetalleVenta);
                    }
                    request.setAttribute("Mensaje", MensajeReporte);
                    request.setAttribute("Aviso", MensajeAviso);
                    request.getRequestDispatcher("Controlador?menu=AccionVentas&accion=Listar").forward(request, response);
                    System.out.println(">> >> >> Controlador / AccionVentas / accion / GenerarFactura / FIN");
                    break;

                default:
                    throw new AssertionError();

            } // switch (accion){}
            request.getRequestDispatcher("jsp/registrarVenta.jsp").forward(request, response);
        } // else if (menu.equals("AccionVentas")) {}
        //
        //
        // ______________________________________________________________________________
        // REALIZA ACCION DE ENTRADA DE VISTA reportes.jsp
        else if (menu.equals("Reportes")) {
            switch (accion) {

                case "Usuarios":
                    String tipos[] = {"Administrador", "Cliente"};
                    List<modelo_user> listaUsuarios = new ArrayList<>();
                    listaUsuarios = usuarioDao.getUsuarios();
                    System.out.println(listaUsuarios.toString());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarios", listaUsuarios);
                    request.getRequestDispatcher("jsp/reporteUsuarios.jsp").forward(request, response);
                    break;

                case "Clientes":
                    List<modelo_customer> listaClientes = new ArrayList<>();
                    listaClientes = clienteDao.getCliente();
                    request.setAttribute("cliente", listaClientes);
                    request.getRequestDispatcher("jsp/reporteClientes.jsp").forward(request, response);
                    break;

                case "Ventas":
                    granTotalVentas = 0;
                    System.out.println("Ingreso reporte ventas");
                    List<modelo_reporte_ventas> reporteVentas = new ArrayList<>();
                    reporteVentas = DaoVentas.reporteVentasXCliente();
                    System.out.println(reporteVentas.toString());
                    for (modelo_reporte_ventas reporteVenta : reporteVentas) {
                        granTotalVentas += reporteVenta.getVentaTotal();
                    }
                    if (reporteVentas.size() == 0) {
                        mensaje = "no existen datos de ventas";
                    } else {
                        mensaje = "reporte generado";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("reporteVentas", reporteVentas);
                    request.setAttribute("granTotalVentas", granTotalVentas);
                    request.getRequestDispatcher("jsp/reporteVentas.jsp").forward(request, response);
                    break;

                default:
                    throw new AssertionError();

            }
        }
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
