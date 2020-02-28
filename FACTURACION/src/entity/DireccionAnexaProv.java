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
public class DireccionAnexaProv {
    
    int idDirAnexa;
    int idProv;
    String direc;
    String obs;
    String campo1;

    public DireccionAnexaProv() {
    }

    public DireccionAnexaProv(int idDirAnexa, int idProv, String direc, String obs, String campo1) {
        this.idDirAnexa = idDirAnexa;
        this.idProv = idProv;
        this.direc = direc;
        this.obs = obs;
        this.campo1 = campo1;
    }

    public DireccionAnexaProv(int idDirAnexa, int idProv, String direc, String obs) {
        this.idDirAnexa = idDirAnexa;
        this.idProv = idProv;
        this.direc = direc;
        this.obs = obs;
    }
    
    public DireccionAnexaProv(int idDirAnexa, String direc, String obs) {
        this.idDirAnexa = idDirAnexa;
        this.direc = direc;
        this.obs = obs;
    }

    public DireccionAnexaProv(int idDirAnexa) {
        this.idDirAnexa = idDirAnexa;
    }
    
        
    public int getIdDirAnexa() {
        return idDirAnexa;
    }

    public void setIdDirAnexa(int idDirAnexa) {
        this.idDirAnexa = idDirAnexa;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }
    
    
    
}
