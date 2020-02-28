/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.TipoCambio;
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
public class TipoCambioDao implements ICrudDao<TipoCambio> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    TipoCambio tipoCambio;
    String q;

    @Override
    public void create(TipoCambio t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into tipodecambio(fechaCambio, cambioCompra, cambioVenta) values(?,?,?)");

            ps.setString(1, t.getFecha());
            ps.setFloat(2, t.getPrecioCompra());
            ps.setFloat(3, t.getPrecioVenta());
            
           

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
    public void update(TipoCambio t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update tipodecambio set fechaCambio=?, cambioCompra=?, cambioVenta=? where idTipoDeCambio=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getFecha());
            ps.setFloat(2, t.getPrecioCompra());
            ps.setFloat(3, t.getPrecioVenta());
            ps.setInt(4, t.getIdTipoCambio());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(TipoCambio t) throws Exception {
          try {
            conexion = AccesoDB.obtener();
            q = "delete from tipodecambio where idTipoDeCambio=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdTipoCambio());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public TipoCambio findForId(Object t) throws Exception {
         tipoCambio = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from tipodecambio where fechaCambio=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, ( t+""));            //ejecutar consulta

            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                tipoCambio= new TipoCambio(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
              conexion.close();
        }
        return tipoCambio;
    }
    
    public TipoCambio findForIdForCambio(Object t) throws Exception {
         tipoCambio = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select cambioCompra, cambioVenta from tipodecambio where fechaCambio=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, ( t+""));            //ejecutar consulta

            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                tipoCambio= new TipoCambio(rs.getFloat(1), rs.getFloat(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
              conexion.close();
        }
        return tipoCambio;
    }
    
     public TipoCambio findLastestFecha() throws Exception {
        tipoCambio= null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select max(fechaCambio) as fechaCambio from tipodecambio";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                tipoCambio = new TipoCambio(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return tipoCambio;
    }

    @Override
    public List<TipoCambio> readAll() throws Exception {
        List<TipoCambio> tipCambio = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from tipodecambio order by fechaCambio desc";
            rs = s.executeQuery(q);

            while (rs.next()) {
                tipoCambio = new TipoCambio(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
                tipCambio.add(tipoCambio);
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
      return tipCambio;
    } 
    
    
}
