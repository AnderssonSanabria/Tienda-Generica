/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_modelo;

/**
 *
 * @author jsotto
 */
public class modelo_producto {

    public int getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(int codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(int nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public double getIva_compra() {
        return iva_compra;
    }

    public void setIva_compra(double iva_compra) {
        this.iva_compra = iva_compra;
    }
    private int codigo_producto;
    private String nombre_producto;
    private int nit_proveedor;
    private double precio_compra;
    private double precio_venta;
    private double iva_compra;
    
    public modelo_producto() {
    }

    public modelo_producto(int codigo_producto, String nombre_producto, int nit_proveedor, double precio_compra, double precio_venta, double iva_compra) {
        this.codigo_producto = codigo_producto;
        this.nombre_producto = nombre_producto;
        this.nit_proveedor = nit_proveedor;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.iva_compra = iva_compra;
    }

    @Override
    public String toString() {
        return "modelo_producto{" + "codigo_producto=" + codigo_producto + ", nombre_producto=" + nombre_producto + ", nit_proveedor=" + nit_proveedor + ", precio_compra=" + precio_compra + ", precio_venta=" + precio_venta + ", iva_compra=" + iva_compra + '}';
    }
    
}
