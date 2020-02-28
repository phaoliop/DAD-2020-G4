/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DireccionAnexaDao;
import entity.DireccionAnexa;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class DireccionAnexaController {
    DireccionAnexaDao dao;

    public DireccionAnexaController() {
        dao= new DireccionAnexaDao();
               
    }
    
    public DireccionAnexaController(DireccionAnexaDao dao) {
        this.dao = dao;
    }
    
    public List<DireccionAnexa> DireccionAnexaListar() throws Exception {
        return dao.readAll();
    }
    
    public DireccionAnexa DireccionAnexaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
     public DireccionAnexa DireccionAnexaBuscarUltimoId(Object t) throws Exception {
        return dao.findLastestId(t);
    }
    
    public List<DireccionAnexa> DireccionAnexaListar(Object id) throws Exception{
       return dao.readAllTabla(id);
     }
   
    public String DireccionAnexaProcesar(DireccionAnexa dir, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(dir);
                msg = "Dirección registrada con exito";
                break;
            case Constante.UPD:
                dao.update(dir);
                msg = "Dirección actualizada con exito";
                break;
            case Constante.DEL:
                dao.delete(dir);
                msg = "Dirección eliminada con exito";
                break;
        }
        return msg;
    }
    
    
}
