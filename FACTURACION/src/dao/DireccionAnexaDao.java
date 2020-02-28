/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DireccionAnexa;
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
public class DireccionAnexaDao implements ICrudDao<DireccionAnexa> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    DireccionAnexa direc;
    String q;

    @Override
    public void create(DireccionAnexa t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into direccionesanexascliente(idClientefkk, "
                                           + "direccion, observacion, campo1, campo2) values(?,?,?,?,?)");
           ps.setInt(1, t.getIdCliente());
           ps.setString(2, t.getDireccion());
           ps.setString(3, t.getObser());
           ps.setString(4, t.getCampo1());
           ps.setString(5, t.getCampo2());
                      
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
    public void update(DireccionAnexa t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update direccionesanexascliente set  idClientefkk=?, direccion=?, observacion=?, campo1=?, campo2=?\n"
                    + " where idDireccionesAnexasCliente=?";
            ps = conexion.prepareStatement(q);
            
           
            ps.setInt(1, t.getIdCliente());
            ps.setString(2, t.getDireccion());
            ps.setString(3, t.getObser());
            ps.setString(4, t.getCampo1());
            ps.setString(5, t.getCampo2());
            ps.setInt(6,t.getIdDireccion());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(DireccionAnexa t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from direccionesanexascliente where idDireccionesAnexasCliente=?;";
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, t.getIdDireccion());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public DireccionAnexa findForId(Object t) throws Exception {
        direc=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDireccionesAnexasCliente,idClientefkk, direccion, observacion\n"
                    + " from direccionesanexascliente where idDireccionesAnexasCliente=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                direc = new DireccionAnexa(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return direc;
    }
    
    public DireccionAnexa findLastestId(Object t) throws Exception {
        direc = null;
        try {

            q = "select  max(idDireccionesAnexasCliente) as idDireccionesAnexasCliente \n"
                    + " from DireccionesAnexasCliente where idClientefkk=?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int)t);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                direc = new DireccionAnexa(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return direc;
    }

    @Override
    public List<DireccionAnexa> readAll() throws Exception {
           List<DireccionAnexa> direccion = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idDireccionesAnexasCliente, idClientefkk, direccion, observacion from direccionesanexascliente";
            rs = s.executeQuery(q);

            while (rs.next()) {
                direc = new DireccionAnexa(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                direccion.add(direc);
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
        return direccion;
    }
   
    
    
    
public List<DireccionAnexa> readAllTabla(Object t) throws Exception {

        List<DireccionAnexa> direccion = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            // s = conexion.createStatement();
            q = "select  idDireccionesAnexasCliente, direccion, observacion from direccionesanexascliente where idClientefkk=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int) t);
            rs = ps.executeQuery();
            while (rs.next()) {
                direc = new DireccionAnexa(rs.getInt(1), rs.getString(2), rs.getString(3));
                direccion.add(direc);
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
        return direccion;

    }
}