/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.OrdenCorte;
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
public class OrdenCorteDao implements ICrudDao<OrdenCorte> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    OrdenCorte ordCort;
    String q;

    @Override
    public void create(OrdenCorte t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into ordencorte(idPedidofk, numOrdCort, fecha, motivo, estado, observacion) values(?,?,?,?,?,?)");

            ps.setInt(1, t.getIdPedido());
            ps.setString(2, t.getNumOrdCort());
            ps.setString(3, t.getFecha());
            ps.setString(4, t.getMotivo());
            ps.setString(5, t.getEstado());
            ps.setString(6, t.getObservacion());
           

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
    public void update(OrdenCorte t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update ordencorte set idPedidofk=?, numOrdCort=?, fecha=?, motivo=?, estado=?, observacion=? where idOrdenCorte=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdPedido());
            ps.setString(2, t.getNumOrdCort());
            ps.setString(3, t.getFecha());
            ps.setString(4, t.getMotivo());
            ps.setString(5, t.getEstado());
            ps.setString(6, t.getObservacion());
            ps.setInt(7, t.getIdOrdenCorte());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(OrdenCorte t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from ordencorte where idOrdenCorte=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdOrdenCorte());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public OrdenCorte findForId(Object t) throws Exception {
        ordCort = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idOrdenCorte, idPedidofk, numOrdCort, fecha, motivo, estado, observacion "
                    + "from ordencorte where numOrdCort=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, ( t+""));
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort = new OrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                         rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
              conexion.close();
        }
        return ordCort;
    }
    
     public OrdenCorte findForId(Object t, Object x) throws Exception {
        ordCort = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idOrdenCorte, idPedidofk, numOrdCort, fecha, motivo, estado, observacion from ordencorte where numOrdCort=? and year(fecha)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, ( t+""));
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort = new OrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                         rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
              conexion.close();
        }
        return ordCort;
    }
    
    public OrdenCorte findForIdInfo(Object t, Object x) throws Exception {
        ordCort = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idOrdenCorte, idPedidofk, numOrdCort, DATE_FORMAT(fecha, '%d/%m/%Y'),"
                    + " motivo, estado, observacion from ordencorte where numOrdCort=? and year(fecha)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, ( t+""));
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort = new OrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                         rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
              conexion.close();
        }
        return ordCort;
    }
    
     public OrdenCorte findForIdInfo(Object t) throws Exception {
        ordCort = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idOrdenCorte, idPedidofk, numOrdCort, DATE_FORMAT(fecha, '%d/%m/%Y'),"
                    + " motivo, estado, observacion from ordencorte where numOrdCort=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, ( t+""));
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort = new OrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                         rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
              conexion.close();
        }
        return ordCort;
    }
    public OrdenCorte findForAnio(Object t, Object x) throws Exception {
        ordCort = null;
        try {
            q = "select numOrdCort, year(fecha) from ordencorte where fecha=? and numOrdCort=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,  t+"");
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                ordCort= new OrdenCorte(rs.getString(1), rs.getString(2));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordCort;
    }
    
    
    public List<OrdenCorte> findForLike(String filtro, String t) throws Exception {
        List<OrdenCorte> lista = new ArrayList<>();
        try {

            q = "select O.numOrdCort,C.razonSocial,P.numPedido, O.fecha, O.estado, O.observacion\n" +
                "from pedido P, cliente C, ordencorte O\n" +
                "where (P.idPedido=O.idPedidofk and P.idClientefk=C.idCliente)\n" +
                "and "+filtro+" like ? order by numOrdcort desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            ordCort = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort = new OrdenCorte(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
                lista.add(ordCort);
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

    public OrdenCorte findLastestId(Object t) throws Exception {
        ordCort = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select max(idOrdenCorte) as idOrdenCorte from OrdenCorte where year(fecha)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int)t);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort= new OrdenCorte(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordCort;
    }
    
    public OrdenCorte findForNum(Object t) throws Exception {
        ordCort = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select numOrdCort from ordencorte  where idOrdenCorte=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordCort = new OrdenCorte(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordCort;
    }
    
    
    @Override
    public List<OrdenCorte> readAll() throws Exception {
        List<OrdenCorte> ordCorte = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from ordencorte";
            rs = s.executeQuery(q);

            while (rs.next()) {
                ordCort = new OrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                                         rs.getString(5), rs.getString(6), rs.getString(7));
                ordCorte.add(ordCort);
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
      return ordCorte;
    } 
}
    
    
    

