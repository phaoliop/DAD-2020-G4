/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Precio;
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
 * @author Yveth Calixto
 */
public class PrecioDao implements ICrudDao<Precio>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Precio pre;
    String q;

    @Override
    public void create(Precio t) throws Exception {
          try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into precio(idArticulo_fk, fechaPrecio, precioVenta) values(?,?,?)");
            
           ps.setInt(1, t.getIdArticulo());
           ps.setString(2, t.getFechaPrecio());
           ps.setFloat(3,t.getPrecioVenta());

           
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
    public void update(Precio t) throws Exception {
       try {
            conexion = AccesoDB.obtener();
            q = "update precio set precioVenta=?, where idPrecio=?";
            ps = conexion.prepareStatement(q);
            
            ps.setFloat(1, t.getPrecioVenta());
            ps.setInt(2, t.getIdArticulo());
            

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Precio t) throws Exception {
            try {
            conexion = AccesoDB.obtener();
            q = "delete from precio where idPrecio=?";
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, t.getIdPrecio());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public Precio findForId(Object t) throws Exception {
        pre=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select fechaPrecio, precioVenta from precio where idPrecio=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1,(int) t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                pre = new Precio(rs.getString(1), rs.getFloat(2) );               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return pre;
    }
    
    
      public List<Precio> findForidArt(int t) throws Exception {
        List<Precio> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select fechaPrecio, precioVenta  from precio where idArticulo_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            pre = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                pre = new Precio(rs.getString(1), rs.getFloat(2));
                lista.add(pre);
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
    public List<Precio> readAll() throws Exception {
          List<Precio> precio = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select  fechaPrecio, precioVenta from precio";
            rs = s.executeQuery(q);

            while (rs.next()) {
                pre = new Precio(rs.getString(1), rs.getFloat(2));
                precio.add(pre);
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
        return precio;
    }
}
    
    
    

