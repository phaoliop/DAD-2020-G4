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
public class ListaPrecio {
    
    int idListaPrecio;
    String nombre;
    String diametro;
    String unidadDiametro;
    float precio;
    String tipoCod;

    public ListaPrecio() {
    }

    public ListaPrecio(String nombre, String diametro, String unidadDiametro, float precio, String tipoCod) {
        this.nombre = nombre;
        this.diametro = diametro;
        this.unidadDiametro = unidadDiametro;
        this.precio = precio;
        this.tipoCod = tipoCod;
    }

    public ListaPrecio(int idListaPrecio, String nombre,String diametro, String unidadDiametro, float precio, String tipoCod) {
        this.idListaPrecio = idListaPrecio;
        this.diametro = diametro;
        this.nombre = nombre;
        this.unidadDiametro = unidadDiametro;
        this.precio = precio;
        this.tipoCod = tipoCod;
    }

    public int getIdListaPrecio() {
        return idListaPrecio;
    }

    public void setIdListaPrecio(int idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public String getUnidadDiametro() {
        return unidadDiametro;
    }

    public void setUnidadDiametro(String unidadDiametro) {
        this.unidadDiametro = unidadDiametro;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipoCod() {
        return tipoCod;
    }

    public void setTipoCod(String tipoCod) {
        this.tipoCod = tipoCod;
    }
     
}
