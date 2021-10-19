/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_modelo;

/**
 *
 * @author luisj
 */
public class modelo_reporte_ventas {
    public int cedulaCliente;
    public String nombreCliente;
    public double ventaTotal;

    public modelo_reporte_ventas() {
    }

    public modelo_reporte_ventas(int cedulaCliente, String nombreCliente, double ventaTotal) {
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.ventaTotal = ventaTotal;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(double ventaTotal) {
        this.ventaTotal = ventaTotal;
    }
    
    @Override
    public String toString() {
        return "modelo_reporte_ventas{" + "cedulaCliente=" + cedulaCliente + ", nombreCliente=" + nombreCliente + ", ventaTotal=" + ventaTotal + '}';
    }
}
