/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDao;
import entity.Cliente;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class ClienteController {

    ClienteDao dao;

    public ClienteController() {
        dao = new ClienteDao();
    }

    public ClienteController(ClienteDao dao) {
        this.dao = dao;
    }

    public List<Cliente> ClienteListar() throws Exception {
        return dao.readAll();
    }

    public Cliente ClienteBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }

    public Cliente ClienteBuscarRuc(Object ruc) throws Exception {
        return dao.findForRuc(ruc);
    }
    
    public DefaultComboBoxModel ClienteListarCombo(String nombre) throws Exception {
        return dao.getLista(nombre);
    }

    public List<Cliente> ClienteFiltrar(String filtro,String nombre) throws Exception {
        return dao.findForLike(filtro, nombre);
    }

    public Cliente ClienteBuscar1(Object id) throws Exception {
        return dao.findForId1(id);
    }
    
    public Cliente ClienteUltimoId() throws Exception {
        return dao.findLastestId();
    }

    public String ClienteProcesar(Cliente cli, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(cli);
                msg = "Cliente registrado con exito";
                break;
            case Constante.UPD:
                dao.update(cli);
                msg = "Cliente actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(cli);
                msg = "Cliente eliminado con exito";
                break;
        }
        return msg;
    }

}
