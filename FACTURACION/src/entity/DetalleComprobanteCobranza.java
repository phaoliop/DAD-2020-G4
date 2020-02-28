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
public class DetalleComprobanteCobranza {

    int idDetalleComprobanteCobranza;
    int idComprobanteCobranza;
    int item;
    int cantidad;
    String descripcion;
    String detalleDescrip;
    float precioUnitario;
    float importe;
    float subtotal;
    float igv;
    float total;

    public DetalleComprobanteCobranza() {
    }

    public DetalleComprobanteCobranza(int idComprobanteCobranza, int item, int cantidad, String descripcion, String detalleDescrip, float precioUnitario) {
        this.idComprobanteCobranza = idComprobanteCobranza;
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.detalleDescrip = detalleDescrip;
        this.precioUnitario = precioUnitario;
    }

    public DetalleComprobanteCobranza(int idDetalleComprobanteCobranza, int idComprobanteCobranza, int item, int cantidad, String descripcion, String detalleDescrip, float precioUnitario) {
        this.idDetalleComprobanteCobranza = idDetalleComprobanteCobranza;
        this.idComprobanteCobranza = idComprobanteCobranza;
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.detalleDescrip = detalleDescrip;
        this.precioUnitario = precioUnitario;
    }

    public DetalleComprobanteCobranza(int item, int cantidad, String descripcion, String detalleDescrip, float precioUnitario) {
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.detalleDescrip = detalleDescrip;
        this.precioUnitario = precioUnitario;
    }

    public DetalleComprobanteCobranza(int item, int cantidad, String descripcion, float precioUnitario, float importe) {
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public DetalleComprobanteCobranza(float subtotal, float igv, float total) {
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
    }

    public int getIdDetalleComprobanteCobranza() {
        return idDetalleComprobanteCobranza;
    }

    public void setIdDetalleComprobanteCobranza(int idDetalleComprobanteCobranza) {
        this.idDetalleComprobanteCobranza = idDetalleComprobanteCobranza;
    }

    public int getIdComprobanteCobranza() {
        return idComprobanteCobranza;
    }

    public void setIdComprobanteCobranza(int idComprobanteCobranza) {
        this.idComprobanteCobranza = idComprobanteCobranza;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalleDescrip() {
        return detalleDescrip;
    }

    public void setDetalleDescrip(String detalleDescrip) {
        this.detalleDescrip = detalleDescrip;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getIgv() {
        return igv;
    }

    public void setIgv(float igv) {
        this.igv = igv;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }



}
