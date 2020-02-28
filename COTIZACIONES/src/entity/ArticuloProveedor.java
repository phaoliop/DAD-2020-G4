/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author ARCRODINPC-02
 */
public class ArticuloProveedor {
    
int idArticulosProveedores;
String descripcion;
String campo1;

    public ArticuloProveedor() {
    }

    public ArticuloProveedor(int idArticulosProveedores, String descripcion, String campo1) {
        this.idArticulosProveedores = idArticulosProveedores;
        this.descripcion = descripcion;
        this.campo1 = campo1;
    }

    public ArticuloProveedor(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getIdArticulosProveedores() {
        return idArticulosProveedores;
    }

    public void setIdArticulosProveedores(int idArticulosProveedores) {
        this.idArticulosProveedores = idArticulosProveedores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }



    
    
}
