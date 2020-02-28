/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RotulacionAtencionDao;
import entity.RotulacionAtencion;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class RotulacionAtencionController {
     RotulacionAtencionDao dao;

    public RotulacionAtencionController(RotulacionAtencionDao dao) {
        this.dao = dao;
    }

    public RotulacionAtencionController() {
        dao= new RotulacionAtencionDao();
    }
    
    
     public List<RotulacionAtencion> RotulacionAtencionListar() throws Exception {
        return dao.readAll();
    }
     
    public RotulacionAtencion RotulacionAtencionBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
      
    public List<RotulacionAtencion> AtencionListar(int id) throws Exception {
        return dao.RotulacionAtencionListar(id);
    }
 
    public String RotulacionAtencionProcesar(RotulacionAtencion rotAt, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(rotAt);
                msg = "Atencion registrado con exito";
                break;
            case Constante.UPD:
                dao.update(rotAt);
                msg = "Atencion actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(rotAt);
                msg = "Atencion eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
