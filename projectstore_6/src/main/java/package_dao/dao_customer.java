package package_dao;

// IMPORTACION DE ELEMENTOS
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import package_conexion.conexion_database;
import package_modelo.modelo_customer;
import package_modelo.modelo_user;

public class dao_customer {

    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    /* PENDIENTE POR VERIFICAR
    // INGRESO DE USUARIO
    public String login(modelo_user usu) {
        System.out.println("\n\n>> >> >> DAO_CUSTOMER / LOGIN / INICIO PROCESO DE CONEXION\n");
        String estado = "";
        ResultSet rs;
        try {
            con = cn.Conexion();
            String sql = "select tipoUsuario, correo from usuario where nombreUsuario=? and clave=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getNombreUsuario());
            ps.setString(2, usu.getClave());
            rs = ps.executeQuery();
            System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / VALIDACION DE USUARIO\n");
            if (rs.next()) {
                estado = "true";
            }
            usu.setTipoUsuario(rs.getString("tipoUsuario"));
            usu.setCorreo(rs.getString("correo"));
            System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / USUARIO CORRECTO\n");
        }
        catch (Exception e) {
            System.err.println("Error:" + e);
            System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / USUARIO INCORRECTO\n");
        }
        return estado;
    } // public String login(modelo_user usu) {}
    */
    
    // AGREGAR CLIENTE
    public List<modelo_customer> ProcAddCustomer() {
        String SqlTableCustomer = "SELECT * FROM table_customer";
        List<modelo_customer> ListCustomer = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(SqlTableCustomer);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_customer ModeloCustomer = new modelo_customer(); // Instanciamos un objeto tipo modelo_user
                ModeloCustomer.setCustomerId(res.getInt(1));
                ModeloCustomer.setCustomerNameFull(res.getString(2));
                ModeloCustomer.setCustomerAddress(res.getString(3));
                ModeloCustomer.setCustomerPhone(res.getInt(4));
                ModeloCustomer.setCustomerEmail(res.getString(5));
                ListCustomer.add(ModeloCustomer); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        }
        catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return ListCustomer; // Devuelve el ArrayList usuarios
    }

    // AGREGAR CLIENTE VERIFICACION
    public boolean ProcAddCustomerCheck(modelo_customer BCustomer /*DEFINE EL NOMBRE DEL MODELO A IMPORTAR*/ ) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String PBuscar = "SELECT * FROM table_customer WHERE CustomerId = " + BCustomer.getCustomerId(); // Para buscar un registro con el mismo id
        encontrado = ProcSearchCustomerCheck(PBuscar); // REALIZA PROCESO EXTERNO A ESTA PROCESO
        if (!encontrado) { // La instrucción para insertar el registro
            String SqlTableCustomerInsert = "INSERT INTO table_customer VALUES (" + BCustomer.getCustomerId()+ ",'" + BCustomer.getCustomerNameFull()
                    + "','" + BCustomer.getCustomerAddress()+ "','" + BCustomer.getCustomerPhone()+ "','" + BCustomer.getCustomerEmail()+ "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(SqlTableCustomerInsert);
                registrar = true;
                stm.close();
                con.close();
            }
            catch (SQLException e) {
                System.out.println("Error: Clase UsuarioDao, método agregarUsuario");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("PROCESO DE VERIFICACION DE BUSQUEDA NO REALIZADO");
        }
        return registrar;
    }

    // BUSCAR USUARIO - VALIDACION
    public boolean ProcSearchCustomerCheck (String sql) {
        boolean encontrado = false;
        con = cn.Conexion();
        try {
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) {
                encontrado = true;
            }
        }
        catch (SQLException e) {
            System.out.println("Mensaje:" + e.getMessage());
            System.out.println("Estado:" + e.getSQLState());
            System.out.println("Codigo del error:" + e.getErrorCode());
            System.out.println("Error: Clase dao_user, método agregarUsuario" + e.getMessage());
        }
        return encontrado;
    }

    // BUSCAR USUARIO POR CEDULA
    public modelo_customer ProcSearchCustomer(int CustomerIdent) {
        String sql = "SELECT * FROM table_customer WHERE CustomerId=" + CustomerIdent;
        modelo_customer ModeloCustomer = new modelo_customer();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                ModeloCustomer.setCustomerId(res.getInt(1));
                ModeloCustomer.setCustomerNameFull(res.getString(2));
                ModeloCustomer.setCustomerAddress(res.getString(3));
                ModeloCustomer.setCustomerPhone(res.getInt(4));
                ModeloCustomer.setCustomerEmail(res.getString(5));
            }
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println("\n\n>> >> >> DAO_CUSTOMER / BUSCAR POR CUSTOMER / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return ModeloCustomer;
    }

    // ACTUALIZACION DE DATOS
    public boolean ProcUpdateCustomer(modelo_customer Acustomer) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE table_customer SET CustomerNameFull = '" + Acustomer.getCustomerNameFull()
                + "', CustomerAddress = '" + Acustomer.getCustomerAddress() + "', CustomerPhone='"
                + Acustomer.getCustomerPhone() + "'" + ", CustomerEmail = '" + Acustomer.getCustomerEmail() + "'"
                + " WHERE CustomerId = " + Acustomer.getCustomerId();
        System.out.println("\n\n>> >> >> dao_user / ACTUALIZACION DE DATOS" + sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            actualizar = true;
        }
        catch (SQLException e) {
            System.out.println("Error: Clase dao_user, método actualizar");
            e.printStackTrace();
        } // catch (SQLException e) {}
        return actualizar;
    }

    // ELIMINAR USUARIO
    public boolean ProcDeleterCustomer(int CId) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM table_customer WHERE CustomerId=" + CId;
        encontrado = ProcSearchCustomerCheck(buscar);
        String sql = "DELETE FROM table_customer WHERE CustomerId = " + CId;
        if (encontrado) {
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            }
            catch (SQLException e) {
                System.out.println("Error: Clase dao_user, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }

}