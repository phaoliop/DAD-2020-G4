/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GuiaDeRemisionDao;
import entity.GuiaDeRemision;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class GuiaDeRemisionController {
      GuiaDeRemisionDao dao;

    public GuiaDeRemisionController() {
        dao = new GuiaDeRemisionDao();
    }

    public GuiaDeRemisionController(GuiaDeRemisionDao dao) {
        this.dao = dao;
    }

    public List<GuiaDeRemision> GuiaDeRemisionListar() throws Exception {
        return dao.readAll();
    }
    
    public List<GuiaDeRemision> GuiaDeRemisionListarVista() throws Exception {
        return dao.readAllVista();
    }

    public List<GuiaDeRemision> GuiaDeRemisionFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }
       
    public GuiaDeRemision GuiaDeRemisionBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public GuiaDeRemision GuiaDeRemisionBuscar(Object x, Object y, Object z) throws Exception {
        return dao.findForId(x,y,z);
    }
    
    public GuiaDeRemision GuiaDeRemisionBuscarAnio(Object t, Object x, Object y) throws Exception {
        return dao.findForAnio( t, x,y);
    }
    
    public GuiaDeRemision GuiaDeRemisionBuscarUltimoId(Object x) throws Exception {
        return dao.findLastestId(x);
    }

    public String GuiaDeRemisionProcesar(GuiaDeRemision guiaRem, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(guiaRem);
                msg = "Guia de Remisión registrada con exito";
                break;
            case Constante.UPD:
                dao.update(guiaRem);
                msg = "Guia de Remisión actualizada con exito";
                break;
            case Constante.DEL:
                dao.delete(guiaRem);
                msg = "Guia de Remisión eliminada con exito";
                break;
        }
        return msg;
    }
    
}
