/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.MotivoGuia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ARCRODINPC-05
 */
public class MotivoGuiaDao implements ICrudDao<MotivoGuia>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    MotivoGuia motGuia;
    String q;

    @Override
    public void create(MotivoGuia t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(MotivoGuia t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MotivoGuia t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MotivoGuia findForId(Object t) throws Exception {
        motGuia=null; 
        try {
           q="select idMotivoGuia, motivo from motivoguia where idMotivoGuia=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                motGuia = new MotivoGuia(rs.getInt(1),rs.getString(2));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return motGuia;
    }

    public MotivoGuia findForNombre(Object t) throws Exception {
        motGuia=null; 
        try {
           q="select idMotivoGuia, motivo from motivoguia where motivo=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                motGuia = new MotivoGuia(rs.getInt(1),rs.getString(2));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return motGuia;
    }
    
    
     public MotivoGuia findForCombox(Object t) throws Exception {
        motGuia=null; 
        try {
           q="select idMotivoGuia, motivo from motivoguia where concat(idMotivoGuia,'.- ',motivo)=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                motGuia = new MotivoGuia(rs.getInt(1),rs.getString(2));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return motGuia;
    }
    
    @Override
    public List<MotivoGuia> readAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public DefaultComboBoxModel MotivoGuiaCombo() throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            conexion = AccesoDB.obtener();
            q = "select idMotivoGuia, motivo from motivoguia";
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            modelo.addElement("ELEGIR MOTIVO DE TRASLADO");
            while (rs.next()) {
                modelo.addElement(rs.getInt(1)+".- "+rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return modelo;
    }
    
}
