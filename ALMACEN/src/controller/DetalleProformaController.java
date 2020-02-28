/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetalleProformaDao;
import entity.DetalleProforma;
import java.util.List;
import javax.swing.JTable;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class DetalleProformaController {
    DetalleProformaDao dao;

    public DetalleProformaController() {
        dao = new DetalleProformaDao();
    }

    public DetalleProformaController(DetalleProformaDao dao) {
        this.dao = dao;
    }
    
    
    public List<DetalleProforma> DetalleProformaListar() throws Exception {
        return dao.readAll();
    }
    
     public List<DetalleProforma> DetalleProformaFiltrar(int t) throws Exception {
        return dao.findForidProf(t);
    }
     
     public DetalleProforma DetalleProformaBuscar(Object id, Object x) throws Exception {
        return dao.findForId(id,x);
    }
    public DetalleProforma DetalleProformaBuscar1(Object id) throws Exception {
        return dao.findForPagos(id);
    }
    public DetalleProforma DetalleProformaBuscar(String fecha, Object id) throws Exception {
        return dao.findForTotal(fecha,id);
    }
   public String DetalleProformaProcesar(DetalleProforma detProf, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(detProf);
                msg = "Detalle registrado con exito";
                break;
            case Constante.UPD:
                dao.update(detProf);
                msg = "Detalle actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(detProf);
                msg = "Detalle eliminado con exito";
                break;
        }
        return msg;
    }

    public void exportarExcel(JTable jTable1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
