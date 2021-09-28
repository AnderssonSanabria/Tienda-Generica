package package_conexion;

// IMPORTACION DE ELEMENTOS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// CUERPO DE PROCESOS DE CONEXION
public class conexion_database {
    private Connection con;
    Statement consulta;
    private static final String URL = "jdbc:mysql://localhost:3306/bd_tienda?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"; {
        System.out.println("\n\n>> >> >> CONEXION / INICIO PROCESO DE CONEXION\n");
    }
    private static final String USER = "root"; {
        System.out.println("\n\n>> >> >> CONEXION / INSERCION DE USUARIO\n");
    }
    private static final String PASS = "root"; {
        System.out.println("\n\n>> >> >> CONEXION / INSERCION DE CONTRASEÑA\n");
    }
    
    public Connection Conexion(){
        System.out.println("\n\n>> >> >> CONEXION / ENTRANDO A CONEXION\n");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            consulta = con.createStatement();
            if(con != null){
                System.out.println("\n\n>> >> >> CONEXION / CONECTADO A BASE DE DATOS\n");
            }
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("\n\n>> >> >> CONEXION / ERROR DE CONEXION\n");
            System.out.println("Error"+e.getMessage());
        }
        return con;
    }
}
