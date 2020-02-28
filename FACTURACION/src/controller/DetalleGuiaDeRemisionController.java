/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleGuiaDeRemisionDao;
import entity.DetalleGuiaDeRemision;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class DetalleGuiaDeRemisionController {
    
    DetalleGuiaDeRemisionDao dao;

    public DetalleGuiaDeRemisionController() {
        dao= new DetalleGuiaDeRemisionDao(); 
    }

    public DetalleGuiaDeRemisionController(DetalleGuiaDeRemisionDao dao) {
        this.dao = dao;
    }
    
    public List<DetalleGuiaDeRemision> DetalleGuiaDeRemisionListar() throws Exception {
        return dao.readAll();
    }
    
     public List<DetalleGuiaDeRemision> DetalleGuiaDeRemisionFiltrar(int t) throws Exception {
        return dao.readAllList(t);
    }    

    public DetalleGuiaDeRemision DetalleGuiaDeRemisionBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
     public DetalleGuiaDeRemision DetalleGuiaDeRemisionBuscarId(String a, Object id) throws Exception {
        return dao.findForIdDetGuia(a,id);
    }
    
    public String DetalleGuiaDeRemisionProcesar(DetalleGuiaDeRemision detGuiaRem, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(detGuiaRem);
                msg = "Usuario registrado con exito";
                break;
            case Constante.UPD:
                dao.update(detGuiaRem);
                msg = "Usuario actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(detGuiaRem);
                msg = "Usuario eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
