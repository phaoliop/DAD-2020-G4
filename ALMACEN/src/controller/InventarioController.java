/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.InventarioDao;
import entity.Inventario;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class InventarioController {
    InventarioDao dao;

    public InventarioController() {
        dao = new InventarioDao(); 
    }

    public InventarioController(InventarioDao dao) {
        this.dao = dao;
    }
    
    public List<Inventario> InventarioListar() throws Exception {
        return dao.readAll();
    }
    
    public List<Inventario> InventarioFiltrar(String filtro, String nombre) throws Exception {
        return dao.findForLike(filtro,nombre);
    }
    
    public Inventario InventarioBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Inventario InventarioBuscarUltimoId() throws Exception {
        return dao.findLastestId();
    }
      
     public String InventarioProcesar(Inventario inv, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(inv);
                msg = "Inventario registrado con exito";
                break;
            case Constante.UPD:
                dao.update(inv);
                msg = "Inventario actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(inv);
                msg = "Inventario eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
