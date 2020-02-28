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
public class DetalleOrdenTrabajo {
    
    int idDetalleOrdenTrabajo;
    int idOrdenTrabajo;
    String item;
    String cant;
    String descripcion;
    String tolerancia;
    String campo1;

    public DetalleOrdenTrabajo() {
    }

    public DetalleOrdenTrabajo(int idDetalleOrdenTrabajo, int idOrdenTrabajo, String item, String cant, String descripcion, String tolerancia, String campo1) {
        this.idDetalleOrdenTrabajo = idDetalleOrdenTrabajo;
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.item = item;
        this.cant = cant;
        this.descripcion = descripcion;
        this.tolerancia = tolerancia;
        this.campo1 = campo1;
    }

    public DetalleOrdenTrabajo(int idDetalleOrdenTrabajo, String item, String cant, String descripcion, String tolerancia) {
        this.idDetalleOrdenTrabajo = idDetalleOrdenTrabajo;
        this.item = item;
        this.cant = cant;
        this.descripcion = descripcion;
        this.tolerancia = tolerancia;
    }
    
    

    public int getIdDetalleOrdenTrabajo() {
        return idDetalleOrdenTrabajo;
    }

    public void setIdDetalleOrdenTrabajo(int idDetalleOrdenTrabajo) {
        this.idDetalleOrdenTrabajo = idDetalleOrdenTrabajo;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(String tolerancia) {
        this.tolerancia = tolerancia;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }
  
}
