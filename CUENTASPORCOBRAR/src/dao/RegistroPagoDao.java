/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.RegistroPago;
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
public class RegistroPagoDao implements ICrudDao<RegistroPago>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    RegistroPago rePa;
    String q;
    
    @Override
    public void create(RegistroPago t) throws Exception {

       try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into registropago(idRegistroVenta_fk ,"
                   + "tipoPago,fechaAbono, moneda, montoAbonado, numeroOperacion, "
                   + "observacion ) values(?,?,?,?,?,?,?)");
           
           ps.setInt(1, t.getIdRegistroVenta());
           ps.setString(2, t.getTipoPago());
           ps.setString(3, t.getFechaAbono());
           ps.setString(4, t.getMoneda());
           ps.setString(5, t.getMontoAbonado());
           ps.setString(6, t.getNumeroOperacion());
           ps.setString(7, t.getObservacion());
           
           
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
    public void update(RegistroPago t) throws Exception {
         try {
            conexion = AccesoDB.obtener();
             q = "update registropago set  idRegistroVenta_fk =?,tipoPago=?,"
                     + "fechaAbono=?, moneda=?, montoAbonado=?, numeroOperacion=?, observacion=?"
                     + " where idRegistroPago=?";
            
            ps = conexion.prepareStatement(q);

           
           ps.setInt(1, t.getIdRegistroVenta());
           ps.setString(2, t.getTipoPago());
           ps.setString(3, t.getFechaAbono());
           ps.setString(4, t.getMoneda());
           ps.setString(5, t.getMontoAbonado());
           ps.setString(6, t.getNumeroOperacion());
           ps.setString(7, t.getObservacion());
           ps.setInt(8, t.getIdRegistroPago());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(RegistroPago t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from registropago where idRegistroPago=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdRegistroPago());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public RegistroPago findForId(Object t) throws Exception {
        rePa=null; 
        try {

            q = "select idRegistroPago, idRegistroVenta_fk ,tipoPago,fechaAbono,"
                    + " moneda, montoAbonado, numeroOperacion, observacion "
                    + " from registropago where idRegistroPago=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rePa = new RegistroPago(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return rePa;
    }
    
    public RegistroPago findTotalSoles(Object t, Object x) throws Exception {
        rePa=null; 
        try {

            q = "select if(round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3)) TotalAbonado, \n"
                    + "	round((rv.montoFacturado*(if(rv.moneda='DOLARES AMERICANOS',t.cambioVenta,1))),3)TotalFacturado,\n"
                    + " if(round(((rv.montoFacturado*(if(rv.moneda='DOLARES AMERICANOS',t.cambioVenta,1)))-(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)))),3) is null, round((rv.montoFacturado*(if(rv.moneda='DOLARES AMERICANOS',t.cambioVenta,1))),3), "
                    + "    round(((rv.montoFacturado*(if(rv.moneda='DOLARES AMERICANOS',t.cambioVenta,1)))-(sum(round(rp.montoAbonado*(if(rp.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)))),3))Resto\n"
                    + "	from registroventa rv   left join registropago rp on rv.idRegistroVenta= rp.idRegistroVenta_fk, tipodecambio t\n"
                    + "where rv.idRegistroVenta=? and t.fechaCambio=? group by rv.idRegistroVenta";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rePa = new RegistroPago(rs.getString(1), rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return rePa;
    }
    
    public RegistroPago findTotalDolares(Object t,Object x) throws Exception {
        rePa=null; 
        try {

            q = "select if(round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)),3)) TotalAbonado, \n"
                    + "	round((rv.montoFacturado/(if(rv.moneda='SOLES',t.cambioVenta,1))),3)TotalFacturado,\n"
                    + " if(round(((rv.montoFacturado/(if(rv.moneda='SOLES',t.cambioVenta,1)))-(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)))),3) is null, round((rv.montoFacturado/(if(rv.moneda='SOLES',t.cambioVenta,1))),3), round(((rv.montoFacturado/(if(rv.moneda='SOLES',t.cambioVenta,1)))-(sum(round(rp.montoAbonado/(if(rp.moneda='SOLES',t.cambioVenta,1)),3)))),3))Resto\n"
                    + "	from registroventa rv   left join registropago rp on rv.idRegistroVenta= rp.idRegistroVenta_fk, tipodecambio t\n"
                    + "where rv.idRegistroVenta=? and t.fechaCambio=? group by rv.idRegistroVenta;";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rePa = new RegistroPago(rs.getString(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return rePa;
    }
    

    @Override
    public List<RegistroPago> readAll() throws Exception {
        List<RegistroPago> registro= new ArrayList<>();
        
        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "SELECT * FROM registropago;";
            rs = s.executeQuery(q);

            while (rs.next()) {
                rePa = new RegistroPago(rs.getInt(1),    rs.getInt(2),    rs.getString(3), 
                                        rs.getString(4), rs.getString(5), rs.getString(6), 
                                        rs.getString(7), rs.getString(8));
                registro.add(rePa);
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
        return registro;
    }
    
    public List<RegistroPago> findForidReVe(int t) throws Exception {
        List<RegistroPago> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idRegistroPago, idRegistroVenta_fk ,tipoPago,fechaAbono,"
                    + " moneda, montoAbonado, numeroOperacion, observacion "
                    + " from registropago where idRegistroVenta_fk=? order by idRegistroPago asc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            rePa = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                rePa = new RegistroPago( rs.getInt(1),   rs.getInt(2),    rs.getString(3), 
                                        rs.getString(4), rs.getString(5), rs.getString(6), 
                                        rs.getString(7), rs.getString(8));
                lista.add(rePa);
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
