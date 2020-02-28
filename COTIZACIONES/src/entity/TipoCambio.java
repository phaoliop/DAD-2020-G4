/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ARCRODINPC-06
 */
public class TipoCambio {
    int idTipoCambio;
    String fecha;
    float precioCompra;
    float precioVenta;

    public TipoCambio() {
    }

    public TipoCambio(String fecha, float precioCompra, float precioVenta) {
        this.fecha = fecha;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public TipoCambio(int idTipoCambio, String fecha, float precioCompra, float precioVenta) {
        this.idTipoCambio = idTipoCambio;
        this.fecha = fecha;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public TipoCambio(float precioCompra, float precioVenta) {
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public TipoCambio(String fecha) {
        this.fecha = fecha;
    }
    
    public int getIdTipoCambio() {
        return idTipoCambio;
    }

    public void setIdTipoCambio(int idTipoCambio) {
        this.idTipoCambio = idTipoCambio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    
    
}
