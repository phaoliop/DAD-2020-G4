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
public class ContactoProveedor {

    int idContactoProveedor;
    int idProveedor;
    String dni;
    String nombres;
    String apellidos;
    String cargo;
    String correo;
    String tlf1;
    String tlf2;
    String sucursal;
    String observacion;
    String razSocial;
    String contacto;

    public ContactoProveedor() {
    }

    public ContactoProveedor(int idContactoProveedor, String dni, String nombres, String apellidos, String cargo, String correo, String tlf1, String tlf2, String sucursal, String observacion) {
        this.idContactoProveedor = idContactoProveedor;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.correo = correo;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.sucursal = sucursal;
        this.observacion = observacion;
    }

    public ContactoProveedor(int idContactoProveedor, int idProveedor, String dni, String nombres, String apellidos, String cargo, String correo, String tlf1, String tlf2, String sucursal, String observacion) {
        this.idContactoProveedor = idContactoProveedor;
        this.idProveedor = idProveedor;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.correo = correo;
        this.tlf1 = tlf1;
        this.tlf2 = tlf2;
        this.sucursal = sucursal;
        this.observacion = observacion;
    }

    public ContactoProveedor(int idContactoProveedor, String contacto, String tlf1, String correo, String razSocial, String sucursal, String observacion) {
        this.idContactoProveedor = idContactoProveedor;
        this.contacto = contacto;
        this.tlf1 = tlf1;
        this.correo = correo;
        this.razSocial = razSocial;
        this.sucursal = sucursal;
        this.observacion = observacion;
        
    }

    public int getIdContactoProveedor() {
        return idContactoProveedor;
    }

    public void setIdContactoProveedor(int idContactoProveedor) {
        this.idContactoProveedor = idContactoProveedor;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    
}
