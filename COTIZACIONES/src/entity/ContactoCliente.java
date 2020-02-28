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
public class ContactoCliente {

    int idContactoCliente;
    int idCliente;
    String dni;
    String nombres;
    String apellidos;
    String cargo;
    String tlf1;
    String tlf2;
    String correo;
    String sucursal;
    String observacion;
    String razSocial;
    String nombCompl;
    String direccion;
    
    public ContactoCliente() {
    }

    public ContactoCliente(String nombres) {
        this.nombres = nombres;
    }

    public ContactoCliente(int idContactoCliente, String dni) {
        this.idContactoCliente = idContactoCliente;
        this.dni = dni;
    }

    public ContactoCliente(int idContactoCliente,String tlf1, String correo) {
        this.idContactoCliente = idContactoCliente;
        this.tlf1 = tlf1;
        this.correo = correo;
    }

    public ContactoCliente(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public ContactoCliente(int idContactoCliente, String dni, String nombres, String apellidos, String cargo, String tlf1, String tlf2, String correo, String sucursal, String observacion) {
        this.idContactoCliente = idContactoCliente;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.correo = correo;
        this.sucursal = sucursal;
        this.observacion = observacion;
    }

    public ContactoCliente(int idContactoCliente, int idCliente, String dni, String nombres, String apellidos, String cargo, String tlf1, String tlf2, String correo, String sucursal, String observacion) {
        this.idContactoCliente = idContactoCliente;
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.correo = correo;
        this.sucursal = sucursal;
        this.observacion = observacion;
    }

    public ContactoCliente(int idContactoCliente, String nombCompl, String tlf1, String correo, String razSocial, String sucursal, String observacion) {
        this.idContactoCliente = idContactoCliente;
        this.nombCompl = nombCompl;
        this.tlf1 = tlf1;
        this.correo = correo;
        this.razSocial = razSocial;
        this.sucursal = sucursal;
        this.observacion = observacion;
          
    }

    public ContactoCliente(int idContactoCliente, int idCliente, String razSocial, String nombCompl, String direccion, String sucursal, String observacion) {
        this.idContactoCliente = idContactoCliente;
        this.idCliente = idCliente;
        this.razSocial = razSocial;
        this.nombCompl = nombCompl;
        this.direccion = direccion;
        this.sucursal = sucursal;
        this.observacion = observacion;
      
    }

       
    public int getIdContactoCliente() {
        return idContactoCliente;
    }

    public void setIdContactoCliente(int idContactoCliente) {
        this.idContactoCliente = idContactoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTlf1() {
        return tlf1;
    }

    public void setTlf1(String tlf1) {
        this.tlf1 = tlf1;
    }

    public String getTlf2() {
        return tlf2;
    }

    public void setTlf2(String tlf2) {
        this.tlf2 = tlf2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRazSocial() {
        return razSocial;
    }

    public void setRazSocial(String razSocial) {
        this.razSocial = razSocial;
    }

    public String getNombCompl() {
        return nombCompl;
    }

    public void setNombCompl(String nombCompl) {
        this.nombCompl = nombCompl;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
     
}
