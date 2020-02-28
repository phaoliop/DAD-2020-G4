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
public class Cliente {

    int idCliente;
    String codigoCliente;
    String razonSocial;
    String ruc;
    String tipo;
    String direccion;
    String observacion;

    public Cliente() {
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public Cliente(String razonSocial, String ruc, String direccion) {
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
    }

    
    public Cliente(String razonSocial, String ruc,  String direccion, String tipo, String observacion) {
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.tipo = tipo;
        this.direccion = direccion;
        this.observacion = observacion;
    }

    
    public Cliente(int idCliente, String codigoCliente,String razonSocial, String ruc, 
                                  String tipo, String direccion, String observacion) {
        this.idCliente = idCliente;
        this.codigoCliente=codigoCliente;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.tipo = tipo;
        this.direccion = direccion;
        this.observacion = observacion;
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
