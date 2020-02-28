/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.RotulacionAtencion;
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
 * @author ARCRODINPC-05
 */
public class RotulacionAtencionDao implements ICrudDao<RotulacionAtencion>{

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    RotulacionAtencion rotAt;
    String q;
    
    @Override
    public void create(RotulacionAtencion t) throws Exception {
          try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert rotulacionatencion(idRotulacionfk, fkkidContactoCliente) values(?,?)");

           ps.setInt(1, t.getIdRotulacion());
           ps.setInt(2, t.getIdContactoCliente());
           
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
    public void update(RotulacionAtencion t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update rotulacionatencion set  idRotulacionfk=?, fkkidContactoCliente=?"
                    + " where idRotulacionAtencion=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdRotulacion());
            ps.setInt(2, t.getIdContactoCliente());
            ps.setInt(3, t.getIdRotulacionAtencion());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(RotulacionAtencion t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "delete from rotulacionatencion where idRotulacionAtencion=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdRotulacionAtencion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public RotulacionAtencion findForId(Object t) throws Exception {
             rotAt=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idRotulacionAtencion,idRotulacionfk, fkkidContactoCliente from rotulacionatencion where idRotulacionAtencion=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rotAt = new RotulacionAtencion(rs.getInt(1), rs.getInt(2), rs.getInt(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return rotAt;

    }

    @Override
    public List<RotulacionAtencion> readAll() throws Exception {
        List<RotulacionAtencion> rotulacionatencion = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from rotulacionatencion";
            rs = s.executeQuery(q);

            while (rs.next()) {
                rotAt = new RotulacionAtencion(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                rotulacionatencion.add(rotAt);
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
        return rotulacionatencion;
    }

    public List<RotulacionAtencion> RotulacionAtencionListar(int t) throws Exception {
        List<RotulacionAtencion> lista = new ArrayList<>();
        try {

            q = "select a.idRotulacionAtencion, a.idRotulacionfk, concat(c.nombres,' ', c.apellidos,' D.N.I.:', c.dni) \n"
                    + "from contactocliente c, rotulacionatencion a, rotulacion r \n"
                    + "where (a.fkkidContactoCliente=c.idContactoCliente and a.idRotulacionfk=r.idRotulacion)\n"
                    + "	and a.idRotulacionfk=?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1,t);
            //ejecutar consulta
            rs = ps.executeQuery();
            rotAt = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                rotAt = new RotulacionAtencion(rs.getInt(1), rs.getInt(2), rs.getString(3));
                lista.add(rotAt);
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
