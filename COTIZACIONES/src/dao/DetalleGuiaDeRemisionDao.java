/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetalleGuiaDeRemision;
import entity.GuiaDeRemision;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ARCRODINPC-06
 */
public class DetalleGuiaDeRemisionDao implements ICrudDao<DetalleGuiaDeRemision>{
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DetalleGuiaDeRemision detGuia;
    String q;
    
    
    @Override
    public void create(DetalleGuiaDeRemision t) throws Exception {

        try {
            conexion = AccesoDB.obtener();
            q = "Insert into detalleguiaremision(idGuiaRemisionfk, \n"
                    + "cant, codigo, descripcion, unidadMedida) \n"
                    + "values(?,?,?,?,?)";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdGuiaRem());
            ps.setString(2, t.getCant());
            ps.setString(3, t.getCod());
            ps.setString(4, t.getDescrip());
            ps.setString(5, t.getUniMed());

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
    public void update(DetalleGuiaDeRemision t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            q = "update detalleguiaremision set   idGuiaRemisionfk=?, \n"
                    + "cant=?, codigo=?, descripcion=?, unidadMedida=? where idDetalleGuiaRemision=?;";

            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdGuiaRem());
            ps.setString(2, t.getCant());
            ps.setString(3, t.getCod());
            ps.setString(4, t.getDescrip());
            ps.setString(5, t.getUniMed());
            ps.setInt(6, t.getIdDetalleGuia());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(DetalleGuiaDeRemision t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "delete from detalleguiaremision where idDetalleGuiaRemision=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdDetalleGuia());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public DetalleGuiaDeRemision findForId(Object t) throws Exception {
        detGuia = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDetalleGuiaRemision, cant, codigo, descripcion, unidadMedida \n"
                    + "from detalleguiaremision\n"
                    + "where idDetalleGuiaRemision=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
           ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detGuia = new DetalleGuiaDeRemision(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detGuia;
    }
    
    public DetalleGuiaDeRemision findForIdDetGuia(String a,Object t) throws Exception {
        detGuia = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDetalleGuiaRemision \n"
                    + "from detalleguiaremision\n"
                    + "where descripcion=? and idGuiaRemisionfk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, a);
            ps.setInt(2, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detGuia = new DetalleGuiaDeRemision(rs.getInt(1));
            }

            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detGuia;
    }

    @Override
    public List<DetalleGuiaDeRemision> readAll() throws Exception {
        List<DetalleGuiaDeRemision> detGuiaRem = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idGuiaRemisionfk, cant, codigo, descripcion, unidadMedida from detalleguiaremision";
            rs = s.executeQuery(q);
    
            while (rs.next()) {
                detGuia = new DetalleGuiaDeRemision();
                detGuiaRem.add(detGuia);
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
        return detGuiaRem;
    }


public List<DetalleGuiaDeRemision> readAllList(int t) throws Exception {
        List<DetalleGuiaDeRemision> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDetalleGuiaRemision, cant, codigo, descripcion, unidadMedida from detalleguiaremision where idGuiaRemisionfk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1,(int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            detGuia = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                detGuia = new DetalleGuiaDeRemision(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(detGuia);
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

    
}
    



    