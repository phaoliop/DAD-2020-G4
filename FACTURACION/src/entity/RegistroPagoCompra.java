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
public class RegistroPagoCompra {
    
    int idRegistroPagoCompra;
    int idRegistroComprafk;
    String tipoPago;
    String fechaAbono;
    String moneda;
    String montoAbonado;
    String numeroOperacion;
    String observacion;
    String montoTotal;
    String resto;

    public RegistroPagoCompra() {
    }

    public RegistroPagoCompra(int idRegistroPagoCompra, int idRegistroComprafk, String tipoPago, String fechaAbono, String moneda, String montoAbonado, String numeroOperacion, String observacion) {
        this.idRegistroPagoCompra = idRegistroPagoCompra;
        this.idRegistroComprafk = idRegistroComprafk;
        this.tipoPago = tipoPago;
        this.fechaAbono = fechaAbono;
        this.moneda = moneda;
        this.montoAbonado = montoAbonado;
        this.numeroOperacion = numeroOperacion;
        this.observacion = observacion;
    }

    public RegistroPagoCompra(String montoAbonado, String montoTotal, String resto) {
        this.montoAbonado = montoAbonado;
        this.montoTotal = montoTotal;
        this.resto = resto;
    }

    public int getIdRegistroPagoCompra() {
        return idRegistroPagoCompra;
    }

    public void setIdRegistroPagoCompra(int idRegistroPagoCompra) {
        this.idRegistroPagoCompra = idRegistroPagoCompra;
    }

    public int getIdRegistroComprafk() {
        return idRegistroComprafk;
    }

    public void setIdRegistroComprafk(int idRegistroComprafk) {
        this.idRegistroComprafk = idRegistroComprafk;
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

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getResto() {
        return resto;
    }

    public void setResto(String resto) {
        this.resto = resto;
    }
    
    
    
}
