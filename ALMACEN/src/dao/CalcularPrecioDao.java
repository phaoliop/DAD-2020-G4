/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.CalcularPrecio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author ARCRODINPC-06
 */
public class CalcularPrecioDao implements ICrudDao<CalcularPrecio> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    CalcularPrecio calPre;
    String q;

    @Override
    public void create(CalcularPrecio t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CalcularPrecio t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CalcularPrecio t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CalcularPrecio findForId(Object t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public CalcularPrecio findForDiaForDolares(float longitud, Object t) throws Exception {
        calPre = null;
        try {

            q = "select  nombre, round(((("+longitud+"/1000)*precio)*0.18),2)IGV,\n"
                    + "round((("+longitud+"/1000)*precio),2)PrecioVentaSinIgvDolares,\n"
                    + "round(((("+longitud+"/1000)*precio)*1.18),2)PrecioVentaConIgvDolares\n"
                    + "from  listaprecio \n"
                    + "where  diametro=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                calPre = new CalcularPrecio(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return calPre;
    }
    
    public CalcularPrecio findForDiaForSoles(float longitud, String fecha, Object t) throws Exception {
        calPre = null;
        try {
            
            q = "select  L.nombre,  round((((("+longitud+"/1000)*precio)*0.18)*T.cambioVenta),2)IgvEnSoles,\n"
                    + "round(((("+longitud+"/1000)*L.precio)*T.cambioVenta),2)PrecioVentaSinIgvSoles,\n"
                    + "round((((("+longitud+"/1000)*L.precio)*1.18)*T.cambioVenta),2)PrecioVentaConIgvSoles\n"
                    + "from tipodecambio T, listaprecio L\n"
                    + "where T.fechaCambio='"+fecha+"'and L.diametro=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                calPre = new CalcularPrecio(rs.getString(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return calPre;
    }
    
    
    @Override
    public List<CalcularPrecio> readAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
