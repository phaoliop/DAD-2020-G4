/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.MedidaSalida;
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
public class MedidaSalidaDao implements ICrudDao<MedidaSalida>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    MedidaSalida medS;
    String q;

    @Override
    public void create(MedidaSalida t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
             ps = conexion.prepareStatement("Insert into medidassalida(idOrdenTrabajo_fk, medida) "
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
    public void update(MedidaSalida t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update medidassalida set idOrdenTrabajo_fk=?, medida=? where idMedidasSalida=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1,t.getIdOrdenTrabajo());
            ps.setString(2,t.getMedida());
            ps.setInt(3, t.getIdMedidaSalida());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(MedidaSalida t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from medidassalida where idMedidasSalida=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdMedidaSalida());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public MedidaSalida findForId(Object t) throws Exception {
        medS=null; 
        try {
           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
           q="select idMedidasSalida, idOrdenTrabajo_fk, medida from medidassalida where idMedidasSalida=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                medS = new MedidaSalida( rs.getInt(1), rs.getInt(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return medS;
    }

    @Override
    public List<MedidaSalida> readAll() throws Exception {
        List<MedidaSalida> medidaS = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select idMedidasSalida, idOrdenTrabajo_fk, medida from medidassalida";
            rs = s.executeQuery(q);

            while (rs.next()) {
                medS = new MedidaSalida(rs.getInt(1), rs.getInt(2), rs.getString(3));
                medidaS.add(medS);
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
        return medidaS;
    }
    
    
}
