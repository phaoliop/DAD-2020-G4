/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetalleProforma;
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
public class DetalleProformaDao implements ICrudDao<DetalleProforma> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DetalleProforma detProf;
    String q;
 
    public void create(DetalleProforma t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into detalleproforma(idProforma_fk, item, cantidad, descripcion, detalleDescrip,precioUnitario) values(?,?,?,?,?,?)");
            ps.setInt(1, t.getIdProforma());
            ps.setString(2, t.getItem());
            ps.setString(3, t.getCantidad());
            ps.setString(4, t.getDescripcion());
            ps.setString(5, t.getDetalleDescrip());
            ps.setString(6, t.getPrecioUnitario());
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
    public void update(DetalleProforma t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update detalleproforma set item=?, cantidad=?, descripcion=?, detalleDescrip=?, "
                    + "precioUnitario=? where idDetalleProforma=?";
            ps = conexion.prepareStatement(q);
            ps.setString(1, t.getItem());
            ps.setString(2, t.getCantidad());
            ps.setString(3, t.getDescripcion());
            ps.setString(4, t.getDetalleDescrip());
            ps.setString(5, t.getPrecioUnitario());
            ps.setInt(6, t.getIdDetalleProforma());
            

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(DetalleProforma t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from detalleproforma where idDetalleProforma=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdDetalleProforma());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
     public DetalleProforma findForId(Object t) throws Exception {
        detProf = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idProforma_fk, item, cantidad, descripcion, detalleDescrip, precioUnitario"
                    + " from detalleproforma where descripcion=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detProf = new DetalleProforma(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detProf;
    }
     
    public DetalleProforma findForId(Object t, Object id) throws Exception {
        detProf = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idProforma_fk, item, cantidad, descripcion, detalleDescrip, precioUnitario "
                    + "from detalleproforma where idProforma_fk=? and idDetalleProforma=? ";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t );
            ps.setInt(2, (int) id);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detProf = new DetalleProforma(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detProf;
    }

    public List<DetalleProforma> findForidProf(int t) throws Exception {
        List<DetalleProforma> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idDetalleProforma, item, cantidad, descripcion, precioUnitario, "
                    + "round(precioUnitario*1.18,2),round(cantidad*precioUnitario,2)importe "
                    + "from detalleproforma where idProforma_fk=? order by idDetalleProforma asc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            detProf = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                detProf = new DetalleProforma( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(detProf);
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

    public DetalleProforma findForPagos(Object t) throws Exception {
        detProf = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  round(SUM(cantidad*precioUnitario),2)Subtotal,\n"
                    + "round((SUM(cantidad*precioUnitario)*0.18),2)IGV, \n"
                    + "round((SUM(cantidad*precioUnitario)*1.18),2)Total\n"
                    + "from detalleproforma where idProforma_fk=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detProf = new DetalleProforma(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detProf;
    }
    
    public DetalleProforma findForTotal(String fecha,Object t) throws Exception {
        detProf = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select round((round((SUM(cantidad*precioUnitario)*1.18),2)*T.cambioVenta),3)Total, T.cambioVenta\n"
                    + "	from detalleproforma D, tipodecambio T where D.idProforma_fk=? and T.fechaCambio='" + fecha + "'";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                detProf = new DetalleProforma(rs.getString(1), rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return detProf;
    }
   @Override
    public List<DetalleProforma> readAll() throws Exception {
        List<DetalleProforma> detalleProf = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select item, cantidad, descripcion, detalleDescrip, precioUnitario from detalleproforma";
            rs = s.executeQuery(q);

            while (rs.next()) {
                detProf = new DetalleProforma(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                detalleProf.add(detProf);
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
        return detalleProf;
    }

 
}
