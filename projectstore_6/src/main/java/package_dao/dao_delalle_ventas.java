/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import package_conexion.conexion_database;
import package_modelo.modelo_detalle_ventas;

/**
 *
 * @author ander
 */
public class dao_delalle_ventas {
    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;
    
    // AGREGAR DETALLE VENTA
    public List<modelo_detalle_ventas> getventa() {
        String sql = "SELECT * FROM detalle_ventas";
        List<modelo_detalle_ventas> detalle_ventas = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_detalle_ventas detv = new modelo_detalle_ventas(); // Instanciamos un objeto tipo modelo_detalle_ventas
                detv.setCodigo_detalle_venta(res.getInt(1));
                detv.setCantidas_producto(res.getInt(2));
                detv.setCodigo_producto(res.getInt(3));
                detv.setCodigo_venta(res.getInt(4));
                detv.setValor_total(res.getDouble(5));
                detv.setValor_venta(res.getDouble(6));
                detv.setValor_iva(res.getDouble(7));
                detalle_ventas.add(detv); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return detalle_ventas; // Devuelve el ArrayList ventas
    }

    // AGREGAR DETALLE VENTAS
    public boolean agregarDetalleVentas(modelo_detalle_ventas detalle_venta) {
        System.out.println("\n\n>> >> >> dao_delalle_ventas / public boolean agregarDetalleVentas(modelo_detalle_ventas detalle_venta) {} / INICIO");
        boolean registrar = false;
        boolean encontrado = false; 
        /* CODIGO INICIAL MODIFICADO
        String buscar = "SELECT * FROM detalle_ventas WHERE codigo_detalle_venta  = " + detalle_venta.getCodigo_detalle_venta ();
        System.out.println(">> >> >> dao_delalle_ventas / public boolean agregarDetalleVentas(modelo_detalle_ventas detalle_venta) {} / buscar: " + buscar);
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        */
        if (!encontrado) {
            String sql = "INSERT INTO detalle_ventas ("
                    + "codigo_detalle_venta,"
                    + "cantidas_producto,"
                    + "codigo_venta,"
                    + "valor_total,"
                    + "valor_venta,"
                    + "valor_iva) "
                    + "VALUES ("
                    + detalle_venta.getCodigo_detalle_venta() + "','"
                    + detalle_venta.getCantidas_producto() + "','"
                    + detalle_venta.getCodigo_venta() + "','"
                    + detalle_venta.getValor_total() + "','"
                    + detalle_venta.getValor_venta() + "','"
                    + detalle_venta.getValor_iva() + ")";
            System.out.println(">> >> >> dao_delalle_ventas / public boolean agregarDetalleVentas(modelo_detalle_ventas detalle_venta) {} / SQL: " + sql);
            
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase dao_detalle_ventas, método agregarDetalleVenta");
                e.printStackTrace();
            }
        }
        System.out.println("\n\n>> >> >> dao_delalle_ventas / public boolean agregarDetalleVentas(modelo_detalle_ventas detalle_venta) {} / FIN");
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
            System.out.println("Error: Clase dao_detalle_ventas, método buscarDetalleVenta" + e.getMessage());
        }
        return encontrado;
    }

    // BUSCAR VENTA POR ID
    public modelo_detalle_ventas getventaId(int id) { 
        String sql = "SELECT * FROM detalle_ventas WHERE codigo_detalle_venta =" + id;
        modelo_detalle_ventas detv = new modelo_detalle_ventas();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
              detv.setCodigo_detalle_venta(res.getInt(1));
                detv.setCantidas_producto(res.getInt(2));
                detv.setCodigo_producto(res.getInt(3));
                detv.setCodigo_venta(res.getInt(4));
                detv.setValor_total(res.getDouble(5));
                detv.setValor_venta(res.getDouble(6));
                detv.setValor_iva(res.getDouble(7));
            }
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println("\n\n>> >> >> dao_detalle_ventas / BUSCAR POR CODIGO DETALLE_VENTA / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return detv;
    }

    // ACTUALIZACION DE DATOS
    public boolean actualizarventa(modelo_detalle_ventas detalle_venta) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE detalle_ventas SET codigo_detalle_venta = '" + detalle_venta.getCodigo_detalle_venta() 
                + "', cantidad_producto = '" + detalle_venta.getCantidas_producto()+ "', codigo_venta='"
                + detalle_venta.getCodigo_venta()+ "'" + ", valor_total = '" + detalle_venta.getValor_total()+ "'" 
               + ", valor_venta = '"  + detalle_venta.getValor_venta()+ "'"
                + ", valor_iva = '"  + detalle_venta.getValor_iva()+ "'"
                + " WHERE codigo_detalle_venta  = " + detalle_venta.getCodigo_detalle_venta();
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

    // ELIMINAR VENTA
    public boolean eliminarventa(int id) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM detalle_ventas WHERE codigo_detalle_venta =" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM detalle_ventas WHERE codigo_detalle_venta = " + id;
        if (encontrado) {
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            } catch (SQLException e) {
                System.out.println("Error: Clase dao_detalle_ventas, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }
}
