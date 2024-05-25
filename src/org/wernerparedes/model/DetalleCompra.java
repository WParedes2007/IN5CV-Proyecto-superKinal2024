/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.model;

import java.time.LocalDate;

/**
 *
 * @author informatica
 */
public class DetalleCompra{
    private int detalleCompraId;
    private int productoId;
    private String producto;
    private int compraId;
    private String compra;

    public DetalleCompra() {
    }

    public DetalleCompra(int detalleCompraId, String producto, String compra) {
        this.detalleCompraId = detalleCompraId;
        this.producto = producto;
        this.compra = compra;
    }

    public DetalleCompra(int detalleCompraId, int productoId, int compraId) {
        this.detalleCompraId = detalleCompraId;
        this.productoId = productoId;
        this.compraId = compraId;
    }

    
    

    public int getDetalleCompraId() {
        return detalleCompraId;
    }

    public void setDetalleCompraId(int detalleCompraId) {
        this.detalleCompraId = detalleCompraId;
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

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "detalleCompraId=" + detalleCompraId + ", productoId=" + productoId + ", producto=" + producto + ", compraId=" + compraId + ", compra=" + compra + '}';
    }
    
    

    

    
    
    
    
}
