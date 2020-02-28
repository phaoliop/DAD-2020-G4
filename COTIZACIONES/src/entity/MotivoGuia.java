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
public class MotivoGuia {
    int idMotivoGuia;
    String motivo;
    String campo1;

    public MotivoGuia() {
    }

    public MotivoGuia(int idMotivoGuia, String motivo, String campo1) {
        this.idMotivoGuia = idMotivoGuia;
        this.motivo = motivo;
        this.campo1 = campo1;
    }

    public MotivoGuia(int idMotivoGuia, String motivo) {
        this.idMotivoGuia = idMotivoGuia;
        this.motivo = motivo;
    }
  
    public int getIdMotivoGuia() {
        return idMotivoGuia;
    }

    public void setIdMotivoGuia(int idMotivoGuia) {
        this.idMotivoGuia = idMotivoGuia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    } 
}
