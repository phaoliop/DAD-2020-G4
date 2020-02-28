/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PedidoDao;
import entity.Pedido;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class PedidoController {
    
    PedidoDao dao;

    public PedidoController() {
        dao= new PedidoDao();
    }

    public PedidoController(PedidoDao dao) {
        this.dao = dao;
    }
     
    public List<Pedido> PedidoListar() throws Exception {
        return dao.readAll();
    }

    public Pedido PedidoBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Pedido PedidoBuscar(Object id, Object x) throws Exception {
        return dao.findForId(id, x);
    }
    
     public Pedido PedidoParaVenta(Object id) throws Exception {
        return dao.findForIdForVenta(id);
    }
    
    public List<Pedido> PedidoFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }

    public List<Pedido> PedidoFiltrarEP(String valor,String nombre) throws Exception {
        return dao.findForLikeEP(valor,nombre);
    }
    
    public DefaultComboBoxModel ListarCombodeContacto(String nombre) throws Exception {
        return dao.CargarContactosCombo(nombre);
    }
    
    public Pedido PedidoBuscarUltimoId(Object t) throws Exception {
        return dao.findLastestId(t);
    }
    
    public Pedido PedidoBuscarNumPed(Object id) throws Exception {
        return dao.findForNumPed(id);
    }
    
     public Pedido PedidoBuscarNumPedAll(Object id) throws Exception {
        return dao.findForNumPedAll(id);
    }
    
    public Pedido PedidoBuscarAnio(Object t, Object x) throws Exception {
        return dao.findForAnio( t, x);
    }
    
     public String PedidoActualizarEstado(Pedido ped) throws Exception {
        String msg = null;

        dao.updateEstado(ped);
        msg = "Estado de Pedido Actualizado";
        return msg;
    }
    
    public String PedidoProcesar(Pedido ped, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(ped);
                msg = "Pedido registrado con exito";
                break;
            case Constante.UPD:
                dao.update(ped);
                msg = "Pedido actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(ped);
                msg = "Pedido eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
