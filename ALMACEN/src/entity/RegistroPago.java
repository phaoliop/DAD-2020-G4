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
public class RegistroPago {
    
    int idRegistroPago;
    int idRegistroVenta;
    String tipoPago;
    String fechaAbono;
    String moneda;
    String montoAbonado;
    String numeroOperacion;
    String observacion;
    String montoFactura;
    String resto;

    public RegistroPago() {
    }

    public RegistroPago(int idRegistroPago, int idRegistroVenta, String tipoPago, String fechaAbono, String moneda, String montoAbonado, String numeroOperacion, String observacion) {
        this.idRegistroPago = idRegistroPago;
        this.idRegistroVenta = idRegistroVenta;
        this.tipoPago = tipoPago;
        this.fechaAbono = fechaAbono;
        this.moneda = moneda;
        this.montoAbonado = montoAbonado;
        this.numeroOperacion = numeroOperacion;
        this.observacion = observacion;
    }

    public RegistroPago(String montoAbonado, String montoFactura, String resto) {
        this.montoAbonado = montoAbonado;
        this.montoFactura = montoFactura;
        this.resto = resto;
    }
    
    public int getIdRegistroPago() {
        return idRegistroPago;
    }

    public void setIdRegistroPago(int idRegistroPago) {
        this.idRegistroPago = idRegistroPago;
    }

    public int getIdRegistroVenta() {
        return idRegistroVenta;
    }

    public void setIdRegistroVenta(int idRegistroVenta) {
        this.idRegistroVenta = idRegistroVenta;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(String montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public String getNumeroOperacion() {
        return numeroOperacion;
    }

    public void setNumeroOperacion(String numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMontoFactura() {
        return montoFactura;
    }

    public void setMontoFactura(String montoFactura) {
        this.montoFactura = montoFactura;
    }

    public String getResto() {
        return resto;
    }

    public void setResto(String resto) {
        this.resto = resto;
    }
    
    
}
