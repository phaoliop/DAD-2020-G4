/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Yveth Calixto
 */
public class Articulo {
    int aidArticulo;
    int idInventario;
    String codigoArticulo;
    String codigoUbicacion;
    String nombre;
    String diametro;
    String diamPulg;
    String unidadMedidaDia;
    String longitud;
    String longitudReal;
    String unidadMedidaLong;
    int cantidad;
    String procedencia;
    String observacion;
    String longCortes;
    String diaYMed;

    public Articulo() {
    }

    public Articulo(String codigoArticulo, String codigoUbicacion, String nombre, String diametro, String diamPulg, String unidadMedidaDia, String longitud, String longitudReal, String unidadMedidaLong, int cantidad, String procedencia, String observacion) {
        this.codigoArticulo = codigoArticulo;
        this.codigoUbicacion = codigoUbicacion;
        this.nombre = nombre;
        this.diametro = diametro;
        this.diamPulg = diamPulg;
        this.unidadMedidaDia = unidadMedidaDia;
        this.longitud = longitud;
        this.longitudReal = longitudReal;
        this.unidadMedidaLong = unidadMedidaLong;
        this.cantidad = cantidad;
        this.procedencia = procedencia;
        this.observacion = observacion;
    }

    public Articulo(int aidArticulo, int idInventario, String codigoArticulo, String codigoUbicacion, String nombre, String diametro, String diamPulg, String unidadMedidaDia, String longitud, String longitudReal, String unidadMedidaLong, int cantidad, String procedencia, String observacion) {
        this.idInventario = idInventario;
        this.aidArticulo = aidArticulo;
        this.codigoArticulo = codigoArticulo;
        this.codigoUbicacion = codigoUbicacion;
        this.nombre = nombre;
        this.diametro = diametro;
        this.diamPulg = diamPulg;
        this.unidadMedidaDia = unidadMedidaDia;
        this.longitud = longitud;
        this.longitudReal = longitudReal;
        this.unidadMedidaLong = unidadMedidaLong;
        this.cantidad = cantidad;
        this.procedencia = procedencia;
        this.observacion = observacion;
    }
    
    public Articulo(int aidArticulo,int idInventario, String codigoArticulo, String codigoUbicacion, String diametro , String diamPulg, String procedencia, String longitud,  String longCortes, String longitudReal, String observacion) {
       this.aidArticulo = aidArticulo;
       this.idInventario = idInventario;
        this.codigoArticulo = codigoArticulo;
        this.codigoUbicacion = codigoUbicacion;
        this.diametro = diametro;
        this.diamPulg = diamPulg;
        this.procedencia = procedencia;
        this.longitud = longitud;
        this.longCortes = longCortes;
        this.longitudReal = longitudReal;
        this.observacion = observacion;
    }

    public Articulo(String codigoArticulo, String codigoUbicacion, String diametro , String longitud,  String longCortes, String longitudReal) {
        this.codigoArticulo = codigoArticulo;
        this.codigoUbicacion = codigoUbicacion;
        this.diametro = diametro;
        this.longitud = longitud;
        this.longCortes = longCortes;
        this.longitudReal = longitudReal;
        
    }
    public Articulo(String codigoArticulo, String codigoUbicacion, String diametro , String longitud) {
        this.codigoArticulo = codigoArticulo;
        this.codigoUbicacion = codigoUbicacion;
        this.diametro = diametro;
        this.longitud = longitud;
        
    }

    public Articulo(String codigoArticulo, String codigoUbicacion) {
        this.codigoArticulo = codigoArticulo;
        this.codigoUbicacion = codigoUbicacion;
    }

    public Articulo(int aidArticulo) {
        this.aidArticulo = aidArticulo;
    }
  
    public int getAidArticulo() {
        return aidArticulo;
    }

    public void setAidArticulo(int aidArticulo) {
        this.aidArticulo = aidArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getCodigoUbicacion() {
        return codigoUbicacion;
    }

    public void setCodigoUbicacion(String codigoUbicacion) {
        this.codigoUbicacion = codigoUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }
    
    public String getUnidadMedidaDia() {
        return unidadMedidaDia;
    }

    public void setUnidadMedidaDia(String unidadMedidaDia) {
        this.unidadMedidaDia = unidadMedidaDia;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLongitudReal() {
        return longitudReal;
    }

    public void setLongitudReal(String longitudReal) {
        this.longitudReal = longitudReal;
    }
    
    public String getUnidadMedidaLong() {
        return unidadMedidaLong;
    }

    public void setUnidadMedidaLong(String unidadMedidaLong) {
        this.unidadMedidaLong = unidadMedidaLong;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public String getLongCortes() {
        return longCortes;
    }

    public void setLongCortes(String longCortes) {
        this.longCortes = longCortes;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getDiamPulg() {
        return diamPulg;
    }

    public void setDiamPulg(String diamPulg) {
        this.diamPulg = diamPulg;
    }

    public String getDiaYMed() {
        return diaYMed;
    }

    public void setDiaYMed(String diaYMed) {
        this.diaYMed = diaYMed;
    }
}
