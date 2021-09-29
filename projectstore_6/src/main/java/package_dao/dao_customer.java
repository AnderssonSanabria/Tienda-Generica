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
import package_modelo.modelo_customer;
import package_modelo.modelo_supplier;

/**
 *
 * @author ander
 */
public class dao_customer {
    
    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    // AGREGAR CLIENTE
    public List<modelo_customer> getCliente() {
        String sql = "SELECT * FROM clientes";
        List<modelo_customer> cliente = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_customer cli = new modelo_customer(); // Instanciamos un objeto tipo modelo_user
                cli.setCustomerId(res.getInt(1));
                cli.setCustomerNameFull(res.getString(2));
                cli.setCustomerAddress(res.getString(3));
                cli.setCustomerPhone(res.getInt(4));
                cli.setCustomerEmail(res.getString(5));
                cliente.add(cli); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return cliente; // Devuelve el ArrayList usuarios
    }

    // AGREGAR CLIENTE
    public boolean agregarCliente(modelo_customer cliente) {
        boolean registrar = false; // Permite identificar si ya existe el cliente
        boolean encontrado = false; 
        String buscar = "SELECT * FROM clientes where cedula_cliente = " // Instrucción sql
                + cliente.getCustomerId(); 
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO clientes values (" + cliente.getCustomerId()+ ",'" + cliente.getCustomerNameFull()
                    + "','" + cliente.getCustomerAddress()+ "','" + cliente.getCustomerPhone()+ "','"
                    + cliente.getCustomerEmail()+ "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase clienteDao, método agregarCliente");
                e.printStackTrace();
            }
        }
        return registrar;
    }

    // BUSCAR Cliente
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
            System.out.println("Error: Clase dao_user, método agregarCliente" + e.getMessage());
        }
        return encontrado;
    }

    // BUSCAR CLIENTE POR ID
    public modelo_customer getClienteId(int id) { // **** SE RECOMIENDA CAMBIAR ESTE CODIGO SI ES NECESARIO
        String sql = "SELECT * FROM clientes WHERE cedula_cliente=" + id;
        modelo_customer cli = new modelo_customer();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                cli.setCustomerId(res.getInt(1));
                cli.setCustomerNameFull(res.getString(2));
                cli.setCustomerAddress(res.getString(3));
                cli.setCustomerPhone(res.getInt(4));
                cli.setCustomerEmail(res.getString(5));
            }
            // CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION
            stm.close();
            res.close();
            con.close();
            System.out.println("\n\n>> >> >> dao_customer / BUSCAR POR IDCLIENTE / CIERRE DE CONEXIONES Y CANALES DE FLUJO DE INFORMACION\n");
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return cli;
    }

    // ACTUALIZACION DE DATOS
    public boolean actualizarCliente (modelo_customer cliente) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE proveedores SET nombre_cliente = '" + cliente.getCustomerNameFull()
                + "', direccion_cliente = '" + cliente.getCustomerAddress()+ "', telefono_cliente='"
                + cliente.getCustomerPhone()+ "'" + ", email_cliente = '" + cliente.getCustomerEmail()+ "'"
                + " WHERE cedula_cliente = " + cliente.getCustomerId();
        System.out.println("\n\n>> >> >> " + sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase dao_customer, método actualizar");
            e.printStackTrace();
        } // catch (SQLException e) {}
        return actualizar;
    }

    // ELIMINAR CLIENTE
    public boolean eliminarCliente(int id) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM clientes WHERE cedula_cliente=" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM clientes WHERE cedula_cliente = " + id;
        if (encontrado) {
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            } catch (SQLException e) {
                System.out.println("Error: Clase dao_customer, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }
}
