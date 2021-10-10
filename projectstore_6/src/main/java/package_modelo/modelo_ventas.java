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
public class modelo_ventas {
    private int codigo_venta;
    private int cedula_cliente;
    private int cedula_usuario;
    private double iva_venta;
    private double total_venta;
    private double valor_venta;

    public modelo_ventas(){}
    
    public modelo_ventas(int codigo_venta,int cedula_cliente,int cedula_usuario,double iva_venta,double total_venta,double valor_venta ){
    this.codigo_venta = codigo_venta;
    this.cedula_cliente = cedula_cliente;
    this.cedula_usuario = cedula_usuario;
    this.iva_venta = iva_venta;
    this.total_venta = total_venta;
    this.valor_venta = valor_venta;    
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
     * @return the cedula_cliente
     */
    public int getCedula_cliente() {
        return cedula_cliente;
    }

    /**
     * @param cedula_cliente the cedula_cliente to set
     */
    public void setCedula_cliente(int cedula_cliente) {
        this.cedula_cliente = cedula_cliente;
    }

    /**
     * @return the cedula_usuario
     */
    public int getCedula_usuario() {
        return cedula_usuario;
    }

    /**
     * @param cedula_usuario the cedula_usuario to set
     */
    public void setCedula_usuario(int cedula_usuario) {
        this.cedula_usuario = cedula_usuario;
    }

    /**
     * @return the iva_venta
     */
    public double getIva_venta() {
        return iva_venta;
    }

    /**
     * @param iva_venta the iva_venta to set
     */
    public void setIva_venta(double iva_venta) {
        this.iva_venta = iva_venta;
    }

    /**
     * @return the total_venta
     */
    public double getTotal_venta() {
        return total_venta;
    }

    /**
     * @param total_venta the total_venta to set
     */
    public void setTotal_venta(double total_venta) {
        this.total_venta = total_venta;
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
    
    
}
