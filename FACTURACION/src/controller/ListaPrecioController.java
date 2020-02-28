/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ListaPrecioDao;
import entity.ListaPrecio;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class ListaPrecioController {

    ListaPrecioDao dao;

    public ListaPrecioController() {
        dao = new ListaPrecioDao();
    }

    public ListaPrecioController(ListaPrecioDao dao) {
        this.dao = dao;
    }

    public List<ListaPrecio> ListaPrecioListar() throws Exception {
        return dao.readAll();
    }

    public DefaultComboBoxModel CargarComboDiametro(String diametro) throws Exception {
        return dao.getListarCombo(diametro);
    }

    public ListaPrecio ListaPrecioBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }

    public String ListaPrecioProcesar(ListaPrecio listPre, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(listPre);
                msg = "Precio registrado con exito";
                break;
            case Constante.UPD:
                dao.update(listPre);
                msg = "Precio actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(listPre);
                msg = "Precio eliminado con exito";
                break;
        }
        return msg;
    }

}
