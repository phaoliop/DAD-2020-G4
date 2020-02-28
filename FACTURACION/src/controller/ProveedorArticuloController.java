/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProveedorArticuloDao;
import entity.ProveedorArticulo;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class ProveedorArticuloController {
    ProveedorArticuloDao dao;

    public ProveedorArticuloController() {
        dao= new ProveedorArticuloDao();
  
    }

    public ProveedorArticuloController(ProveedorArticuloDao dao) {
        this.dao = dao;
    }
    
     public List<ProveedorArticulo> ProveedorArticuloListar() throws Exception {
        return dao.readAll();
    }
    
     public List<ProveedorArticulo> ProveedorArticuloListar(Object t) throws Exception {
        return dao.readAllTabla(t);
    }
     
    public ProveedorArticulo ProveedorArticuloBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
        public List<ProveedorArticulo> ProveedorArticuloFiltrar(String nombre, String t) throws Exception {
        return dao.findForLike(nombre,t);
    }
     
    public String ProveedorArticuloProcesar(ProveedorArticulo proArt, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(proArt);
                msg = "Articulo del proveedor registrado con exito";
                break;
            case Constante.UPD:
                dao.update(proArt);
                msg = "Articulo del proveedor actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(proArt);
                msg = "Articulo del proveedor eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
