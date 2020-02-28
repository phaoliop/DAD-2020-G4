/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MedidaIngresoDao;
import entity.MedidaIngreso;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class MedidaIngresoController {
    
    MedidaIngresoDao dao;

    public MedidaIngresoController() {
        
        dao= new MedidaIngresoDao();
    }

    public MedidaIngresoController(MedidaIngresoDao dao) {
        this.dao = dao;
    }
    
     public List<MedidaIngreso> MedidaIngresoListar() throws Exception {
        return dao.readAll();
    }
    
    public MedidaIngreso MedidaIngresoBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
     public String MedidaIngresoProcesar(MedidaIngreso medI,  int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(medI);
                msg = "Medida de ingreso registrado con exito";
                break;
            case Constante.UPD:
                dao.update(medI);
                msg = "Medida de ingreso actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(medI);
                msg = "Medida de ingreso eliminado con exito";
                break;
        }
        return msg;
    }
    
}
