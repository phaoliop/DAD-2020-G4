/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ContactoCliente;
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
public class ContactoClienteDao implements ICrudDao<ContactoCliente> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ContactoCliente contCli;
    String q;

    @Override
    public void create(ContactoCliente t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into contactocliente(idCliente_fk, dni, nombres, apellidos, cargo, telefono1, telefono2, correo, sucursal, obserContactoCliente) values(?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, t.getIdCliente());
            ps.setString(2, t.getDni());
            ps.setString(3, t.getNombres());
            ps.setString(4, t.getApellidos());
            ps.setString(5, t.getCargo());
            ps.setString(6, t.getTlf1());
            ps.setString(7, t.getTlf2());
            ps.setString(8, t.getCorreo());
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
    public void update(ContactoCliente t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update contactocliente set dni=?, nombres=?, apellidos=?, cargo=?, telefono1=?, telefono2=?, correo=?, sucursal=?, obserContactoCliente=?  where idContactoCliente=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getDni());
            ps.setString(2, t.getNombres());
            ps.setString(3, t.getApellidos());
            ps.setString(4, t.getCargo());
            ps.setString(5, t.getTlf1());
            ps.setString(6, t.getTlf2());
            ps.setString(7, t.getCorreo());
            ps.setString(8, t.getSucursal());
            ps.setString(9, t.getObservacion());
            ps.setInt(10, t.getIdContactoCliente());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(ContactoCliente t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from contactocliente where idContactoCliente=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdContactoCliente());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public ContactoCliente findForId(Object t) throws Exception {
        contCli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idContactoCliente, idCliente_fk, dni, nombres, apellidos, cargo, telefono1, telefono2, correo, sucursal, "
                    + "obserContactoCliente from contactocliente where idContactoCliente=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contCli = new ContactoCliente(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getString(11) );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contCli;
    }

    public ContactoCliente findForId(Object t, Object x) throws Exception {
        contCli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idContactoCliente, dni, nombres, apellidos, cargo, telefono1, telefono2, correo, sucursal, "
                    + "obserContactoCliente from contactocliente where nombres=? and idCliente_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contCli = new ContactoCliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contCli;
    }
    
    public ContactoCliente findForId1(Object t, int i) throws Exception {
        contCli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idContactoCliente, dni, nombres, apellidos, cargo, telefono1, telefono2, correo, sucursal, "
                    + "obserContactoCliente from contactocliente "
                    + "where concat_ws(' ', nombres, apellidos)=? and idCliente_fk="+i;
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contCli = new ContactoCliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contCli;
    }

    public ContactoCliente findForIdDni(Object t, Object x) throws Exception {
        contCli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idContactoCliente, dni from contactocliente where concat_ws(' ', nombres, apellidos)=? and  idCliente_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contCli = new ContactoCliente(rs.getInt(1), rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contCli;
    }
    public ContactoCliente findForId2(Object t) throws Exception {
        contCli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select nombres, apellidos from contactocliente where idContactoCliente=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                contCli = new ContactoCliente(rs.getString(1), rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return contCli;
    }

     public DefaultComboBoxModel getLista(String t, int id) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from contactocliente where idCliente_fk="+id+" and nombres like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            contCli = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores

                modelo.addElement(rs.getString(4));
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
     
    
    
    public List<ContactoCliente> findForLike(String filtro, String t) throws Exception {
        List<ContactoCliente> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "SELECT cc.idContactoCliente, concat(cc.nombres,' ',cc.apellidos), cc.telefono1, cc.correo, c.razonSocial,\n"
                    + " cc.sucursal,cc.obserContactoCliente\n"
                    + "FROM contactocliente cc, cliente c\n"
                    + "where cc.idCliente_fk=c.idCliente  and " + filtro + " like ? \n"
                    + "order by c.razonSocial asc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            contCli = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
               contCli = new ContactoCliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(contCli);
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
    public List<ContactoCliente> readAll() throws Exception {

        List<ContactoCliente> contactoCli = new ArrayList<>();
        int a = 0;

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idContactoCliente, dni, nombres, apellidos, cargo, telefono1, telefono2, correo, sucursal, obserContactoCliente from contactocliente"; //where idCliente=?";
            rs = s.executeQuery(q);

            while (rs.next()) {
                contCli = new ContactoCliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                contactoCli.add(contCli);
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
        return contactoCli;
    }

    public List<ContactoCliente> readAll1(Object t) throws Exception {

        List<ContactoCliente> contactoCli = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            // s = conexion.createStatement();
            q = "select idContactoCliente, dni, nombres, apellidos, cargo, telefono1, telefono2, correo, sucursal, obserContactoCliente from contactocliente where idCliente_fk=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int) t);
            rs = ps.executeQuery();
            while (rs.next()) {
                contCli = new ContactoCliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                contactoCli.add(contCli);
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
        return contactoCli;

    }
    
     public DefaultComboBoxModel CargarContactosCombo(String nombre, int i) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            conexion = AccesoDB.obtener();
            q = "select A.nombres,A.apellidos from contactocliente A ,"
                    + " cliente B where b.razonsocial = ? and A.idcliente_fk = "+i;
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
