/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.RegistroVenta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARCRODINPC-05
 */
public class RegistroVentaDao implements ICrudDao<RegistroVenta>{

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    RegistroVenta reVe;
    String q;
    
    @Override
    public void create(RegistroVenta t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
             ps = conexion.prepareStatement("Insert into registroventa(fechaRegistro,"
                     + "idPedido_fkk, guia, tipoComprobante, serie, numero, fechaEmision, fechaAprobacion,\n"
                    + "fechaVencimiento,tipo, moneda, montoFacturado, detraccion,  \n"
                    + "estado,observacion) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
           
           ps.setString(1, t.getFechaRegistro());
           ps.setInt(2,t.getIdPedido());
           ps.setString(3, t.getGuia());
           ps.setString(4, t.getTipoComprobante());
           ps.setString(5, t.getSerie());
           ps.setString(6, t.getNumero());
           ps.setString(7, t.getFechaEmision());
           ps.setString(8, t.getFechaAprobacion());
           ps.setString(9, t.getFechaVencimiento());
           ps.setString(10, t.getTipo());
           ps.setString(11, t.getMoneda());
           ps.setString(12, t.getMontoFacturado());
           ps.setString(13, t.getDetraccion());
           ps.setString(14, t.getEstado());
           ps.setString(15, t.getObservacion());
           
            ps.executeUpdate();
            ps.close();

        } catch (ClassNotFoundException e1) {
            System.out.println("Error:" + e1.getMessage());
            System.exit(0);
        } catch (SQLException e2) {
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
            System.exit(0);
        } finally {
            conexion.close();
        }
    }

