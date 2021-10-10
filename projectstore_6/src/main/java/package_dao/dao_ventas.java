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
import package_modelo.modelo_ventas;

/**
 *
 * @author ander
 */
public class dao_ventas {
        // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;
    
    
    // AGREGAR VENTA
    public List<modelo_ventas> getventa() {
        String sql = "SELECT * FROM ventas";
        List<modelo_ventas> venta = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_ventas ven = new modelo_ventas(); // Instanciamos un objeto tipo modelo_venta
                ven.setCodigo_venta(res.getInt(1));
                ven.setCedula_cliente(res.getInt(2));
                ven.setCedula_usuario(res.getInt(3));
                ven.setIva_venta(res.getDouble(4));
                ven.setTotal_venta(res.getDouble(5));
                ven.setValor_venta(res.getDouble(6));
                venta.add(ven); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return venta; // Devuelve el ArrayList ventas
    }

    // AGREGAR venta
    public boolean agregarventa(modelo_ventas venta) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM ventas where codigo_venta = " // Instrucción sql
                + venta.getCodigo_venta(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO ventas values (" + venta.getCodigo_venta() + ",'" + venta.getCedula_cliente()
                    + "','" + venta.getCedula_usuario() + "','" + venta.getIva_venta() + "','"
                    + venta.getTotal_venta() + "','" + venta.getValor_venta() + "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase dao_ventas, método agregarVenta");
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
            System.out.println("Error: Clase dao_ventas, método buscarventa" + e.getMessage());
        }
        return encontrado;
    }

    // BUSCAR VENTA POR ID
    public modelo_ventas getventaId(int id) { 
        String sql = "SELECT * FROM ventas WHERE codigo_venta=" + id;
        modelo_ventas ven = new modelo_ventas();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                ven.setCodigo_venta(res.getInt(1));
                ven.setCedula_cliente(res.getInt(2));
                ven.setCedula_usuario(res.getInt(3));
                ven.setIva_venta(res.getDouble(4));
                ven.setTotal_venta(res.getDouble(5));
                ven.setValor_venta(res.getDouble(6));
            }
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println("\n\n>> >> >> dao_ventas / BUSCAR POR CODIGO VENTA / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return ven;
    }

    // ACTUALIZACION DE DATOS
    public boolean actualizarventa(modelo_ventas venta) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE ventas SET cedula_cliente = '" + venta.getCedula_cliente()
                + "', cedula_usuario = '" + venta.getCedula_usuario()+ "', iva_venta='"
                + venta.getIva_venta()+ "'" + ", total_venta = '" + venta.getTotal_venta()+ "'" 
                + venta.getValor_venta()+ "'"
                + " WHERE codigo_venta = " + venta.getCodigo_venta();
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
        String buscar = "SELECT * FROM ventas WHERE codigo_venta=" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM ventas WHERE codigo_venta = " + id;
        if (encontrado) {
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            } catch (SQLException e) {
                System.out.println("Error: Clase dao_ventas, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }

}
