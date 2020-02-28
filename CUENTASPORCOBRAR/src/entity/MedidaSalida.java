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
public class MedidaSalida {
    
    int idMedidaSalida;
    int idOrdenTrabajo;
    String medida;

    public MedidaSalida() {
    }

    public MedidaSalida(int idMedidaSalida, int idOrdenTrabajo, String medida) {
        this.idMedidaSalida = idMedidaSalida;
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.medida = medida;
    }
    
    public int getIdMedidaSalida() {
        return idMedidaSalida;
    }

    public void setIdMedidaSalida(int idMedidaSalida) {
        this.idMedidaSalida = idMedidaSalida;
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
