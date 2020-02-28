/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegistroPagoCompraDao;
import entity.RegistroPagoCompra;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class RegistroPagoCompraController {
    
    RegistroPagoCompraDao dao;

    public RegistroPagoCompraController() {
        dao=new RegistroPagoCompraDao();
    }

    public RegistroPagoCompraController(RegistroPagoCompraDao dao) {
        this.dao = dao;
    }
    
     public List<RegistroPagoCompra> RegistroPagoCompraListar() throws Exception {
        return dao.readAll();
    }
     
    public List<RegistroPagoCompra> RegistroPagoCompraListar(int t) throws Exception {
        return dao.findForidReVe(t);
    }
        
    public RegistroPagoCompra RegistroPagoCompraBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public RegistroPagoCompra RegistroPagoTotalSoles(Object id, Object x) throws Exception {
        return dao.findTotalSoles(id,x);
    }
    
    public RegistroPagoCompra RegistroPagoTotalDolares(Object id, Object x) throws Exception {
        return dao.findTotalDolares(id,x);
    }
    
    public String RegistroPagoCompraProcesar(RegistroPagoCompra repaco, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(repaco);
                msg = "Pago registrado con exito";
                break;
            case Constante.UPD:
                dao.update(repaco);
                msg = "Pago actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(repaco);
                msg = "Pago eliminado con exito";
                break;
        }
        return msg;
    }
    
}
