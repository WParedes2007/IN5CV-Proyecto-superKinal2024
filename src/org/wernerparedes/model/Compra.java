/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wernerparedes.model;

import java.time.LocalDate;

/**
 *
 * @author hermanos_P2
 */
public class Compra {
    private int compraId;
    private LocalDate fechaCompra;
    private double totalCompra;

    public Compra() {
    }

    public Compra(int compraId, LocalDate fechaCompra, double totalCompra) {
        this.compraId = compraId;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
    }

    public int getCompraId() {
        return compraId;
    }

    public void setCompraId(int compraId) {
        this.compraId = compraId;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    @Override
    public String toString() {
        return "Compra{" + "compraId=" + compraId + ", fechaCompra=" + fechaCompra + ", totalCompra=" + totalCompra + '}';
    }
    
    
    
}
