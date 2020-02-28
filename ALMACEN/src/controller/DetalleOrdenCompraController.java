/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleOrdenCompraDao;
import entity.DetalleOrdenCompra;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class DetalleOrdenCompraController {
    
    DetalleOrdenCompraDao dao;

    public DetalleOrdenCompraController() {
        dao= new DetalleOrdenCompraDao();
    }

    public DetalleOrdenCompraController(DetalleOrdenCompraDao dao) {
        this.dao = dao;
    }

    public List<DetalleOrdenCompra> DetalleOrdenCompraListar() throws Exception {
        return dao.readAll();
    }

    public DetalleOrdenCompra DetalleOrdenCompraBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public DetalleOrdenCompra DetalleOrdenCompraTotal(Object id) throws Exception {
        return dao.findForPagos(id);
    }
    
    public List<DetalleOrdenCompra> DetalleOrdenCompraFiltrar(int t) throws Exception {
        return dao.findForidOrdC(t);
    }
    
    public String DetalleOrdenCompraProcesar(DetalleOrdenCompra detOrd, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(detOrd);
                msg = "Detalle de Orden de Compra registrado con exito";
                break;
            case Constante.UPD:
                dao.update(detOrd);
                msg = "Detalle de Orden de Compra actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(detOrd);
                msg = "Detalle de Orden de Compra eliminado con exito";
                break;
        }
        return msg;
    }
    
}
