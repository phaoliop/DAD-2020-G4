/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Rubro;
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
 * @author ARCRODINPC-06
 */
public class RubroDao implements ICrudDao<Rubro>{
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Rubro rub;
    String q;

    @Override
    public void create(Rubro t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert rubro(rubroDesc) values(?)");
            
            ps.setString(1, t.getDescripcion());
            
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
    public void update(Rubro t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "update rubro set  rubroDesc=?"
                    + " where idRubro=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getIdRubro());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Rubro t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from rubro where idRubro=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdRubro());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public Rubro findForId(Object t) throws Exception {
       rub=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idRubro, rubroDesc"
                    + " from rubro where idRubro=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rub = new Rubro(rs.getInt(1),rs.getString(2));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return rub;
    }

     public Rubro findForName(Object t) throws Exception {
       rub=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idRubro, rubroDesc from rubro where rubroDesc=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rub = new Rubro(rs.getInt(1),rs.getString(2));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return rub;
    }
    
    
    @Override
    public List<Rubro> readAll() throws Exception {
        List<Rubro> rubro = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from rubro";
            rs = s.executeQuery(q);

            while (rs.next()) {
                rub = new Rubro(rs.getInt(1), rs.getString(2));
                rubro.add(rub);
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
        return rubro;
    }
    
     public DefaultComboBoxModel getLista(Object t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from rubro where rubroDesc like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            rub = null;
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
