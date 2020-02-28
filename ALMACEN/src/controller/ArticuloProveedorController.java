/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticuloProveedorDao;
import entity.ArticuloProveedor;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;



/**
 *
 * @author ARCRODINPC-02
 */
public class ArticuloProveedorController {

     ArticuloProveedorDao dao;
    
    public ArticuloProveedorController() {
        
        dao=new ArticuloProveedorDao();
    }

    public ArticuloProveedorController(ArticuloProveedorDao dao) {
        this.dao = dao;
    }
    
    public List<ArticuloProveedor> ArticuloProveedorListar() throws Exception {
        return dao.readAll();
    }
    
//    public List<ArticuloProveedor> ArticuloProveedorListar1(Object id) throws Exception {
//        return dao.readAll1(id);
//    }
//    
     public ArticuloProveedor ArticuloProveedorBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
     
     public ArticuloProveedor ArticuloProveedorBuscarNombre(Object id) throws Exception {
        return dao.findForName(id);
    }
     
     public DefaultComboBoxModel ArticuloProveedorListarCombo(Object t) throws Exception {
        return dao.getLista(t);
    }
     
    public String ArticuloProveedorProcesar(ArticuloProveedor artPro, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(artPro);
                msg = "Articulo registrado con exito";
                break;
            case Constante.UPD:
                dao.update(artPro);
                msg = "Articulo actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(artPro);
                msg = "Articulo eliminado con exito";
                break;
        }
        return msg;
    }
   
}
