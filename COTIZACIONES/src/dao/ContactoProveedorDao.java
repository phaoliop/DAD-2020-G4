/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ContactoProveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ARCRODINPC-02
 */
public class ContactoProveedorDao implements ICrudDao<ContactoProveedor> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ContactoProveedor contPro;
    String q;

    @Override
    public void create(ContactoProveedor t) throws Exception {

        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into contactoproveedor(idProveedor_fk, dni, nombres, apellidos, cargo, correo, telefono1, telefono2, sucursal, ObsContactoProveedor) values(?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, t.getIdProveedor());
            ps.setString(2, t.getDni());
            ps.setString(3, t.getNombres());
            ps.setString(4, t.getApellidos());
            ps.setString(5, t.getCargo());
            ps.setString(6, t.getCorreo());
            ps.setString(7, t.getTlf1());
            ps.setString(8, t.getTlf2());
            ps.setString(9, t.getSucursal());
            ps.setString(10, t.getObservacion());

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
    public void update(ContactoProveedor t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update contactoproveedor set dni=?, nombres=?, apellidos=?, cargo=?, "
                    + "correo=?, telefono1=?, telefono2=?, sucursal=?, ObsContactoProveedor=? "
                    + " where idContactoProveedor=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getDni());
            ps.setString(2, t.getNombres());
            ps.setString(3, t.getApellidos());
            ps.setString(4, t.getCargo());
            ps.setString(5, t.getCorreo());
            ps.setString(6, t.getTlf1());
            ps.setString(7, t.getTlf2());
            ps.setString(8, t.getSucursal());
            ps.setString(9, t.getObservacion());
            ps.setInt(10, t.getIdContactoProveedor());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(ContactoProveedor t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from contactoproveedor where idContactoProveedor=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdContactoProveedor());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public ContactoProveedor findForId(Object t) throws Exception {
        contPro = null;
        try {
            
            q = "select idContactoProveedor, idProveedor_fk, dni, nombres, apellidos, cargo, correo, telefono1, telefono2, sucursal, "
                    + "ObsContactoProveedor from contactoproveedor where idContactoProveedor=?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contPro = new ContactoProveedor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contPro;
    }

    public ContactoProveedor findForId(Object t, Object x) throws Exception {
        contPro = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idContactoProveedor, dni, nombres, apellidos, cargo, correo, telefono1, "
                    + "telefono2, sucursal, ObsContactoProveedor from contactoproveedor "
                    + "where concat_ws(' ', nombres, apellidos)=? and idProveedor_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contPro = new ContactoProveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contPro;
    }
    @Override
    public List<ContactoProveedor> readAll() throws Exception {
        List<ContactoProveedor> contactoPro = new ArrayList<>();
        int a = 0;

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idContactoProveedor, dni, nombres, apellidos, cargo, correo, telefono1, telefono2, sucursal, ObsContactoProveedor from contactoproveedor";
            rs = s.executeQuery(q);

            while (rs.next()) {
                contPro = new ContactoProveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                contactoPro.add(contPro);
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
        return contactoPro;
    }

    public List<ContactoProveedor> readAll1(Object t) throws Exception {

        List<ContactoProveedor> contactoPro = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            // s = conexion.createStatement();
            q = "select idContactoProveedor, dni, nombres, apellidos, cargo, correo, telefono1, telefono2, sucursal, ObsContactoProveedor from contactoproveedor where idProveedor_fk=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int) t);
            rs = ps.executeQuery();
            while (rs.next()) {
                contPro = new ContactoProveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                contactoPro.add(contPro);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
            }
        }
        return contactoPro;

    }
    
     
    
    public List<ContactoProveedor> findForLike(String filtro, String t) throws Exception {
        List<ContactoProveedor> lista = new ArrayList<>();
        try {

            q = "SELECT cp.idContactoProveedor, concat(cp.nombres,' ',cp.apellidos), cp.telefono1, cp.correo, p.razonSocial ,\n"
                    + " cp.sucursal,cp.ObsContactoProveedor\n"
                    + " FROM contactoproveedor cp, proveedor p\n"
                    + " where cp.idProveedor_fk= p.idProveedor  and " + filtro + "  like ?\n"
                    + " order by p.razonSocial asc";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%" + t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            contPro = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                contPro = new ContactoProveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(contPro);
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
    
    public DefaultComboBoxModel CargarContactosCombo(String nombre, int i) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            conexion = AccesoDB.obtener();
            q = "select A.nombres,A.apellidos from contactoproveedor A ,\n"
                    + " proveedor B where  B.razonSocial=? and A.idProveedor_fk ="+i;
            
            ps = conexion.prepareStatement(q);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            modelo.addElement("Elegir contacto");
            while (rs.next()) {
                modelo.addElement(rs.getString(1) + " " + rs.getString(2));
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
