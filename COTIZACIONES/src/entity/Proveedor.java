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
public class Proveedor {
    int idProveedor;
    String razonSocial;
    String ruc;
    String direccion;
    String rubro;
    String observacion;

    public Proveedor() {
    }

    public Proveedor(String razonSocial, String ruc, String direccion, String rubro, String observacion) {
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.rubro = rubro;
        this.observacion = observacion;
    }

    public Proveedor(int idProveedor, String razonSocial, String ruc, String direccion, String rubro, String observacion) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.rubro = rubro;
        this.observacion = observacion;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

 }
