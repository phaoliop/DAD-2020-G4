package dao;

import Service.ICrudDao;
import database.AccesoDB;
import entity.OrdenCompra;
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
 * @author Diego Lopez
 */
public class OrdenCompraDao implements ICrudDao<OrdenCompra> {

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    
    OrdenCompra ordC;
    String q;
    
    
    @Override
    public void create(OrdenCompra t) throws Exception {
        try {

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement("Insert into ordencompra(oc_num, oc_fecha,oc_idProveedor_fk,oc_idContactoProv_fk,\n"
                    + "oc_moneda, oc_formaPago, oc_tiempoEntrega, oc_fechaEntrega,oc_docExterno, oc_idUsuario_fk, \n"
                    + " oc_lugarEntrega, oc_estado, oc_observacion, oc_campoAdd2)\n"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, t.getOc_num());
            ps.setString(2, t.getOc_fecha());
            ps.setInt(3, t.getOc_idProveedor_fk());
            ps.setInt(4, t.getOc_idContactoProv_fk());
            ps.setString(5, t.getOc_moneda());
            ps.setString(6, t.getOc_formaPago());
            ps.setString(7, t.getOc_tiempoEntrega());
            ps.setString(8, t.getOc_fechaEntrega());
            ps.setString(9, t.getOc_docExterno());
            ps.setInt(10, t.getOc_idUsuario_fk());
            ps.setString(11, t.getOc_lugarEntrega());
            ps.setString(12, t.getOc_estado());
            ps.setString(13, t.getOc_observacion());
            ps.setString(14, t.getOc_campoAdd2());
            
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
    public void update(OrdenCompra t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "update ordencompra set oc_num=?,oc_fecha=?,oc_idProveedor_fk=?,oc_idContactoProv_fk=?,"
                    + "oc_moneda=?,oc_formaPago=?,  \n"
                    + "oc_tiempoEntrega=?, oc_fechaEntrega=?, oc_docExterno=?, oc_idUsuario_fk=?, oc_lugarEntrega=?"
                    + ",oc_estado=?,oc_observacion=?"
                    + " where oc_id=?";
            ps = conexion.prepareStatement(q);
            
            ps.setInt(1, t.getOc_num());
            ps.setString(2, t.getOc_fecha());
            ps.setInt(3, t.getOc_idProveedor_fk());
            ps.setInt(4, t.getOc_idContactoProv_fk());
            ps.setString(5, t.getOc_moneda());
            ps.setString(6, t.getOc_formaPago());
            ps.setString(7, t.getOc_tiempoEntrega());
            ps.setString(8, t.getOc_fechaEntrega());
            ps.setString(9, t.getOc_docExterno());
            ps.setInt(10, t.getOc_idUsuario_fk());
            ps.setString(11, t.getOc_lugarEntrega());
            ps.setString(12, t.getOc_estado());
            ps.setString(13, t.getOc_observacion());
            ps.setInt(14, t.getOc_id());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public void delete(OrdenCompra t) throws Exception {
        try {
            conexion = AccesoDB.obtener();
            q = "delete from ordencompra where oc_id=?";
            ps = conexion.prepareStatement(q);

            ps.setInt(1, t.getOc_id());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
    }

    @Override
    public OrdenCompra findForId(Object t) throws Exception {
        ordC = null;        
        try {
            
            q = "select oc_id, oc_num, oc_fecha,oc_idProveedor_fk,oc_idContactoProv_fk,oc_moneda, oc_formaPago, \n"
                    + "oc_tiempoEntrega, oc_fechaEntrega, oc_docExterno, oc_idUsuario_fk, oc_lugarEntrega,oc_estado, oc_observacion,"
                    + " oc_campoAdd2 from ordencompra where oc_id=? ";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            //ejecutar consulta
            rs = ps.executeQuery();            
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordC = new OrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), 
                           rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10), rs.getInt(11),
                           rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15));                
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordC;
    }

    public OrdenCompra findForId(Object t, int x) throws Exception {
        ordC = null;
        try {

            q = "select oc_id, oc_num, oc_fecha,oc_idProveedor_fk,oc_idContactoProv_fk,oc_moneda, oc_formaPago, \n"
                    + "oc_tiempoEntrega, oc_fechaEntrega, oc_docExterno, oc_idUsuario_fk, oc_lugarEntrega,oc_estado, oc_observacion,"
                    + " oc_campoAdd2 from ordencompra where oc_num=? and year(oc_fecha)=? ";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setInt(1, (int) t);
            ps.setInt(2, x);
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordC = new OrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordC;
    }
    
    
     public OrdenCompra findForNumYAnio(String t) throws Exception {
        ordC = null;
        try {

            q = "select oc_id, oc_num, oc_fecha,oc_idProveedor_fk,oc_idContactoProv_fk,oc_moneda, oc_formaPago, \n"
                    + "oc_tiempoEntrega, oc_fechaEntrega, oc_docExterno, oc_idUsuario_fk, oc_lugarEntrega,oc_estado, oc_observacion,"
                    + " oc_campoAdd2 from ordencompra where concat(oc_num,'-',year(oc_fecha))=? ";

            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,t+"");
            //ejecutar consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordC = new OrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordC;
    }
    
    public OrdenCompra findLastestNumOC(Object t) throws Exception {
        ordC = null;
        try {
            // q="select nombres, apellidos, telefono, correo, cargo, usuario, password from usuario where nombres=?";
            q = "select max(oc_num) from ordencompra where year(oc_fecha)=?";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            ps.setInt(1, (int)t);
            rs = ps.executeQuery();
            if (rs.next()) {
                //crear objeto pro y asignar valores
                ordC = new OrdenCompra(rs.getInt(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            conexion.close();
        }
        return ordC;
    }
    
    public List<OrdenCompra> findForLike(String filtro,String t) throws Exception {
        List<OrdenCompra> lista = new ArrayList<>();
        try {

            q = "Select o.oc_id, o.oc_num, p.razonSocial, concat(c.nombres,' ',c.apellidos)contacto, o.oc_fecha, \n"
                    + "	   o.oc_formaPago, o.oc_tiempoEntrega, o.oc_fechaEntrega, o.oc_lugarEntrega, o.oc_estado,"
                    + "    o.oc_observacion  \n"
                    + "from ordencompra o, proveedor p, contactoproveedor c, usuario u\n"
                    + "where (o.oc_idProveedor_fk=p.idProveedor and o.oc_idContactoProv_fk=c.idContactoProveedor\n"
                    + "	  and o.oc_idUsuario_fk=u.idUsuario)\n"
                    + "	  and "+filtro+" like ? \n"
                    + "order by o.oc_num desc;";
            conexion = AccesoDB.obtener();
            ps = conexion.prepareStatement(q);
            //preparar valor del parametro
            ps.setString(1,"%"+t+"%");
            //ejecutar consulta
            rs = ps.executeQuery();
            ordC = null;
            while (rs.next()) {
                //crear objeto pro y asignar valores
                ordC = new OrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                                       rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9), 
                                       rs.getString(10), rs.getString(11));
                lista.add(ordC);
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
    public List<OrdenCompra> readAll() throws Exception {
        List<OrdenCompra> ordencompra = new ArrayList<>();

        try {
            conexion = AccesoDB.obtener();
            s = conexion.createStatement();
            q = "select oc_id, oc_num, oc_fecha,oc_idProveedor_fk,oc_idContactoProv_fk,oc_moneda, oc_formaPago, \n"
                    + "oc_tiempoEntrega, oc_fechaEntrega,oc_docExterno, oc_idUsuario_fk, oc_lugarEntrega,oc_estado,"
                    + " oc_observacion,"
                    + " oc_campoAdd2 from ordencompra";
            rs = s.executeQuery(q);

            while (rs.next()) {
                ordC = new OrdenCompra(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15));
                ordencompra.add(ordC);
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
        return ordencompra;
    }

    
   
}
