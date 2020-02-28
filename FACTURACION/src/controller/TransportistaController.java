/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TransportistaDao;
import entity.Transportista;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class TransportistaController {
    TransportistaDao dao;

    public TransportistaController() {
        dao=new TransportistaDao();
    }
    
    public TransportistaController(TransportistaDao dao) {
        this.dao = dao;
    }
    
     public List<Transportista> TransportistaListar() throws Exception {
        return dao.readAll();
    }
     
    public Transportista TransportistaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Transportista TransportistaBuscarNombre(Object id) throws Exception {
        return dao.findForIdNombre(id);
    }
    
    public DefaultComboBoxModel TransportistaListarCombo(Object t) throws Exception {
        return dao.getLista(t);
    }
    
    public String TransportistaProcesar(Transportista trans, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(trans);
                msg = "Transportista registrado con exito";
                break;
            case Constante.UPD:
                dao.update(trans);
                msg = "Transportista actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(trans);
                msg = "Transportista eliminado con exito";
                break;
        }
        return msg;
    }
    
}