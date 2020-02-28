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
public class DetalleGuiaDeRemision {
    int idDetalleGuia;
    int idGuiaRem;
    String cant;
    String cod;
    String descrip;
    String uniMed;

    public DetalleGuiaDeRemision() {
    }

    public DetalleGuiaDeRemision(int idDetalleGuia) {
        this.idDetalleGuia = idDetalleGuia;
    }

      public DetalleGuiaDeRemision(int idDetalleGuia, String cant, String cod, String descrip, String uniMed) {
        this.idDetalleGuia = idDetalleGuia;
        this.cant = cant;
        this.cod = cod;
        this.descrip = descrip;
        this.uniMed = uniMed;
    }

    public DetalleGuiaDeRemision(int idDetalleGuia, int idGuiaRem, String cant, String cod, String descrip, String uniMed) {
        this.idDetalleGuia = idDetalleGuia;
        this.idGuiaRem = idGuiaRem;
        this.cant = cant;
        this.cod = cod;
        this.descrip = descrip;
        this.uniMed = uniMed;
    }

    public DetalleGuiaDeRemision(String cant, String cod, String descrip, String uniMed) {
        this.cant = cant;
        this.cod = cod;
        this.descrip = descrip;
        this.uniMed = uniMed;
    }

    public int getIdDetalleGuia() {
        return idDetalleGuia;
    }

    public void setIdDetalleGuia(int idDetalleGuia) {
        this.idDetalleGuia = idDetalleGuia;
    }

    public int getIdGuiaRem() {
        return idGuiaRem;
    }

    public void setIdGuiaRem(int idGuiaRem) {
        this.idGuiaRem = idGuiaRem;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getUniMed() {
        return uniMed;
    }

    public void setUniMed(String uniMed) {
        this.uniMed = uniMed;
    }   
}
