/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ARCRODINPC-06
 */
public class DireccionAnexa {
    
    int idDireccion;
    int idCliente;
    String direccion;
    String obser;
    String campo1;
    String campo2;

    public DireccionAnexa() {
    }

    public DireccionAnexa(int idDireccion, int idCliente, String direccion, String obser, String campo1, String campo2) {
        this.idDireccion = idDireccion;
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.obser = obser;
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

    public DireccionAnexa(int idDireccion, int idCliente, String direccion, String obser) {
        this.idDireccion = idDireccion;
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.obser = obser;
    }

    public DireccionAnexa(int idDireccion, String direccion, String obser) {
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.obser = obser;
    }

    public DireccionAnexa(int idDireccion) {
        this.idDireccion = idDireccion;
    }
     
    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObser() {
        return obser;
    }

    public void setObser(String obser) {
        this.obser = obser;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }
    
}
