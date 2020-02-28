/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ArticuloProveedor;
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
 * @author ARCRODINPC-02
 */
public class ArticuloProveedorDao implements ICrudDao<ArticuloProveedor>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ArticuloProveedor artPro;
    String q;

    @Override
    public void create(ArticuloProveedor t) throws Exception {
       try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into articulosproveedores(descripcion, campo1) values(?,?)");
            ps.setString(1, t.getDescripcion());
            ps.setString(2,t.getCampo1());
            
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
    public void update(ArticuloProveedor t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update articulosproveedores set descripcion=?, campo1=? where idArticulosProveedores=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getDescripcion());
            ps.setString(2, t.getCampo1());
            ps.setInt(3, t.getIdArticulosProveedores());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(ArticuloProveedor t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "delete from articulosproveedores where idArticulosProveedores=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdArticulosProveedores());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public ArticuloProveedor findForId(Object t) throws Exception {
        artPro=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idArticulosProveedores, descripcion, campo1 from articulosproveedores"
                    + " where idArticulosProveedores=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                artPro = new ArticuloProveedor(rs.getInt(1),rs.getString(2),rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return artPro;
    }

     public ArticuloProveedor findForName(Object t) throws Exception {
        artPro=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idArticulosProveedores, descripcion, campo1 from articulosproveedores"
                    + " where descripcion=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                artPro = new ArticuloProveedor(rs.getInt(1),rs.getString(2),rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return artPro;
    }
    
    
    @Override
    public List<ArticuloProveedor> readAll() throws Exception {
        List<ArticuloProveedor> articuloPro = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from articulosproveedores";
            rs = s.executeQuery(q);

            while (rs.next()) {
                artPro = new ArticuloProveedor(rs.getInt(1), rs.getString(2), rs.getString(3));
                articuloPro.add(artPro);
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
        return articuloPro;
    }

    
//    public List<ArticuloProveedor> readAll1(Object t) throws Exception {
//
//        List<ArticuloProveedor> articuloPro = new ArrayList<>();
//
//        try {
//            conexion = AccesoDB.obtener();
//            // s = conexion.createStatement();
//            q = "select idArticuloProveedor, item, descripcion, precioCompra from articuloproveedor where idProveedorfk=?";
//            ps = conexion.prepareStatement(q);
//            ps.setInt(1, (int) t);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                artPro = new ArticuloProveedor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4));
//                articuloPro.add(artPro);
//            }
//            rs.close();
//            ps.close();
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            try {
//                conexion.close();
//            } catch (SQLException e) {
//            }
//        }
//        return articuloPro;
//
//    }
    
     public DefaultComboBoxModel getLista(Object t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from articulosproveedores where descripcion like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            artPro = null;
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
    
    

