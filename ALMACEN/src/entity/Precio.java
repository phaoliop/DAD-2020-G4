/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Yveth Calixto
 */
public class Precio {
    int idPrecio;
    int idArticulo;
    String fechaPrecio;
    float precioVenta;

    public Precio() {
    }

    public Precio(int idArticulo, String fechaPrecio, float precioVenta) {
        this.idArticulo = idArticulo;
        this.fechaPrecio = fechaPrecio;
        this.precioVenta = precioVenta;
    }

    public Precio(int idPrecio, int idArticulo, String fechaPrecio, float precioVenta) {
        this.idPrecio = idPrecio;
        this.idArticulo = idArticulo;
        this.fechaPrecio = fechaPrecio;
        this.precioVenta = precioVenta;
    }

    public Precio(String fechaPrecio, float precioVenta) {
        this.fechaPrecio = fechaPrecio;
        this.precioVenta = precioVenta;
    }

    
    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getFechaPrecio() {
        return fechaPrecio;
    }

    public void setFechaPrecio(String fechaPrecio) {
        this.fechaPrecio = fechaPrecio;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    
    
}
