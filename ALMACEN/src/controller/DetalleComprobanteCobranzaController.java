/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleComprobanteCobranzaDao;
import entity.DetalleComprobanteCobranza;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class DetalleComprobanteCobranzaController {

    DetalleComprobanteCobranzaDao dao;

    public DetalleComprobanteCobranzaController() {
        dao = new DetalleComprobanteCobranzaDao();
    }

    public DetalleComprobanteCobranzaController(DetalleComprobanteCobranzaDao dao) {
        this.dao = dao;
    }

    public List<DetalleComprobanteCobranza> DetalleComprobanteCobranzaListar() throws Exception {
        return dao.readAll();
    }

    public List<DetalleComprobanteCobranza> DetalleComprobanteCobranzaFiltrar(int t) throws Exception {
        return dao.findForidCob(t);
    }

    public DetalleComprobanteCobranza DetalleComprobanteCobranzaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }

    public DetalleComprobanteCobranza DetalleComprobanteCobranzaBuscar1(Object id) throws Exception {
        return dao.findForPagos(id);
    }

    public String DetalleComprobanteCobranzaProcesar(DetalleComprobanteCobranza detCob, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(detCob);
                msg = "Detalle de Comprobante registrado con exito";
                break;
            case Constante.UPD:
                dao.update(detCob);
                msg = "Detalle de Comprobante actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(detCob);
                msg = "Detalle de Comprobante con exito";
                break;
        }
        return msg;
    }
}
