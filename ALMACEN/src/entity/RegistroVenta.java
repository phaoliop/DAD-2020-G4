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
public class RegistroVenta {
    
int idRegistroVenta;
String fechaRegistro;
int idPedido; 
String guia; 
String tipoComprobante;
String serie; 
String numero; 
String fechaEmision;
String fechaAprobacion;
String fechaVencimiento; 
String tipo;
String moneda; 
String montoFacturado; 
String detraccion;
String estado;
String observacion;
String razonSocial;
String ruc;
String contacto;
String numPedido;
int idProforma;
String codProforma;
String formaPago;
int idCliente;
String numComprobante;
String fechaCambio;
String cambioVenta;
String abonado;
String deuda;


    public RegistroVenta() {
    }

    public RegistroVenta(int idRegistroVenta,  String fechaRegistro, int idPedido, String guia, String tipoComprobante, String serie, String numero, String fechaEmision, String fechaAprobacion, String fechaVencimiento, String tipo, String moneda, String montoFacturado, String detraccion, String estado, String observacion) {
        this.idRegistroVenta = idRegistroVenta;
        this.fechaRegistro = fechaRegistro;
        this.idPedido = idPedido;
        this.guia = guia;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.moneda = moneda;
        this.montoFacturado = montoFacturado;
        this.detraccion = detraccion;
        this.estado = estado;
        this.observacion = observacion;
    }

    public RegistroVenta(int idRegistroVenta, String fechaRegistro,String razonSocial, String ruc, String contacto, int idPedido, String numPedido, int idProforma, String codProforma, String guia, String tipoComprobante, String serie, String numero, String tipo, String moneda, String formaPago, String fechaEmision, String fechaAprobacion, String fechaVencimiento, String montoFacturado, String detraccion, String estado, String observacion) {
        this.idRegistroVenta = idRegistroVenta;
        this.fechaRegistro= fechaRegistro;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.contacto = contacto;
        this.idPedido = idPedido;
        this.numPedido = numPedido;
        this.idProforma = idProforma;
        this.codProforma = codProforma;
        this.guia = guia;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
        this.tipo = tipo;
        this.moneda = moneda;
        this.formaPago = formaPago;
        this.fechaEmision = fechaEmision;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaVencimiento = fechaVencimiento;
        this.montoFacturado = montoFacturado;
        this.detraccion = detraccion;
        this.estado = estado;
        this.observacion = observacion;
        
    }

    public RegistroVenta(int idRegistroVenta,
                         String razonSocial,
                         String ruc,
                         int idCliente, 
                         int idPedido, 
                         String numPedido,
                         String codProforma,
                         String tipoComprobante, 
                         String numComprobante,
                         String fechaEmision,
                         String fechaCambio, 
                         String cambioVenta,
                         String fechaVencimiento, 
                         String tipo, 
                         String moneda, 
                         String montoFacturado, 
                         String detraccion, 
                         String observacion, 
                         String abonado, 
                         String deuda) {
        this.idRegistroVenta = idRegistroVenta;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.idCliente = idCliente;
        this.idPedido = idPedido;
        this.numPedido = numPedido;
        this.codProforma = codProforma;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.fechaEmision = fechaEmision;
        this.fechaCambio = fechaCambio;
        this.cambioVenta = cambioVenta;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.moneda = moneda;
        this.montoFacturado = montoFacturado;
        this.detraccion = detraccion;
        this.observacion = observacion;
        this.abonado = abonado;
        this.deuda = deuda;
    }
      
    public int getIdRegistroVenta() {
        return idRegistroVenta;
    }

    public void setIdRegistroVenta(int idRegistroVenta) {
        this.idRegistroVenta = idRegistroVenta;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(String fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(String montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    public String getDetraccion() {
        return detraccion;
    }

    public void setDetraccion(String detraccion) {
        this.detraccion = detraccion;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
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

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getCambioVenta() {
        return cambioVenta;
    }

    public void setCambioVenta(String cambioVenta) {
        this.cambioVenta = cambioVenta;
    }

    public String getAbonado() {
        return abonado;
    }

    public void setAbonado(String abonado) {
        this.abonado = abonado;
    }

    public String getDeuda() {
        return deuda;
    }

    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }

}
