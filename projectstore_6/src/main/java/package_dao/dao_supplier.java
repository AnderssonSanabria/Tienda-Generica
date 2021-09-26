package package_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
// IMPORTACION DE CONEXION
import package_conexion.conexion_database;
// IMPORTACION DE MODELO_SUPPLIER
import package_modelo.modelo_supplier;

// CUERPO DAO
public class dao_supplier {
    
    
    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    // AGREGAR PROVEEDOR
    public List<modelo_supplier> getProveedor() {
        String sql = "SELECT * FROM table_supplier";
        List<modelo_supplier> proveedor = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_supplier sup = new modelo_supplier(); // Instanciamos un objeto tipo modelo_user
                sup.setSupplierNit(res.getInt(1));
                sup.setSupplierName(res.getString(2));
                sup.setSupplierAddress(res.getString(3));
                sup.setSupplierPhone(res.getInt(4));
                sup.setSupplierCity(res.getString(5));
                proveedor.add(sup); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return proveedor; // Devuelve el ArrayList usuarios
    }

    // AGREGAR USUARIO
    public boolean agregarProveedor(modelo_supplier proveedor) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM table_supplier where SupplierNit = " // Instrucción sql
                + proveedor.getSupplierNit(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO table_supplier values (" + proveedor.getSupplierNit() + ",'" + proveedor.getSupplierName()
                    + "','" + proveedor.getSupplierAddress() + "','" + proveedor.getSupplierPhone() + "','"
                    + proveedor.getSupplierCity() + "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
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
        } catch (SQLException e) {
            System.out.println("Mensaje:" + e.getMessage());
            System.out.println("Estado:" + e.getSQLState());
            System.out.println("Codigo del error:" + e.getErrorCode());
            System.out.println("Error: Clase dao_user, método agregarUsuario" + e.getMessage());
        }
        return encontrado;
    }

    // BUSCAR USUARIO POR ID
    public modelo_supplier getProveedorId(int id) { // **** SE RECOMIENDA CAMBIAR ESTE CODIGO SI ES NECESARIO
        String sql = "SELECT * FROM table_supplier WHERE SupplierNit=" + id;
        modelo_supplier sup = new modelo_supplier();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                sup.setSupplierNit(res.getInt(1));
                sup.setSupplierName(res.getString(2));
                sup.setSupplierAddress(res.getString(3));
                sup.setSupplierPhone(res.getInt(4));
                sup.setSupplierCity(res.getString(5));
            }
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println("\n\n>> >> >> dao_suppler / BUSCAR POR IDPROVEEDOR / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return sup;
    }

    // ACTUALIZACION DE DATOS
    public boolean actualizarProveedor(modelo_supplier proveedor) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE table_supplier SET SupplierName = '" + proveedor.getSupplierName()
                + "', SupplierAddress = '" + proveedor.getSupplierAddress()+ "', SupplierPhone='"
                + proveedor.getSupplierPhone()+ "'" + ", SupplierCity = '" + proveedor.getSupplierCity()+ "'"
                + " WHERE SupplierNit = " + proveedor.getSupplierNit();
        System.out.println("\n\n>> >> >> " + sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase dao_user, método actualizar");
            e.printStackTrace();
        } // catch (SQLException e) {}
        return actualizar;
    }

    // ELIMINAR USUARIO
    public boolean eliminarProveedor(int id) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM table_supplier WHERE SupplierNit=" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM table_supplier WHERE SupplierNit = " + id;
        if (encontrado) {
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            } catch (SQLException e) {
                System.out.println("Error: Clase dao_user, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }

}
