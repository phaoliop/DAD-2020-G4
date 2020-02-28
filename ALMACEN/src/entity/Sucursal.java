/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.logging.Logger;

/**
 *
 * @author ARCRODINPC-05
 */
public class Sucursal {
    int idSucursal;
    String sucursal;
    String clave;

    public Sucursal() {
    }

    public Sucursal(int idSucursal, String sucursal, String clave) {
        this.idSucursal = idSucursal;
        this.sucursal = sucursal;
        this.clave = clave;
    }

    public Sucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

   
    
    
}
