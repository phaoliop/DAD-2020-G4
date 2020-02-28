/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Glosa;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ARCRODINPC-05
 */
public class GlosaDao implements ICrudDao<Glosa>{

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Glosa glos;
    String q;
    
    @Override
    public void create(Glosa t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert glosa(concepto, codigo) values(?,?)");
            
            ps.setString(1, t.getConcepto());
            ps.setString(2, t.getCodigo());
            
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
    public void update(Glosa t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update glosa set concepto=?, codigo=? "
                    + " where idGlosa=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getConcepto());
            ps.setString(2, t.getCodigo());
            ps.setInt(3, t.getIdGlosa());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Glosa t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from glosa where idGlosa=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdGlosa());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Glosa findForId(Object t) throws Exception {
        glos=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idGlosa, concepto, codigo  "
                    + " from glosa where idGlosa=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                glos = new Glosa(rs.getInt(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return glos;
    }

    @Override
    public List<Glosa> readAll() throws Exception {
        List<Glosa> glosa = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from glosa";
            rs = s.executeQuery(q);

            while (rs.next()) {
                glos = new Glosa(rs.getInt(1), rs.getString(2), rs.getString(3));
                glosa.add(glos);
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
        return glosa;
    }
 
    
    public Glosa findForConcepto(Object t) throws Exception {
        glos=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idGlosa, concepto, codigo  "
                    + " from glosa where concepto=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                glos = new Glosa(rs.getInt(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return glos;
    }
    
      public DefaultComboBoxModel getLista(String t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
       
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from glosa where concepto like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+ t +"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            glos = null;
            
            while (rs.next()) {
                //crear objeto pro y asignar valores
                
                modelo.addElement(rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (NullPointerException e1) {
            throw e1;
        } finally {
            conexion.close();
        }
        return modelo;
      }
}
