/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.Proforma;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;



/**
 *
 * @author ARCRODINPC-02
 */
public class ProformaDao implements ICrudDao<Proforma> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    Proforma prof;
    String q;

    @Override
    public void create(Proforma t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into proforma(codProforma, fk_idCliente,"
                    + " idContactoCliente_fk, idUsuario_fk, moneda, validez, formaPago, "
                    + "tipo, detraccion, fechaEmision,dia1,dia2, estado, observacion, "
                    + "observacionInterna) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setString(1, t.getCodProforma());
            ps.setInt(2, t.getIdCliente());
            ps.setInt(3, t.getIdContactoCliente());
            ps.setInt(4, t.getIdUsuario());
            ps.setString(5, t.getMoneda());
            ps.setString(6, t.getValidez());
            ps.setString(7, t.getFormPago());
            ps.setString(8, t.getTipo());
            ps.setString(9, t.getDetraccion());
            ps.setString(10, t.getFechaEmision());
            ps.setInt(11, t.getDia1());
            ps.setInt(12, t.getDia2());
            ps.setString(13, t.getEstado());
            ps.setString(14, t.getObservacion());
            ps.setString(15, t.getObservacionInterna());

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
    public void update(Proforma t) throws Exception {
        try {
            System.out.println("2222222222222");
            conexion = AccesoDB.obtener();
            q = "update proforma set fk_idCliente=?,idContactoCliente_fk=?, idUsuario_fk=?,"
                    + " moneda=?, validez=?, formaPago=?, tipo=?, detraccion=?,fechaEmision=?, "
                    + " dia1=?, dia2=?, estado=?, observacion=?, "
                    + "observacionInterna=? where idProforma=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdCliente());
            ps.setInt(2, t.getIdContactoCliente());
            ps.setInt(3, t.getIdUsuario());
            ps.setString(4, t.getMoneda());
            ps.setString(5, t.getValidez());
            ps.setString(6, t.getFormPago());
            ps.setString(7, t.getTipo());
            ps.setString(8, t.getDetraccion());
            ps.setString(9, t.getFechaEmision());
            ps.setInt(10, t.getDia1());
            ps.setInt(11, t.getDia2());
            ps.setString(12, t.getEstado());
            ps.setString(13, t.getObservacion());
            ps.setString(14, t.getObservacionInterna());
            ps.setInt(15, t.getIdProforma());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(Proforma t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from proforma where idProforma=?";
            ps = conexion.prepareStatement(q);
            ps.setInt(1, t.getIdProforma());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public Proforma findForId(Object t) throws Exception {
        prof = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idProforma, codProforma, fk_idCliente, idContactoCliente_fk, idUsuario_fk, "
                    + "moneda, validez, formaPago, tipo, detraccion, fechaEmision, "
                    + " dia1,dia2,estado, observacion, observacionInterna"
                    + " from proforma "
                    + "where codProforma=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                prof = new Proforma(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13),
                        rs.getString(14),rs.getString(15), rs.getString(16));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return prof;
    }
    
    public Proforma findForId(Object t, Object x) throws Exception {
        prof = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select idProforma, codProforma, fk_idCliente, idContactoCliente_fk, idUsuario_fk, "
                    + "moneda, validez, formaPago, tipo, detraccion, fechaEmision, "
                    + " dia1, dia2, estado, observacion, observacionInterna"
                    + " from proforma "
                    + "where codProforma=? and year(fechaEmision)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1, t + "");
            ps.setInt(2, (int)x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                prof = new Proforma(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13),
                        rs.getString(14), rs.getString(15),rs.getString(16));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return prof;
    }
    
    public Proforma findForAnio(Object t, Object x) throws Exception {
        prof = null;
        try {
            q = "select  idProforma, year(fechaEmision) from proforma where fechaEmision=? and codProforma=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,  t+"");
            ps.setString(2, x+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores

                prof = new Proforma(rs.getInt(1),rs.getString(2));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return prof;
    }

    public Proforma findForId1(Object t) throws Exception {
        prof = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select codProforma from proforma  where idProforma=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                prof = new Proforma(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return prof;
    }
    
    
    public Proforma findIfTherePedido(Object t) throws Exception {
        prof = null;
        try {
            q = "select  c.idProforma, p.numPedido\n"
                    + "from proforma c, pedido p\n"
                    + "where p.idProforma_fk=c.idProforma and c.idProforma=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                prof = new Proforma(rs.getInt(1), rs.getInt(2));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return prof;
    }
    
    public Proforma findLastestId(Object t) throws Exception {
        prof = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select  max(idProforma) as idProforma from proforma where year(fechaEmision)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int)t);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                prof = new Proforma(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return prof;
    }

    public DefaultComboBoxModel CargarContactosCombo(String nombre, int i) throws Exception {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        try {
            conexion = AccesoDB.obtener();
            q = "select A.nombres,A.apellidos from contactocliente A ,"
                    + "cliente B where b.razonsocial = ? and A.idcliente_fk = "+i;
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

    public List<Proforma> findForLike(String filtro,String t) throws Exception {
        List<Proforma> lista = new ArrayList<>();
        try {

            q = "SELECT codProforma, razonSocial, concat(contactocliente.nombres,' ',contactocliente.apellidos)contacto, \n"
                    + "fechaEmision, proforma.tipo, estado, concat(usuario.nombres,' ',usuario.apellidos),"
                    + "proforma.observacionInterna \n"
                    + "FROM proforma, cliente, contactocliente, usuario\n"
                    + "WHERE (proforma.fk_idCliente=cliente.idCliente \n"
                    + "and proforma.idContactoCliente_fk= contactocliente.idContactoCliente \n"
                    + "and proforma.idUsuario_fk=usuario.idUsuario)  "
                    + "and " + filtro + " like ? order by idProforma desc";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            prof = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                prof = new Proforma(rs.getString(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                lista.add(prof);
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
    public List<Proforma> readAll() throws Exception {

        List<Proforma> proforma = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select *from Proforma";
            rs = s.executeQuery(q);

            while (rs.next()) {
                prof = new Proforma(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getInt(12),rs.getInt(13),
                        rs.getString(14), rs.getString(15), rs.getString(16));
                proforma.add(prof);
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
        return proforma;
    }

}
