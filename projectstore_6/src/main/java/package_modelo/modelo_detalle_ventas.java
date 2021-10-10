/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_modelo;

/**
 *
 * @author ander
 */
public class modelo_detalle_ventas {
    private int codigo_detalle_venta;
    private int cantidas_producto;
    private int codigo_producto;
    private int codigo_venta;
    private double valor_total;
    private double valor_venta;
    private double valor_iva;
    
    public modelo_detalle_ventas(){}
    
    public modelo_detalle_ventas(int codigo_detalle_venta,int cantidas_producto,int codigo_producto,int codigo_venta,double valor_total,double valor_venta,double valor_iva){
    this.codigo_detalle_venta = codigo_detalle_venta;
    this.cantidas_producto = cantidas_producto;
    this.codigo_producto = codigo_producto;
    this.codigo_venta = codigo_venta;
    this.valor_total = valor_total;
    this.valor_venta = valor_venta;
    this.valor_iva = valor_iva;
    }
    

    /**
     * @return the codigo_detalle_venta
     */
    public int getCodigo_detalle_venta() {
        return codigo_detalle_venta;
    }

    /**
     * @param codigo_detalle_venta the codigo_detalle_venta to set
     */
    public void setCodigo_detalle_venta(int codigo_detalle_venta) {
        this.codigo_detalle_venta = codigo_detalle_venta;
    }

    /**
     * @return the cantidas_producto
     */
    public int getCantidas_producto() {
        return cantidas_producto;
    }

    /**
     * @param cantidas_producto the cantidas_producto to set
     */
    public void setCantidas_producto(int cantidas_producto) {
        this.cantidas_producto = cantidas_producto;
    }

    /**
     * @return the codigo_producto
     */
    public int getCodigo_producto() {
        return codigo_producto;
    }

    /**
     * @param codigo_producto the codigo_producto to set
     */
    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    /**
     * @return the codigo_venta
     */
    public int getCodigo_venta() {
        return codigo_venta;
    }

    /**
     * @param codigo_venta the codigo_venta to set
     */
    public void setCodigo_venta(int codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    /**
     * @return the valor_total
     */
    public double getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the valor_venta
     */
    public double getValor_venta() {
        return valor_venta;
    }

    /**
     * @param valor_venta the valor_venta to set
     */
    public void setValor_venta(double valor_venta) {
        this.valor_venta = valor_venta;
    }

    /**
     * @return the valor_iva
     */
    public double getValor_iva() {
        return valor_iva;
    }

    /**
     * @param valor_iva the valor_iva to set
     */
    public void setValor_iva(double valor_iva) {
        this.valor_iva = valor_iva;
    }
    
}
