/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Proveedor;
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
public class ProveedorDao implements ICrudDao<Proveedor> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Proveedor pro;
    String q;

    @Override
    public void create(Proveedor t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert Proveedor(razonSocial, ruc, direccion, rubro, obsProveedor) values(?,?,?,?,?)");
            ps.setString(1, t.getRazonSocial());
            ps.setString(2, t.getRuc());
            ps.setString(3, t.getDireccion());
            ps.setString(4, t.getRubro());
            ps.setString(5, t.getObservacion());

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
    public void update(Proveedor t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update proveedor set razonSocial=?,ruc=?,direccion=?,rubro=?,obsProveedor=?"
                    + " where idProveedor=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getRazonSocial());
            ps.setString(2, t.getRuc());
            ps.setString(3, t.getDireccion());
            ps.setString(4, t.getRubro());
            ps.setString(5, t.getObservacion());
            ps.setInt(6, t.getIdProveedor());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Proveedor t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from proveedor where razonSocial=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getRazonSocial());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Proveedor findForId(Object t) throws Exception {
        pro = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from proveedor where razonSocial=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                pro = new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return pro;
    }

    public Proveedor findId(Object t) throws Exception {
        pro = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from proveedor where idProveedor=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                pro = new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return pro;
    }
    
    public List<Proveedor> findForLike(String filtro, String t) throws Exception {
        List<Proveedor> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from proveedor where "+filtro+" like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            pro = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                pro = new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                lista.add(pro);
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
    public List<Proveedor> readAll() throws Exception {
        List<Proveedor> proveedor = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from proveedor";
            rs = s.executeQuery(q);

            while (rs.next()) {
                pro = new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                proveedor.add(pro);
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
        return proveedor;
    }

    public DefaultComboBoxModel getLista(String t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
       
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from proveedor where razonSocial like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+ t +"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            pro = null;
            
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
