package package_modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.util.ArrayList;
import java.util.List;
import package_dao.dao_producto;


/**
 *
 * @author jsotto
 */
public class LectorCSV {
    //Propiedades
    private char separador;
    private char comillas;
    
    //Constructor
    
    public LectorCSV(char separador, char comillas){
        this.separador=separador;
        this.comillas=comillas;
    }
    
    public List<modelo_producto> leerCSVSimple(String path) throws IOException {
        System.out.println("Leer CSV");
        BufferedReader bufferLectura = new BufferedReader (new FileReader(path));
        String linea = bufferLectura.readLine();
        List<modelo_producto> productos = new ArrayList<>();
        dao_producto DAO_Producto = new dao_producto();
        try {
        while (linea!=null){
            String[] campos =linea.split(String.valueOf(this.separador));
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
            linea=bufferLectura.readLine();
        }
        if (bufferLectura != null){
            bufferLectura.close();
        }
        } catch (Exception e) {
                System.out.println("Error: "+e);
            }

        return productos;
    }
}
