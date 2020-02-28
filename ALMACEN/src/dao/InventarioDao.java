/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Inventario;
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
public class InventarioDao implements ICrudDao<Inventario> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    Inventario inv;
    String q;

    
    @Override
    public void create(Inventario t) throws Exception {
             try {

            conexion = AccesoDB.obtener();
                 ps = conexion.prepareStatement("Insert into inventario (fechaRealizado, fechaRegistro, "
                         + "encargado, estado, observacion, campo1) \n"
                         + "values(?,?,?,?,?,?)");
                 
           ps.setString(1, t.getFechRea());
           ps.setString(2, t.getFechReg());
           ps.setString(3, t.getEncargado());
           ps.setString(4, t.getEstado());
           ps.setString(5, t.getObser());
           ps.setString(6, t.getCampo1());
          
           
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
    public void update(Inventario t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "update inventario set  fechaRealizado=?, fechaRegistro=?, encargado=?, estado=?,"
                    + " observacion=?, campo1=? where idInventario=?";
            ps = conexion.prepareStatement(q);
            
           
            ps.setString(1, t.getFechRea());
            ps.setString(2, t.getFechReg());
            ps.setString(3, t.getEncargado());
            ps.setString(4, t.getEstado());
            ps.setString(5, t.getObser());
            ps.setString(6, t.getCampo1());
            ps.setInt(7, t.getIdInventario());
            

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Inventario t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from inventario where idInventario=?";
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, t.getIdInventario());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }
    
    
     public List<Inventario> findForLike(String filtro,String t) throws Exception {
        List<Inventario> lista = new ArrayList<>();
        try {
            
            q = "select idInventario, encargado, fechaRealizado, fechaRegistro, estado, observacion \n"
                    + "from inventario where "+ filtro +" like ? order by idInventario desc";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "%");
            //ejecutar consulta
            rs = ps.executeQuery();
            inv = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                inv = new Inventario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                lista.add(inv);
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
    public Inventario findForId(Object t) throws Exception {
        inv=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select idInventario, encargado, fechaRealizado, fechaRegistro, estado, observacion "
                   + "from inventario where idInventario=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                inv = new Inventario(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return inv;
    }
    
    public Inventario findLastestId() throws Exception {
        inv = null;
        try {
            q = "select max(idInventario) as idInventario from Inventario";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                inv= new Inventario(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return inv;
    }
    

    @Override
    public List<Inventario> readAll() throws Exception {
         List<Inventario> inventario = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idInventario, encargado, fechaRealizado, fechaRegistro, estado, observacion "
                   + "from inventario";
            rs = s.executeQuery(q);

            while (rs.next()) {
                inv = new Inventario(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                inventario.add(inv);
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
        return inventario;
    }
    
}
