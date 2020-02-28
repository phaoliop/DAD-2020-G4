/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PrecioDao;
import entity.Precio;
import java.util.List;
import util.Constante;

/**
 *
 * @author Yveth Calixto
 */
public class PrecioController {
    
    PrecioDao dao;

    public PrecioController() {
        
        dao=new PrecioDao();
        
    }

    public PrecioController(PrecioDao dao) {
        this.dao = dao;
    }
    
       public List<Precio> PrecioListar() throws Exception {
        return dao.readAll();
    }
       
     public List<Precio> PrecioListar1(int t) throws Exception {
        return dao.findForidArt(t);
    }
    
     public Precio PrecioBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
     
       
    public String PrecioProcesar(Precio pre, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(pre);
                msg = "Cliente registrado con exito";
                break;
            case Constante.UPD:
                dao.update(pre);
                msg = "Cliente actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(pre);
                msg = "Cliente eliminado con exito";
                break;
        }
        return msg;
    
    }
}
