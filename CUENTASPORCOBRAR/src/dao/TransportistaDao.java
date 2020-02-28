/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Transportista;
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
public class TransportistaDao implements ICrudDao<Transportista> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Transportista trans;
    String q;

    @Override
    public void create(Transportista t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert transportista(nombre, ruc, marcaYPlaca, certificadoInscripcion,"
                    + "licenciaConducir) values(?,?,?,?,?)");
            ps.setString(1, t.getNombre());
            ps.setString(2, t.getRuc());
            ps.setString(3, t.getMarcaYPlaca());
            ps.setString(4, t.getCertificadoInscripcion());
            ps.setString(5, t.getLicenciaConducir());

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
    public void update(Transportista t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update transportista set  nombre=?, ruc=?, marcaYPlaca=?, certificadoInscripcion=?, licenciaConducir=?"
                    + " where idTransportista=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getRuc());
            ps.setString(3, t.getMarcaYPlaca());
            ps.setString(4, t.getCertificadoInscripcion());
            ps.setString(5, t.getLicenciaConducir());
            ps.setInt(6, t.getIdTransportista());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Transportista t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from transportista where idTransportista=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdTransportista());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Transportista findForId(Object t) throws Exception {
        trans = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idTransportista, nombre, ruc, marcaYPlaca, certificadoInscripcion, licenciaConducir "
                    + " from transportista where idTransportista=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                trans = new Transportista(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return trans;
    }
    
    
    public Transportista findForIdNombre(Object t) throws Exception {
        trans = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idTransportista, nombre, ruc, marcaYPlaca, certificadoInscripcion, licenciaConducir "
                    + " from transportista where nombre=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                trans = new Transportista(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return trans;
    }
    

    @Override
    public List<Transportista> readAll() throws Exception {
        List<Transportista> transportista = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from transportista";
            rs = s.executeQuery(q);

            while (rs.next()) {
                trans = new Transportista(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                transportista.add(trans);
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
        return transportista;
    }
    
    public DefaultComboBoxModel getLista(Object t) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            
            q = "select *from  transportista where nombre like ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            trans = null;
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
