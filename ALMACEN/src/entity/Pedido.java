/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ARCRODINPC-02
 */
public class Pedido {
   int idPedido;
   int numPedido;
   String numeroPedido;
   int idProforma;
   int idCliente;
   int idContactoCliente;
   int idUsuario;
   String moneda;
   String formPago;
   String tipo;
   String fechaEmision;
   int dia1;
   int dia2;
   String ordenCompra; 
   String estado;
   String Observacion;
   String observacionInterna;
   String campo2;
   String campo3;
   
   String codCotiz;
   String razSocial;
   String contacto;
   String ruc;
   
   String usuario;
     
   String anio;
   
   String guia;
   String numReferencial;
   String montoFact;
   

    public Pedido() {
    }

    public Pedido(int idPedido, int numPedido, int idProforma, int idCliente, int idContactoCliente, int idUsuario, String moneda, String formPago, String tipo, String fechaEmision, int dia1, int dia2, String ordenCompra,String estado, String Observacion, String observacionInterna, String campo2, String campo3) {
        this.idPedido = idPedido;
        this.numPedido = numPedido;
        this.idProforma = idProforma;
        this.idCliente = idCliente;
        this.idContactoCliente = idContactoCliente;
        this.idUsuario = idUsuario;
        this.moneda = moneda;
        this.formPago = formPago;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.dia1 = dia1;
        this.dia2 = dia2;
        this.ordenCompra=ordenCompra;
        this.estado = estado;
        this.Observacion = Observacion;
        this.observacionInterna = observacionInterna;
        this.campo2 = campo2;
        this.campo3 = campo3;
    }

    public Pedido(int numPedido, int idProforma, int idCliente, int idContactoCliente, int idUsuario, String moneda, String formPago, String tipo, String fechaEmision, int dia1, int dia2, String ordenCompra,String estado, String Observacion, String observacionInterna, String campo2, String campo3) {
        this.numPedido = numPedido;
        this.idProforma = idProforma;
        this.idCliente = idCliente;
        this.idContactoCliente = idContactoCliente;
        this.idUsuario = idUsuario;
        this.moneda = moneda;
        this.formPago = formPago;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.dia1 = dia1;
        this.dia2 = dia2;
        this.ordenCompra=ordenCompra;
        this.estado = estado;
        this.Observacion = Observacion;
        this.observacionInterna = observacionInterna;
        this.campo2 = campo2;
        this.campo3 = campo3;
    }

    public Pedido(int idPedido, int numPedido, int idProforma, int idCliente, int idContactoCliente, int idUsuario, String moneda, String formPago, String tipo, String fechaEmision, int dia1, int dia2, String ordenCompra,String estado, String Observacion, String observacionInterna) {
        this.idPedido = idPedido;
        this.numPedido = numPedido;
        this.idProforma = idProforma;
        this.idCliente = idCliente;
        this.idContactoCliente = idContactoCliente;
        this.idUsuario = idUsuario;
        this.moneda = moneda;
        this.formPago = formPago;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.dia1 = dia1;
        this.dia2 = dia2;
        this.ordenCompra=ordenCompra;
        this.estado = estado;
        this.Observacion = Observacion;
        this.observacionInterna=observacionInterna;
    }

    public Pedido(int numPedido, String codCotiz, String razSocial, String contacto, String fechaEmision,String tipo, String estado, String usuario, String observacionInterna) {
        this.numPedido = numPedido;
        this.codCotiz = codCotiz;
        this.razSocial = razSocial;
        this.contacto = contacto;
        this.fechaEmision = fechaEmision;
        this.tipo=tipo;
        this.estado = estado;
        this.usuario = usuario;
        this.observacionInterna=observacionInterna;
        
    }
    
     public Pedido(int numPedido, String codCotiz, String razSocial, String contacto, String fechaEmision, String estado, String usuario) {
        this.numPedido = numPedido;
        this.codCotiz = codCotiz;
        this.razSocial = razSocial;
        this.contacto = contacto;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
        this.usuario = usuario;
        
    }

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(int idPedido, int numPedido) {
        this.idPedido = idPedido;
        this.numPedido = numPedido;
    }

    public Pedido(int numPedido, String anio) {
        this.numPedido = numPedido;
        this.anio = anio;
    }

    public Pedido(String razSocial,String ruc, String contacto, int numPedido, int idProforma,String codCotiz,  String guia, String numReferencial, String tipo, String moneda, String formPago, String montoFact) {
        this.razSocial = razSocial;
        this.ruc = ruc;
        this.contacto = contacto;
        this.numPedido = numPedido;
        this.idProforma = idProforma;
        this.codCotiz = codCotiz;
        this.guia = guia;
        this.numReferencial = numReferencial;
        this.tipo=tipo;
        this.moneda = moneda;
        this.formPago = formPago;
        this.montoFact = montoFact;
    }
   
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idProforma) {
        this.idProforma = idProforma;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdContactoCliente() {
        return idContactoCliente;
    }

    public void setIdContactoCliente(int idContactoCliente) {
        this.idContactoCliente = idContactoCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFormPago() {
        return formPago;
    }

    public void setFormPago(String formPago) {
        this.formPago = formPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getDia1() {
        return dia1;
    }

    public void setDia1(int dia1) {
        this.dia1 = dia1;
    }

    public int getDia2() {
        return dia2;
    }

    public void setDia2(int dia2) {
        this.dia2 = dia2;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
    
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getObservacionInterna() {
        return observacionInterna;
    }

    public void setObservacionInterna(String observacionInterna) {
        this.observacionInterna = observacionInterna;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo3() {
        return campo3;
    }

    public void setCampo3(String campo3) {
        this.campo3 = campo3;
    }

    public String getCodCotiz() {
        return codCotiz;
    }

    public void setCodCotiz(String codCotiz) {
        this.codCotiz = codCotiz;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public String getNumReferencial() {
        return numReferencial;
    }

    public void setNumReferencial(String numReferencial) {
        this.numReferencial = numReferencial;
    }

    public String getMontoFact() {
        return montoFact;
    }

    public void setMontoFact(String montoFact) {
        this.montoFact = montoFact;
    }
    
}
