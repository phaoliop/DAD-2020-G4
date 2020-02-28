/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegistroPagoDao;
import entity.RegistroPago;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class RegistroPagoController {
    
    RegistroPagoDao dao;

    public RegistroPagoController() {
        dao= new RegistroPagoDao();
    }

    public RegistroPagoController(RegistroPagoDao dao) {
        this.dao = dao;
    }
    
    public List<RegistroPago> RegistroPagoListar() throws Exception {
        return dao.readAll();
    }
    
     public List<RegistroPago> RegistroPagoListar(int t) throws Exception {
        return dao.findForidReVe(t);
    }
    
    public RegistroPago RegistroPagoBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public RegistroPago RegistroPagoTotalSoles(Object id, Object x) throws Exception {
        return dao.findTotalSoles(id,x);
    }
    
    public RegistroPago RegistroPagoTotalDolares(Object id, Object x) throws Exception {
        return dao.findTotalDolares(id,x);
    }
    
    public String RegistroPagoProcesar(RegistroPago repa, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(repa);
                msg = "Pago registrado con exito";
                break;
            case Constante.UPD:
                dao.update(repa);
                msg = "Pago actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(repa);
                msg = "Pago eliminado con exito";
                break;
        }
        return msg;
    }
    
}
