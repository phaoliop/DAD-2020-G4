/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ComprobanteSunat;
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
public class ComprobanteSunatDao implements ICrudDao<ComprobanteSunat>{
   
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ComprobanteSunat coSu;
    String q;

    @Override
    public void create(ComprobanteSunat t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert comprobantesunat(descripcion, codigo) values(?,?)");
            
            ps.setString(1, t.getDescripcion());
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
    public void update(ComprobanteSunat t) throws Exception {
           try {
            conexion = AccesoDB.obtener();
            q = "update comprobantesunat set descripcion=?, codigo=? "
                    + " where idComprobanteSunat=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getDescripcion());
            ps.setString(2, t.getCodigo());
            ps.setInt(3, t.getIdComprobanteSunat());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(ComprobanteSunat t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from comprobantesunat where idComprobanteSunat=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdComprobanteSunat());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public ComprobanteSunat findForId(Object t) throws Exception {
        coSu=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idComprobanteSunat, descripcion , codigo "
                    + " from comprobantesunat where idComprobanteSunat=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                coSu = new ComprobanteSunat(rs.getInt(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return coSu;
    }

    
    public ComprobanteSunat findForDescripcion(Object t) throws Exception {
        coSu=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idComprobanteSunat, descripcion , codigo "
                    + " from comprobantesunat where descripcion=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                coSu = new ComprobanteSunat(rs.getInt(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return coSu;
    }
    
    
    @Override
    public List<ComprobanteSunat> readAll() throws Exception {
        List<ComprobanteSunat> comprobante = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from comprobantesunat";
            rs = s.executeQuery(q);

            while (rs.next()) {
                coSu = new ComprobanteSunat(rs.getInt(1), rs.getString(2), rs.getString(3));
                comprobante.add(coSu);
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
        return comprobante;
    }
    
    
    public DefaultComboBoxModel getLista(String t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
       
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from comprobantesunat where descripcion like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+ t +"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            coSu = null;
            
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
