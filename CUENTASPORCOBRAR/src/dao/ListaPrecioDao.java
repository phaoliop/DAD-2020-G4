/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ListaPrecio;
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
public class ListaPrecioDao implements ICrudDao<ListaPrecio> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ListaPrecio listaPrecio;
    String q;

    @Override
    public void create(ListaPrecio t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("insert into listaprecio(nombre, diametro, unidadDiametro, precio, tipoCod) values(?,?,?,?,?);");
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDiametro());
            ps.setString(3, t.getUnidadDiametro());
            ps.setFloat(4, t.getPrecio());
            ps.setString(5, t.getTipoCod());

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
    public void update(ListaPrecio t) throws Exception {

        try {

            conexion = AccesoDB.obtener();
            q = "update listaprecio set  nombre=?, diametro=?, unidadDiametro=?, precio=?, tipoCod=? where idListaPrecio=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDiametro());
            ps.setString(3, t.getUnidadDiametro());
            ps.setFloat(4, t.getPrecio());
            ps.setString(5, t.getTipoCod());
            ps.setInt(6, t.getIdListaPrecio());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }

    }

    @Override
    public void delete(ListaPrecio t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from listaprecio where idListaPrecio=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdListaPrecio());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public ListaPrecio findForId(Object t) throws Exception {
        listaPrecio = null;
        try {

            q = "select nombre, diametro, unidadDiametro, precio, tipoCod from listaprecio where idListaPrecio=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                listaPrecio = new ListaPrecio(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return listaPrecio;
    }

    public DefaultComboBoxModel getListarCombo(String t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from listaprecio where diametro like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            listaPrecio = null;
            while (rs.next()) {
                modelo.addElement(rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            Logger.getLogger(ListaPrecioDao.class.getName()).log(Level.SEVERE, null, e);
        } catch (NullPointerException e1) {
            throw e1;
        } finally {
            conexion.close();
        }
        return modelo;
    }

    @Override
    public List<ListaPrecio> readAll() throws Exception {
        List<ListaPrecio> ListPrec = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select nombre, diametro, unidadDiametro, precio, tipoCod from listaprecio";
            rs = s.executeQuery(q);

            while (rs.next()) {
                listaPrecio = new ListaPrecio(rs.getString(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5));
                ListPrec.add(listaPrecio);
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
        return ListPrec;
    }

}
