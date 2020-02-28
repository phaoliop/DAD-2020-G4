/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.DetallePedido;
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
public class DetallePedidoDao implements ICrudDao<DetallePedido> {
    
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    DetallePedido detPed;
    String q;

    @Override
    public void create(DetallePedido t) throws Exception {
//         try {
//
//            conexion = AccesoDB.obtener();
//            ps = conexion.prepareStatement("Insert into detallepedido(fk_idPedido, item, cantidad, descripcion, detalleDescrip, precioUnitario) values(?,?,?,?,?,?)");
//            ps.setInt(1, t.getIdPedido());
//            ps.setString(2, t.getItem());
//            ps.setString(3, t.getCantidad());
//            ps.setString(4, t.getDescripcion()); 
//            ps.setString(5, t.getDetalleDescrip());
//            ps.setString(6, t.getPrecioUnitario());
//            
//
//            ps.executeUpdate();
//            ps.close();
//
//        } catch (ClassNotFoundException e1) {
//            System.out.println("Error:" + e1.getMessage());
//            System.exit(0);
//        } catch (SQLException e2) {
//            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
//            System.exit(0);
//        } finally {
//            conexion.close();
//        }
    }

    @Override
    public void update(DetallePedido t) throws Exception {
        
//        try {
//            conexion = AccesoDB.obtener();         
//            q = "update detallepedido set item=?, cantidad=?, descripcion=?, detalleDescrip=?, precioUnitario=? where idDetallePedido=?";
//            ps = conexion.prepareStatement(q);
//            ps.setString(1, t.getItem());
//            ps.setString(2, t.getCantidad());
//            ps.setString(3, t.getDescripcion());
//            ps.setString(4, t.getDetalleDescrip());
//            ps.setString(5, t.getPrecioUnitario());
//            ps.setInt(6, t.getIdDetallePedido());
//            
//            
//
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            conexion.close();
//        }
    }

    @Override
    public void delete(DetallePedido t) throws Exception {
      
//          try {
//            conexion = AccesoDB.obtener();
//            q = "delete from detallepedido where idDetallePedido=?";
//            ps = conexion.prepareStatement(q);
//            ps.setInt(1, t.getIdDetallePedido());
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//             conexion.close();
//        }
    }

    @Override
    public DetallePedido findForId(Object t) throws Exception {
//        detPed=null; 
//        try {
//           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
//           q="select fk_idPedido, item, cantidad, descripcion, detalleDescrip, precioUnitario from detallepedido "
//                   + "where idDetallePedido=?";
//           conexion = AccesoDB.obtener();
//            ps = conexion.prepareStatement(q);
//            //preparar valor del parametro
//            ps.setInt(1, (int)t);
//            //ejecutar consulta
//            rs = ps.executeQuery();           
//            if (rs.next()) {
//                //crear objeto pro y asignar valores
//                detPed = new DetallePedido(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6));               
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            conexion.close();
//        }
        return detPed;
    }   
    
     public DetallePedido findForId(Object t, Object id) throws Exception {
//        detPed=null; 
//        try {
//           // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
//           q="select idDetallePedido,fk_idPedido, item, cantidad, descripcion, detalleDescrip, precioUnitario from detallepedido "
//                   + "where fk_idPedido=? and idDetallePedido=?";
//           conexion = AccesoDB.obtener();
//            ps = conexion.prepareStatement(q);
//            //preparar valor del parametro
//            ps.setInt(1, (int)t);
//            ps.setInt(2, (int)id);
//            //ejecutar consulta
//            rs = ps.executeQuery();           
//            if (rs.next()) {
//                //crear objeto pro y asignar valores
//                detPed = new DetallePedido(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getString(6),rs.getString(7));               
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            conexion.close();
//        }
        return detPed;
    }
     
    public DetallePedido findForPagos(Object t) throws Exception {
//        detPed = null;
//        try {
//            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
//            q = "select  round(SUM(cantidad*precioUnitario),2)Subtotal,\n"
//                    + "round((SUM(cantidad*precioUnitario)*0.18),2)IGV, \n"
//                    + "round((SUM(cantidad*precioUnitario)*1.18),2)Total\n"
//                    + "from detallepedido where fk_idPedido=?";
//            conexion = AccesoDB.obtener();
//            ps = conexion.prepareStatement(q);
//            //preparar valor del parametro
//            ps.setInt(1, (int) t);
//            //ejecutar consulta
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                //crear objeto pro y asignar valores
//                detPed = new DetallePedido(rs.getString(1), rs.getString(2), rs.getString(3));
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            conexion.close();
//        }
        return detPed;
    }

    public List<DetallePedido> findForidPed(int t) throws Exception {
        List<DetallePedido> lista = new ArrayList<>();
//        try {
//            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
//            q = "select idDetallePedido, item, cantidad, descripcion, precioUnitario, round(precioUnitario*1.18,2),"
//                    + "round(cantidad*precioUnitario,2)importe from detallepedido where fk_idPedido=? order by idDetallePedido asc";
//            conexion = AccesoDB.obtener();
//            ps = conexion.prepareStatement(q);
//            //preparar valor del parametro
//            ps.setInt(1, t);
//            //ejecutar consulta
//            rs = ps.executeQuery();
//            detPed = null;
//            while (rs.next()) {
//                //crear objeto pro y asignar valores
//                detPed = new DetallePedido( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
//                lista.add(detPed);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            conexion.close();
//        }
        return lista;
    }
    
    @Override
    public List<DetallePedido> readAll() throws Exception {
        List<DetallePedido> detallePed = new ArrayList<>();

//        try {
//            conexion = AccesoDB.obtener();
//            s = conexion.createStatement();
//            q = "select item, cantidad, descripcion, detalleDescrip, precioUnitario from detallepedido";
//            rs = s.executeQuery(q);
//
//            while (rs.next()) {
//                detPed = new DetallePedido(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6));
//                detallePed.add(detPed);
//            }
//            s.close();
//            rs.close();
//
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            try {
//                conexion.close();
//            } catch (SQLException e) {
//            }
//        }
        return detallePed;
    }

}

 
  
    

