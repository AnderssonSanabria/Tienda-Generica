package package_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import package_conexion.conexion_database;
import package_modelo.modelo_producto;
import package_modelo.modelo_user;


public class dao_producto {
        // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;
    
    public boolean agregarProducto(modelo_producto producto) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM productos where codigo_producto = " + producto.getCodigo_producto(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO productos values (" + producto.getCodigo_producto() + "," + producto.getIva_compra()
                    + "," + producto.getNit_proveedor() + ",'" + producto.getNombre_producto()+ "'," + producto.getPrecio_compra() + ","
                    + producto.getPrecio_venta()+ ")";
            System.out.println(sql);
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            }
            catch (SQLException e) {
                System.out.println("Error: Clase ProductoDAO, método agregaProducto");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("PROCESO DE VERIFICACION DE BUSQUEDA NO REALIZADO");
        }
        return registrar;
    }
    
    public modelo_producto BuscarProductoPorCodigo (int CodigoProducto) {
        
        System.out.println("\n\n>> >> >> dao_producto / BuscarProductoPorCodigo / INICIO");
        String AccionSql ="select * from productos where codigo_producto = " + CodigoProducto;
        System.out.println(">> >> >> dao_producto / BuscarProductoPorCodigo / AccionSql: ["+AccionSql+"]");
        modelo_producto MProducto = new modelo_producto();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(AccionSql);
            res = ps.executeQuery();
            while (res.next()) {
                MProducto.setCodigo_producto(res.getInt(1));
                MProducto.setNombre_producto(res.getString(2));
                MProducto.setNit_proveedor(res.getInt(3));
                MProducto.setPrecio_compra(res.getDouble(4));
                MProducto.setIva_compra(res.getDouble(5));
                MProducto.setPrecio_venta(res.getDouble(6));
            }
            System.out.println(">> >> >> dao_producto / BuscarProductoPorCodigo / MProducto: ["+MProducto.toString()+"] / "+MProducto);
            // CIERRE DE JDBC
            ps.close();
            res.close();
            con.close();
        }
        catch (SQLException er) {
            System.err.println("ERROR: "+er);
        }
        System.out.println(">> >> >> dao_producto / BuscarProductoPorCodigo / return: ["+MProducto.toString()+"]");
        return MProducto;
    }
    
    
    
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
            System.out.println("Error: Clase dao_Producto, método agregaProducto" + e.getMessage());
        }
        return encontrado;
    }
}


