package package_modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.util.ArrayList;
import java.util.List;
import package_dao.dao_producto;

public class LectorCSV {

    //Propiedades
    private char separador;
    private char comillas;

    //Constructor
    public LectorCSV(char separador, char comillas) {
        this.separador = separador;
        this.comillas = comillas;
    }

    public List<modelo_producto> leerCSVSimple(String path) throws IOException {
        System.out.println("\n\n>> >> >> LectorCSV / leerCSVSimple {}/ INICIO");
        System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ BufferedReader");
        BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
        System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ String linea");
        String linea = bufferLectura.readLine();
        System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ List<modelo_producto> productos");
        List<modelo_producto> productos = new ArrayList<>();
        System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ dao_producto DAO_Producto");
        dao_producto DAO_Producto = new dao_producto();
        System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ LECTURA");
        try {
            while (linea != null) {
                String[] campos = linea.split(String.valueOf(this.separador));
                modelo_producto pro = new modelo_producto();
                pro.setCodigo_producto(Integer.parseInt(campos[0]));
                pro.setIva_compra(Double.parseDouble(campos[1]));
                pro.setNit_proveedor(Integer.parseInt(campos[2]));
                pro.setNombre_producto(campos[3]);
                pro.setPrecio_compra(Double.parseDouble(campos[4]));
                pro.setPrecio_venta(Double.parseDouble(campos[5]));
                DAO_Producto.agregarProducto(pro);
                productos.add(pro);
                System.out.println(Arrays.toString(campos));
                //vuelvo a leer del fichero
                linea = bufferLectura.readLine();
            }
            System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ LISTA: \n"+productos);
            if (bufferLectura != null) {
                bufferLectura.close();
                System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ INFORMACION OBTENIDA");
            }
        System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ FIN");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println(">> >> >> LectorCSV / leerCSVSimple {}/ ERROR DE CARGUE DE ARICHIVO !!!");
        }

        return productos;
    }
}
