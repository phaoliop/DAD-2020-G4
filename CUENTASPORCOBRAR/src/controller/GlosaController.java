/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GlosaDao;
import entity.Glosa;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class GlosaController {
    GlosaDao dao;

    public GlosaController() {
        dao=new GlosaDao();
    }

    public GlosaController(GlosaDao dao) {
        this.dao = dao;
    }
    
   public List<Glosa> GlosaListar() throws Exception {
        return dao.readAll();
    }
     
    public Glosa GlosaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Glosa GlosaBuscarConcepto(Object id) throws Exception {
        return dao.findForConcepto(id);
    }
    
    public DefaultComboBoxModel GlosaListarCombo(String nombre) throws Exception {
        return dao.getLista(nombre);
    }
    
    public String GlosaProcesar(Glosa glos, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(glos);
                msg = "Concepto de pago registrado con exito";
                break;
            case Constante.UPD:
                dao.update(glos);
                msg = "Concepto de pago actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(glos);
                msg = "Concepto de pago eliminado con exito";
                break;
        }
        return msg;
    } 
    
    
}
