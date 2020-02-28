/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.GuiaDeRemision;
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
 * @author ARCRODINPC-06
 */
public class GuiaDeRemisionDao implements ICrudDao<GuiaDeRemision> {
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;

    GuiaDeRemision guia;
    String q;
    

    @Override
    public void create(GuiaDeRemision t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            q = "Insert into guiaremision( serieGuia, numGuia, fechaEmision, fechaTraslado, \n"
                    + "direccionPartida, direccionLlegada, fkidCliente, tipoDocCli,fkidmotivoGuia, motivoDescripcion, vehMarca,  \n"
                    + "vehCertificado, vehLicencia, nombreTransp, rucTransp, tipoComprobante, numComprobante, estado,"
                    + "observacion, numPedido) \n"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getSerieGuia());
            ps.setString(2, t.getNumGuia());
            ps.setString(3, t.getFechEmi());
            ps.setString(4, t.getFechaTraslado());
            ps.setString(5, t.getDirecPart());
            ps.setString(6, t.getDirecLleg());
            ps.setInt(7, t.getIdCliente());
            ps.setString(8, t.getTipoDocCli());
            ps.setInt(9, t.getIdMotivoGuia());
            ps.setString(10, t.getMotivoDescripcion());
            ps.setString(11, t.getVehMarca());
            ps.setString(12, t.getVehCertif());
            ps.setString(13, t.getVehLic());
            ps.setString(14, t.getNomTransp());
            ps.setString(15, t.getRucTransp());
            ps.setString(16, t.getTipoComprobante());
            ps.setString(17, t.getNumComprobante());
            ps.setString(18, t.getEstado());
            ps.setString(19, t.getObservacion());
            ps.setString(20, t.getNumPedi());

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
    public void update(GuiaDeRemision t) throws Exception {
          try {
            conexion = AccesoDB.obtener();
            q = "update guiaremision set  serieGuia=?, numGuia=?, fechaEmision=?, fechaTraslado=?, \n"
                    + "direccionPartida=?, direccionLlegada=?, fkidCliente=?, tipoDocCli=?,"
                    + "fkidmotivoGuia=?,motivoDescripcion=?, \n"
                    + "vehMarca=?, vehCertificado=?, vehLicencia=?, nombreTransp=?, rucTransp=?, \n"
                    + "tipoComprobante=?, numComprobante=?, estado=?, observacion=?, numPedido=? \n"
                    + "where idGuiaRemision=?";

            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getSerieGuia());
            ps.setString(2, t.getNumGuia());
            ps.setString(3, t.getFechEmi());
            ps.setString(4, t.getFechaTraslado());
            ps.setString(5, t.getDirecPart());
            ps.setString(6, t.getDirecLleg());
            ps.setInt(7, t.getIdCliente());
            ps.setString(8, t.getTipoDocCli());
            ps.setInt(9, t.getIdMotivoGuia());
            ps.setString(10, t.getMotivoDescripcion());
            ps.setString(11, t.getVehMarca());
            ps.setString(12, t.getVehCertif());
            ps.setString(13, t.getVehLic());
            ps.setString(14, t.getNomTransp());
            ps.setString(15, t.getRucTransp());
            ps.setString(16, t.getTipoComprobante());
            ps.setString(17, t.getNumComprobante());
            ps.setString(18, t.getEstado());
            ps.setString(19, t.getObservacion());
            ps.setString(20, t.getNumPedi());
            ps.setInt(21, t.getIdGuiaRemi());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(GuiaDeRemision t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from guiaremision where idGuiaRemision=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdGuiaRemi());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public GuiaDeRemision findForId(Object t) throws Exception {
        guia = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "Select idGuiaRemision, serieGuia, numGuia, fechaEmision, fechaTraslado, \n"
                    + "direccionPartida, direccionLlegada, fkidCliente, tipoDocCli, fkidmotivoGuia, motivoDescripcion, vehMarca,  \n"
                    + "vehCertificado, vehLicencia, nombreTransp, rucTransp, tipoComprobante, numComprobante,"
                    + "estado, observacion, numPedido \n"
                    + "from guiaremision\n"
                    + "where idGuiaRemision=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1,(int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                guia = new GuiaDeRemision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getInt(10),rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)
                , rs.getString(20), rs.getString(21));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return guia;
    }
    
