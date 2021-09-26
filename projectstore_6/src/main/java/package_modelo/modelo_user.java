package package_modelo;

// CUERPO DE MODELO
public class modelo_user {
    
    // ELEMENTOS DE MODELO
    private int cedulaUsuario;
    private String nombreUsuario;
    private String clave;
    private String correo;
    private String tipoUsuario;

    // CONSTRUCTOR POR DEFECTO
    public modelo_user() {}

    // CONSTRUCTOR COMPLETO
    public modelo_user(int cedulaUsuario, String nombreUsuario, String clave,String correo, String tipoUsuario) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        System.out.println("\n\n>> >> >> MODEL USUARIO / CONSTRUCTOR estructurado\n");
    }
    
    // ENTRADAS Y SALIDAS DE MODELO
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getCedulaUsuario() {
        return cedulaUsuario;
    }
    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
