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
public class MedidaIngreso {
    int idMedidaIngreso;
    int idOrdenTrabajo;
    String medida;

    public MedidaIngreso() {
    }

    public MedidaIngreso(int idMedidaIngreso, int idOrdenTrabajo, String medida) {
        this.idMedidaIngreso = idMedidaIngreso;
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.medida = medida;
    }

    public int getIdMedidaIngreso() {
        return idMedidaIngreso;
    }

    public void setIdMedidaIngreso(int idMedidaIngreso) {
        this.idMedidaIngreso = idMedidaIngreso;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }
  
}
