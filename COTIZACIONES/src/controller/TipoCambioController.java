/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TipoCambioDao;
import entity.TipoCambio;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class TipoCambioController {
    TipoCambioDao dao;

    public TipoCambioController() {
        
        dao=new TipoCambioDao();
    }

    public TipoCambioController(TipoCambioDao dao) {
        this.dao = dao;
    }
    
        public List<TipoCambio> TipoCambioListar() throws Exception {
        return dao.readAll();
    }

    public TipoCambio TipoCambioBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public TipoCambio TipoCambioBuscarCambio(Object id) throws Exception {
        return dao.findForIdForCambio(id);
    }
    public TipoCambio TipoCambioBuscarUltimaFecha() throws Exception {
        return dao.findLastestFecha();
    }
    
 
    public String TipoCambioProcesar(TipoCambio tipo, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(tipo);
                msg = "Tipo de cambio registrado con exito";
                break;
            case Constante.UPD:
                dao.update(tipo);
                msg = "Tipo de cambio actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(tipo);
                msg = "Tipo de cambio eliminado con exito";
                break;
        }
        return msg;
    }
}
