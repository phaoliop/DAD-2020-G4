/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegistroVentaDao;
import entity.RegistroVenta;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class RegistroVentaController {
    
    RegistroVentaDao dao;

    public RegistroVentaController() {
        dao= new RegistroVentaDao();
    }

    public RegistroVentaController(RegistroVentaDao dao) {
        this.dao = dao;
    }
    
    public List<RegistroVenta> RegistroVentaListar() throws Exception {
        return dao.readAll();
    }
    
    public List<RegistroVenta> RegistroVentaFiltrar(String nombre,String valor) throws Exception {
        return dao.findForLike(nombre, valor);
    }
    
    public List<RegistroVenta> RegistroVentaOrdenar() throws Exception {
        return dao.findForLikeOrden();
    }
    public List<RegistroVenta> RegistroVentaFiltrarExport(String d,String h) throws Exception {
        return dao.findForExport(d, h);
    }
    public List<RegistroVenta> RegistroVentaFiltrarSinPagos(String nombre,String valor) throws Exception {
        return dao.findForLikeIdPagoNull(nombre, valor);
    }
    
    public RegistroVenta RegistroVentaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public RegistroVenta RegistroVentaPorId(Object id) throws Exception {
        return dao.findForIdRegistroVenta(id);
    }
    
    public List<RegistroVenta> RegistroVentaCuentasCobrar(String nombre,String valor) throws Exception {
        return dao.findCuentasPorCobrar(nombre, valor);
    }
    
    public List<RegistroVenta> RegistroVentaCuentasCobrarVencidos(String nombre,String valor) throws Exception {
        return dao.findCuentasPorCobrarVencidos(nombre, valor);
    }
    
    public RegistroVenta RegistroVentaBuscarPorFactura(Object s, Object n) throws Exception {
        return dao.findForFactura(s,n);
    }
    
    public String RegistroVentaProcesar(RegistroVenta reve, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(reve);
                msg = "Venta registrada con exito";
                break;
            case Constante.UPD:
                dao.update(reve);
                msg = "Venta actualizada con exito";
                break;
            case Constante.DEL:
                dao.delete(reve);
                msg = "Venta eliminada con exito";
                break;
        }
        return msg;
    }
    
}
