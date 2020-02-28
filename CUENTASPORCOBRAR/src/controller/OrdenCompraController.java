/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrdenCompraDao;
import entity.OrdenCompra;
import java.util.List;
import util.Constante;

/**
 *
 * @author Diego Lopez
 */
public class OrdenCompraController {

    OrdenCompraDao dao;

    public OrdenCompraController() {
        dao=new OrdenCompraDao();
    }

    public OrdenCompraController(OrdenCompraDao dao) {
        this.dao = dao;
    }

    public List<OrdenCompra> OrdenCorteListar() throws Exception {
        return dao.readAll();
    }

    public OrdenCompra OrdenCompraBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public OrdenCompra OrdenCompraBuscar(Object id, int x) throws Exception {
        return dao.findForId(id, x);
    }
    
    public OrdenCompra OrdenCompraBuscar(String id) throws Exception {
        return dao.findForNumYAnio(id);
    }
    
    public OrdenCompra OrdenCompraBuscarUltimoNumOC(Object t) throws Exception {
        return dao.findLastestNumOC(t);
    }
    
    public List<OrdenCompra> OrdenCompraFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }
    
    public String OrdenCompraProcesar(OrdenCompra ord, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(ord);
                msg = "Orden de Compra registrado con exito";
                break;
            case Constante.UPD:
                dao.update(ord);
                msg = "Orden de Compra actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(ord);
                msg = "Orden de Compra eliminado con exito";
                break;
        }
        return msg;
    }
    
}
