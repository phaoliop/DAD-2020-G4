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
public class CalcularPrecio {
    
    String moneda;
    String diametro;
    String nombre;
    float longitud;
    float precioMetro;
    float precio;
    float precioConIgv;

    public CalcularPrecio() {
    }

    public CalcularPrecio(String moneda, String diametro, String nombre, float longitud, float precioMetro, float precio, float precioConIgv) {
        this.moneda = moneda;
        this.diametro = diametro;
        this.nombre = nombre;
        this.longitud = longitud;
        this.precioMetro = precioMetro;
        this.precio = precio;
        this.precioConIgv = precioConIgv;
    }

    public CalcularPrecio( String nombre, float precioMetro, float precio, float precioConIgv) {
        this.nombre = nombre;
        this.precioMetro = precioMetro;
        this.precio = precio;
        this.precioConIgv = precioConIgv;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getPrecioMetro() {
        return precioMetro;
    }

    public void setPrecioMetro(float precioMetro) {
        this.precioMetro = precioMetro;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioConIgv() {
        return precioConIgv;
    }

    public void setPrecioConIgv(float precioConIgv) {
        this.precioConIgv = precioConIgv;
    }
  
    
}
