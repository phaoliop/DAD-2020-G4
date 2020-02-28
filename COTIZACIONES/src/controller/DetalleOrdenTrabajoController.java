/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleOrdenTrabajoDao;
import entity.DetalleOrdenTrabajo;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class DetalleOrdenTrabajoController {
    
    DetalleOrdenTrabajoDao dao;

    public DetalleOrdenTrabajoController() {
        dao= new DetalleOrdenTrabajoDao();
    }

    public DetalleOrdenTrabajoController(DetalleOrdenTrabajoDao dao) {
        this.dao = dao;
    }
    
    public List<DetalleOrdenTrabajo> DetalleOrdenTrabajoListar() throws Exception {
        return dao.readAll();
    }

    public DetalleOrdenTrabajo DetalleOrdenTrabajoBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }

    public List<DetalleOrdenTrabajo> DetalleOrdenTrabajoFiltrar(int t) throws Exception {
        return dao.findForidOrdTrab(t);
    }
    
    public String DetalleOrdenTrabajoProcesar(DetalleOrdenTrabajo dot, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(dot);
                msg = "Detalle del Orden de Trabajo registrado con exito";
                break;
            case Constante.UPD:
                dao.update(dot);
                msg = "Detalle del Orden de Trabajo actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(dot);
                msg = "Detalle del Orden de Trabajo eliminado con exito";
                break;
        }
        return msg;
    }
    
    
    
}
