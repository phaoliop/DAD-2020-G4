/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.CajaChica;
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
public class CajaChicaDao implements ICrudDao<CajaChica> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    CajaChica caja;
    String q;

    @Override
    public void create(CajaChica t) throws Exception {
                 try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into cajachica(fechaCaja, descripcionCaja, egreso, ingreso) "
                                             + "values(?,?,?,?)");
           ps.setString(1, t.getFechaCaja());
           ps.setString(2, t.getDescripCaja());
           ps.setString(3, t.getEgreso());
           ps.setString(4, t.getIngreso());
           
           
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
    public void update(CajaChica t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update cajachica set fechaCaja=?, descripcionCaja=?, egreso=?, ingreso=?  where idCajaChica=?";
            ps = conexion.prepareStatement(q);
                       
            ps.setString(1, t.getFechaCaja());
            ps.setString(2, t.getDescripCaja());
            ps.setString(3, t.getEgreso());
            ps.setString(4, t.getIngreso());
            ps.setInt(5, t.getIdCajaChica());
           

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(CajaChica t) throws Exception {
               try {
            conexion = AccesoDB.obtener();
            q = "delete from cajachica where idCajaChica=?";
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, t.getIdCajaChica());
            
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
             conexion.close();
        }
    }

    @Override
    public CajaChica findForId(Object t) throws Exception {
         caja=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select idCajaChica, fechaCaja, descripcionCaja, egreso, ingreso from cajachica where idCajaChica=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                caja = new CajaChica(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return caja;
    }
    
    public CajaChica findForSuma() throws Exception {
        caja = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select round(sum(egreso),2)TotalEgreso, round(sum(ingreso),2)TotalIngreso, round((sum(ingreso)-sum(egreso)),2)Saldo from cajaChica";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                caja = new CajaChica(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return caja;
    }

    
     public CajaChica findForSuma(String x,String y) throws Exception {
        caja = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select round(sum(egreso),2)TotalEgreso, round(sum(ingreso),2)TotalIngreso, "
                    + "round((sum(ingreso)-sum(egreso)),2)Saldo from cajaChica "
                    + "where  fechaCaja  between '"+x+"' and '"+y+"'";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                caja = new CajaChica(rs.getString(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return caja;
    }
      
      public List<CajaChica> findForDates(String x,String t) throws Exception {
        List<CajaChica> lista = new ArrayList<>();
        try {
            
            q = "select idCajaChica, fechaCaja, descripcionCaja, egreso, ingreso\n"
                    + "from cajachica where  fechaCaja  between ? and ? \n"
                    + "group by idCajaChica order by fechaCaja asc;";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, x);
            ps.setString(2, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            caja = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                caja= new CajaChica(rs.getInt(1), rs.getString(2), rs.getString(3),
                                   rs.getString(4), rs.getString(5));
                lista.add(caja);
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
    public List<CajaChica> readAll() throws Exception {
         List<CajaChica> cajaChica = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idCajaChica, fechaCaja, descripcionCaja, egreso, ingreso from cajachica order by  fechaCaja desc, idCajaChica desc";
            rs = s.executeQuery(q);

            while (rs.next()) {
                caja = new CajaChica(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                cajaChica.add(caja);
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
        return cajaChica;
    }
    
}
