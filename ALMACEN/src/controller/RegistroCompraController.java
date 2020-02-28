/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegistroCompraDao;
import entity.RegistroCompra;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class RegistroCompraController {
    
    RegistroCompraDao dao;

    public RegistroCompraController() {
        dao= new RegistroCompraDao();
    }

    public RegistroCompraController(RegistroCompraDao dao) {
        this.dao = dao;
        
    }
    
    public List<RegistroCompra> RegistroCompraListar() throws Exception {
        return dao.readAll();
        }
    
    public RegistroCompra RegistroCompraBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public RegistroCompra RegistroCompraBuscar(String s, String n) throws Exception {
        return dao.findForId(s,n);
    }

    public List<RegistroCompra> RegistroCompraFiltrar(String nombre,String valor) throws Exception {
        return dao.findForLike(nombre, valor);
    }
    
    public List<RegistroCompra> RegistroCompraOrdenar() throws Exception {
        return dao.findForOrden();
    }
    
    public List<RegistroCompra> RegistroCompraFiltrarExport(String d,String h) throws Exception {
        return dao.findForExport(d, h);
    }
                      
    
    public String RegistroCompraProcesar(RegistroCompra regCo, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(regCo);
                msg = "Compra registrada con exito";
                break;
            case Constante.UPD:
                dao.update(regCo);
                msg = "Compra actualizada con exito";
                break;
            case Constante.DEL:
                dao.delete(regCo);
                msg = "Compra eliminada con exito";
                break;
        }
        return msg;
    }
    
    
    
    
}
