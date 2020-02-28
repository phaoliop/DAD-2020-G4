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
public class RotulacionAtencion {
    int idRotulacionAtencion;
    int idRotulacion;
    int idContactoCliente;
    String atencion;

    public RotulacionAtencion() {
    }

    public RotulacionAtencion(int idRotulacionAtencion, int idRotulacion, int idContactoCliente) {
        this.idRotulacionAtencion = idRotulacionAtencion;
        this.idRotulacion = idRotulacion;
        this.idContactoCliente = idContactoCliente;
    }

    public RotulacionAtencion(int idRotulacionAtencion, int idRotulacion, String atencion) {
        this.idRotulacionAtencion = idRotulacionAtencion;
        this.idRotulacion = idRotulacion;
        this.atencion = atencion;
    }
    
    public int getIdRotulacionAtencion() {
        return idRotulacionAtencion;
    }

    public void setIdRotulacionAtencion(int idRotulacionAtencion) {
        this.idRotulacionAtencion = idRotulacionAtencion;
    }

    public int getIdRotulacion() {
        return idRotulacion;
    }

    public void setIdRotulacion(int idRotulacion) {
        this.idRotulacion = idRotulacion;
    }

    public int getIdContactoCliente() {
        return idContactoCliente;
    }

    public void setIdContactoCliente(int idContactoCliente) {
        this.idContactoCliente = idContactoCliente;
    }

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }
}
