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
public class Proforma {

    int idProforma;
    String codProforma;
    int idCliente;
    int idContactoCliente;
    int idUsuario;
    String moneda;
    String validez;
    String formPago;
    String tipo;
    String detraccion;
    String fechaEmision;
    int dia1;
    int dia2;
    String estado;
    String Observacion;
    String razonSocial;
    String contacto;
    String anio;
    String usuario;
    int numPedido;
    String observacionInterna;

    public Proforma() {
    }

    public Proforma(String codProforma) {
        this.codProforma = codProforma;
    }

    public Proforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public Proforma(int idProforma, String anio) {
        this.idProforma = idProforma;
        this.anio = anio;
    }

    public Proforma(int idProforma, int numPedido) {
        this.idProforma = idProforma;
        this.numPedido = numPedido;
    }

    public Proforma(String codProforma, int idCliente, int idContactoCliente, 
            int idUsuario, String moneda, String validez, String formPago, 
            String tipo, String detraccion, String fechaEmision, int dia1, int dia2, 
            String estado, String Observacion, String observacionInterna) {
        this.codProforma = codProforma;
        this.idCliente = idCliente;
        this.idContactoCliente = idContactoCliente;
        this.idUsuario = idUsuario;
        this.moneda = moneda;
        this.validez = validez;
        this.formPago = formPago;
        this.tipo = tipo;
        this.detraccion = detraccion;
        this.fechaEmision = fechaEmision;
        this.dia1=dia1;
        this.dia2=dia2;
        this.estado = estado;
        this.Observacion = Observacion;
        this.observacionInterna = observacionInterna;
    }

    public Proforma(int idProforma, String codProforma, int idCliente, int idContactoCliente, 
            int idUsuario, String moneda, String validez, String formPago, String tipo, 
            String detraccion, String fechaEmision, int dia1, int dia2, 
            String estado, String Observacion, String observacionInterna) {
        this.idProforma = idProforma;
        this.codProforma = codProforma;
        this.idCliente = idCliente;
        this.idContactoCliente = idContactoCliente;
        this.idUsuario = idUsuario;
        this.moneda = moneda;
        this.validez = validez;
        this.formPago = formPago;
        this.tipo = tipo;
        this.detraccion = detraccion;
        this.fechaEmision = fechaEmision;
        this.dia1=dia1;
        this.dia2=dia2;
        this.estado = estado;
        this.Observacion = Observacion;
        this.observacionInterna = observacionInterna;
    }

    public Proforma(String codProforma, String razonSocial, String contacto, String fechaEmision, String tipo, String estado, String usuario, String observacionInterna) {
        this.codProforma = codProforma;
        this.razonSocial = razonSocial;
        this.contacto = contacto;
        this.fechaEmision = fechaEmision;
        this.tipo= tipo;
        this.estado = estado;
        this.usuario = usuario;
        this.observacionInterna=observacionInterna;

    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public String getCodProforma() {
        return codProforma;
    }

    public void setCodProforma(String codProforma) {
        this.codProforma = codProforma;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdContactoCliente() {
        return idContactoCliente;
    }

    public void setIdContactoCliente(int idContactoCliente) {
        this.idContactoCliente = idContactoCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getValidez() {
        return validez;
    }

    public void setValidez(String validez) {
        this.validez = validez;
    }

    public String getFormPago() {
        return formPago;
    }

    public void setFormPago(String formPago) {
        this.formPago = formPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetraccion() {
        return detraccion;
    }

    public void setDetraccion(String detraccion) {
        this.detraccion = detraccion;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getDia1() {
        return dia1;
    }

    public void setDia1(int dia1) {
        this.dia1 = dia1;
    }

    public int getDia2() {
        return dia2;
    }

    public void setDia2(int dia2) {
        this.dia2 = dia2;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }  

    public String getObservacionInterna() {
        return observacionInterna;
    }

    public void setObservacionInterna(String observacionInterna) {
        this.observacionInterna = observacionInterna;
    } 
    
}
