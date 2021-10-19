package package_modelo;

public class modelo_detalle_ventas {

    // ELEMENTOS DE MODELO
    private int codigo_detalle_venta; // id
    private int codigo_producto; // idProducto
    private String VentaDetalleDescripcion; // descripcion

    private int codigo_venta; // idVenta
    private int cantidas_producto; // cantidadPRODUCTO
    private double PrecioVentaDetalle; // precioVenta

    private double valor_total; // valorTotal
    private double valor_venta; // valorVenta
    private double valor_iva; // valorIva

    // METODOS DE ENTRADA Y SALIDA
    public String getVentaDetalleDescripcion() {
        return VentaDetalleDescripcion;
    }
    public void setVentaDetalleDescripcion(String VentaDetalleDescripcion) {
        this.VentaDetalleDescripcion = VentaDetalleDescripcion;
    }
    public double getPrecioVentaDetalle() {
        return PrecioVentaDetalle;
    }
    public void setPrecioVentaDetalle(double PrecioVentaDetalle) {
        this.PrecioVentaDetalle = PrecioVentaDetalle;
    }
    public int getCodigo_detalle_venta() {
        return codigo_detalle_venta;
    }
    public void setCodigo_detalle_venta(int codigo_detalle_venta) {
        this.codigo_detalle_venta = codigo_detalle_venta;
    }
    public int getCantidas_producto() {
        return cantidas_producto;
    }
    public void setCantidas_producto(int cantidas_producto) {
        this.cantidas_producto = cantidas_producto;
    }
    public int getCodigo_producto() {
        return codigo_producto;
    }
    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }
    public int getCodigo_venta() {
        return codigo_venta;
    }
    public void setCodigo_venta(int codigo_venta) {
        this.codigo_venta = codigo_venta;
    }
    public double getValor_total() {
        return valor_total;
    }
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }
    public double getValor_venta() {
        return valor_venta;
    }
    public void setValor_venta(double valor_venta) {
        this.valor_venta = valor_venta;
    }
    public double getValor_iva() {
        return valor_iva;
    }
    public void setValor_iva(double valor_iva) {
        this.valor_iva = valor_iva;
    }

    // CONSTRUCTOR POR DEFECTO
    public modelo_detalle_ventas() {
    }

    // CONSTRUCTOR ESTRUCTURADO
    public modelo_detalle_ventas(
            int codigo_detalle_venta, 
            int codigo_producto, 
            String VentaDetalleDescripcion, 
            int codigo_venta, 
            int cantidas_producto, 
            double PrecioVentaDetalle,
            double valor_total,
            double valor_venta,
            double valor_iva) {
        this.codigo_detalle_venta = codigo_detalle_venta;
        this.codigo_producto = codigo_producto;
        this.VentaDetalleDescripcion = VentaDetalleDescripcion;
        this.codigo_venta = codigo_venta;
        this.cantidas_producto = cantidas_producto;
        this.PrecioVentaDetalle = PrecioVentaDetalle;
        this.valor_total = valor_total;
        this.valor_venta = valor_venta;
        this.valor_iva = valor_iva;
    }

}
