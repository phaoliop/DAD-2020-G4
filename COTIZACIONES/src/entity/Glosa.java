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
public class Glosa {
    
   int  idGlosa;
   String concepto;
   String codigo;

    public Glosa() {
    }

    public Glosa(int idGlosa, String concepto, String codigo) {
        this.idGlosa = idGlosa;
        this.concepto = concepto;
        this.codigo = codigo;
    }

    public int getIdGlosa() {
        return idGlosa;
    }

    public void setIdGlosa(int idGlosa) {
        this.idGlosa = idGlosa;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
}
