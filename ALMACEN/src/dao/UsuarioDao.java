/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Usuario;
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
 * @author ARCRODINPC-02
 */
public class UsuarioDao implements ICrudDao<Usuario> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Usuario us;
    String q;

    @Override
    public void create(Usuario t) throws Exception {

        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into usuario(nombres, apellidos, telefono, correo, cargo, usuario,clave) values(?,?,?,?,?,?,?)");
            ps.setString(1, t.getNombres());
            ps.setString(2, t.getApellidos());
            ps.setString(3, t.getTelefono());
            ps.setString(4, t.getCorreo());
            ps.setString(5, t.getCargo());
            ps.setString(6, t.getUsuario());
            ps.setString(7, t.getPassword());

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
    public void update(Usuario t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update usuario set nombres=?, apellidos=?,telefono=?,correo=?,cargo=?,usuario=?, clave=? where idUsuario=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getNombres());
            ps.setString(2, t.getApellidos());
            ps.setString(3, t.getTelefono());
            ps.setString(4, t.getCorreo());
            ps.setString(5, t.getCargo());
            ps.setString(6, t.getUsuario());
            ps.setString(7, t.getPassword());
            ps.setInt(8, t.getIdUsuario());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Usuario t) throws Exception {

        try {
            conexion = AccesoDB.obtener();
            q = "delete from usuario where idUsuario=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdUsuario());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Usuario findForId(Object t) throws Exception {
        us = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from usuario where nombres=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                us = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return us;
    }
    
    public Usuario findForIdUs(Object t) throws Exception {
        us = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from usuario where idUsuario=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                us = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return us;
    }


    public Usuario findForId1(Object t) throws Exception {
        us = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idUsuario from usuario where concat_ws(' ', nombres, apellidos)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                us = new Usuario(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return us;
    }

    public Usuario findForId2(Object t) throws Exception {
        us = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select nombres, apellidos from usuario where idUsuario=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                us = new Usuario(rs.getString(1), rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return us;
    }

    @Override
    public List<Usuario> readAll() throws Exception {
        List<Usuario> usuario = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idUsuario, nombres, apellidos, telefono, correo, cargo, usuario from usuario";
            rs = s.executeQuery(q);

            while (rs.next()) {
                us = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                usuario.add(us);
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
        return usuario;
    }
    
    
    public Usuario validar(String usuario, String clave) throws Exception {
        us = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select *from usuario where usuario = ? and clave = ?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, usuario);
            ps.setString(2, clave);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                us = new Usuario(rs.getInt(1), rs.getString(2), 
                        rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), 
                        rs.getString(7));
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
        return us;
    }    

}
