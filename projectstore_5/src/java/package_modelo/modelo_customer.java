package package_modelo;

// CUERPO DE MODELO
public class modelo_customer {
    
    // ELEMENTOS DE MODELO
    private int CustomerId;
    private String CustomerNameFull;
    private String CustomerAddress;
    private int CustomerPhone;
    private String CustomerEmail;
    
    // CONSTRUCTOR POR DEFECTO
    public modelo_customer () {}
    
    // CONSTRUCTOR COMPLETO
    public modelo_customer (int CustomerId, String CustomerNameFull, String CustomerAddress, int CustomerPhone, String CustomerEmail) {
        this.CustomerId = CustomerId;
        this.CustomerNameFull = CustomerNameFull;
        this.CustomerAddress = CustomerAddress;
        this.CustomerPhone = CustomerPhone;
        this.CustomerEmail = CustomerEmail;
        System.out.println("\n\n>> >> >> MODEL CLIENTE / CONSTRUCTOR DE MODELO\n");
    }
    
    // ENTRADAS Y SALIDAS DE MODELO
    public int getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }
    public String getCustomerNameFull() {
        return CustomerNameFull;
    }
    public void setCustomerNameFull(String CustomerNameFull) {
        this.CustomerNameFull = CustomerNameFull;
    }
    public String getCustomerAddress() {
        return CustomerAddress;
    }
    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }
    public int getCustomerPhone() {
        return CustomerPhone;
    }
    public void setCustomerPhone(int CustomerPhone) {
        this.CustomerPhone = CustomerPhone;
    }
    public String getCustomerEmail() {
        return CustomerEmail;
    }
    public void setCustomerEmail(String CustomerEmail) {
        this.CustomerEmail = CustomerEmail;
    }
    
}
