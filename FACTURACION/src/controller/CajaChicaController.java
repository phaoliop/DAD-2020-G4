/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CajaChicaDao;
import entity.CajaChica;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class CajaChicaController {
    
    CajaChicaDao dao;
    
    public CajaChicaController() {
        dao= new CajaChicaDao();
    }
    
     public CajaChicaController(CajaChicaDao dao) {
        this.dao = dao;
    }
    
    
      public List<CajaChica> CajaChicaListar() throws Exception {
        return dao.readAll();
    }
    
     public CajaChica CajaChicaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
     
    public List<CajaChica> CajaChicaBuscarFechas(String fechIni, String fechFin) throws Exception {
        return dao.findForDates(fechIni, fechFin);
    }
     
     
     public CajaChica CajaChicaCalcular() throws Exception {
        return dao.findForSuma();
    }
    
     public CajaChica CajaChicaCalcularFechas(String x, String y) throws Exception {
        return dao.findForSuma(x,y);
    }
           
    public String CajaChicaProcesar(CajaChica caj, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(caj);
                msg = "Item registrado con exito";
                break;
            case Constante.UPD:
                dao.update(caj);
                msg = "Item actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(caj);
                msg = "Item eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
