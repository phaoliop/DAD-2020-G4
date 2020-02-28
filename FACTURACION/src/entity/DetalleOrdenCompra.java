/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Diego Lopez
 */
public class DetalleOrdenCompra {
    
    private int doc_id;
    private int doc_idOrdenComprafk;
    private String doc_item;
    private String doc_cantidad;
    private String doc_desc;
    private String doc_detalleDesc;
    private String doc_precioUnit;
    private String doc_total;
    private String subtotal;
    private String igv;
    private String total;
   

    public DetalleOrdenCompra() {
    }
   
    public DetalleOrdenCompra(int doc_id, int doc_idOrdenComprafk, String doc_item, String doc_cantidad, String doc_desc, String doc_detalleDesc, String doc_precioUnit) {
        this.doc_id = doc_id;
        this.doc_idOrdenComprafk = doc_idOrdenComprafk;
        this.doc_item = doc_item;
        this.doc_cantidad = doc_cantidad;
        this.doc_desc = doc_desc;
        this.doc_detalleDesc = doc_detalleDesc;
        this.doc_precioUnit = doc_precioUnit;
    }

    public DetalleOrdenCompra(int doc_id, String doc_item, String doc_cantidad, String doc_desc, String doc_precioUnit, String doc_total) {
        this.doc_id = doc_id;
        this.doc_item = doc_item;
        this.doc_cantidad = doc_cantidad;
        this.doc_desc = doc_desc;
        this.doc_precioUnit = doc_precioUnit;
        this.doc_total = doc_total;
    }

    public DetalleOrdenCompra(String subtotal, String igv, String total) {
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
    }
  
    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getDoc_idOrdenComprafk() {
        return doc_idOrdenComprafk;
    }

    public void setDoc_idOrdenComprafk(int doc_idOrdenComprafk) {
        this.doc_idOrdenComprafk = doc_idOrdenComprafk;
    }

    public String getDoc_item() {
        return doc_item;
    }

    public void setDoc_item(String doc_item) {
        this.doc_item = doc_item;
    }

    public String getDoc_cantidad() {
        return doc_cantidad;
    }

    public void setDoc_cantidad(String doc_cantidad) {
        this.doc_cantidad = doc_cantidad;
    }

    public String getDoc_desc() {
        return doc_desc;
    }

    public void setDoc_desc(String doc_desc) {
        this.doc_desc = doc_desc;
    }

    public String getDoc_detalleDesc() {
        return doc_detalleDesc;
    }

    public void setDoc_detalleDesc(String doc_detalleDesc) {
        this.doc_detalleDesc = doc_detalleDesc;
    }

    public String getDoc_precioUnit() {
        return doc_precioUnit;
    }

    public void setDoc_precioUnit(String doc_precioUnit) {
        this.doc_precioUnit = doc_precioUnit;
    }

    public String getDoc_total() {
        return doc_total;
    }

    public void setDoc_total(String doc_total) {
        this.doc_total = doc_total;
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
 
}
