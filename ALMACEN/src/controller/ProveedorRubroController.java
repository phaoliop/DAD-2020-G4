/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProveedorRubroDao;
import entity.ProveedorRubro;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class ProveedorRubroController {
    ProveedorRubroDao dao;

    public ProveedorRubroController() {
        dao= new ProveedorRubroDao();
    }

    public ProveedorRubroController(ProveedorRubroDao dao) {
        this.dao = dao;
    }
    
     public List<ProveedorRubro> ProveedorRubroListar() throws Exception {
        return dao.readAll();
    }
     
      public List<ProveedorRubro> ProveedorRubroListar(Object t) throws Exception {
        return dao.readAllTabla(t);
    }
     
    public ProveedorRubro ProveedorRubroBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
      public List<ProveedorRubro> ProveedorRubroFiltrar(String nombre, String like) throws Exception {
        return dao.findForLike(nombre, like);
    }
     
    public String ProveedorRubroProcesar(ProveedorRubro proRub, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(proRub);
                msg = "Rubro registrado con exito";
                break;
            case Constante.UPD:
                dao.update(proRub);
                msg = "Rubro actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(proRub);
                msg = "Rubro eliminado con exito";
                break;
        }
        return msg;
    }
    
}
