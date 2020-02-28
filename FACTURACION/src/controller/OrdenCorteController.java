/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrdenCorteDao;
import entity.OrdenCorte;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-06
 */
public class OrdenCorteController {

    OrdenCorteDao dao;

    public OrdenCorteController() {
        dao = new OrdenCorteDao();
    }

    public OrdenCorteController(OrdenCorteDao dao) {
        this.dao = dao;
    }

    public List<OrdenCorte> OrdenCorteListar() throws Exception {
        return dao.readAll();
    }

    public OrdenCorte OrdenCorteBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }

    public OrdenCorte OrdenCorteBuscar(Object id, Object x) throws Exception {
        return dao.findForId(id, x);
    }

    public OrdenCorte OrdenCorteBuscarAnio(Object t, Object x) throws Exception {
        return dao.findForAnio(t, x);
    }

    public OrdenCorte OrdenCorteBuscarInfo(Object id) throws Exception {
        return dao.findForIdInfo(id);
    }

    public OrdenCorte OrdenCorteBuscarInfo(Object id, Object x) throws Exception {
        return dao.findForIdInfo(id, x);
    }

    public OrdenCorte OrdenCorteBuscarUltimoId(Object t) throws Exception {
        return dao.findLastestId(t);
    }

    public OrdenCorte OrdenCorteBuscarNum(Object id) throws Exception {
        return dao.findForNum(id);
    }

    public List<OrdenCorte> OrdenCorteFiltrar(String filtro, String nombre) throws Exception {
        return dao.findForLike(filtro, nombre);
    }

    public String OrdenCorteProcesar(OrdenCorte ord, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(ord);
                msg = "Orden de Corte registrado con exito";
                break;
            case Constante.UPD:
                dao.update(ord);
                msg = "Orden de Corte actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(ord);
                msg = "Orden de Corte eliminado con exito";
                break;
        }
        return msg;
    }
}
