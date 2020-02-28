/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetalleOrdenTrabajo;
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
public class DetalleOrdenTrabajoDao implements ICrudDao<DetalleOrdenTrabajo> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    DetalleOrdenTrabajo dot;
    String q;
    
    @Override
    public void create(DetalleOrdenTrabajo t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into detalleordentrabajo(idOrdenTrabajofk, item, cant, \n"
                    + "descripcion, tolerancia, campo1) values(?,?,?,?,?,?)");
            
            ps.setInt(1, t.getIdOrdenTrabajo());
            ps.setString(2, t.getItem());
            ps.setString(3, t.getCant());
            ps.setString(4, t.getDescripcion());
            ps.setString(5, t.getTolerancia());
            ps.setString(6, t.getCampo1());
           
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
    public void update(DetalleOrdenTrabajo t) throws Exception {
          try {
            conexion = AccesoDB.obtener();
              q = "update detalleordentrabajo set idOrdenTrabajofk=?, item=?, cant=?,descripcion=?, tolerancia=?, "
                      + "campo1=? where idDetalleOrdenTrabajo=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1,t.getIdOrdenTrabajo());
            ps.setString(2, t.getItem());
            ps.setString(3, t.getCant());
            ps.setString(4, t.getDescripcion());
            ps.setString(5, t.getTolerancia());
            ps.setString(6, t.getCampo1());
            ps.setInt(7, t.getIdDetalleOrdenTrabajo());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(DetalleOrdenTrabajo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from detalleordentrabajo where idDetalleOrdenTrabajo=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdDetalleOrdenTrabajo());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public DetalleOrdenTrabajo findForId(Object t) throws Exception {
        dot=null; 
        try {
            
            q = "select idDetalleOrdenTrabajo, idOrdenTrabajofk, item, cant , descripcion, tolerancia, campo1"
                    + " from detalleordentrabajo where idDetalleOrdenTrabajo=?";
            
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                dot = new DetalleOrdenTrabajo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                         rs.getString(6), rs.getString(7));             
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return dot;
    }

    @Override
    public List<DetalleOrdenTrabajo> readAll() throws Exception {
        List<DetalleOrdenTrabajo> detalleordentrabajo = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idDetalleOrdenTrabajo, idOrdenTrabajofk, item, cant , descripcion, tolerancia, campo1"
                    + " from detalleordentrabajo";
            rs = s.executeQuery(q);

            while (rs.next()) {
                dot = new DetalleOrdenTrabajo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                         rs.getString(6), rs.getString(7));
                detalleordentrabajo.add(dot);
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
        return detalleordentrabajo;
    }
    
     public List<DetalleOrdenTrabajo> findForidOrdTrab(int t) throws Exception {
        List<DetalleOrdenTrabajo> lista = new ArrayList<>();
        try {

            q = "SELECT idDetalleOrdenTrabajo, item, cant, descripcion, tolerancia \n"
                    + "FROM   detalleordentrabajo\n"
                    + "where idOrdenTrabajofk=? order by idDetalleOrdenTrabajo asc";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            dot = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                dot = new DetalleOrdenTrabajo( rs.getInt(1),rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5));
                lista.add(dot);
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
