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
public class GuiaDeRemision {
  String guia;
  String cliente;
  int idGuiaRemi;
  String numGuia;
  String serieGuia;
  String fechEmi;
  String fechaTraslado;
  String direcPart;
  String direcLleg;
  int idCliente;
  String tipoDocCli;
  int idMotivoGuia;
  String motivoDescripcion;
  String vehMarca;
  String vehCertif;
  String vehLic;
  String nomTransp;
  String rucTransp;
  String tipoComprobante;
  String numComprobante;
  String serieGuiaAct;
  String anio;
  String estado;
  String observacion;
  String numPedi;
  
  
  

    public GuiaDeRemision() {
    }

    public GuiaDeRemision(String serieGuia, String numGuia, String cliente, String tipoComprobante, String numComprobante, String fechEmi, String fechaTraslado, String estado, String observacion) {
        this.serieGuia = serieGuia;
        this.numGuia = numGuia;
        this.cliente = cliente;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.fechEmi = fechEmi;
        this.fechaTraslado = fechaTraslado;
        this.estado = estado;
        this.observacion = observacion;
    }
    
    
    public GuiaDeRemision(String serieGuia, String numGuia, String cliente, String tipoComprobante, String numComprobante, String fechEmi, String estado, String observacion) {
        this.serieGuia = serieGuia;
        this.numGuia = numGuia;
        this.cliente = cliente;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.fechEmi = fechEmi;
        this.estado = estado;
        this.observacion = observacion;
    }
    
    public GuiaDeRemision( String serieGuia, String numGuia, String fechEmi, String fechaTraslado, String direcPart, String direcLleg, int idCliente, String tipoDocCli, int idMotivoGuia, String motivoDescripcion, String vehMarca, String vehCertif, String vehLic, String nomTransp, String rucTransp, String tipoComprobante, String numComprobante) {
        
        this.serieGuia = serieGuia;
        this.numGuia = numGuia;
        this.fechEmi = fechEmi;
        this.fechaTraslado = fechaTraslado;
        this.direcPart = direcPart;
        this.direcLleg = direcLleg;
        this.idCliente = idCliente;
        this.tipoDocCli = tipoDocCli;
        this.idMotivoGuia=idMotivoGuia;
        this.motivoDescripcion = motivoDescripcion;
        this.vehMarca = vehMarca;
        this.vehCertif = vehCertif;
        this.vehLic = vehLic;
        this.nomTransp = nomTransp;
        this.rucTransp = rucTransp;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
    }

    public GuiaDeRemision(int idGuiaRemi,        String serieGuia,      String numGuia,        String fechEmi, 
                           String fechaTraslado, String direcPart,      String direcLleg,      int idCliente, 
                           String tipoDocCli,    int idMotivoGuia,      String motivoDescripcion, 
                           String vehMarca,      String vehCertif,      String vehLic,          String nomTransp, 
                           String rucTransp,     String tipoComprobante,String numComprobante,
                           String estado,        String observacion,    String numPedi){
        this.idGuiaRemi = idGuiaRemi;
        this.serieGuia = serieGuia;
        this.numGuia = numGuia;
        this.fechEmi = fechEmi;
        this.fechaTraslado = fechaTraslado;
        this.direcPart = direcPart;
        this.direcLleg = direcLleg;
        this.idCliente = idCliente;
        this.tipoDocCli = tipoDocCli;
        this.idMotivoGuia=idMotivoGuia;
        this.motivoDescripcion = motivoDescripcion;
        this.vehMarca = vehMarca;
        this.vehCertif = vehCertif;
        this.vehLic = vehLic;
        this.nomTransp = nomTransp;
        this.rucTransp = rucTransp;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.estado = estado;
        this.observacion = observacion;
        this.numPedi= numPedi;
    }

