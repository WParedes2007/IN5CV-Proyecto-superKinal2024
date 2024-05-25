/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author informatica
 */
public class DetalleFactura{
    private int detalleFacturaId;
    private int productoId;
    private String producto;
    private int facturaId;

    public DetalleFactura() {
    }

    public DetalleFactura(int detalleFacturaId, int facturaId, int productoId) {
        this.detalleFacturaId = detalleFacturaId;
        this.facturaId = facturaId;
        this.productoId = productoId;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }
    
    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
     public int getDetalleFacturaId() {
        return detalleFacturaId;
    }

    public void setDetalleFacturaId(int detalleFacturaId) {
        this.detalleFacturaId = detalleFacturaId;
    }

    /*public DetalleFactura(int detalleFacturaId, int productoId, int facturaId, LocalDate fecha, LocalTime hora, double total, int clienteId, int empleadoId) {
        super(facturaId, fecha, hora, total, clienteId, empleadoId);
        this.detalleFacturaId = detalleFacturaId;
        this.productoId = productoId;
    }*/

    @Override
    public String toString() {
        return "DetalleFactura{" + "detalleFacturaId=" + detalleFacturaId + ", productoId=" + productoId + ", producto=" + producto + ", facturaId=" + facturaId + '}';
    }
    
    
    
    
    
    

   

   

    
    
    
}
