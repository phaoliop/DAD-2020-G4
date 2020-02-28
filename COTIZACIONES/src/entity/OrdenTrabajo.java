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
public class OrdenTrabajo {
    
  int idOrdenTrabajo;
  int idPedido;
  int idProforma;
  String numOrdenTrabajo;
  String tipoServicio;
  String recepcion;
  String destino;
  String fechaEmision;
  String fechaInicio;
  String fechaFin;
  String fechaEntrega;
  String estado;
  String observacion;
  String medidaIngreso;
  String medidaFinaliza;
  
  String razSocial;
  int numPed;
  String numCot;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(int idOrdenTrabajo, int idPedido, int idProforma, String numOrdenTrabajo, String tipoServicio, String recepcion, String destino, String fechaEmision, String fechaInicio, String fechaFin, String fechaEntrega, String estado, String observacion, String medidaIngreso, String medidaFinaliza) {
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.idPedido = idPedido;
        this.idProforma = idProforma;
        this.numOrdenTrabajo = numOrdenTrabajo;
        this.tipoServicio = tipoServicio;
        this.recepcion = recepcion;
        this.destino = destino;
        this.fechaEmision = fechaEmision;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.observacion = observacion;
        this.medidaIngreso =medidaIngreso;
        this.medidaFinaliza = medidaFinaliza;
    }

    public OrdenTrabajo(int idOrdenTrabajo, String numOrdenTrabajo, String razSocial, int numPed, String numCot, String fechaEmision, String fechaInicio, String fechaFin, String fechaEntrega, String estado, String observacion) {
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.numOrdenTrabajo = numOrdenTrabajo;
        this.razSocial = razSocial;
        this.numPed = numPed;
        this.numCot = numCot;
        this.fechaEmision = fechaEmision;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.observacion = observacion;
    }

    
    public OrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }
    
    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }

    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public String getNumOrdenTrabajo() {
        return numOrdenTrabajo;
    }

    public void setNumOrdenTrabajo(String numOrdenTrabajo) {
        this.numOrdenTrabajo = numOrdenTrabajo;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(String recepcion) {
        this.recepcion = recepcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getMedidaIngreso() {
        return medidaIngreso;
    }

    public void setMedidaIngreso(String medidaIngreso) {
        this.medidaIngreso = medidaIngreso;
    }

    public String getMedidaFinaliza() {
        return medidaFinaliza;
    }

    public void setMedidaFinaliza(String medidaFinaliza) {
        this.medidaFinaliza = medidaFinaliza;
    }

    

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public int getNumPed() {
        return numPed;
    }

    public void setNumPed(int numPed) {
        this.numPed = numPed;
    }

    public String getNumCot() {
        return numCot;
    }

    public void setNumCot(String numCot) {
        this.numCot = numCot;
    }
 
}
