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
public class Transportista {

    int idTransportista;
    String nombre;
    String ruc;
    String marcaYPlaca;
    String certificadoInscripcion;
    String licenciaConducir;

    public Transportista() {
    }

    public Transportista(int idTransportista, String nombre, String ruc, String marcaYPlaca, String certificadoInscripcion, String licenciaConducir) {
        this.idTransportista = idTransportista;
        this.nombre = nombre;
        this.ruc = ruc;
        this.marcaYPlaca = marcaYPlaca;
        this.certificadoInscripcion = certificadoInscripcion;
        this.licenciaConducir = licenciaConducir;
    }

    public int getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        this.idTransportista = idTransportista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getMarcaYPlaca() {
        return marcaYPlaca;
    }

    public void setMarcaYPlaca(String marcaYPlaca) {
        this.marcaYPlaca = marcaYPlaca;
    }

    public String getCertificadoInscripcion() {
        return certificadoInscripcion;
    }

    public void setCertificadoInscripcion(String certificadoInscripcion) {
        this.certificadoInscripcion = certificadoInscripcion;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }
    
}
