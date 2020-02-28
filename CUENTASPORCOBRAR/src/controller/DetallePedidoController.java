/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetallePedidoDao;
import entity.DetallePedido;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class DetallePedidoController {
    
//     DetallePedidoDao dao;
//
//    public DetallePedidoController() {
//        dao = new DetallePedidoDao();
//    }
//
//    public DetallePedidoController(DetallePedidoDao dao) {
//        this.dao = dao;
//    }
//    
//    public List<DetallePedido> DetallePedidoListar() throws Exception {
//        return dao.readAll();
//    }
//    
//     public DetallePedido DetallePedidoBuscar(Object id) throws Exception {
//        return dao.findForId(id);
//    }
//    
//    public DetallePedido DetallePedidoBuscar(Object id, Object x) throws Exception {
//        return dao.findForId(id,x);
//    }
//    
//    public DetallePedido DetallePedidoTotal(Object id) throws Exception {
//        return dao.findForPagos(id);
//    }
//    
//    public List<DetallePedido> DetallePedidoFiltrar(int t) throws Exception {
//        return dao.findForidPed(t);
//    }
//     
//    public String DetallePedidoProcesar(DetallePedido detPed, int op) throws Exception {
//        String msg = null;
//        switch (op) {
//            case Constante.INS:
//                dao.create(detPed);
//                msg = "Detalle de pedido registrado con exito";
//                break;
//            case Constante.UPD:
//                dao.update(detPed);
//                msg = "Detalle de pedido actualizado con exito";
//                break;
//            case Constante.DEL:
//                dao.delete(detPed);
//                msg = "Detalle de pedido eliminado con exito";
//                break;
//        }
//        return msg;
//    }
}
