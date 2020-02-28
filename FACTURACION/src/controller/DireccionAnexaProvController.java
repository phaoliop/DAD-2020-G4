/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DireccionAnexaProvDao;
import entity.DireccionAnexaProv;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class DireccionAnexaProvController {
    DireccionAnexaProvDao dao;

    public DireccionAnexaProvController() {
        dao = new DireccionAnexaProvDao();
    }

    public DireccionAnexaProvController(DireccionAnexaProvDao dao) {
        this.dao = dao;
    }
    
    public List<DireccionAnexaProv> DireccionAnexaProvListar() throws Exception {
        return dao.readAll();
    }
    
     public List<DireccionAnexaProv> DireccionAnexaProvListar(Object t) throws Exception {
        return dao.readAll(t);
    }
    
    public DireccionAnexaProv DireccionAnexaProvBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public DireccionAnexaProv DireccionAnexaProvBuscarUltimoId(Object t) throws Exception {
        return dao.findLastestId(t);
    }
     
    public String DireccionAnexaProvProcesar(DireccionAnexaProv dirNexa, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(dirNexa);
                msg = "Dirección del proveedor registrado con exito";
                break;
            case Constante.UPD:
                dao.update(dirNexa);
                msg = "Dirección del proveedor actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(dirNexa);
                msg = "Dirección del proveedor eliminado con exito";
                break;
        }
        return msg;
    }
    
}
