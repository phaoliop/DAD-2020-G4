/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ArticuloDao;
import entity.Articulo;
import java.util.List;
import util.Constante;

/**
 *
 * @author Yveth Calixto
 */
public class ArticuloController {
    
    ArticuloDao dao;

    public ArticuloController() {
        dao= new ArticuloDao();
    }

    public ArticuloController(ArticuloDao dao) {
        this.dao = dao;
    }
    
    
    public List<Articulo> ArticuloListar() throws Exception {
        return dao.readAll();
    }
    
    public Articulo ArticuloBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Articulo ArticuloBuscar(Object id, Object in) throws Exception {
        return dao.findForId(id,in);
    } 
    
     public Articulo ArticuloBuscarCod(Object id) throws Exception {
        return dao.findCodArt(id);
    }
     
     public List<Articulo> ArticuloFiltrar(String filtro, String nombre, int id) throws Exception {
        return dao.findForLike(filtro,nombre,id);
    }
     
     public List<Articulo> ArticuloListarInventario() throws Exception {
        return dao.readAllInventario();
    }
       
    public String ArticuloProcesar(Articulo art, Articulo x, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(art);
                msg = "Producto registrado con exito";
                break;
            case Constante.UPD:
                dao.update(art,x);
                msg = "Producto actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(art,x);
                msg = "Producto eliminado con exito";
                break;
        }
        return msg;
    }
  
}
