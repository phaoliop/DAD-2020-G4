/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.RegistroPagoCompra;
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
public class RegistroPagoCompraDao implements ICrudDao<RegistroPagoCompra>{
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    RegistroPagoCompra rePaCo;
    String q;

    @Override
    public void create(RegistroPagoCompra t) throws Exception {
        
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into registropagocompra(idRegistroComprafk ,"
                   + "tipoPago,fechaAbono, moneda, montoAbonado, numeroOperacion, "
                   + "observacion ) values(?,?,?,?,?,?,?)");
           
           ps.setInt(1, t.getIdRegistroComprafk());
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
    public void update(RegistroPagoCompra t) throws Exception {
        
        try {
            conexion = AccesoDB.obtener();
             q = "update registropagocompra set  idRegistroComprafk=?, tipoPago=?,"
                     + "fechaAbono=?, moneda=?, montoAbonado=?, numeroOperacion=?, observacion=?"
                     + " where idRegistroPagoCompra=?";
            
            ps = conexion.prepareStatement(q);

           
           ps.setInt(1, t.getIdRegistroComprafk());
           ps.setString(2, t.getTipoPago());
           ps.setString(3, t.getFechaAbono());
           ps.setString(4, t.getMoneda());
           ps.setString(5, t.getMontoAbonado());
           ps.setString(6, t.getNumeroOperacion());
           ps.setString(7, t.getObservacion());
           ps.setInt(8, t.getIdRegistroPagoCompra());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(RegistroPagoCompra t) throws Exception {
        
        try {
            conexion = AccesoDB.obtener();
            q = "delete from registropagocompra where idRegistroPagoCompra=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getIdRegistroPagoCompra());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public RegistroPagoCompra findForId(Object t) throws Exception {
        
        rePaCo=null; 
        try {

            q = "select idRegistroPagoCompra, idRegistroComprafk, tipoPago,fechaAbono,"
                    + " moneda, montoAbonado, numeroOperacion, observacion "
                    + " from registropagocompra where idRegistroPagoCompra=?";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rePaCo = new RegistroPagoCompra(rs.getInt(1), rs.getInt(2),
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
        return rePaCo;
    }

    
    
     public RegistroPagoCompra findTotalSoles(Object t, Object x) throws Exception {
        rePaCo=null; 
        try {

            q = "select if(round(sum(round(rpc.montoAbonado*(if(rpc.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rpc.montoAbonado*(if(rpc.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)),3)) TotalAbonado, \n"
                    + "  round((rc.montoFacturado *(if(rc.moneda='DOLARES AMERICANOS',t.cambioVenta,1))),3)TotalFacturado,\n"
                    + "  if(round(((rc.montoFacturado*(if(rc.moneda='DOLARES AMERICANOS',t.cambioVenta,1)))-(sum(round(rpc.montoAbonado*(if(rpc.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)))),3) is null, round((rc.montoFacturado*(if(rc.moneda='DOLARES AMERICANOS',t.cambioVenta,1))),3), \n"
                    + "   round(((rc.montoFacturado*(if(rc.moneda='DOLARES AMERICANOS',t.cambioVenta,1)))-(sum(round(rpc.montoAbonado*(if(rpc.moneda='DOLARES AMERICANOS',t.cambioVenta,1)),3)))),3))Resto\n"
                    + "   from registrocompra rc   left join registropagocompra rpc on rc.idRegistroCompra= rpc.idRegistroComprafk, \n"
                    + "   tipodecambio t\n"
                    + " where rc.idRegistroCompra=? and t.fechaCambio=? group by rc.idRegistroCompra";
           conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rePaCo = new RegistroPagoCompra(rs.getString(1), rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return rePaCo;
    }
    
    public RegistroPagoCompra findTotalDolares(Object t,Object x) throws Exception {
        rePaCo=null; 
        try {

            q = "select if(round(sum(round(rpc.montoAbonado/(if(rpc.moneda='SOLES',t.cambioVenta,1)),3)),3) is null , '00.00',round(sum(round(rpc.montoAbonado/(if(rpc.moneda='SOLES',t.cambioVenta,1)),3)),3)) TotalAbonado,\n"
                    + " round((rc.montoFacturado/(if(rc.moneda='SOLES',t.cambioVenta,1))),3)TotalFacturado,\n"
                    + " if(round(((rc.montoFacturado/(if(rc.moneda='SOLES',t.cambioVenta,1)))-(sum(round(rpc.montoAbonado/(if(rpc.moneda='SOLES',t.cambioVenta,1)),3)))),3) is null, round((rc.montoFacturado/(if(rc.moneda='SOLES',t.cambioVenta,1))),3), round(((rc.montoFacturado/(if(rc.moneda='SOLES',t.cambioVenta,1)))-(sum(round(rpc.montoAbonado/(if(rpc.moneda='SOLES',t.cambioVenta,1)),3)))),3))Resto\n"
                    + " from registrocompra rc  left join registropagocompra rpc on rc.idRegistroCompra= rpc.idRegistroComprafk , tipodecambio t\n"
                    + "    where rc.idRegistroCompra=? and t.fechaCambio=? group by rc.idRegistroCompra;";
           
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int)t);
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();           
            if (rs.next()) {
                //crear objeto pro y asignar valores
                rePaCo = new RegistroPagoCompra(rs.getString(1),rs.getString(2), rs.getString(3));               
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return rePaCo;
    }
    
    
    
    @Override
    public List<RegistroPagoCompra> readAll() throws Exception {
        
        List<RegistroPagoCompra> registro= new ArrayList<>();
        
        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "SELECT * FROM registropagocompra;";
            rs = s.executeQuery(q);

            while (rs.next()) {
                rePaCo = new RegistroPagoCompra(rs.getInt(1),    rs.getInt(2),    rs.getString(3), 
                                                rs.getString(4), rs.getString(5), rs.getString(6), 
                                                rs.getString(7), rs.getString(8));
                registro.add(rePaCo);
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
    
    public List<RegistroPagoCompra> findForidReVe(int t) throws Exception {
        List<RegistroPagoCompra> lista = new ArrayList<>();
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idRegistroPagoCompra, idRegistroComprafk, tipoPago, fechaAbono,"
                    + " moneda, montoAbonado, numeroOperacion, observacion "
                    + " from registropagocompra where idRegistroComprafk=? order by fechaAbono desc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, t);
            //ejecutar consulta
            rs = ps.executeQuery();
            rePaCo = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                rePaCo = new RegistroPagoCompra( rs.getInt(1),   rs.getInt(2),    rs.getString(3), 
                                        rs.getString(4), rs.getString(5), rs.getString(6), 
                                        rs.getString(7), rs.getString(8));
                lista.add(rePaCo);
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
