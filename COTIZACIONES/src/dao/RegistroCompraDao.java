/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.RegistroCompra;
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
public class RegistroCompraDao implements ICrudDao <RegistroCompra>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    RegistroCompra reCo;
    String q;

    @Override
    public void create(RegistroCompra t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into registrocompra(fechaRegistro,"
                    + " idProveedor_fk, numOrdenCompra, idComprobanteSunat_fk, serie, numero, "
                    + "fechaEmision, fechaAprobacion,fechaVencimiento, idGlosa_fk, "
                    + "moneda, montoFacturado, detraccion, estado, observacion) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, t.getFechaRegistro());
            ps.setInt(2, t.getIdProveedor_fk());
            ps.setString(3, t.getNumOrdenCompra());
            ps.setInt(4, t.getIdComprobanteSunat_fk());
            ps.setString(5, t.getSerie());
            ps.setString(6, t.getNumero());
            ps.setString(7, t.getFechaEmision());
            ps.setString(8, t.getFechaAprobacion());
            ps.setString(9, t.getFechaVencimiento());
            ps.setInt(10, t.getIdGlosa_fk());
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
    public void update(RegistroCompra t) throws Exception {
        
        try {
            conexion = AccesoDB.obtener();
             q = "update registrocompra set fechaRegistro=?,"
                    + " idProveedor_fk=?, numOrdenCompra=?, idComprobanteSunat_fk=?, serie=?, numero=?, "
                    + "fechaEmision=?, fechaAprobacion=?, fechaVencimiento=?, idGlosa_fk=?, "
                    + "moneda=?, montoFacturado=?, detraccion=?, estado=?, observacion=?"
                     + " where idRegistroCompra=?";
            
            ps = conexion.prepareStatement(q);

           
            ps.setString(1, t.getFechaRegistro());
            ps.setInt(2, t.getIdProveedor_fk());
            ps.setString(3, t.getNumOrdenCompra());
            ps.setInt(4, t.getIdComprobanteSunat_fk());
            ps.setString(5, t.getSerie());
            ps.setString(6, t.getNumero());
            ps.setString(7, t.getFechaEmision());
            ps.setString(8, t.getFechaAprobacion());
            ps.setString(9, t.getFechaVencimiento());
            ps.setInt(10, t.getIdGlosa_fk());
            ps.setString(11, t.getMoneda());
            ps.setString(12, t.getMontoFacturado());
            ps.setString(13, t.getDetraccion());
            ps.setString(14, t.getEstado());
            ps.setString(15, t.getObservacion());
            ps.setInt(16, t.getIdRegistroCompra());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(RegistroCompra t) throws Exception {
        
        try {
            conexion = AccesoDB.obtener();
            q = "delete from registrocompra where idRegistroCompra=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdRegistroCompra());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public RegistroCompra findForId(Object t) throws Exception {
        reCo = null;
        try {

            q = "select idRegistroCompra, fechaRegistro,"
                    + " idProveedor_fk, numOrdenCompra, idComprobanteSunat_fk, serie, numero, "
                    + "fechaEmision, fechaAprobacion, fechaVencimiento, idGlosa_fk, "
                    + "moneda, montoFacturado, detraccion, estado, observacion "
                    + "from registrocompra where idRegistroCompra=?";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                reCo = new RegistroCompra(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return reCo;
    }
    
    public RegistroCompra findForId(String t, String x) throws Exception {
        reCo = null;
        try {

            q = "select idRegistroCompra, fechaRegistro,"
                    + " idProveedor_fk, numOrdenCompra, idComprobanteSunat_fk, serie, numero, "
                    + "fechaEmision, fechaAprobacion, fechaVencimiento, idGlosa_fk, "
                    + "moneda, montoFacturado, detraccion, estado, observacion "
                    + "from registrocompra where serie=? and numero=?";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t);
            ps.setString(2, x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                reCo = new RegistroCompra(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return reCo;
    }

    @Override
    public List<RegistroCompra> readAll() throws Exception {

        List<RegistroCompra> registro = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "SELECT * FROM registroventa;";
            rs = s.executeQuery(q);

            while (rs.next()) {
                reCo = new RegistroCompra(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                registro.add(reCo);
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
    
     public List<RegistroCompra> findForLike(String filtro, String t) throws Exception {
        List<RegistroCompra> lista = new ArrayList<>();
        try {

            q = "select \n"
                    + "rc.idRegistroCompra,\n"
                    + "rc.fechaEmision,\n"
                    + "rc.idProveedor_fk,\n"
                    + "p.razonSocial,\n"
                    + "p.ruc,\n"
                    + "rc.idComprobanteSunat_fk,\n"
                    + "cs.descripcion,\n"
                    + "cs.codigo,\n"
                    + "rc.serie,\n"
                    + "rc.numero,\n"
                    + "rc.idGlosa_fk,\n"
                    + "g.concepto,\n"
                    + "g.codigo,\n"
                    + "rc.moneda,\n"
                    + "rc.montoFacturado,\n"
                    + "rc.detraccion,\n"
                    + "rc.fechaVencimiento,\n"
                    + "rc.estado,\n"
                    + "rc.observacion\n"
                    + "\n"
                    + "from registrocompra rc  left join proveedor p         on rc.idProveedor_fk=p.idProveedor \n"
                    + "			       left join comprobantesunat cs on rc.idComprobanteSunat_fk= cs.idComprobanteSunat\n"
                    + "                        left join glosa g             on rc.idGlosa_fk= g.idGlosa\n"
                    + "where "+ filtro +" like ? \n"
                    + "GROUP BY rc.idRegistroCompra\n"
                    + "order by field (rc.estado, 'EN PROCESO') desc,  rc.idRegistroCompra desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%" + t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            reCo = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reCo = new RegistroCompra(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19));
                lista.add(reCo);
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
    
     public List<RegistroCompra> findForOrden() throws Exception {
        List<RegistroCompra> lista = new ArrayList<>();
        try {

            q = "select \n"
                    + "rc.idRegistroCompra,\n"
                    + "rc.fechaEmision,\n"
                    + "rc.idProveedor_fk,\n"
                    + "p.razonSocial,\n"
                    + "p.ruc,\n"
                    + "rc.idComprobanteSunat_fk,\n"
                    + "cs.descripcion,\n"
                    + "cs.codigo,\n"
                    + "rc.serie,\n"
                    + "rc.numero,\n"
                    + "rc.idGlosa_fk,\n"
                    + "g.concepto,\n"
                    + "g.codigo,\n"
                    + "rc.moneda,\n"
                    + "rc.montoFacturado,\n"
                    + "rc.detraccion,\n"
                    + "rc.fechaVencimiento,\n"
                    + "rc.estado,\n"
                    + "rc.observacion\n"
                    + "\n"
                    + "from registrocompra rc  left join proveedor p         on rc.idProveedor_fk=p.idProveedor \n"
                    + "			       left join comprobantesunat cs on rc.idComprobanteSunat_fk= cs.idComprobanteSunat\n"
                    + "                        left join glosa g             on rc.idGlosa_fk= g.idGlosa\n"
                    + "GROUP BY rc.idRegistroCompra\n"
                    + "order by field (rc.estado, 'EN PROCESO') desc,  \n"
                    + "case when rc.estado='EN PROCESO' THEN rc.fechaVencimiento end asc, rc.idRegistroCompra desc,\n"
                    + "case when rc.estado<>'EN PROCESO' THEN rc.idRegistroCompra end desc, rc.fechaRegistro desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //ejecutar consulta
            rs = ps.executeQuery();
            reCo = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                reCo = new RegistroCompra(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19));
                lista.add(reCo);
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
     
     public List<RegistroCompra> findForExport(String d,String h) throws Exception {
        List<RegistroCompra> lista = new ArrayList<>();
        try {

            q = "select \n"
                    + "rc.idRegistroCompra,\n"
                    + "rc.fechaEmision,\n"
                    + "rc.idProveedor_fk,\n"
                    + "p.razonSocial,\n"
                    + "p.ruc,\n"
                    + "rc.idComprobanteSunat_fk,\n"
                    + "cs.descripcion,\n"
                    + "cs.codigo,\n"
                    + "rc.serie,\n"
                    + "rc.numero,\n"
                    + "rc.idGlosa_fk,\n"
                    + "g.concepto,\n"
                    + "g.codigo,\n"
                    + "rc.moneda,\n"
                    + "rc.montoFacturado,\n"
                    + "rc.detraccion,\n"
                    + "rc.fechaVencimiento,\n"
                    + "rc.estado,\n"
                    + "rc.observacion\n"
                    + "\n"
                    + "from registrocompra rc  left join proveedor p         on rc.idProveedor_fk=p.idProveedor \n"
                    + "			       left join comprobantesunat cs on rc.idComprobanteSunat_fk= cs.idComprobanteSunat\n"
                    + "                        left join glosa g             on rc.idGlosa_fk= g.idGlosa\n"
                    + "where rc.fechaEmision between ? and ? \n"
                    + "GROUP BY rc.idRegistroCompra\n"
                    + "order by field (rc.estado, 'EN PROCESO') desc,  rc.idRegistroCompra desc;";
                    
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,d+"");
            ps.setString(2, h+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            reCo = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
               reCo = new RegistroCompra(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19));
               
                lista.add(reCo);
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
