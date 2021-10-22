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
// IMPORTACION DE MODELOS
import package_modelo.modelo_ventas;
import package_modelo.modelo_reporte_ventas;

// PROCESOS DE VENTAS
public class dao_ventas {

    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    conexion_database cn = new conexion_database();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    public int CalcularIdVenta() {
        System.out.println("\n\n>> >> >> dao_ventas / public int CalcularIdVenta() {} / INICIO");
        int IdVenta = 0;
        System.out.println(">> >> >> dao_ventas / public int CalcularIdVenta() {} / IdVenta: ["+IdVenta+"]");
        String AccionSql = "select max(codigo_venta) from ventas";
        System.out.println(">> >> >> dao_ventas / public int CalcularIdVenta() {} / IdVenta: ["+IdVenta+"]");
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(AccionSql);
            while (res.next()) {
                IdVenta = res.getInt(1);
            }
            stm.close();
            res.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        System.out.println(">> >> >> dao_ventas / public int CalcularIdVenta() {} / IdVenta: ["+IdVenta+"]");
        return IdVenta;
    }

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
        System.out.println("\n\n>> >> >> dao_ventas / public boolean agregarventa(modelo_ventas venta) {} / INICIO");
        boolean registrar = false;
        boolean encontrado = false;
        String buscar = "SELECT * FROM ventas where codigo_venta = " + venta.getCodigo_venta();
        System.out.println(">> >> >> dao_ventas / public boolean agregarventa(modelo_ventas venta) {} / buscar: " + buscar);
        encontrado = buscar(buscar);
        if (!encontrado) {
            // La instrucción para insertar el registro
            String sql = "INSERT INTO ventas values ("
                    + venta.getCodigo_venta() + ",'"
                    + venta.getCedula_cliente() + "','"
                    + venta.getCedula_usuario() + "','"
                    + venta.getIva_venta() + "','"
                    + venta.getTotal_venta() + "','"
                    + venta.getValor_venta() + "')";
            System.out.println(">> >> >> dao_ventas / public boolean agregarventa(modelo_ventas venta) {} / SQL: " + sql);
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase [dao_ventas], método [agregarVenta]");
                e.printStackTrace();
            }
        }
        System.out.println("\n\n>> >> >> dao_ventas / public boolean agregarventa(modelo_ventas venta) {} / FIN");
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
                + "', cedula_usuario = '" + venta.getCedula_usuario() + "', iva_venta='"
                + venta.getIva_venta() + "'" + ", total_venta = '" + venta.getTotal_venta() + "'"
                + ",valor_venta = '" + venta.getValor_venta() + "'"
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

    // NOTA: SE DEBE REVISAR CODIGO POR NUEVO PROCESO
    // NOTA: SE DEBE REVISAR CODIGO SQUL DE BUSQUEDA
    // REPORTE DE VENTAS
    public List<modelo_reporte_ventas> reporteVentasXCliente() {
        String sql = "SELECT v.cedula_cliente, c.nombre_cliente, sum(v.total_venta) FROM ventas v, clientes c WHERE v.cedula_cliente=c.cedula_cliente group by v.cedula_cliente, c.nombre_cliente";
        List<modelo_reporte_ventas> reporteVentas = new ArrayList<>();
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                modelo_reporte_ventas repv = new modelo_reporte_ventas(); // Instanciamos un objeto tipo modelo_user
                repv.setCedulaCliente(res.getInt(1));
                repv.setNombreCliente(res.getString(2));
                repv.setVentaTotal(res.getDouble(3));
                reporteVentas.add(repv); // Agregarlo al ArrayList
            } // while (res.next()) {}
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error: en Reporte Ventas" + e);
        }
        return reporteVentas; // Devuelve el ArrayList usuarios
    } // public List<modelo_reporte_ventas> reporteVentasXCliente() {}

}