    public GuiaDeRemision(String serieGuia, String numGuia, String fechEmi, String fechaTraslado, String direcPart, String direcLleg, int idCliente, String tipoDocCli,int idMotivoGuia, String motivoDescripcion, String vehMarca, String vehCertif, String vehLic, String nomTransp, String rucTransp, String tipoComprobante, String numComprobante, String serieGuiaAct) {
        
        this.serieGuia = serieGuia;
        this.numGuia = numGuia;
        this.fechEmi = fechEmi;
        this.fechaTraslado = fechaTraslado;
        this.direcPart = direcPart;
        this.direcLleg = direcLleg;
        this.idCliente = idCliente;
        this.tipoDocCli = tipoDocCli;
        this.idMotivoGuia=idMotivoGuia;
        this.motivoDescripcion = motivoDescripcion;
        this.vehMarca = vehMarca;
        this.vehCertif = vehCertif;
        this.vehLic = vehLic;
        this.nomTransp = nomTransp;
        this.rucTransp = rucTransp;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.serieGuiaAct = serieGuiaAct;
    }

    public GuiaDeRemision(int idGuiaRemi, String anio) {
        this.idGuiaRemi = idGuiaRemi;
        this.anio = anio;
    }
    
    public GuiaDeRemision(int idGuiaRemi) {
        this.idGuiaRemi = idGuiaRemi;
    }

    public GuiaDeRemision(String numGuia) {
        this.numGuia = numGuia;
    }
  
    public int getIdGuiaRemi() {
        return idGuiaRemi;
    }

    public void setIdGuiaRemi(int idGuiaRemi) {
        this.idGuiaRemi = idGuiaRemi;
    }

    public String getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public String getSerieGuia() {
        return serieGuia;
    }

    public void setSerieGuia(String serieGuia) {
        this.serieGuia = serieGuia;
    }

    public String getFechEmi() {
        return fechEmi;
    }

    public void setFechEmi(String fechEmi) {
        this.fechEmi = fechEmi;
    }

    public String getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(String fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public String getDirecPart() {
        return direcPart;
    }

    public void setDirecPart(String direcPart) {
        this.direcPart = direcPart;
    }

    public String getDirecLleg() {
        return direcLleg;
    }

    public void setDirecLleg(String direcLleg) {
        this.direcLleg = direcLleg;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoDocCli() {
        return tipoDocCli;
    }

    public void setTipoDocCli(String tipoDocCli) {
        this.tipoDocCli = tipoDocCli;
    }

    public int getIdMotivoGuia() {
        return idMotivoGuia;
    }

    public void setIdMotivoGuia(int idMotivoGuia) {
        this.idMotivoGuia = idMotivoGuia;
    }

    public String getMotivoDescripcion() {
        return motivoDescripcion;
    }

    public void setMotivoDescripcion(String motivoDescripcion) {
        this.motivoDescripcion = motivoDescripcion;
    }

    
    public String getVehMarca() {
        return vehMarca;
    }

    public void setVehMarca(String vehMarca) {
        this.vehMarca = vehMarca;
    }

    public String getVehCertif() {
        return vehCertif;
    }

    public void setVehCertif(String vehCertif) {
        this.vehCertif = vehCertif;
    }

    public String getVehLic() {
        return vehLic;
    }

    public void setVehLic(String vehLic) {
        this.vehLic = vehLic;
    }

    public String getNomTransp() {
        return nomTransp;
    }

    public void setNomTransp(String nomTransp) {
        this.nomTransp = nomTransp;
    }

    public String getRucTransp() {
        return rucTransp;
    }

    public void setRucTransp(String rucTransp) {
        this.rucTransp = rucTransp;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSerieGuiaAct() {
        return serieGuiaAct;
    }

    public void setSerieGuiaAct(String serieGuiaAct) {
        this.serieGuiaAct = serieGuiaAct;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
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

    public String getNumPedi() {
        return numPedi;
    }

    public void setNumPedi(String numPedi) {
        this.numPedi = numPedi;
    }
    
  }
