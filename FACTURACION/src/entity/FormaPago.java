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
public class FormaPago {
    private int idFormaPago;
    private String descripcion;
    private int dias;

    public FormaPago(int idFormaPago, String descripcion, int dias) {
        this.idFormaPago = idFormaPago;
        this.descripcion = descripcion;
        this.dias = dias;
    }
    
        public FormaPago(String descripcion, int dias) {

        this.descripcion = descripcion;
        this.dias = dias;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }    
}
