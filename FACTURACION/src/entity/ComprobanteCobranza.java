/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ARCRODINPC-02
 */
public class ComprobanteCobranza {
    
    int idComprobanteCobranza;
    int idPedido;
    String numSerie;
    String numCorrelativo;
    String tipoDoc;
    String moneda;
    String fechEmision;
    String fechCancelacion;
    String observacion;
    String razonSocial;
    String comprobante;

    public ComprobanteCobranza() {
    }

    public ComprobanteCobranza(int idPedido, String numSerie, String numCorrelativo, String tipoDoc, String moneda, String fechEmision, String fechCancelacion, String observacion) {
        this.idPedido = idPedido;
        this.numSerie = numSerie;
        this.numCorrelativo = numCorrelativo;
        this.tipoDoc = tipoDoc;
        this.moneda = moneda;
        this.fechEmision = fechEmision;
        this.fechCancelacion = fechCancelacion;
        this.observacion = observacion;
    }

    public ComprobanteCobranza(int idComprobanteCobranza, int idPedido, String numSerie, String numCorrelativo, String tipoDoc, String moneda, String fechEmision, String fechCancelacion, String observacion) {
        this.idComprobanteCobranza = idComprobanteCobranza;
        this.idPedido = idPedido;
        this.numSerie = numSerie;
        this.numCorrelativo = numCorrelativo;
        this.tipoDoc = tipoDoc;
        this.moneda = moneda;
        this.fechEmision = fechEmision;
        this.fechCancelacion = fechCancelacion;
        this.observacion = observacion;
    }

    public ComprobanteCobranza(String comprobante, String razonSocial, String fechEmision, String fechCancelacion) {
        this.comprobante = comprobante;
        this.razonSocial = razonSocial;
        this.fechEmision = fechEmision;
        this.fechCancelacion = fechCancelacion;
    }
    
    

    public int getIdComprobanteCobranza() {
        return idComprobanteCobranza;
    }

    public void setIdComprobanteCobranza(int idComprobanteCobranza) {
        this.idComprobanteCobranza = idComprobanteCobranza;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNumCorrelativo() {
        return numCorrelativo;
    }

    public void setNumCorrelativo(String numCorrelativo) {
        this.numCorrelativo = numCorrelativo;
    }

 
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFechEmision() {
        return fechEmision;
    }

    public void setFechEmision(String fechEmision) {
        this.fechEmision = fechEmision;
    }

    public String getFechCancelacion() {
        return fechCancelacion;
    }

    public void setFechCancelacion(String fechCancelacion) {
        this.fechCancelacion = fechCancelacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

}
