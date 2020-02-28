/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ARCRODINPC-05
 */
public class ComprobanteSunat {
    
    int idComprobanteSunat;
    String descripcion;
    String codigo;

    public ComprobanteSunat() {
    }

    public ComprobanteSunat(int idComprobanteSunat, String descripcion, String codigo) {
        this.idComprobanteSunat = idComprobanteSunat;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public int getIdComprobanteSunat() {
        return idComprobanteSunat;
    }

    public void setIdComprobanteSunat(int idComprobanteSunat) {
        this.idComprobanteSunat = idComprobanteSunat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
  
}
