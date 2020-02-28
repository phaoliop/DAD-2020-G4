/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Rotulacion;
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
 * @author ARCRODINPC-05
 */
public class RotulacionDao implements ICrudDao<Rotulacion>{

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Rotulacion rot;
    String q;
    
    @Override
    public void create(Rotulacion t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert rotulacion(remitente, fkkidCliente,"
                    + " destino) values(?,?,?)");

            ps.setString(1, t.getRemitente());
            ps.setInt(2, t.getIdCliente());
            ps.setString(3, t.getDestino());
            
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
    public void update(Rotulacion t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update rotulacion set  remitente=?, fkkidCliente=?, destino=?"
                    + " where idRotulacion=?";
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getRemitente());
            ps.setInt(2, t.getIdCliente());
            ps.setString(3, t.getDestino());
            ps.setInt(4, t.getIdRotulacion());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Rotulacion t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "delete from rotulacion where idRotulacion=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdRotulacion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public Rotulacion findForId(Object t) throws Exception {
        rot=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idRotulacion, remitente, fkkidCliente, destino from rotulacion where idRotulacion=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rot = new Rotulacion(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return rot;
    }

    @Override
    public List<Rotulacion> readAll() throws Exception {
         List<Rotulacion> rotulacion = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from rotulacion";
            rs = s.executeQuery(q);

            while (rs.next()) {
                rot = new Rotulacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                rotulacion.add(rot);
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
        return rotulacion;
    }
    
    public List<Rotulacion> findForLike(String filtro,String t) throws Exception {
        List<Rotulacion> lista = new ArrayList<>();
        try {

            q = "select r.idRotulacion, c.razonSocial, r.remitente, r.destino \n"
                    + "from cliente c, rotulacion r\n"
                    + "where r.fkkidCliente=c.idCliente and  " + filtro + " like ? order by r.idRotulacion desc";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            rot = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                rot = new Rotulacion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                lista.add(rot);
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
        
    
    public Rotulacion findLastestId() throws Exception {
        rot = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  max(idRotulacion) as idRotulacion from rotulacion";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rot = new Rotulacion(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return rot;
    }
}
