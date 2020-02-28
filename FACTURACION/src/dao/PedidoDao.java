/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import entity.Pedido;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ARCRODINPC-02
 */
public class PedidoDao implements ICrudDao<Pedido> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Pedido ped;
    String q;

    @Override
    public void create(Pedido t) throws Exception {

        try {
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into pedido(numPedido, idProforma_fk, idClientefk, "
                    + "fk_idContactoCliente, fk_idUsuario, moneda, formaPago, tipo, fechaPedido, dia1, dia2, ordenCompra, "
                    + "estado, observacion, observacionInterna, campo2, campo3) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, t.getNumPedido());
            ps.setInt(2, t.getIdProforma());
            ps.setInt(3, t.getIdCliente());
            ps.setInt(4, t.getIdContactoCliente());
            ps.setInt(5, t.getIdUsuario());
            ps.setString(6, t.getMoneda());
            ps.setString(7, t.getFormPago());
            ps.setString(8, t.getTipo());
            ps.setString(9, t.getFechaEmision());
            ps.setInt(10, t.getDia1());
            ps.setInt(11,t.getDia2());
            ps.setString(12, t.getOrdenCompra());
            ps.setString(13, t.getEstado());
            ps.setString(14, t.getObservacion());
            ps.setString(15, t.getObservacionInterna());
            ps.setString(16, t.getCampo2());
            ps.setString(17, t.getCampo3());

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
    public void update(Pedido t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            q = "update pedido set  numPedido=?,idProforma_fk=?, idClientefk=?, fk_idContactoCliente=?, fk_idUsuario=?,"
                    + " moneda=?,formaPago=?, tipo=?, fechaPedido=?,dia1=?, dia2=?, ordenCompra=?, estado=?, observacion=?, observacionInterna=?, "
                    + "campo2=?, campo3=? where idPedido=?";
               
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getNumPedido());
            ps.setInt(2, t.getIdProforma());
            ps.setInt(3, t.getIdCliente());
            ps.setInt(4, t.getIdContactoCliente());
            ps.setInt(5, t.getIdUsuario());
            ps.setString(6, t.getMoneda());
            ps.setString(7, t.getFormPago());
            ps.setString(8, t.getTipo());
            ps.setString(9, t.getFechaEmision());
            ps.setInt(10, t.getDia1());
            ps.setInt(11,t.getDia2());
            ps.setString(12, t.getOrdenCompra());
            ps.setString(13, t.getEstado());
            ps.setString(14, t.getObservacion());
            ps.setString(15, t.getObservacionInterna());
            ps.setString(16, t.getCampo2());
            ps.setString(17, t.getCampo3());
            ps.setInt(18, t.getIdPedido());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }
    
    public void updateEstado(Pedido t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            q = "update pedido set estado=? where idPedido=?";
               
            ps = conexion.prepareStatement(q);

            ps.setString(1, t.getEstado());
            ps.setInt(2, t.getIdPedido());
           
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Pedido t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from pedido where idPedido=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdPedido());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Pedido findForId(Object t) throws Exception {
        ped = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idPedido, numPedido, idProforma_fk, idClientefk, fk_idContactoCliente, fk_idUsuario, "
                    + "moneda, formaPago, tipo, fechaPedido, dia1, dia2, ordenCompra,estado, observacion, "
                    + "observacionInterna "
                    + "from pedido where idPedido=? ";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                ped = new Pedido(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getInt(11), rs.getInt(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }

    public Pedido findForId(Object t, Object x) throws Exception {
        ped = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idPedido, numPedido, idProforma_fk, idClientefk, fk_idContactoCliente, fk_idUsuario, "
                    + "moneda, formaPago, tipo, fechaPedido, dia1, dia2, ordenCompra, estado, observacion, "
                    + "observacionInterna "
                    + "from pedido where numPedido=? "
                    + " and year(fechaPedido)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                ped = new Pedido(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), 
                        rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12),
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }
    
    public Pedido findForAnio(Object t, Object x) throws Exception {
        ped = null;
        try {
            q = "select numPedido, year(fechaPedido) from pedido where fechaPedido=? and numPedido=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,  t+"");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                ped = new Pedido(rs.getInt(1), rs.getString(2));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }
    
     public Pedido findForIdForVenta(Object t) throws Exception {
        ped = null;
        try {
            q = "SELECT \n"
                    + "    c.razonSocial,\n"
                    + "    c.ruc,\n"
                    + "    CONCAT(cl.nombres, ' ', cl.apellidos)Contacto,\n"
                    + "    d.numPedido,\n"
                    + "    p.idProforma,\n"
                    + "    p.codProforma,\n"
                    + "    CONCAT(g.serieGuia, ' - ', g.numGuia)GuiaRemision,\n"
                    + "    g.numComprobante, "
                    + "    p.tipo,\n"
                    + "    p.moneda,\n"
                    + "    p.formaPago,\n"
                    + "    round((SUM(dp.cantidad*dp.precioUnitario)*1.18),2)Total\n"
                    + "    \n"
                    + "FROM  pedido d  left join    guiaremision g      on d.numPedido=g.numPedido \n"
                    + "		       left join    proforma p          on d.idProforma_fk=p.idProforma\n"
                    + "                left join    detalleproforma dp  on d.idProforma_fk=dp.idProforma_fk,\n"
                    + "	     cliente c ,contactocliente cl\n"
                    + "WHERE (      d.idClientefk = c.idCliente\n" +
"                              AND d.fk_idContactoCliente = cl.idContactoCliente)\n"
                    + "        AND d.idPedido = ?";
            
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1,  (int)t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                ped = new Pedido(rs.getString(1), rs.getString(2), rs.getString(3),
                                 rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7),
                                 rs.getString(8), rs.getString(9), rs.getString(10), 
                                 rs.getString(11), rs.getString(12));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }
    
    public List<Pedido> findForLike(String filtro,String t) throws Exception {
        List<Pedido> lista = new ArrayList<>();
        try {

            q = "SELECT ped.numPedido, prof.codProforma, cli.razonSocial, concat(concli.nombres,' ',concli.apellidos)contacto,\n"
                    + "ped.fechaPedido, ped.tipo, ped.estado, concat(u.nombres,' ',u.apellidos)usuario, ped.observacionInterna\n"
                    + "FROM pedido ped,proforma prof, cliente cli, contactocliente concli, usuario u\n"
                    + "WHERE (ped.idProforma_fk=prof.idProforma and ped.idClientefk = cli.idCliente \n"
                    + "and ped.fk_idContactoCliente= concli.idContactoCliente and ped.fk_idUsuario=u.idUsuario)"
                    + "and " + filtro + " like ? "
                    + "order by field (ped.estado,'EN PROCESO') desc, "
                    + "ped.fechaPedido desc, ped.numPedido desc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            ped = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                ped = new Pedido(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                                 rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9));
                lista.add(ped);
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

    
    public List<Pedido> findForLikeEP(String filtro,String t) throws Exception {
        List<Pedido> lista = new ArrayList<>();
        try {

            q = "SELECT ped.numPedido, prof.codProforma, cli.razonSocial, concat(concli.nombres,' ',concli.apellidos)contacto,\n"
                    + "ped.fechaPedido, ped.estado, concat(u.nombres,' ',u.apellidos)usuario\n"
                    + "FROM pedido ped,proforma prof, cliente cli, contactocliente concli, usuario u\n"
                    + "WHERE (ped.idProforma_fk=prof.idProforma and ped.idClientefk = cli.idCliente \n"
                    + "and ped.fk_idContactoCliente= concli.idContactoCliente and ped.fk_idUsuario=u.idUsuario)"
                    + "and " + filtro + " like ? and ped.estado='EN PROCESO' order by idPedido desc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            ped = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                ped = new Pedido(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
                lista.add(ped);
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
    
    
    public DefaultComboBoxModel CargarContactosCombo(String nombre) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            conexion = AccesoDB.obtener();
            q = "select A.nombres,A.apellidos from contactocliente A , "
                    + "cliente B where b.razonsocial = ? and A.idcliente_fk = b.idcliente";
            ps = conexion.prepareStatement(q);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            modelo.addElement("Elegir contacto");
            while (rs.next()) {
                modelo.addElement(rs.getString(1) + " " + rs.getString(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return modelo;
    }
    
    public Pedido findLastestId(Object t) throws Exception {
        ped = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  max(idPedido) as idPedido from pedido where year(fechaPedido)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int)t);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ped = new Pedido(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }
    
    public Pedido findForNumPed(Object t) throws Exception {
        ped = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  idPedido, numPedido from pedido where idPedido=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ped = new Pedido(rs.getInt(1), rs.getInt(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }
    
    public Pedido findForNumPedAll(Object t) throws Exception {
        ped = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idPedido, numPedido, idProforma_fk, idClientefk, fk_idContactoCliente, fk_idUsuario, "
                    + "moneda, formaPago, tipo, fechaPedido, dia1, dia2, ordenCompra, estado, observacion,"
                    + " observacionInterna  from pedido where idPedido=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ped = new Pedido(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10),rs.getInt(11),rs.getInt(12), rs.getString(13),rs.getString(14),
                        rs.getString(15), rs.getString(16) );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ped;
    }

    @Override
    public List<Pedido> readAll() throws Exception {
        List<Pedido> pedido = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from Pedido";
            rs = s.executeQuery(q);

            while (rs.next()) {
                ped = new Pedido(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getString(13),rs.getString(14),
                        rs.getString(15), rs.getString(16));
                pedido.add(ped);
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
        return pedido;
    }

}
