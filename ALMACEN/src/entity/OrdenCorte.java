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
public class OrdenCorte {
    int idOrdenCorte;
    int idPedido;
    String numOrdCort;
    String fecha;
    String motivo;
    int numPedido;
    String razonSocial;
    String anio;
    String estado;
    String observacion;

    public OrdenCorte() {
    }

    public OrdenCorte(int idPedido, String numOrdCort, String fecha, String motivo) {
        this.idPedido = idPedido;
        this.numOrdCort = numOrdCort;
        this.fecha = fecha;
        this.motivo = motivo;
        
    }

    public OrdenCorte(int idOrdenCorte, int idPedido, String numOrdCort, String fecha, String motivo, String estado, String observacion) {
        this.idOrdenCorte = idOrdenCorte;
        this.idPedido = idPedido;
        this.numOrdCort = numOrdCort;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
        this.observacion = observacion;
    }

    public OrdenCorte(String numOrdCort, String razonSocial, int numPedido, String fecha, String estado,String observacion) {
        this.numOrdCort = numOrdCort;
        this.fecha = fecha;
        this.numPedido = numPedido;
        this.razonSocial = razonSocial;
        this.estado = estado;
        this.observacion = observacion;
    }

    public OrdenCorte(String numOrdCort, String anio) {
        this.numOrdCort = numOrdCort;
        this.anio = anio;
    }
    
    public OrdenCorte(int idOrdenCorte) {
        this.idOrdenCorte = idOrdenCorte;
    }

    public OrdenCorte(String numOrdCort) {
        this.numOrdCort = numOrdCort;
    }
    
    public int getIdOrdenCorte() {
        return idOrdenCorte;
    }

    public void setIdOrdenCorte(int idOrdenCorte) {
        this.idOrdenCorte = idOrdenCorte;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNumOrdCort() {
        return numOrdCort;
    }

    public void setNumOrdCort(String numOrdCort) {
        this.numOrdCort = numOrdCort;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
 
}
