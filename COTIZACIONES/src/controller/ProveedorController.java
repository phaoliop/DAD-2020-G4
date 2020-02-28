/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProveedorDao;
import entity.Proveedor;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class ProveedorController {

    ProveedorDao dao;

    public ProveedorController(ProveedorDao dao) {
        this.dao = dao;
    }

    public ProveedorController() {

        dao = new ProveedorDao();
    }

    public List<Proveedor> ProveedorListar() throws Exception {
        return dao.readAll();
    }

    public Proveedor ProveedorBuscar(String id) throws Exception {
        return dao.findForId(id);
    }
    
    public Proveedor ProveedorBuscarId(Object id) throws Exception {
        return dao.findId(id);
    }
        public List<Proveedor> ProveedorFiltrar(String filtro,String nombre) throws Exception {
        return dao.findForLike(filtro,nombre);
    }

    public DefaultComboBoxModel ProveedorListarCombo(String nombre) throws Exception {
        return dao.getLista(nombre);
    }

    public String ProveedorProcesar(Proveedor pro, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(pro);
                msg = "Proveedor registrado con exito";
                break;
            case Constante.UPD:
                dao.update(pro);
                msg = "Proveedor actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(pro);
                msg = "Proveedor eliminado con exito";
                break;
        }
        return msg;
    }

}
