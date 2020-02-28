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
public class DetalleOrdenCorte {
    int idDetalleOrdenCorte;
    int idOrdenCorte;
    int cantidad;
    String diametro;
    int idArticulo;
    String longitud;
    String codArt;
    String codUbi;
    String procedencia;
    String longOfi;
    String longReal;
    String numOrdenCorte;
    String nombre;
    String fecha;
    String cliente;
    int numPedido;
    String numCot;
    int inventario;
    

    public DetalleOrdenCorte() {
    }


    public DetalleOrdenCorte(int idDetalleOrdenCorte, int cantidad,String diametro, int idArticulo,  String longitud) {
        this.idDetalleOrdenCorte = idDetalleOrdenCorte;
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.diametro = diametro;
        this.longitud = longitud;
    }

    public DetalleOrdenCorte(int idDetalleOrdenCorte, int idOrdenCorte, int cantidad,String diametro, int idArticulo,  String longitud) {
        this.idDetalleOrdenCorte = idDetalleOrdenCorte;
        this.idOrdenCorte = idOrdenCorte;
        this.cantidad = cantidad;
        this.idArticulo = idArticulo;
        this.diametro = diametro;
        this.longitud = longitud;
    }

    public DetalleOrdenCorte(String codArt,String codUbi,int cantidad, String diametro, String longitud) {
        this.cantidad = cantidad;
        this.diametro = diametro;
        this.longitud = longitud;
        this.codArt = codArt;
        this.codUbi = codUbi;
    }

    public DetalleOrdenCorte(String codArt, String codUbi,int cantidad, String diametro, String longitud, String procedencia, String longOfi) {
        this.cantidad = cantidad;
        this.diametro = diametro;
        this.longitud = longitud;
        this.codArt = codArt;
        this.codUbi = codUbi;
        this.procedencia = procedencia;
        this.longOfi = longOfi;
}

    
     public DetalleOrdenCorte(String codArt, String codUbi,int cantidad, String diametro, String longitud, String procedencia,String longReal, int idDetalleOrdenCorte) {
        this.cantidad = cantidad;
        this.diametro = diametro;
        this.longitud = longitud;
        this.codArt = codArt;
        this.codUbi = codUbi;
        this.procedencia = procedencia;
        this.longReal = longReal;
        this.idDetalleOrdenCorte = idDetalleOrdenCorte;
}

    public DetalleOrdenCorte(int idDetalleOrdenCorte) {
        this.idDetalleOrdenCorte = idDetalleOrdenCorte;
    }

    public DetalleOrdenCorte(int idDetalleOrdenCorte, String numOrdenCorte, String codArt, String codUbi, int cantidad, String nombre, String diametro, String longitud, String procedencia, String fecha, String cliente, int numPedido, String numCot, int inventario) {
        this.idDetalleOrdenCorte = idDetalleOrdenCorte;
        this.numOrdenCorte = numOrdenCorte;
        this.cantidad = cantidad;
        this.codArt = codArt;
        this.codUbi = codUbi;
        this.nombre = nombre;
        this.diametro = diametro;
        this.longitud = longitud;
        this.procedencia = procedencia;
        this.fecha = fecha;
        this.cliente = cliente;
        this.numPedido = numPedido;
        this.numCot = numCot;
        this.inventario = inventario;
    }
     
    
    
    public int getIdDetalleOrdenCorte() {
        return idDetalleOrdenCorte;
    }

    public void setIdDetalleOrdenCorte(int idDetalleOrdenCorte) {
        this.idDetalleOrdenCorte = idDetalleOrdenCorte;
    }

    public int getIdOrdenCorte() {
        return idOrdenCorte;
    }

    public void setIdOrdenCorte(int idOrdenCorte) {
        this.idOrdenCorte = idOrdenCorte;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    public String getCodUbi() {
        return codUbi;
    }

    public void setCodUbi(String codUbi) {
        this.codUbi = codUbi;
    }

    public String getDiametro() {
        return diametro;
    }

    public void setDiametro(String diametro) {
        this.diametro = diametro;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getLongOfi() {
        return longOfi;
    }

    public void setLongOfi(String longOfi) {
        this.longOfi = longOfi;
    }

    public String getLongReal() {
        return longReal;
    }

    public void setLongReal(String longReal) {
        this.longReal = longReal;
    }

    public String getNumOrdenCorte() {
        return numOrdenCorte;
    }

    public void setNumOrdenCorte(String numOrdenCorte) {
        this.numOrdenCorte = numOrdenCorte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public String getNumCot() {
        return numCot;
    }

    public void setNumCot(String numCot) {
        this.numCot = numCot;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

}
