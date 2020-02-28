/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DireccionAnexaProv;
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
public class DireccionAnexaProvDao implements ICrudDao<DireccionAnexaProv> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DireccionAnexaProv dirNexa;
    String q;
    
    @Override
    public void create(DireccionAnexaProv t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into direccionanexaprov(fkidProveedor, direccion, observacion,"
                    + " campo1) values(?,?,?,?)");
            ps.setInt(1, t.getIdProv());
            ps.setString(2, t.getDirec());
            ps.setString(3, t.getObs());
            ps.setString(4, t.getCampo1());

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
    public void update(DireccionAnexaProv t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "update direccionanexaprov set fkidProveedor=?, direccion=?, observacion=?,campo1=?"
                    + " where idDireccionAnexaProv=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdProv());
            ps.setString(2, t.getDirec());
            ps.setString(3, t.getObs());
            ps.setString(4, t.getCampo1());
            ps.setInt(5,t.getIdDirAnexa() );
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(DireccionAnexaProv t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from direccionanexaprov where idDireccionAnexaProv=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdDirAnexa());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public DireccionAnexaProv findForId(Object t) throws Exception {
            dirNexa=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDireccionAnexaProv, fkidProveedor, direccion, observacion"
                    + " from direccionanexaprov where idDireccionAnexaProv=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                dirNexa = new DireccionAnexaProv(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return dirNexa;
    }
    
    
    public DireccionAnexaProv findLastestId(Object t) throws Exception {
        dirNexa = null;
        try {

            q = "select  max(idDireccionAnexaProv) as idDireccionAnexaProv "
                    + " from direccionanexaprov where fkidProveedor=?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, (int)t);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                //crear objeto pro y asignar valores
                dirNexa = new DireccionAnexaProv(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return dirNexa;
    }
    
    

    @Override
    public List<DireccionAnexaProv> readAll() throws Exception {
        List<DireccionAnexaProv> dirNexaProv = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from direccionanexaprov";
            rs = s.executeQuery(q);

            while (rs.next()) {
                dirNexa = new DireccionAnexaProv(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getString(4), rs.getString(5));
                dirNexaProv.add(dirNexa);
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
        return dirNexaProv;
    }
    
   public List<DireccionAnexaProv> readAll(Object t) throws Exception {

        List<DireccionAnexaProv> direccion = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            // s = conexion.createStatement();
            q = "select idDireccionAnexaProv, direccion, observacion \n"
                    + " from direccionanexaprov \n"
                    + " where fkidProveedor=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int) t);
            rs = ps.executeQuery();
            while (rs.next()) {
                dirNexa = new DireccionAnexaProv(rs.getInt(1), rs.getString(2), rs.getString(3));
                direccion.add(dirNexa);
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
