/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetalleOrdenCompra;
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
public class DetalleOrdenCompraDao implements ICrudDao<DetalleOrdenCompra>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DetalleOrdenCompra detordcompra;
    String q;

    
    @Override
    public void create(DetalleOrdenCompra t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into detalleordencompra(doc_oc_id, doc_item, doc_cantidad, "
                    + "doc_desc, doc_detalleDesc, doc_precioUnit) values(?,?,?,?,?,?)");

            ps.setInt(1, t.getDoc_idOrdenComprafk());
            ps.setString(2, t.getDoc_item());
            ps.setString(3, t.getDoc_cantidad());
            ps.setString(4, t.getDoc_desc());
            ps.setString(5, t.getDoc_detalleDesc());
            ps.setString(6, t.getDoc_precioUnit());

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
    public void update(DetalleOrdenCompra t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update detalleordencompra set  doc_item=?, doc_cantidad=?, "
                    + "doc_desc=?, doc_detalleDesc=?, doc_precioUnit=? where doc_id=?";
            
            ps = conexion.prepareStatement(q);
            
            ps.setString(1, t.getDoc_item());
            ps.setString(2, t.getDoc_cantidad());
            ps.setString(3, t.getDoc_desc());
            ps.setString(4, t.getDoc_detalleDesc());
            ps.setString(5, t.getDoc_precioUnit());
            ps.setInt(6, t.getDoc_id());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            //conexion.close();
        }
    }

    @Override
    public void delete(DetalleOrdenCompra t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from detalleordencompra where doc_id=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getDoc_id());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public DetalleOrdenCompra findForId(Object t) throws Exception {
        detordcompra = null;
        try {

            q = "select doc_id, doc_oc_id, doc_item, doc_cantidad, doc_desc, doc_detalleDesc, doc_precioUnit "
                    + "from detalleordencompra where doc_id=?";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detordcompra = new DetalleOrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detordcompra;
    }
    
    public List<DetalleOrdenCompra> findForidOrdC(int t) throws Exception {
        List<DetalleOrdenCompra> lista = new ArrayList<>();
        try {

            q = "select doc_id, doc_item, doc_cantidad, doc_desc, doc_precioUnit, round(doc_precioUnit*doc_cantidad,2)"
                    + " from detalleordencompra where doc_oc_id=? order by doc_id asc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            detordcompra= null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                detordcompra = new DetalleOrdenCompra(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6));
                lista.add(detordcompra);
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
    
    public DetalleOrdenCompra findForPagos(Object t) throws Exception {
        detordcompra = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  round(SUM(doc_cantidad*doc_precioUnit),2)Subtotal,\n"
                    + "round((SUM(doc_cantidad*doc_precioUnit)*0.18),2)IGV, \n"
                    + "round((SUM(doc_cantidad*doc_precioUnit)*1.18),2)Total\n"
                    + "from detalleordencompra where doc_oc_id=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detordcompra = new DetalleOrdenCompra(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detordcompra;
    }

    @Override
    public List<DetalleOrdenCompra> readAll() throws Exception {
        List<DetalleOrdenCompra> detOrdCompra = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from detalleordencompra";
            rs = s.executeQuery(q);

            while (rs.next()) {
                detordcompra = new DetalleOrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                detOrdCompra.add(detordcompra);
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
        return detOrdCompra;
    }
}


