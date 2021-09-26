package mintic.edu.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mintic.edu.proyecto.conexion.conexion_database;
import mintic.edu.proyecto.modelo.modelo_user;

/**
 *
 * @author luisj
 */
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

    // AGREGAR USUARIO
    public List<modelo_user> getUsuarios() {
        String sql = "SELECT * FROM usuario";
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

    // AGREGAR USUARIO
    public boolean agregarUsuario(modelo_user usuario) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM usuario where idUsuario = " // Instrucción sql
                + usuario.getCedulaUsuario(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO usuario values (" + usuario.getCedulaUsuario() + ",'" + usuario.getNombreUsuario()
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

    // BUSCAR USUARIO POR ID
    public modelo_user getUsuarioId(int id) {
        String sql = "SELECT * FROM usuario WHERE idUsuario=" + id;
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
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println("\n\n>> >> >> dao_user / BUSCAR POR IDUSER / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return usu;
    }

    // ACTUALIZACION DE DATOS
    public boolean actualizarUsuario(modelo_user usuario) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE usuario SET nombreUsuario = '" + usuario.getNombreUsuario()
                + "', correo = '" + usuario.getCorreo() + "', tipoUsuario='"
                + usuario.getTipoUsuario() + "'" + ", clave = '" + usuario.getClave() + "'"
                + " WHERE cedulaUsuario = " + usuario.getCedulaUsuario();
        System.out.println("\n\n>> >> >> " + sql);
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
        String buscar = "SELECT * FROM usuario WHERE idUsuario=" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM usuario WHERE cedulaUsuario = " + id;
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
