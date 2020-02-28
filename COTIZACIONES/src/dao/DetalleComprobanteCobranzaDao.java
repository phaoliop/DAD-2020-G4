/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetalleComprobanteCobranza;
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
public class DetalleComprobanteCobranzaDao implements ICrudDao<DetalleComprobanteCobranza> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DetalleComprobanteCobranza detCob;
    String q;

    @Override
    public void create(DetalleComprobanteCobranza t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into detallecomprobantecobranza(idComprobanteCobranza_fk, item, cantidad, descripcion, detalleDescrip, precioUnidad) values(?,?,?,?,?,?)");
            ps.setInt(1, t.getIdComprobanteCobranza());
            ps.setInt(2, t.getItem());
            ps.setInt(3, t.getCantidad());
            ps.setString(4, t.getDescripcion());
            ps.setString(5, t.getDetalleDescrip());
            ps.setFloat(6, t.getPrecioUnitario());

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
    public void update(DetalleComprobanteCobranza t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update detallecomprobantecobranza set item=?, cantidad=?, detalleDescrip=?, precioUnidad=?  where descripcion=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getItem());
            ps.setInt(2, t.getCantidad());
            ps.setString(3, t.getDetalleDescrip());
            ps.setFloat(4, t.getPrecioUnitario());
            ps.setString(5, t.getDescripcion());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(DetalleComprobanteCobranza t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from detallecomprobantecobranza where descripcion=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getDescripcion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public DetalleComprobanteCobranza findForId(Object t) throws Exception {
        detCob = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idComprobanteCobranza_fk, item, cantidad, descripcion, detalleDescrip, precioUnidad from detallecomprobantecobranza where descripcion=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detCob = new DetalleComprobanteCobranza(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getFloat(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detCob;
    }

    public List<DetalleComprobanteCobranza> findForidCob(int t) throws Exception {
        List<DetalleComprobanteCobranza> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select item, cantidad, descripcion, precioUnidad, (cantidad*preciounidad)importe from detallecomprobantecobranza where idComprobanteCobranza_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            detCob = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                detCob = new DetalleComprobanteCobranza(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5));
                lista.add(detCob);
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

    public DetalleComprobanteCobranza findForPagos(Object t) throws Exception {
        detCob = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  SUM(cantidad*precioUnidad)Subtotal, ((SUM(cantidad*precioUnidad)*18)/100)IGV, (((SUM(cantidad*precioUnidad)*18)/100)+SUM(cantidad*precioUnidad))Total from detallecomprobantecobranza where idComprobanteCobranza_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detCob = new DetalleComprobanteCobranza(rs.getFloat(1), rs.getFloat(2), rs.getFloat(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detCob;
    }

    @Override
    public List<DetalleComprobanteCobranza> readAll() throws Exception {
        List<DetalleComprobanteCobranza> detalleCob = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select item, cantidad, descripcion, detalleDescrip, precioUnidad from detallecomprobantecobranza";
            rs = s.executeQuery(q);

            while (rs.next()) {
                detCob = new DetalleComprobanteCobranza(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5));
                detalleCob.add(detCob);
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
        return detalleCob;
    }

}
