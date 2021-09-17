package package_modelo;

// CUERPO DE MODELO
public class modelo_supplier {
    
    // ELEMENTOS DE MODELO
    private int SupplierNit;
    private String SupplierName;
    private String SupplierAddress;
    private int SupplierPhone;
    private String SupplierCity;
    
    // CONSTRUCTOR POR DEFECTO
    public modelo_supplier() {}
    
    // CONSTRUCTOR COMPLETO
    public modelo_supplier(int SupplierNit, String SupplierName, String SupplierAddress, int SupplierPhone,String SupplierCity) {
        this.SupplierNit = SupplierNit;
        this.SupplierName = SupplierName;
        this.SupplierAddress = SupplierAddress;
        this.SupplierPhone = SupplierPhone;
        this.SupplierCity = SupplierCity;
        System.out.println("\n\n>> >> >> MODEL PROVEEDOR / CONSTRUCTOR DE MODELO\n");
    }
    
    // ENTRADAS Y SALIDAS DE MODELO
    public int getSupplierNit() {
        return SupplierNit;
    }
    public void setSupplierNit(int SupplierNit) {
        this.SupplierNit = SupplierNit;
    }
    public String getSupplierName() {
        return SupplierName;
    }
    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }
    public String getSupplierAddress() {
        return SupplierAddress;
    }
    public void setSupplierAddress(String SupplierAddress) {
        this.SupplierAddress = SupplierAddress;
    }
    public int getSupplierPhone() {
        return SupplierPhone;
    }
    public void setSupplierPhone(int SupplierPhone) {
        this.SupplierPhone = SupplierPhone;
    }
    public String getSupplierCity() {
        return SupplierCity;
    }
    public void setSupplierCity(String SupplierCity) {
        this.SupplierCity = SupplierCity;
    }
    
}
