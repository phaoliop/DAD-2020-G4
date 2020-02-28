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
public class ProveedorRubro {
    int idProvRubpk;
    int idProv;
    int idRubro;
    String obs;
    String estado;
    String rubro;
    String provee;
  

    public ProveedorRubro() {
    }

    public ProveedorRubro(int idProvRubpk, int idProv, int idRubro, String obs, String estado) {
        this.idProvRubpk = idProvRubpk;
        this.idProv = idProv;
        this.idRubro = idRubro;
        this.obs = obs;
        this.estado = estado;
    }

    public ProveedorRubro(int idProvRubpk, String rubro, String obs, String estado) {
        this.idProvRubpk = idProvRubpk;
        this.rubro = rubro;
        this.obs = obs;
        this.estado = estado;
        
    }

    public ProveedorRubro(int idProvRubpk, String provee, String rubro, String obs, String estado) {
        
        this.idProvRubpk = idProvRubpk;
        this.provee = provee;
        this.rubro = rubro;
        this.obs = obs;
        this.estado = estado;
     }

    public int getIdProvRubpk() {
        return idProvRubpk;
    }

    public void setIdProvRubpk(int idProvRubpk) {
        this.idProvRubpk = idProvRubpk;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getProvee() {
        return provee;
    }

    public void setProvee(String provee) {
        this.provee = provee;
    }
    
}
