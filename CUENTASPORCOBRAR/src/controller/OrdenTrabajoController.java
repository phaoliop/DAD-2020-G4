/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrdenTrabajoDao;
import entity.OrdenTrabajo;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class OrdenTrabajoController {

    OrdenTrabajoDao dao;

    public OrdenTrabajoController() {
        dao = new OrdenTrabajoDao();
    }

    public OrdenTrabajoController(OrdenTrabajoDao dao) {
        this.dao = dao;
    }

    public List<OrdenTrabajo> OrdenTrabajoListar() throws Exception {
        return dao.readAll();
    }

    public OrdenTrabajo OrdenTrabajoBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public OrdenTrabajo OrdenTrabajoBuscar(Object id, Object ani) throws Exception {
        return dao.findForId(id,ani);
    }
    
    public OrdenTrabajo OrdenTrabajoBuscarUltimoId() throws Exception {
        return dao.findLastestId();
    }
    
    public List<OrdenTrabajo> OrdenTrabajoFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }

     public List<OrdenTrabajo> OrdenTrabajoListarFecha() throws Exception {
        return dao.listPorFecha();
    }
     
    public String OrdenTrabajoProcesar(OrdenTrabajo oti, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(oti);
                msg = "Orden de Trabajo registrado con exito";
                break;
            case Constante.UPD:
                dao.update(oti);
                msg = "Orden de Trabajo actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(oti);
                msg = "Orden de Trabajo eliminado con exito";
                break;
        }
        return msg;
    }
}
