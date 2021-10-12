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
import package_conexion.conexion_database;
import package_modelo.modelo_producto;
import package_modelo.modelo_user;

/**
 *
 * @author jsotto
 */
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


