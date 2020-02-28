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
public class ProveedorArticulo {
    int idProvArtPk;
    int idProv;
    int idArtProv;
    String precio;
    String obs;
    String articulo;
    String razSocial;
            
    public ProveedorArticulo() {
    }

    public ProveedorArticulo(int idProvArtPk, int idProv, int idArtProv, String precio, String obs) {
        this.idProvArtPk= idProvArtPk;
        this.idProv = idProv;
        this.idArtProv = idArtProv;
        this.precio = precio;
        this.obs = obs;
    }

    public ProveedorArticulo(int idProvArtPk, String articulo, String precio, String obs) {
        this.idProvArtPk = idProvArtPk;
        this.articulo = articulo;
        this.precio = precio;
        this.obs = obs;
        
    }

    public ProveedorArticulo(int idProvArtPk, String razSocial, String articulo, String precio, String obs) {
        this.idProvArtPk = idProvArtPk;
        this.razSocial = razSocial;
        this.articulo = articulo;
        this.precio = precio;
        this.obs = obs;
    }
    
    
    
    

    public int getIdProvArtPk() {
        return idProvArtPk;
    }

    public void setIdProvArtPk(int idProvArt) {
        this.idProvArtPk = idProvArt;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public int getIdArtProv() {
        return idArtProv;
    }

    public void setIdArtProv(int idArtProv) {
        this.idArtProv = idArtProv;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }
   
}