     public GuiaDeRemision findForId(Object t, Object x, Object y) throws Exception {
        guia = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "Select idGuiaRemision, serieGuia, numGuia, fechaEmision, fechaTraslado, \n"
                    + "direccionPartida, direccionLlegada, fkidCliente, tipoDocCli, fkidmotivoGuia, motivoDescripcion, vehMarca,  \n"
                    + "vehCertificado, vehLicencia, nombreTransp, rucTransp, tipoComprobante, numComprobante, estado,"
                    + " observacion,numPedido  \n"
                    + "from guiaremision\n"
                    + "where serieGuia=? and numGuia=? and year(fechaEmision)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            ps.setString(2, x+"");
            ps.setInt(3, (int)y);
            
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                guia = new GuiaDeRemision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9),
                        rs.getInt(10),rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14),
                        rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19),
                        rs.getString(20), rs.getString(21));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return guia;
    }
     
    public GuiaDeRemision findForAnio(Object t, Object x, Object y) throws Exception {
        guia = null;
        try {
            q = "select  idGuiaRemision, year(fechaEmision) from guiaremision where fechaEmision=? and serieGuia=? and numGuia=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,  t+"");
            ps.setString(2, x+"");
            ps.setString(3, y+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                guia = new GuiaDeRemision(rs.getInt(1),rs.getString(2));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return guia;
    }

    @Override
    public List<GuiaDeRemision> readAll() throws Exception {
        List<GuiaDeRemision> guiaRemi = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from guiaremision";
            rs = s.executeQuery(q);

            while (rs.next()) {
                guia = new GuiaDeRemision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17),
                        rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21));
                guiaRemi.add(guia);
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
        return guiaRemi;
    }
    
     public List<GuiaDeRemision> readAllVista() throws Exception {
        List<GuiaDeRemision> guiaRemi = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "SELECT g.serieGuia,g.numGuia,c.razonSocial, g.tipoComprobante, g.numComprobante, g.fechaEmision\n"
                    + "FROM guiaremision g, cliente c \n"
                    + "WHERE g.fkidCliente=c.idCliente order by idGuiaRemision desc";
            rs = s.executeQuery(q);

            while (rs.next()) {
                guia = new GuiaDeRemision(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)
                                          , rs.getString(7), rs.getString(8));
                guiaRemi.add(guia);
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
        return guiaRemi;
    }
     
     public List<GuiaDeRemision> findForLike(String filtro,String t) throws Exception {
        List<GuiaDeRemision> lista = new ArrayList<>();
        try {

            q = "SELECT g.serieGuia,g.numGuia,c.razonSocial, g.tipoComprobante, g.numComprobante, g.fechaEmision, "
                    + "g.fechaTraslado, g.estado, g.observacion \n"
                    + "FROM guiaremision g, cliente c\n"
                    + "WHERE g.fkidCliente=c.idCliente \n"
                    + "and "+ filtro +" like ? order by g.fechaEmision desc, g.numGuia desc,idGuiaRemision desc ";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            guia = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                guia = new GuiaDeRemision(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                                          rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                                          rs.getString(9));
                lista.add(guia);
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

     
     public GuiaDeRemision findLastestId( Object x) throws Exception {
        guia = null;
        try {

            q = "select  max(numGuia) as numGuia from guiaremision "
                    + "where serieGuia=? ";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            
            ps.setString(1, x+"");
            
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                guia = new GuiaDeRemision(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return guia;
    }

}
