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
public class CajaChica {
    int idCajaChica;
    String fechaCaja;
    String descripCaja;
    String egreso;
    String ingreso;
    String totalEgreso;
    String totalIngreso;
    String diferencia;
    

    public CajaChica() {
    }

    public CajaChica(int idCajaChica, String fechaCaja, String descripCaja, String egreso, String ingreso) {
        this.idCajaChica = idCajaChica;
        this.fechaCaja = fechaCaja;
        this.descripCaja = descripCaja;
        this.egreso = egreso;
        this.ingreso = ingreso;
    }

    public CajaChica(String fechaCaja, String descripCaja, String egreso, String ingreso) {
        this.fechaCaja = fechaCaja;
        this.descripCaja = descripCaja;
        this.egreso = egreso;
        this.ingreso = ingreso;
    }

    public CajaChica(String totalEgreso, String totalIngreso, String diferencia) {
        this.totalEgreso = totalEgreso;
        this.totalIngreso = totalIngreso;
        this.diferencia = diferencia;
    }

    public int getIdCajaChica() {
        return idCajaChica;
    }

    public void setIdCajaChica(int idCajaChica) {
        this.idCajaChica = idCajaChica;
    }

    public String getFechaCaja() {
        return fechaCaja;
    }

    public void setFechaCaja(String fechaCaja) {
        this.fechaCaja = fechaCaja;
    }

    public String getDescripCaja() {
        return descripCaja;
    }

    public void setDescripCaja(String descripCaja) {
        this.descripCaja = descripCaja;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getTotalEgreso() {
        return totalEgreso;
    }

    public void setTotalEgreso(String totalEgreso) {
        this.totalEgreso = totalEgreso;
    }

    public String getTotalIngreso() {
        return totalIngreso;
    }

    public void setTotalIngreso(String totalIngreso) {
        this.totalIngreso = totalIngreso;
    }

    public String getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }
    
    
    
}
