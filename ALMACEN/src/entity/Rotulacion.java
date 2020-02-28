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
public class Rotulacion {
    int idRotulacion;
    String remitente;
    int idCliente;
    String destino;
    String razSocial;

    public Rotulacion() {
    }

    public Rotulacion(int idRotulacion, String remitente, int idCliente, String destino) {
        this.idRotulacion = idRotulacion;
        this.remitente = remitente;
        this.idCliente = idCliente;
        this.destino = destino;
    }

    public Rotulacion(int idRotulacion, String razSocial, String remitente, String destino) {
        this.idRotulacion = idRotulacion;
        this.razSocial = razSocial;
        this.remitente = remitente;
        this.destino = destino;   
    }

    public Rotulacion(int idRotulacion) {
        this.idRotulacion = idRotulacion;
    }
    
    

    public int getIdRotulacion() {
        return idRotulacion;
    }

    public void setIdRotulacion(int idRotulacion) {
        this.idRotulacion = idRotulacion;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }
}
