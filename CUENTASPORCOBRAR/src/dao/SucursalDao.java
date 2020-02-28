/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Sucursal;
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
public class SucursalDao implements ICrudDao<Sucursal> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Sucursal sucu;
    String q;


    @Override
    public void create(Sucursal t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into sucursal(sucursal,clave) values(?,?)");
            ps.setString(1, t.getSucursal());
            ps.setString(2, t.getClave());

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
    public void update(Sucursal t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update sucursal set sucursal=?, clave=? where idSucursal=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getSucursal());
            ps.setString(2, t.getClave());
            ps.setInt(3, t.getIdSucursal());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Sucursal t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from sucursal where idSucursal=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdSucursal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Sucursal findForId(Object t) throws Exception {
        sucu = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from sucursal where idSucursal=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                sucu = new Sucursal(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return sucu;
    }
    
    public Sucursal findForName(Object t) throws Exception {
       sucu=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idSucursal, sucursal, clave from sucursal where sucursal=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                sucu = new Sucursal(rs.getInt(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return sucu;
    }

    @Override
    public List<Sucursal> readAll() throws Exception {
        List<Sucursal> sucursal = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from sucursal";
            rs = s.executeQuery(q);

            while (rs.next()) {
                sucu = new Sucursal(rs.getInt(1), rs.getString(2), rs.getString(3));
                sucursal.add(sucu);
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
        return sucursal;
    }
 
     public DefaultComboBoxModel getLista(Object t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from sucursal where sucursal like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            sucu = null;
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
