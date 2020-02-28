/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProformaDao;
import entity.Proforma;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class ProformaController {
    ProformaDao dao;

    public ProformaController() {
        dao= new ProformaDao();
    }

    public ProformaController(ProformaDao dao) {
        this.dao = dao;
    }
    
    
    public List<Proforma> ProformaListar() throws Exception {
        return dao.readAll();
    }
    
     public Proforma ProformaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
     
    public Proforma ProformaBuscar(Object id, Object x) throws Exception {
        return dao.findForId(id, x);
    }
    
     public Proforma ProformaBuscar1(Object id) throws Exception {
        return dao.findForId1(id);
    }
    
     public Proforma ProformaBuscarCotizacionParaPedido(Object id) throws Exception {
        return dao.findIfTherePedido(id);
    }
    
    public Proforma ProformaBuscarAnio(Object t, Object x) throws Exception {
        return dao.findForAnio( t, x);
    }
    
     public Proforma ProformaBuscarUltimoId(Object t) throws Exception {
        return dao.findLastestId(t);
    }
     
    public DefaultComboBoxModel ListarCombodeContacto(String nombre, int i) throws Exception {
        return dao.CargarContactosCombo(nombre,i);
    }     
     
    public List<Proforma> ProformaFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }

    public String ProformaProcesar(Proforma prof, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(prof);
                msg = "Proforma registrado con exito";
                break;
            case Constante.UPD:
                System.out.println("xddddddddddddddd");
                dao.update(prof);
                System.out.println("333333333333");
                msg = "Proforma actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(prof);
                msg = "Proforma eliminado con exito";
                break;
        }
        return msg;
    }
    
}
