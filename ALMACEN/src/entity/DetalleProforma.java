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
public class DetalleProforma {

    int idDetalleProforma;
    int idProforma;
    String item;
    String item1;
    String cantidad;
    String cant;
    String descripcion;
    String detalleDescrip;
    String precioUnitario;
    String precio;
    String importe;
    String subtotal;
    String igv;
    String total;
    String tcVenta;

    public DetalleProforma() {
    }

    public DetalleProforma(String subtotal, String igv, String total) {
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
    }

    public DetalleProforma(String total) {
        this.total = total;
    }

    public DetalleProforma(String total, String tcVenta) {
        this.total = total;
        this.tcVenta = tcVenta;
    }
    
   public DetalleProforma(String item, String cantidad, String descripcion, String detalleDescrip, String precioUnitario) {
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.detalleDescrip = detalleDescrip;
        this.precioUnitario = precioUnitario;
    }

    public DetalleProforma(int idDetalleProforma ,String item, String cantidad, String descripcion,  String precioUnitario,String item1, String importe) {
        this.idDetalleProforma = idDetalleProforma;
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.item1 = item1;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        
    }

    public DetalleProforma(int idDetalleProforma, String item, String cantidad, String descripcion, String detalleDescrip, String precioUnitario) {
        this.idDetalleProforma = idDetalleProforma;
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.detalleDescrip = detalleDescrip;
        this.precioUnitario = precioUnitario;
    }

    public DetalleProforma(int idDetalleProforma, int idProforma, String item, String cantidad, String descripcion, String detalleDescrip, String precioUnitario) {
        this.idDetalleProforma = idDetalleProforma;
        this.idProforma = idProforma;
        this.item = item;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.detalleDescrip = detalleDescrip;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalleProforma() {
        return idDetalleProforma;
    }

    public void setIdDetalleProforma(int idDetalleProforma) {
        this.idDetalleProforma = idDetalleProforma;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
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

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
    

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTcVenta() {
        return tcVenta;
    }

    public void setTcVenta(String tcVenta) {
        this.tcVenta = tcVenta;
    }
    
}
