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
import package_modelo.modelo_user;

// CUERPO DAO
public class dao_user {

    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    // INGRESO DE USUARIO
    public String login(modelo_user usu) {
        System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / INICIO PROCESO DE CONEXION\n");
        String estado = "";
        ResultSet rs;
        try {
            con = cn.Conexion();
            String sql = "select rol, email_usuario from usuarios where nombre_usuario=? and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getNombreUsuario());
            ps.setString(2, usu.getClave());
            rs = ps.executeQuery();
            System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / VALIDACION DE USUARIO\n");
            if (rs.next()) {
                estado = "true";
            }
            usu.setTipoUsuario(rs.getString("rol"));
            usu.setCorreo(rs.getString("email_usuario"));
            System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / USUARIO CORRECTO\n");
        }
        catch (Exception e) {
            System.err.println("Error:" + e);
            System.out.println("\n\n>> >> >> DAO_USUARIO / LOGIN / USUARIO INCORRECTO\n");
        }
        return estado;
    } // public String login(modelo_user usu) {}

    // AGREGAR USUARIO
    public List<modelo_user> getUsuarios() {
        String sql = "SELECT * FROM usuarios";
        List<modelo_user> usuarios = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_user usu = new modelo_user(); // Instanciamos un objeto tipo modelo_user
                usu.setCedulaUsuario(res.getInt(1));
                usu.setNombreUsuario(res.getString(2));
                usu.setClave(res.getString(3));
                usu.setCorreo(res.getString(4));
                usu.setTipoUsuario(res.getString(5));
                usuarios.add(usu); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        }
        catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return usuarios; // Devuelve el ArrayList usuarios
    }

    // AGREGAR USUARIO VERIFICACION
    public boolean agregarUsuario(modelo_user usuario) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM usuarios where cedula_usuario = " // Instrucción sql
                + usuario.getCedulaUsuario(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO usuarios values (" + usuario.getCedulaUsuario() + ",'" + usuario.getNombreUsuario()
                    + "','" + usuario.getClave() + "','" + usuario.getCorreo() + "','"
                    + usuario.getTipoUsuario() + "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
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

    // BUSCAR USUARIO
    public boolean buscar(String sql) {
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
    public modelo_user getUsuarioCedula(int CedulaB) {
        System.out.println("\n\n>> >> >> dao_user / public modelo_user getUsuarioCedula(int CedulaB) {} / INICIO");
        String sql = "SELECT * FROM usuarios WHERE cedula_usuario=" + CedulaB;
        System.out.println(">> >> >> dao_user / public modelo_user getUsuarioCedula(int CedulaB) {} / MYSQL CLIENTE: "+sql);
        modelo_user usu = new modelo_user();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                usu.setCedulaUsuario(res.getInt(1));
                usu.setNombreUsuario(res.getString(2));
                usu.setClave(res.getString(3));
                usu.setCorreo(res.getString(4));
                usu.setTipoUsuario(res.getString(5));
            }
            System.out.println(">> >> >> dao_user / public modelo_user getUsuarioCedula(int CedulaB) {} / CLIENTE: "+usu.toString());
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println(">> >> >> dao_user / public modelo_user getUsuarioCedula(int CedulaB) {} / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
            System.out.println(">> >> >> dao_user / public modelo_user getUsuarioCedula(int CedulaB) {} / !! ERROR");
        }
        return usu;
    }

    // ACTUALIZACION DE DATOS
    public boolean actualizarUsuario(modelo_user usuario) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE usuarios SET nombre_usuario = '" + usuario.getNombreUsuario()
                + "', email_usuario = '" + usuario.getCorreo() + "', rol='"
                + usuario.getTipoUsuario() + "'" + ", clave = '" + usuario.getClave() + "'"
                + " WHERE cedula_usuario = " + usuario.getCedulaUsuario();
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
    public boolean eliminarUsuario(int id) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM usuarios WHERE cedula_usuario=" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM usuarios WHERE cedula_usuario = " + id;
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
