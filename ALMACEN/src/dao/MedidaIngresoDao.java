/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.MedidaIngreso;
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
public class MedidaIngresoDao implements ICrudDao<MedidaIngreso>{

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    MedidaIngreso medI;
    String q;
    
    @Override
    public void create(MedidaIngreso t) throws Exception {
         try {

            conexion = AccesoDB.obtener();
             ps = conexion.prepareStatement("Insert into medidasingreso(fkidOrdenTrabajo, medida) "
                                          + "values(?,?)");
           ps.setInt(1, t.getIdOrdenTrabajo());
           ps.setString(2, t.getMedida());
           
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
    public void update(MedidaIngreso t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update medidasingreso set fkidOrdenTrabajo=?, medida=? where idMedidasIngreso=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1,t.getIdOrdenTrabajo());
            ps.setString(2,t.getMedida());
            ps.setInt(3, t.getIdMedidaIngreso());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(MedidaIngreso t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from medidasingreso where idMedidasIngreso=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdMedidaIngreso());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public MedidaIngreso findForId(Object t) throws Exception {
        medI=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select idMedidasIngreso,fkidOrdenTrabajo, medida from medidasingreso where idMedidasIngreso=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                medI = new MedidaIngreso( rs.getInt(1), rs.getInt(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return medI;
    }

    @Override
    public List<MedidaIngreso> readAll() throws Exception {
        List<MedidaIngreso> medidaI = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idMedidasIngreso,fkidOrdenTrabajo, medida from medidasingreso";
            rs = s.executeQuery(q);

            while (rs.next()) {
                medI = new MedidaIngreso(rs.getInt(1), rs.getInt(2), rs.getString(3));
                medidaI.add(medI);
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
        return medidaI;
    }
    
}
