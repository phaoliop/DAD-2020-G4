/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ComprobanteSunatDao;
import entity.ComprobanteSunat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class ComprobanteSunatController {
    
    ComprobanteSunatDao dao;

    public ComprobanteSunatController() {
        dao=new ComprobanteSunatDao();
    }

    public ComprobanteSunatController(ComprobanteSunatDao dao) {
        this.dao = dao;
    }
    
    public List<ComprobanteSunat> ComprobanteSunatListar() throws Exception {
        return dao.readAll();
    }
     
    public ComprobanteSunat ComprobanteSunatBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    
    public ComprobanteSunat ComprobanteSunatBuscarDescripcion(Object id) throws Exception {
        return dao.findForDescripcion(id);
    }
    
    public DefaultComboBoxModel ComprobanteSunatListarCombo(String nombre) throws Exception {
        return dao.getLista(nombre);
    }
    
    public String ComprobanteSunatProcesar(ComprobanteSunat coSu, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(coSu);
                msg = "Tipo de Comprobante registrado con exito";
                break;
            case Constante.UPD:
                dao.update(coSu);
                msg = "Tipo de Comprobante actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(coSu);
                msg = "Tipo de Comprobante eliminado con exito";
                break;
        }
        return msg;
    }
    
}