    @Override
    public void update(RegistroVenta t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
             q = "update registroventa set  fechaRegistro=?,"
                     + "idPedido_fkk=?, guia=?, tipoComprobante=?, serie=?, numero=?, fechaEmision=?,"
                     + " fechaAprobacion=?, fechaVencimiento=?,tipo=? , moneda=?, montoFacturado=?,"
                     + " detraccion=?,estado=?,observacion=?"
                     + " where idRegistroVenta=?";
            
            ps = conexion.prepareStatement(q);

           
           ps.setString(1, t.getFechaRegistro());
           ps.setInt(2,t.getIdPedido());
           ps.setString(3, t.getGuia());
           ps.setString(4, t.getTipoComprobante());
           ps.setString(5, t.getSerie());
           ps.setString(6, t.getNumero());
           ps.setString(7, t.getFechaEmision());
           ps.setString(8, t.getFechaAprobacion());
           ps.setString(9, t.getFechaVencimiento());
           ps.setString(10, t.getTipo());
           ps.setString(11, t.getMoneda());
           ps.setString(12, t.getMontoFacturado());
           ps.setString(13, t.getDetraccion());
           ps.setString(14, t.getEstado());
           ps.setString(15, t.getObservacion());
           ps.setInt(16, t.getIdRegistroVenta());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(RegistroVenta t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from registroventa where idRegistroVenta=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdRegistroVenta());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public RegistroVenta findForId(Object t) throws Exception {
        reVe=null; 
        try {

            q = "select idRegistroVenta,  fechaRegistro,"
                    + "idPedido_fkk, guia, tipoComprobante, serie, numero, fechaEmision, fechaAprobacion,\n"
                    + "fechaVencimiento,tipo ,moneda, montoFacturado, detraccion, \n"
                    + "estado,observacion from registroventa where idRegistroVenta=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1), rs.getString(2), rs.getInt(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), 
                            rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return reVe;
    }
    
    public RegistroVenta findForFactura(Object t, Object x) throws Exception {
        reVe=null; 
        try {

            q = "select idRegistroVenta,  fechaRegistro,"
                    + "idPedido_fkk, guia, tipoComprobante, serie, numero, fechaEmision, fechaAprobacion,\n"
                    + "fechaVencimiento, tipo, moneda, montoFacturado, detraccion, \n"
                    + "estado,observacion from registroventa where serie=? and numero=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1), rs.getString(2), rs.getInt(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), 
                            rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return reVe;
    }
    
   
     public RegistroVenta findForIdRegistroVenta(Object t) throws Exception {
        reVe=null; 
        try {

            q = "SELECT \n"
                    + " rv.idRegistroVenta, \n"
                    + " rv.fechaRegistro,\n"
                    + " c.razonSocial,\n"
                    + " c.ruc,\n"
                    + " CONCAT(cl.nombres, ' ', cl.apellidos) Contacto,\n"
                    + " d.idPedido,\n"
                    + " d.numPedido,\n"
                    + " p.idProforma,\n"
                    + " p.codProforma,\n"
                    + " rv.guia,\n"
                    + " rv.tipoComprobante,\n"
                    + " rv.serie,\n"
                    + " rv.numero,\n"
                    + " rv.tipo,\n"
                    + " rv.moneda,\n"
                    + " p.formaPago,\n"
                    + " rv.fechaEmision,\n"
                    + " rv.fechaAprobacion,\n"
                    + " rv.fechaVencimiento,\n"
                    + " rv.montoFacturado,\n"
                    + " rv.detraccion,\n"
                    + " rv.estado,\n"
                    + " rv.observacion\n"
                    + "FROM\n"
                    + " pedido d LEFT JOIN guiaremision g ON d.numPedido = g.numPedido\n"
                    + "          LEFT JOIN proforma p ON d.idProforma_fk = p.idProforma\n"
                    + "          LEFT JOIN detalleproforma dp ON d.idProforma_fk = dp.idProforma_fk,\n"
                    + " cliente c,\n"
                    + " contactocliente cl,\n"
                    + " registroventa rv\n"
                    + "WHERE\n"
                    + "    (d.idClientefk= c.idCliente\n"
                    + " AND d.fk_idContactoCliente = cl.idContactoCliente\n"
                    + " AND rv.idPedido_fkk = d.idPedido)\n"
                    + " AND rv.idRegistroVenta = ?\n"
                    + "GROUP BY rv.idRegistroVenta";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),     rs.getString(2),  rs.getString(3),  rs.getString(4),  
                                         rs.getString(5),  rs.getInt(6),     rs.getString(7),  rs.getInt(8),     
                                         rs.getString(9),  rs.getString(10), rs.getString(11), rs.getString(12), 
                                         rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), 
                                         rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), 
                                         rs.getString(21), rs.getString(22), rs.getString(23));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return reVe;
    }
    
    
    @Override
    public List<RegistroVenta> readAll() throws Exception {
        List<RegistroVenta> registro= new ArrayList<>();
        
        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "SELECT * FROM registroventa;";
            rs = s.executeQuery(q);

            while (rs.next()) {
                reVe = new RegistroVenta(rs.getInt(1), rs.getString(2), rs.getInt(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), 
                            rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                registro.add(reVe);
            }
            s.close();
            rs.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
            }
        }
        return registro;
    }

    public List<RegistroVenta> findForLike(String filtro,String t) throws Exception {
        List<RegistroVenta> lista = new ArrayList<>();
        try {

            q = "SELECT \n"
                    + "    rv.idRegistroVenta, \n"
                    + "    rv.fechaRegistro,\n"
                    + "    c.razonSocial,\n"
                    + "    c.ruc,\n"
                    + "    CONCAT(cl.nombres, ' ', cl.apellidos) Contacto,\n"
                    + "    d.idPedido,\n"
                    + "    d.numPedido,\n"
                    + "    p.idProforma,\n"
                    + "    p.codProforma,\n"
                    + "    rv.guia,\n"
                    + "    rv.tipoComprobante,\n"
                    + "    rv.serie,\n"
                    + "    rv.numero,\n"
                    + "    rv.tipo,\n"
                    + "    rv.moneda,\n"
                    + "    p.formaPago,\n"
                    + "    rv.fechaEmision,\n"
                    + "    rv.fechaAprobacion,\n"
                    + "    rv.fechaVencimiento,\n"
                    + "    rv.montoFacturado,\n"
                    + "    rv.detraccion,\n"
                    + "    rv.estado,\n"
                    + "    rv.observacion\n"
                    + "	\n"
                    + "FROM\n"
                    + "    pedido d   LEFT  JOIN guiaremision g     ON d.numPedido = g.numPedido\n"
                    + "               LEFT  JOIN proforma p         ON d.idProforma_fk = p.idProforma\n"
                    + "               LEFT  JOIN detalleproforma dp ON d.idProforma_fk = dp.idProforma_fk,\n"
                    + "    cliente c,\n"
                    + "    contactocliente cl,\n"
                    + "    registroventa rv left outer join registropago rp on rv.idRegistroVenta=rp.idRegistroVenta_fk \n"
                    + "WHERE (     d.idClientefk= c.idCliente\n"
                    + "        AND d.fk_idContactoCliente = cl.idContactoCliente\n"
                    + "        AND rv.idPedido_fkk = d.idPedido )\n"
                    + "        and "+ filtro +" like ? \n"
                    + "GROUP BY rv.idRegistroVenta\n"
                    + "order by field (rv.estado, 'EN PROCESO') desc, rv.idRegistroVenta desc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            reVe = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),     rs.getString(2),  rs.getString(3),  rs.getString(4),  
                                         rs.getString(5),  rs.getInt(6),     rs.getString(7),  rs.getInt(8),     
                                         rs.getString(9),  rs.getString(10), rs.getString(11), rs.getString(12), 
                                         rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), 
                                         rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), 
                                         rs.getString(21), rs.getString(22), rs.getString(23));
                lista.add(reVe);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return lista;
    }
    
    
    public List<RegistroVenta> findForLikeOrden() throws Exception {
        List<RegistroVenta> lista = new ArrayList<>();
        try {

            q = "SELECT  rv.idRegistroVenta, \n"
                    + "  rv.fechaRegistro,\n"
                    + "  c.razonSocial,\n"
                    + "  c.ruc,\n"
                    + "  CONCAT(cl.nombres, ' ', cl.apellidos) Contacto,\n"
                    + "  d.idPedido,\n"
                    + "  d.numPedido,\n"
                    + "  p.idProforma,\n"
                    + "  p.codProforma,\n"
                    + "  rv.guia,\n"
                    + "  rv.tipoComprobante,\n"
                    + "  rv.serie,\n"
                    + "  rv.numero,\n"
                    + "  rv.tipo,\n"
                    + "  rv.moneda,\n"
                    + "  p.formaPago,\n"
                    + "  rv.fechaEmision,\n"
                    + "  rv.fechaAprobacion,\n"
                    + "  rv.fechaVencimiento,\n"
                    + "  rv.montoFacturado,\n"
                    + "  rv.detraccion,\n"
                    + "  rv.estado,\n"
                    + "  rv.observacion\n"
                    + "   \n"
                    + "  FROM\n"
                    + "  pedido d   LEFT  JOIN guiaremision g     ON d.numPedido = g.numPedido\n"
                    + "             LEFT  JOIN proforma p         ON d.idProforma_fk = p.idProforma\n"
                    + "             LEFT  JOIN detalleproforma dp ON d.idProforma_fk = dp.idProforma_fk,\n"
                    + "  cliente c,\n"
                    + "  contactocliente cl,\n"
                    + "  registroventa rv left outer join registropago rp on rv.idRegistroVenta=rp.idRegistroVenta_fk \n"
                    + "  WHERE (d.idClientefk= c.idCliente\n"
                    + "     AND d.fk_idContactoCliente = cl.idContactoCliente\n"
                    + "     AND rv.idPedido_fkk = d.idPedido )\n"
                    + "     and c.razonSocial like '%%'\n"
                    + "  GROUP BY rv.idRegistroVenta\n"
                    + "  order by field (rv.estado, 'EN PROCESO') desc,\n"
                    + "	 case when rv.estado='EN PROCESO' THEN rv.fechaVencimiento end asc,\n"
                    + "	 case when rv.estado<>'EN PROCESO' THEN rv.idRegistroVenta end desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
             //ejecutar consulta
            rs = ps.executeQuery();
            reVe = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),     rs.getString(2),  rs.getString(3),  rs.getString(4),  
                                         rs.getString(5),  rs.getInt(6),     rs.getString(7),  rs.getInt(8),     
                                         rs.getString(9),  rs.getString(10), rs.getString(11), rs.getString(12), 
                                         rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), 
                                         rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), 
                                         rs.getString(21), rs.getString(22), rs.getString(23));
                lista.add(reVe);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return lista;
    }
    
    public List<RegistroVenta> findForExport(String d,String h) throws Exception {
        List<RegistroVenta> lista = new ArrayList<>();
        try {

            q = "SELECT \n"
                    + "    rv.idRegistroVenta, \n"
                    + "    rv.fechaRegistro,\n"
                    + "    c.razonSocial,\n"
                    + "    c.ruc,\n"
                    + "    CONCAT(cl.nombres, ' ', cl.apellidos) Contacto,\n"
                    + "    d.idPedido,\n"
                    + "    d.numPedido,\n"
                    + "    p.idProforma,\n"
                    + "    p.codProforma,\n"
                    + "    rv.guia,\n"
                    + "    rv.tipoComprobante,\n"
                    + "    rv.serie,\n"
                    + "    rv.numero,\n"
                    + "    rv.tipo,\n"
                    + "    rv.moneda,\n"
                    + "    p.formaPago,\n"
                    + "    rv.fechaEmision,\n"
                    + "    rv.fechaAprobacion,\n"
                    + "    rv.fechaVencimiento,\n"
                    + "    rv.montoFacturado,\n"
                    + "    rv.detraccion,\n"
                    + "    rv.estado,\n"
                    + "    rv.observacion\n"
                    + "	\n"
                    + "FROM\n"
                    + "    pedido d   LEFT  JOIN guiaremision g     ON d.numPedido = g.numPedido\n"
                    + "               LEFT  JOIN proforma p         ON d.idProforma_fk = p.idProforma\n"
                    + "               LEFT  JOIN detalleproforma dp ON d.idProforma_fk = dp.idProforma_fk,\n"
                    + "    cliente c,\n"
                    + "    contactocliente cl,\n"
                    + "    registroventa rv left outer join registropago rp on rv.idRegistroVenta=rp.idRegistroVenta_fk \n"
                    + "WHERE (     d.idClientefk= c.idCliente\n"
                    + "        AND d.fk_idContactoCliente = cl.idContactoCliente\n"
                    + "        AND rv.idPedido_fkk = d.idPedido )\n"
                    + "        AND rv.fechaEmision between ? and ? \n"
                    + "	   GROUP BY rv.idRegistroVenta\n"
                    + "    order by rv.idRegistroVenta desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,d+"");
            ps.setString(2, h+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            reVe = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),     rs.getString(2),  rs.getString(3),  rs.getString(4),  
                                         rs.getString(5),  rs.getInt(6),     rs.getString(7),  rs.getInt(8),     
                                         rs.getString(9),  rs.getString(10), rs.getString(11), rs.getString(12), 
                                         rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), 
                                         rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), 
                                         rs.getString(21), rs.getString(22), rs.getString(23));
                lista.add(reVe);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return lista;
    }
    
    public List<RegistroVenta> findForLikeIdPagoNull(String filtro,String t) throws Exception {
        List<RegistroVenta> lista = new ArrayList<>();
        try {

            q = "SELECT \n"
                    + "    rv.idRegistroVenta, \n"
                    + "    rv.fechaRegistro,\n"
                    + "    c.razonSocial,\n"
                    + "    c.ruc,\n"
                    + "    CONCAT(cl.nombres, ' ', cl.apellidos) Contacto,\n"
                    + "    d.idPedido,\n"
                    + "    d.numPedido,\n"
                    + "    p.idProforma,\n"
                    + "    p.codProforma,\n"
                    + "    rv.guia,\n"
                    + "    rv.tipoComprobante,\n"
                    + "    rv.serie,\n"
                    + "    rv.numero,\n"
                    + "    rv.tipo,\n"
                    + "    rv.moneda,\n"
                    + "    p.formaPago,\n"
                    + "    rv.fechaEmision,\n"
                    + "    rv.fechaAprobacion,\n"
                    + "    rv.fechaVencimiento,\n"
                    + "    rv.montoFacturado,\n"
                    + "    rv.detraccion,\n"
                    + "    rv.estado,\n"
                    + "    rv.observacion\n"
                    + "	\n"
                    + "FROM\n"
                    + "    pedido d   LEFT  JOIN guiaremision g      ON d.numPedido = g.numPedido\n"
                    + "               LEFT  JOIN proforma p          ON d.idProforma_fk = p.idProforma\n"
                    + "               LEFT  JOIN detalleproforma dp  ON d.idProforma_fk = dp.idProforma_fk,\n"
                    + "    cliente c,\n"
                    + "    contactocliente cl,\n"
                    + "    registroventa rv left outer join registropago rp on rv.idRegistroVenta=rp.idRegistroVenta_fk \n"
                    + "WHERE (     d.idClientefk= c.idCliente\n"
                    + "        AND d.fk_idContactoCliente = cl.idContactoCliente\n"
                    + "        AND rv.idPedido_fkk = d.idPedido ) "
                    + "        AND rp.idRegistroPago is null\n"
                    + "        AND " + filtro + " like ? \n"
                    + "	   GROUP BY rv.idRegistroVenta\n"
                    + "    ORDER BY rv.idRegistroVenta desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            reVe = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),     rs.getString(2),  rs.getString(3),  rs.getString(4),  
                                         rs.getString(5),  rs.getInt(6),     rs.getString(7),  rs.getInt(8),     
                                         rs.getString(9),  rs.getString(10), rs.getString(11), rs.getString(12), 
                                         rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), 
                                         rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), 
                                         rs.getString(21), rs.getString(22), rs.getString(23));
                lista.add(reVe);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return lista;
    }
    
    public List<RegistroVenta> findCuentasPorCobrar(String filtro,String t) throws Exception {
        List<RegistroVenta> lista = new ArrayList<>();
        try {

            q = "select rv.idRegistroVenta, \n"
                    + "	c.razonSocial, \n"
                    + "	c.ruc,\n"
                    + " c.idCliente,\n"
                    + "	rv.idPedido_fkk, \n"
                    + " p.numPedido ,\n"
                    + " prof.codProforma,\n"
                    + " rv.tipoComprobante,\n"
                    + " concat(rv.serie,'-', rv.numero) numComprobante, \n"
                    + " rv.fechaEmision, \n"
                    + " t.fechaCambio,\n"
                    + " t.cambioVenta,\n"
                    + "	rv.fechaVencimiento, \n"
                    + " rv.tipo, \n"
                    + " rv.moneda, \n"
                    + " rv.montoFacturado, \n"
                    + " rv.detraccion,\n"
                    + " rv.observacion,\n"
                    + " if(rv.moneda='SOLES', if(round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3)),if(round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3))) montoAbonado, \n"
                    + " round( rv.montoFacturado-if(rv.moneda='SOLES', if(round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3)),if(round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3))),3) deuda\n"
                    + "\n"
                    + "from registroventa rv LEFT JOIN  registropago rp on rv.idRegistroVenta=rp.idRegistroVenta_fk\n"
                    + "		             inner join tipodecambio t  on rv.fechaEmision=t.fechaCambio,\n"
                    + "	 pedido p,\n"
                    + "  proforma prof,\n"
                    + "  cliente c\n"
                    + " where (rv.idPedido_fkk=p.idPedido "
                    + "    and p.idClientefk=c.idCliente "
                    + "    and p.idProforma_fk=prof.idProforma)\n"
                    + " and "+filtro+" like ? "
                    + " and rv.estado='EN PROCESO' "
                   // + " and rv.fechaVencimiento <= curdate()\n"
                    + " group by rv.idRegistroVenta"
                    + " order by rv.fechaVencimiento asc";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,t);
            //ejecutar consulta
            rs = ps.executeQuery();
            reVe = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),      rs.getString(2) ,  rs.getString(3),
                                         rs.getInt(4),      rs.getInt(5),      rs.getString(6) ,
                                         rs.getString(7) ,  rs.getString(8) ,  rs.getString(9) ,
                                         rs.getString(10) , rs.getString(11) , rs.getString(12) ,
                                         rs.getString(13) , rs.getString(14) , rs.getString(15),
                                         rs.getString(16) , rs.getString(17) , rs.getString(18) ,
                                         rs.getString(19) , rs.getString(20));
                lista.add(reVe);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return lista;
    }
    
    public List<RegistroVenta> findCuentasPorCobrarVencidos(String filtro,String t) throws Exception {
        List<RegistroVenta> lista = new ArrayList<>();
        try {

            q = "select rv.idRegistroVenta, \n"
                    + "	c.razonSocial, \n"
                    + "	c.ruc,\n"
                    + " c.idCliente,\n"
                    + "	rv.idPedido_fkk, \n"
                    + " p.numPedido ,\n"
                    + " prof.codProforma,\n"
                    + " rv.tipoComprobante,\n"
                    + " concat(rv.serie,'-', rv.numero) numComprobante, \n"
                    + " rv.fechaEmision, \n"
                    + " t.fechaCambio,\n"
                    + " t.cambioVenta,\n"
                    + "	rv.fechaVencimiento, \n"
                    + " rv.tipo, \n"
                    + " rv.moneda, \n"
                    + " rv.montoFacturado, \n"
                    + " rv.detraccion,\n"
                    + " rv.observacion,\n"
                    + " if(rv.moneda='SOLES', if(round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3)),if(round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3))) montoAbonado, \n"
                    + " round( rv.montoFacturado-if(rv.moneda='SOLES', if(round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3)),if(round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3))),3) deuda\n"
                    + "\n"
                    + "from registroventa rv LEFT JOIN  registropago rp on rv.idRegistroVenta=rp.idRegistroVenta_fk\n"
                    + "		             inner join tipodecambio t  on rv.fechaEmision=t.fechaCambio,\n"
                    + "	 pedido p,\n"
                    + "  proforma prof,\n"
                    + "  cliente c\n"
                    + " where (rv.idPedido_fkk=p.idPedido "
                    + "    and p.idClientefk=c.idCliente "
                    + "    and p.idProforma_fk=prof.idProforma)\n"
                    + " and "+filtro+" like ? "
                    + " and rv.estado='EN PROCESO' "
                    + " and rv.fechaVencimiento <= curdate()\n"
                    + " group by rv.idRegistroVenta";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,t);
            //ejecutar consulta
            rs = ps.executeQuery();
            reVe = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reVe = new RegistroVenta(rs.getInt(1),      rs.getString(2) ,  rs.getString(3),
                                         rs.getInt(4),      rs.getInt(5),      rs.getString(6) ,
                                         rs.getString(7) ,  rs.getString(8) ,  rs.getString(9) ,
                                         rs.getString(10) , rs.getString(11) , rs.getString(12) ,
                                         rs.getString(13) , rs.getString(14) , rs.getString(15),
                                         rs.getString(16) , rs.getString(17) , rs.getString(18) ,
                                         rs.getString(19) , rs.getString(20));
                lista.add(reVe);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return lista;
    }
    
}
