/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetalleOrdenCorte;
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
public class DetalleOrdenCorteDao implements ICrudDao<DetalleOrdenCorte>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DetalleOrdenCorte detOrd;
    String q;

    @Override
    public void create(DetalleOrdenCorte t) throws Exception {
         try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into detalleordencorte(idOrdenCortefk, cantidadOrden, diametroDet,idArticulofk, longitudOrden) values(?,?,?,?,?)");

            ps.setInt(1, t.getIdOrdenCorte());
            ps.setInt(2, t.getCantidad());
            ps.setString(3, t.getDiametro());
            ps.setInt(4, t.getIdArticulo());
            ps.setString(5, t.getLongitud());

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
    public void update(DetalleOrdenCorte t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update detalleordencorte set cantidadOrden=?, longitudOrden=? where idDetalleOrdenCorte=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getCantidad());
            ps.setString(2, t.getLongitud());
            ps.setInt(3, t.getIdDetalleOrdenCorte());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            //conexion.close();
        }
    }

    @Override
    public void delete(DetalleOrdenCorte t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from detalleordencorte where idDetalleOrdenCorte=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdDetalleOrdenCorte());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public DetalleOrdenCorte findForId(Object t) throws Exception {
        detOrd = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from detalleordencorte where idDetalleOrdenCorte=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detOrd = new DetalleOrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detOrd;
    }

    public DetalleOrdenCorte findAllForId(Object t) throws Exception {
        detOrd = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDetalleOrdenCorte, cantidadOrden, diametroDet, idArticulofk, "
                    + "longitudOrden from detalleordencorte where idDetalleOrdenCorte=?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detOrd = new DetalleOrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detOrd;
    }
        
    public DetalleOrdenCorte findLastestId() throws Exception {
        detOrd = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select max(idDetalleOrdenCorte) as idDetalleOrdenCorte from detalleordencorte";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detOrd= new DetalleOrdenCorte(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detOrd;
    }
    
     public List<DetalleOrdenCorte> findForidOrd(int t) throws Exception {
        List<DetalleOrdenCorte> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "SELECT codigoArticulo, codigoUbicacion, cantidadOrden,diametroDet,longitudOrden, "
                    + "procedencia, longitudReal, idDetalleOrdenCorte\n"
                    + "FROM articulo, detalleordencorte\n"
                    + "WHERE (articulo.idArticulo=detalleordencorte.idArticulofk ) and idOrdenCortefk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            detOrd = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                detOrd = new DetalleOrdenCorte(rs.getString(1), rs.getString(2), rs.getInt(3),rs.getString(4) ,rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
                lista.add(detOrd);
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
     
    public List<DetalleOrdenCorte> findForLike(String filtro, String t) throws Exception {
        List<DetalleOrdenCorte> lista = new ArrayList<>();
        try {

            q = "SELECT doc.idDetalleOrdenCorte, oc.numOrdCort, a.codigoArticulo, a.codigoUbicacion,\n"
                    + "	   doc.cantidadOrden, a.nombre,concat(doc.diametroDet,' ',a.unidadMedidaDiam), doc.longitudOrden,\n"
                    + "       a.procedencia, oc.fecha, c.razonSocial, p.numPedido, prof.codProforma, i.idInventario\n"
                    + "FROM detalleordencorte doc, articulo a, ordencorte oc, pedido p, cliente c, proforma prof,\n"
                    + "	 inventario i\n"
                    + "where (doc.idOrdenCortefk=oc.idOrdenCorte and doc.idArticulofk=a.idArticulo\n"
                    + "	    and oc.idPedidofk=p.idPedido and oc.idPedidofk=p.idPedido\n"
                    + "     and p.idClientefk=c.idCliente and p.idProforma_fk=prof.idProforma"
                    + "     and a.idInventariofk=i.idInventario)"
                    + "and " + filtro + " like ? order by doc.idDetalleOrdenCorte desc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            detOrd = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                detOrd = new DetalleOrdenCorte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getInt(12), rs.getString(13), rs.getInt(14));
                lista.add(detOrd);
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

    @Override
    public List<DetalleOrdenCorte> readAll() throws Exception {
        List<DetalleOrdenCorte> detOrdCorte = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from detalleordencorte";
            rs = s.executeQuery(q);

            while (rs.next()) {
                detOrd = new DetalleOrdenCorte(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                detOrdCorte.add(detOrd);
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
        return detOrdCorte;
    }
}
    
