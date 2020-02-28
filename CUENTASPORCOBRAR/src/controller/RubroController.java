/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RubroDao;
import entity.Rubro;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class RubroController {
    RubroDao dao;

    public RubroController() {
        dao= new RubroDao();
    }

    public RubroController(RubroDao dao) {
        this.dao = dao;
    }
    
    public List<Rubro> RubroListar() throws Exception {
        return dao.readAll();
    }
     
    public Rubro RubroBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Rubro RubroBuscarNombre(Object id) throws Exception {
        return dao.findForName(id);
    }
    
    public DefaultComboBoxModel RubroListarCombo(Object t) throws Exception {
        return dao.getLista(t);
    }
    
    public String RubroProcesar(Rubro rub, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(rub);
                msg = "Rubro registrado con exito";
                break;
            case Constante.UPD:
                dao.update(rub);
                msg = "Rubro actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(rub);
                msg = "Rubro eliminado con exito";
                break;
        }
        return msg;
    }
      
}
