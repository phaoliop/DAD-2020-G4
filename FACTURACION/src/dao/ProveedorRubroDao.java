/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ProveedorRubro;
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
public class ProveedorRubroDao implements ICrudDao<ProveedorRubro>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ProveedorRubro proRub;
    String q;

    @Override
    public void create(ProveedorRubro t) throws Exception {
         try {

            conexion = AccesoDB.obtener();
             ps = conexion.prepareStatement("Insert proveedorrubro(fk_idProveedor, idRubro_fk, observ, estado) "
                     + "values(?,?,?,?)");
          
            ps.setInt(1, t.getIdProv());
            ps.setInt(2, t.getIdRubro());
            ps.setString(3, t.getObs());
            ps.setString(4, t.getEstado());
            
            
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
    public void update(ProveedorRubro t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update proveedorrubro set  fk_idProveedor=?, idRubro_fk=?, observ=?, estado=?"
                    + " where idProveedorRubro=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdProv());
            ps.setInt(2, t.getIdRubro());
            ps.setString(3, t.getObs());
            ps.setString(4, t.getEstado());
            ps.setInt(5, t.getIdProvRubpk());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(ProveedorRubro t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
            q = "delete from proveedorrubro where idProveedorRubro=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdProvRubpk());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public ProveedorRubro findForId(Object t) throws Exception {
        proRub=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idProveedorRubro, fk_idProveedor, idRubro_fk, observ, estado"
                    + " from proveedorrubro where idProveedorRubro=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                proRub = new ProveedorRubro(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return proRub;
    }

    @Override
    public List<ProveedorRubro> readAll() throws Exception {
         List<ProveedorRubro> proveedorrubro = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select p.idProveedorRubro, r.rubroDesc,p.observ,p.estado\n"
                    + "from proveedorrubro p, rubro r\n"
                    + "where r.idRubro =p.idRubro_fk\n"
                    + "group by  p.idProveedorRubro";
            rs = s.executeQuery(q);

            while (rs.next()) {
                proRub = new ProveedorRubro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                proveedorrubro.add(proRub);
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
        return proveedorrubro;
    }
 
    public List<ProveedorRubro> readAllTabla(Object t) throws Exception {

        List<ProveedorRubro> proveedorrubro = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            // s = conexion.createStatement();
            q = "select p.idProveedorRubro, r.rubroDesc,p.observ,p.estado\n"
                    + " from proveedorrubro p, rubro r\n"
                    + " where r.idRubro =p.idRubro_fk and p.fk_idProveedor=? \n"
                    + " group by  p.idProveedorRubro";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int) t);
            rs = ps.executeQuery();
            while (rs.next()) {
                proRub = new ProveedorRubro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                proveedorrubro.add(proRub);
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
        return proveedorrubro;

    }
    
     public List<ProveedorRubro> findForLike(String filtro, String t) throws Exception {
        List<ProveedorRubro> lista = new ArrayList<>();
        try {
            
            q = "SELECT pr.idProveedorRubro, p.razonSocial, r.rubroDesc, pr.observ, pr.estado\n"
                    + " FROM proveedorrubro pr, proveedor p, rubro r\n"
                    + " where pr.fk_idProveedor=p.idProveedor and pr.idRubro_fk=r.idRubro  and "+ filtro +"  like ?\n"
                    + " order by p.razonSocial asc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            proRub= null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                proRub = new ProveedorRubro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5));
                lista.add(proRub);
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
