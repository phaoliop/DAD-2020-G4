/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleOrdenCorteDao;
import entity.DetalleOrdenCorte;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class DetalleOrdenCorteController {
    
    DetalleOrdenCorteDao dao;

    public DetalleOrdenCorteController() {
        dao = new DetalleOrdenCorteDao();
    }

    public DetalleOrdenCorteController(DetalleOrdenCorteDao dao) {
        this.dao = dao;
    }

    public List<DetalleOrdenCorte> DetalleOrdenCorteListar() throws Exception {
        return dao.readAll();
    }

    public DetalleOrdenCorte DetalleOrdenCorteBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
     public DetalleOrdenCorte DetalleOrdenCorteBuscarTodo(Object id) throws Exception {
        return dao.findAllForId(id);
    }
     
     public DetalleOrdenCorte DetalleOrdenCorteBuscarUltimoId() throws Exception {
        return dao.findLastestId();
    }

       public List<DetalleOrdenCorte> DetalleOrdenCorteFiltrar(int t) throws Exception {
        return dao.findForidOrd(t);
    }
   
    public List<DetalleOrdenCorte> DetalleOrdenCorteFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }
    
    public String DetalleOrdenCorteProcesar(DetalleOrdenCorte detOrd, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(detOrd);
                msg = "Orden de Corte registrado con exito";
                break;
            case Constante.UPD:
                dao.update(detOrd);
                msg = "Orden de Corte actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(detOrd);
                msg = "Orden de Corte eliminado con exito";
                break;
        }
        return msg;
    }
}
