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
public class RegistroCompra {
    
    int idRegistroCompra;
    String fechaRegistro;
    int idProveedor_fk;
    String numOrdenCompra;
    int idComprobanteSunat_fk;
    String serie;
    String numero;
    String fechaEmision;
    String fechaAprobacion;
    String fechaVencimiento;
    int idGlosa_fk;
    String moneda;
    String montoFacturado;
    String detraccion;
    String estado;
    String observacion;
    String razonSocial;
    String ruc;
    String descripcion;
    String concepto;
    String codigos;
    String codigog;

    public RegistroCompra() {
    }

    public RegistroCompra(int idRegistroCompra, String fechaRegistro, int idProveedor_fk, String numOrdenCompra, int idComprobanteSunat_fk, String serie, String numero, String fechaEmision, String fechaAprobacion, String fechaVencimiento, int idGlosa_fk, String moneda, String montoFacturado, String detraccion, String estado, String observacion) {
        this.idRegistroCompra = idRegistroCompra;
        this.fechaRegistro = fechaRegistro;
        this.idProveedor_fk = idProveedor_fk;
        this.numOrdenCompra = numOrdenCompra;
        this.idComprobanteSunat_fk = idComprobanteSunat_fk;
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaVencimiento = fechaVencimiento;
        this.idGlosa_fk = idGlosa_fk;
        this.moneda = moneda;
        this.montoFacturado = montoFacturado;
        this.detraccion = detraccion;
        this.estado = estado;
        this.observacion = observacion;
    }

    public RegistroCompra(int idRegistroCompra, String fechaEmision, int idProveedor_fk, String razonSocial, String ruc, int idComprobanteSunat_fk, String descripcion, String codigos,String serie, String numero, int idGlosa_fk, String concepto, String codigog, String moneda,String montoFacturado, String detraccion, String fechaVencimiento, String estado, String observacion) {
        this.idRegistroCompra = idRegistroCompra;
        this.fechaEmision = fechaEmision;
        this.idProveedor_fk = idProveedor_fk;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.idComprobanteSunat_fk = idComprobanteSunat_fk;
        this.descripcion = descripcion;
        this.codigos=codigos;
        this.serie = serie;
        this.numero = numero;
        this.idGlosa_fk = idGlosa_fk;
        this.concepto = concepto;
        this.codigog=codigog;
        this.moneda = moneda;
        this.montoFacturado = montoFacturado;
        this.detraccion = detraccion;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
        this.observacion = observacion;   
        
    }

    public int getIdRegistroCompra() {
        return idRegistroCompra;
    }

    public void setIdRegistroCompra(int idRegistroCompra) {
        this.idRegistroCompra = idRegistroCompra;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdProveedor_fk() {
        return idProveedor_fk;
    }

    public void setIdProveedor_fk(int idProveedor_fk) {
        this.idProveedor_fk = idProveedor_fk;
    }

    public String getNumOrdenCompra() {
        return numOrdenCompra;
    }

    public void setNumOrdenCompra(String numOrdenCompra) {
        this.numOrdenCompra = numOrdenCompra;
    }

    public int getIdComprobanteSunat_fk() {
        return idComprobanteSunat_fk;
    }

    public void setIdComprobanteSunat_fk(int idComprobanteSunat_fk) {
        this.idComprobanteSunat_fk = idComprobanteSunat_fk;
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

    public int getIdGlosa_fk() {
        return idGlosa_fk;
    }

    public void setIdGlosa_fk(int idGlosa_fk) {
        this.idGlosa_fk = idGlosa_fk;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCodigos() {
        return codigos;
    }

    public void setCodigos(String codigos) {
        this.codigos = codigos;
    }

    public String getCodigog() {
        return codigog;
    }

    public void setCodigog(String codigog) {
        this.codigog = codigog;
    }

}
