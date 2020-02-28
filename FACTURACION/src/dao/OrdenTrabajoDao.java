/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.OrdenTrabajo;
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
public class OrdenTrabajoDao implements ICrudDao<OrdenTrabajo>{

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    OrdenTrabajo oti;
    String q;
    
    
    @Override
    public void create(OrdenTrabajo t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into ordendetrabajo(idPedidofkk,idCotizacion, numOrdenTrabajo,tipoServicio,recepcion, \n"
                    + " destino, fechaEmision, fechaInicio,fechaFinal,fechaEntrega, estado, observacion, medidasIngreso, medidasFinaliza) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, t.getIdPedido());
            ps.setInt(2, t.getIdProforma());
            ps.setString(3, t.getNumOrdenTrabajo());
            ps.setString(4, t.getTipoServicio());
            ps.setString(5, t.getRecepcion());
            ps.setString(6, t.getDestino());
            ps.setString(7, t.getFechaEmision());
            ps.setString(8, t.getFechaInicio());
            ps.setString(9, t.getFechaFin());
            ps.setString(10, t.getFechaEntrega());
            ps.setString(11, t.getEstado());
            ps.setString(12, t.getObservacion());
            ps.setString(13, t.getMedidaIngreso());
            ps.setString(14, t.getMedidaFinaliza());

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
    public void update(OrdenTrabajo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update ordendetrabajo set idPedidofkk=?,idCotizacion=?, numOrdenTrabajo=? ,tipoServicio=?,recepcion=?,"
                    + " destino=?, fechaEmision=?, fechaInicio=?,fechaFinal=?,fechaEntrega=?, estado=?, observacion=?,"
                    + " medidasIngreso=?, medidasFinaliza=?"
                    + " where idOrdenDeTrabajo=?";
            ps = conexion.prepareStatement(q);
            
           
            ps.setInt(1, t.getIdPedido());
            ps.setInt(2, t.getIdProforma());
            ps.setString(3, t.getNumOrdenTrabajo());
            ps.setString(4, t.getTipoServicio());
            ps.setString(5, t.getRecepcion());
            ps.setString(6, t.getDestino());
            ps.setString(7, t.getFechaEmision());
            ps.setString(8, t.getFechaInicio());
            ps.setString(9, t.getFechaFin());
            ps.setString(10, t.getFechaEntrega());
            ps.setString(11, t.getEstado());
            ps.setString(12, t.getObservacion());
            ps.setString(13, t.getMedidaIngreso());
            ps.setString(14, t.getMedidaFinaliza());
            ps.setInt(15, t.getIdOrdenTrabajo());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(OrdenTrabajo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from ordendetrabajo where idOrdenDeTrabajo=?";
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, t.getIdOrdenTrabajo());
                     
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public OrdenTrabajo findForId(Object t) throws Exception {
        oti = null;        
        try {
            
            q = "select idOrdenDeTrabajo, idPedidofkk, idCotizacion, numOrdenTrabajo, tipoServicio, recepcion, \n"
                    + " destino,  fechaEmision, fechaInicio, fechaFinal, fechaEntrega, estado, observacion, "
                    + "medidasIngreso, medidasFinaliza from ordendetrabajo where idOrdenDeTrabajo=? ";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();            
            if (rs.next()) {
                //crear objeto pro y asignar valores
                oti = new OrdenTrabajo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15));                
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return oti;
    }

    public OrdenTrabajo findForId(Object t, Object x) throws Exception {
        oti = null;
        try {
            
            q = "select idOrdenDeTrabajo, idPedidofkk, idCotizacion, numOrdenTrabajo, tipoServicio, recepcion, \n"
                    + " destino,  fechaEmision, fechaInicio, fechaFinal, fechaEntrega, estado, observacion, \n"
                    + " medidasIngreso, medidasFinaliza from ordendetrabajo where numOrdenTrabajo=? and year(fechaEmision)=?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                oti = new OrdenTrabajo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), 
                        rs.getString(15));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return oti;
    }
    
    
    @Override
    public List<OrdenTrabajo> readAll() throws Exception {
        List<OrdenTrabajo> ordentrabajo = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idOrdenDeTrabajo, idPedidofkk, idCotizacion, numOrdenTrabajo, tipoServicio, recepcion, \n"
                    + " destino, fechaEmision, fechaInicio, fechaFinal, fechaEntrega, estado, observacion, "
                    + "medidasIngreso, medidasFinaliza from ordendetrabajo";
            rs = s.executeQuery(q);

            while (rs.next()) {
                oti = new OrdenTrabajo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15));
                ordentrabajo.add(oti);
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
        return ordentrabajo;
    }
    
    public OrdenTrabajo findLastestId() throws Exception {
        oti = null;
        try {

            q = "select  max(idOrdenDeTrabajo) as idOrdenDeTrabajo from ordendetrabajo ";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                oti = new OrdenTrabajo(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return oti;
    }
    
    
     public List<OrdenTrabajo> findForLike(String filtro,String t) throws Exception {
        List<OrdenTrabajo> lista = new ArrayList<>();
        try {

            q = "SELECT o.idOrdenDeTrabajo, o.numOrdenTrabajo, c.razonSocial, p.numPedido, pr.codProforma, o.fechaEmision, o.fechaInicio,\n"
                    + " o.fechaFinal, o.fechaEntrega, o.estado, o.observacion\n"
                    + "FROM pedido p, cliente c, ordendetrabajo o, proforma pr \n"
                    + "WHERE (o.idPedidofkk=p.idPedido and p.idClientefk=c.idCliente and p.idProforma_fk= pr.idProforma) \n"
                    + "and " + filtro + " like ? "
                    + "order by field (o.estado, 'EN PROCESO') desc, o.numOrdenTrabajo desc \n";
//                    + " case when o.estado='EN PROCESO' THEN o.fechaEntrega end asc,\n"
//                    + " case when o.estado<>'EN PROCESO' THEN o.numOrdenTrabajo end desc";
// //                    + "order by field (o.estado,'EN PROCESO') desc, o.fechaEntrega asc, o.numOrdenTrabajo desc,"
////                    + "o.idOrdenDeTrabajo desc ";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            oti = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                oti = new OrdenTrabajo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                                          rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                          rs.getString(9), rs.getString(10), rs.getString(11));
                lista.add(oti);
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
     
     public List<OrdenTrabajo> listPorFecha() throws Exception {
        List<OrdenTrabajo> ordentrabajo = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
           q = "SELECT o.idOrdenDeTrabajo, o.numOrdenTrabajo, c.razonSocial, p.numPedido, pr.codProforma, o.fechaEmision, o.fechaInicio,\n"
                    + " o.fechaFinal, o.fechaEntrega, o.estado, o.observacion\n"
                    + "FROM pedido p, cliente c, ordendetrabajo o, proforma pr \n"
                    + "WHERE (o.idPedidofkk=p.idPedido and p.idClientefk=c.idCliente and p.idProforma_fk= pr.idProforma) \n"
                    + "order by field (o.estado, 'EN PROCESO') desc,\n"
                    + " case when o.estado='EN PROCESO' THEN o.fechaEntrega end asc,\n"
                    + " case when o.estado<>'EN PROCESO' THEN o.numOrdenTrabajo end desc";
            rs = s.executeQuery(q);

            while (rs.next()) {
                oti = new OrdenTrabajo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11));
                ordentrabajo.add(oti);
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
        return ordentrabajo;
    }
 
}
