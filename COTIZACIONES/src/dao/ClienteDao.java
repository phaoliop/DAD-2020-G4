/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Cliente;
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
public class ClienteDao implements ICrudDao<Cliente> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Cliente cli;
    String q;

    @Override
    public void create(Cliente t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into cliente(codigoCliente,razonSocial, "
                    + "ruc, tipo, direccion, obserCliente) values(?,?,?,?,?,?)");
            ps.setString(1, t.getCodigoCliente());
            ps.setString(2, t.getRazonSocial());
            ps.setString(3, t.getRuc());
            ps.setString(4, t.getTipo());
            ps.setString(5, t.getDireccion());
            ps.setString(6, t.getObservacion());

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
    public void update(Cliente t) throws Exception {

        try {
            conexion = AccesoDB.obtener();
            q = "update cliente set codigoCliente=?, razonSocial=?,ruc=?,tipo=?,direccion=?,"
                    + "obserCliente=? where idCliente=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getCodigoCliente());
            ps.setString(2, t.getRazonSocial());
            ps.setString(3, t.getRuc());
            ps.setString(4, t.getTipo());
            ps.setString(5, t.getDireccion());
            ps.setString(6, t.getObservacion());
            ps.setInt(7, t.getIdCliente());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Cliente t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from cliente where razonSocial=?";
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
    public Cliente findForId(Object t) throws Exception {
        cli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from cliente where razonSocial=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return cli;
    }

    public Cliente findForId1(Object t) throws Exception {
        cli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idCliente, codigoCliente,razonSocial, ruc, tipo, direccion, obserCliente   from cliente where idCliente=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                cli = new Cliente(rs.getInt(1),rs.getString(2), rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return cli;
    }
    
    public Cliente findForRuc(Object t) throws Exception {
        cli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idCliente, codigoCliente,razonSocial, ruc, tipo, direccion, "
                    + "obserCliente from cliente where ruc=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                cli = new Cliente(rs.getInt(1),rs.getString(2), rs.getString(3), 
                        rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return cli;
    }

    public Cliente findLastestId() throws Exception {
        cli = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  max(idCliente) as idCliente from cliente";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                cli = new Cliente(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return cli;
    }
    
    public List<Cliente> findForLike(String filtro, String t) throws Exception {
        List<Cliente> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from cliente where "+filtro+" like ? ";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            cli = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(cli);
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

    public DefaultComboBoxModel getLista(String t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from cliente where razonSocial like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+ t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            cli = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores

                modelo.addElement(rs.getString(3));
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

    @Override
    public List<Cliente> readAll() throws Exception {
        List<Cliente> cliente = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from cliente";
            rs = s.executeQuery(q);

            while (rs.next()) {
                cli = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), 
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                cliente.add(cli);
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
        return cliente;
    }

}
