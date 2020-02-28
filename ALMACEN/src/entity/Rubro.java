/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author ARCRODINPC-06
 */
public class Rubro {
    int idRubro;
    String descripcion;

    public Rubro() {
    }

    public Rubro(int idRubro, String descripcion) {
        this.idRubro = idRubro;
        this.descripcion = descripcion;
    }

    public Rubro(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

     
        
}
