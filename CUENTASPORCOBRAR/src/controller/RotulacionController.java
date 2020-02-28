/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RotulacionDao;
import entity.Rotulacion;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class RotulacionController {
    RotulacionDao dao;

    public RotulacionController(RotulacionDao dao) {
        this.dao = dao;
    }

    public RotulacionController() {
        dao= new RotulacionDao();
    }
    
    
     public List<Rotulacion> RotulacionListar() throws Exception {
        return dao.readAll();
    }
     
    public Rotulacion RotulacionBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public List<Rotulacion> RotulacionFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }

    
    public Rotulacion RotulacionBuscarUltimoId() throws Exception {
        return dao.findLastestId();
    }
    
    public String RotulacionProcesar(Rotulacion rot, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(rot);
                msg = "Rotulacion registrado con exito";
                break;
            case Constante.UPD:
                dao.update(rot);
                msg = "Rotulacion actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(rot);
                msg = "Rotulacion eliminado con exito";
                break;
        }
        return msg;
    }
    
    
    
}
