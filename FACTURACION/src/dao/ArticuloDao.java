/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Articulo;
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
 * @author Yveth Calixto
 */
public class ArticuloDao implements ICrudDao<Articulo>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    Articulo art;
    String q;

    @Override
    public void create(Articulo t) throws Exception {
         try {

            conexion = AccesoDB.obtener();
             ps = conexion.prepareStatement("Insert into articulo(idInventariofk, codigoArticulo, codigoUbicacion,"
                     + "nombre, diametro, diametroPulg, unidadMedidaDiam, "
                     + "longitud,longitudReal,unidadMedidaLong, cantidad, procedencia,observacion) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
           ps.setInt(1, t.getIdInventario());
           ps.setString(2, t.getCodigoArticulo());
           ps.setString(3, t.getCodigoUbicacion());
           ps.setString(4, t.getNombre());
           ps.setString(5, t.getDiametro());
           ps.setString(6, t.getDiamPulg());
           ps.setString(7, t.getUnidadMedidaDia());
           ps.setString(8, t.getLongitud());
           ps.setString(9, t.getLongitudReal());
           ps.setString(10, t.getUnidadMedidaLong());
           ps.setInt(11, t.getCantidad());
           ps.setString(12, t.getProcedencia());
           ps.setString(13, t.getObservacion());
           
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
    public void update(Articulo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update articulo set idInventariofk=?, codigoArticulo=?, codigoUbicacion=?, nombre=?, diametro=?,"
                    + " diametroPulg=?, unidadMedidaDiam=?, longitud=?, longitudReal=?, unidadMedidaLong=?,"
                    + " cantidad=?, procedencia=?, observacion=? where codigoArticulo=?";
            ps = conexion.prepareStatement(q);
            
           
            ps.setInt(1,t.getIdInventario());
            ps.setString(2,t.getCodigoArticulo());
            ps.setString(3, t.getCodigoUbicacion());
            ps.setString(4, t.getNombre());
            ps.setString(5, t.getDiametro());
            ps.setString(6, t.getDiamPulg());
            ps.setString(7, t.getUnidadMedidaDia());
            ps.setString(8, t.getLongitud());
            ps.setString(9, t.getLongitudReal());
            ps.setString(10, t.getUnidadMedidaLong());
            ps.setInt(11, t.getCantidad());
            ps.setString(12, t.getProcedencia());
            ps.setString(13, t.getObservacion());
            ps.setString(14,t.getCodigoArticulo());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

     public void update(Articulo t, Articulo x) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update articulo set idInventariofk=?, codigoArticulo=?, codigoUbicacion=?, nombre=?, diametro=?,"
                    + " diametroPulg=?, unidadMedidaDiam=?, longitud=?, longitudReal=?, unidadMedidaLong=?,"
                    + " cantidad=?, procedencia=?, observacion=? where codigoArticulo=? and idInventariofk=?";
            ps = conexion.prepareStatement(q);
            
           
            ps.setInt(1,t.getIdInventario());
            ps.setString(2,t.getCodigoArticulo());
            ps.setString(3, t.getCodigoUbicacion());
            ps.setString(4, t.getNombre());
            ps.setString(5, t.getDiametro());
            ps.setString(6, t.getDiamPulg());
            ps.setString(7, t.getUnidadMedidaDia());
            ps.setString(8, t.getLongitud());
            ps.setString(9, t.getLongitudReal());
            ps.setString(10, t.getUnidadMedidaLong());
            ps.setInt(11, t.getCantidad());
            ps.setString(12, t.getProcedencia());
            ps.setString(13, t.getObservacion());
            ps.setString(14,t.getCodigoArticulo());
            ps.setInt(15,t.getIdInventario());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }
    
    @Override
    public void delete(Articulo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from articulo where codigoArticulo=?";
            ps = conexion.prepareStatement(q);
            
            ps.setString(1, t.getCodigoArticulo());
                     
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }
    
     public void delete(Articulo t, Articulo x) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from articulo where codigoArticulo=? and idInventariofk=?";
            ps = conexion.prepareStatement(q);
            
            ps.setString(1, t.getCodigoArticulo());
            ps.setInt(2, x.getIdInventario());
                     
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public Articulo findForId(Object t) throws Exception {
       art=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select idArticulo,idInventariofk, codigoArticulo, codigoUbicacion, nombre, diametro,"
                    + " diametroPulg, unidadMedidaDiam, longitud, longitudReal, unidadMedidaLong, cantidad, "
                    + "procedencia, observacion from articulo where codigoArticulo=? ";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                art = new Articulo( rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), 
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getInt(12), rs.getString(13), rs.getString(14) );               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return art;
    }
    
    public Articulo findForId(Object t, Object x) throws Exception {
       art=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select idArticulo,idInventariofk, codigoArticulo, codigoUbicacion, nombre, diametro,"
                    + " diametroPulg, unidadMedidaDiam, longitud, longitudReal, unidadMedidaLong, cantidad, "
                    + "procedencia, observacion from articulo where codigoArticulo=? and idInventariofk=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                art = new Articulo(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), 
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                         rs.getInt(12),rs.getString(13), rs.getString(14) );               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return art;
    }
    
     public Articulo findCodArt(Object t) throws Exception {
       art=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select codigoArticulo, codigoUbicacion from articulo where idArticulo=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                art = new Articulo(rs.getString(1), rs.getString(2));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return art;
    }
     
   
    public List<Articulo> findForLike(String filtro,String t, int id) throws Exception {
        List<Articulo> lista = new ArrayList<>();
        try {
            
            q = "SELECT articulo.idArticulo, articulo.idInventariofk, articulo.codigoArticulo, "
                    + "articulo.codigoUbicacion,concat(articulo.diametro,' ',articulo.unidadMedidaDiam), "
                    + "articulo.diametroPulg, "
                    + "articulo.procedencia,articulo.longitud,\n"
                    + " round((articulo.longitud-sum(detalleordencorte.cantidadOrden*detalleordencorte.longitudOrden)),2)longitudIdeal, "
                    + "articulo.longitudReal, articulo.observacion\n"
                    + "FROM articulo \n"
                    + "LEFT JOIN detalleordencorte ON articulo.idArticulo = detalleordencorte.idArticulofk \n"
                    + "LEFT JOIN ordencorte ON detalleordencorte.idOrdenCortefk = ordencorte.idOrdenCorte\n"
                    + "where " + filtro + " like ? and articulo.idInventariofk="+id+"\n"
                    + "group by articulo.idArticulo order by articulo.diametro asc, idArticulo asc";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            art = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                art = new Articulo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                                   rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                                   rs.getString(10),rs.getString(11));
                lista.add(art);
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
    public List<Articulo> readAll() throws Exception {
        List<Articulo> articulo = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select codigoArticulo, codigoUbicacion, nombre, longitud from articulo";
            rs = s.executeQuery(q);

            while (rs.next()) {
                art = new Articulo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                articulo.add(art);
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
        return articulo;
    }
    
    public List<Articulo> readAllInventario() throws Exception {
           List<Articulo> articulo = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select A.codigoArticulo, A.nombre, A.diametro,  A.longitud, (A.longitud-sum(D.longitudOrden)), "
                 + "A.longitudReal\n" +
                 "from detalleordencorte D, articulo A\n" +
                 "where D.idArticulofk=A.idArticulo group by A.idArticulo";
            rs = s.executeQuery(q);

            while (rs.next()) {
                art = new Articulo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                articulo.add(art);
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
        return articulo;
    }
}
