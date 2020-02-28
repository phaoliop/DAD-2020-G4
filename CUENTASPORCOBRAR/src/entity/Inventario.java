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
public class Inventario {
    int idInventario;
    String fechRea;
    String fechReg;
    String encargado;
    String estado;
    String obser;
    String campo1;

    
    public Inventario() {
    }

    public Inventario(int idInventario, String fechRea, String fechReg, String encargado, String estado, String obser, String campo1) {
        this.idInventario = idInventario;
        this.fechRea = fechRea;
        this.fechReg = fechReg;
        this.encargado = encargado;
        this.estado = estado;
        this.obser = obser;
        this.campo1 = campo1;
    }

    public Inventario(String fechRea, String fechReg, String encargado, String estado, String obser, String campo1) {
        this.fechRea = fechRea;
        this.fechReg = fechReg;
        this.encargado = encargado;
        this.estado = estado;
        this.obser = obser;
        this.campo1 = campo1;
    }

    public Inventario(int idInventario, String encargado, String fechRea, String fechReg, String estado, String obser) {
        this.idInventario = idInventario;
        this.encargado = encargado;
        this.fechRea = fechRea;
        this.fechReg = fechReg;        
        this.estado = estado;
        this.obser = obser;
    }

    public Inventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getFechRea() {
        return fechRea;
    }

    public void setFechRea(String fechRea) {
        this.fechRea = fechRea;
    }

    public String getFechReg() {
        return fechReg;
    }

    public void setFechReg(String fechReg) {
        this.fechReg = fechReg;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
    
    
    
}
