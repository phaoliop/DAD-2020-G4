/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SucursalDao;
import entity.Sucursal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class SucursalController {
    SucursalDao dao;

    public SucursalController() {
        dao=new SucursalDao();
    }

    public SucursalController(SucursalDao dao) {
        this.dao = dao;
    }
    
    public List<Sucursal> SucursalListar() throws Exception {
        return dao.readAll();
    }
    
    public Sucursal SucursalBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Sucursal SucursalBuscarNombre(Object id) throws Exception {
        return dao.findForName(id);
    }
    
    public DefaultComboBoxModel SucursalListarCombo(Object t) throws Exception {
        return dao.getLista(t);
    }
    
    public String SucursalProcesar(Sucursal sucu, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(sucu);
                msg = "Sucursal registrado con exito";
                break;
            case Constante.UPD:
                dao.update(sucu);
                msg = "Sucursal actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(sucu);
                msg = "Sucursal eliminado con exito";
                break;
        }
        return msg;
    }
    
    
    
    
    
}
