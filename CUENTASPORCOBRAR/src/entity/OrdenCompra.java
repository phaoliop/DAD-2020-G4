package entity;

import java.util.List;

/**
 *
 * @author Diego Lopez
 */
public class OrdenCompra {
    private int oc_id;
    private int oc_num;
    private String oc_fecha;
    private int oc_idProveedor_fk;
    private int oc_idContactoProv_fk;
    private String oc_moneda;
    private String oc_formaPago;
    private String oc_tiempoEntrega;
    private String oc_fechaEntrega;
    private String oc_docExterno;
    private int oc_idUsuario_fk;
    private String oc_lugarEntrega;
    private String oc_estado;
    private String oc_observacion;
    private String oc_campoAdd2;
    private String proveedor;
    private String contacto;
    private List<DetalleOrdenCompra> detalleOrdenCompra;// Ver que esté creado

    public OrdenCompra() {
    }

    public OrdenCompra(int oc_id, int oc_num, String oc_fecha, int oc_idProveedor_fk, int oc_idContactoProv_fk, String oc_moneda, String oc_formaPago, String oc_tiempoEntrega, String oc_fechaEntrega, String oc_docExterno, int oc_idUsuario_fk, String oc_lugarEntrega, String oc_estado, String oc_observacion, String oc_campoAdd2) {
        this.oc_id = oc_id;
        this.oc_num = oc_num;
        this.oc_fecha = oc_fecha;
        this.oc_idProveedor_fk = oc_idProveedor_fk;
        this.oc_idContactoProv_fk = oc_idContactoProv_fk;
        this.oc_moneda = oc_moneda;
        this.oc_formaPago = oc_formaPago;
        this.oc_tiempoEntrega = oc_tiempoEntrega;
        this.oc_fechaEntrega = oc_fechaEntrega;
        this.oc_docExterno = oc_docExterno;
        this.oc_idUsuario_fk = oc_idUsuario_fk;
        this.oc_lugarEntrega = oc_lugarEntrega;
        this.oc_estado = oc_estado;
        this.oc_observacion = oc_observacion;
        this.oc_campoAdd2 = oc_campoAdd2; 
    }

    public OrdenCompra(int oc_id, int oc_num, String proveedor, String contacto, String oc_fecha, String oc_formaPago, String oc_tiempoEntrega, String oc_fechaEntrega, String oc_lugarEntrega, String oc_estado, String oc_observacion ) {
        this.oc_id = oc_id;
        this.oc_num = oc_num;
        this.proveedor = proveedor;
        this.contacto = contacto;
        this.oc_fecha = oc_fecha;
        this.oc_formaPago = oc_formaPago;
        this.oc_tiempoEntrega = oc_tiempoEntrega;
        this.oc_fechaEntrega = oc_fechaEntrega;
        this.oc_lugarEntrega = oc_lugarEntrega;
        this.oc_estado = oc_estado;
        this.oc_observacion=oc_observacion;
    }

    public OrdenCompra(int oc_num) {
        this.oc_num = oc_num;
    }
    
    public int getOc_id() {
        return oc_id;
    }

    public void setOc_id(int oc_id) {
        this.oc_id = oc_id;
    }

    public int getOc_num() {
        return oc_num;
    }

    public void setOc_num(int oc_num) {
        this.oc_num = oc_num;
    }

    public String getOc_fecha() {
        return oc_fecha;
    }

    public void setOc_fecha(String oc_fecha) {
        this.oc_fecha = oc_fecha;
    }

    public int getOc_idProveedor_fk() {
        return oc_idProveedor_fk;
    }

    public void setOc_idProveedor_fk(int oc_idProveedor_fk) {
        this.oc_idProveedor_fk = oc_idProveedor_fk;
    }

    public int getOc_idContactoProv_fk() {
        return oc_idContactoProv_fk;
    }

    public void setOc_idContactoProv_fk(int oc_idContactoProv_fk) {
        this.oc_idContactoProv_fk = oc_idContactoProv_fk;
    }

    public String getOc_moneda() {
        return oc_moneda;
    }

    public void setOc_moneda(String oc_moneda) {
        this.oc_moneda = oc_moneda;
    }

    public String getOc_formaPago() {
        return oc_formaPago;
    }

    public void setOc_formaPago(String oc_formaPago) {
        this.oc_formaPago = oc_formaPago;
    }

    public String getOc_tiempoEntrega() {
        return oc_tiempoEntrega;
    }

    public void setOc_tiempoEntrega(String oc_tiempoEntrega) {
        this.oc_tiempoEntrega = oc_tiempoEntrega;
    }

    public String getOc_fechaEntrega() {
        return oc_fechaEntrega;
    }

    public void setOc_fechaEntrega(String oc_fechaEntrega) {
        this.oc_fechaEntrega = oc_fechaEntrega;
    }

    public String getOc_docExterno() {
        return oc_docExterno;
    }

    public void setOc_docExterno(String oc_docExterno) {
        this.oc_docExterno = oc_docExterno;
    }
 
    public int getOc_idUsuario_fk() {
        return oc_idUsuario_fk;
    }

    public void setOc_idUsuario_fk(int oc_idUsuario_fk) {
        this.oc_idUsuario_fk = oc_idUsuario_fk;
    }

    public String getOc_lugarEntrega() {
        return oc_lugarEntrega;
    }

    public void setOc_lugarEntrega(String oc_lugarEntrega) {
        this.oc_lugarEntrega = oc_lugarEntrega;
    }
 
    public String getOc_estado() {
        return oc_estado;
    }

    public void setOc_estado(String oc_estado) {
        this.oc_estado = oc_estado;
    }

    public String getOc_observacion() {
        return oc_observacion;
    }

    public void setOc_observacion(String oc_observacion) {
        this.oc_observacion = oc_observacion;
    }

    public String getOc_campoAdd2() {
        return oc_campoAdd2;
    }

    public void setOc_campoAdd2(String oc_campoAdd2) {
        this.oc_campoAdd2 = oc_campoAdd2;
    }

    public List<DetalleOrdenCompra> getDetalleOrdenCompra() {
        return detalleOrdenCompra;
    }

    public void setDetalleOrdenCompra(List<DetalleOrdenCompra> detalleOrdenCompra) {
        this.detalleOrdenCompra = detalleOrdenCompra;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

}
