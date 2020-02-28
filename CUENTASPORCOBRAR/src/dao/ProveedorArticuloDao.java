/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.ArticuloProveedor;
import entity.ProveedorArticulo;
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
public class ProveedorArticuloDao implements ICrudDao<ProveedorArticulo> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ProveedorArticulo proArt;
    String q;

    @Override
    public void create(ProveedorArticulo t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into proveedorarticulo(idProveedorfk,idArticuloProveedorfk,"
                        + "PrecioCompra,ObservArtProv) values(?,?,?,?)");
            ps.setInt(1, t.getIdProv());
            ps.setInt(2, t.getIdArtProv());
            ps.setString(3, t.getPrecio());
            ps.setString(4, t.getObs());

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
    public void update(ProveedorArticulo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update proveedorarticulo set idProveedorfk=?, idArticuloProveedorfk=?, PrecioCompra=?,"
                    + "ObservArtProv=? where idProveedorArticulo=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdProv());
            ps.setInt(2, t.getIdArtProv());
            ps.setString(3, t.getPrecio());
            ps.setString(4, t.getObs());
            ps.setInt(5, t.getIdProvArtPk());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(ProveedorArticulo t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from proveedorarticulo where idProveedorArticulo=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdProvArtPk());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public ProveedorArticulo findForId(Object t) throws Exception {
        proArt=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idProveedorArticulo, idProveedorfk, idArticuloProveedorfk, PrecioCompra, ObservArtProv "
                    + " from proveedorarticulo where idProveedorArticulo=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                proArt = new ProveedorArticulo(rs.getInt(1),rs.getInt(2), rs.getInt(3),rs.getString(4),rs.getString(5));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
           conexion.close();
        }
        return proArt;
    }

    @Override
    public List<ProveedorArticulo> readAll() throws Exception {
         List<ProveedorArticulo> proveedorArt = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select p.idProveedorArticulo, a.descripcion , p.PrecioCompra, p.ObservArtProv \n"
                    + "from proveedorarticulo p, articulosproveedores a  \n"
                    + "where a.idArticulosProveedores=p.idArticuloProveedorfk\n"
                    + "group by  p.idProveedorArticulo";
            rs = s.executeQuery(q);

            while (rs.next()) {
                proArt = new ProveedorArticulo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
                proveedorArt.add(proArt);
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
        return proveedorArt;
    }
    
 public List<ProveedorArticulo> readAllTabla(Object t) throws Exception {

        List<ProveedorArticulo> proveedorArt = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            // s = conexion.createStatement();
            q = "select p.idProveedorArticulo, a.descripcion , p.PrecioCompra, p.ObservArtProv \n"
                    + "from proveedorarticulo p, articulosproveedores a  \n"
                    + "where a.idArticulosProveedores=p.idArticuloProveedorfk "
                    + " and p.idProveedorfk=? \n"
                    + "group by  p.idProveedorArticulo";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int) t);
            rs = ps.executeQuery();
            while (rs.next()) {
                proArt = new ProveedorArticulo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4));
                proveedorArt.add(proArt);
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
        return proveedorArt;    }
 
 public List<ProveedorArticulo> findForLike(String filtro, String t) throws Exception {
        List<ProveedorArticulo> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "SELECT pa.idProveedorArticulo, p.razonSocial, ap.descripcion, pa.PrecioCompra, pa.ObservArtProv\n"
                    + " FROM  proveedorarticulo pa, proveedor p, articulosproveedores ap\n"
                    + " where pa.idProveedorfk=p.idProveedor and  pa.idArticuloProveedorfk=ap.idArticulosProveedores "
                    + " and "+filtro+"  like ?\n"
                    + " order by p.razonSocial asc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, "%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            proArt = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                proArt = new ProveedorArticulo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5));
                lista.add(proArt);
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
