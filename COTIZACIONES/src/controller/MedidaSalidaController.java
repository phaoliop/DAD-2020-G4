/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MedidaSalidaDao;
import entity.MedidaSalida;
import java.util.List;
import util.Constante;

/**
 *
 * @author ARCRODINPC-05
 */
public class MedidaSalidaController {
    
    MedidaSalidaDao dao;

    public MedidaSalidaController() {
        dao= new MedidaSalidaDao();
    }

    public MedidaSalidaController(MedidaSalidaDao dao) {
        this.dao = dao;
    }
    
    public List<MedidaSalida> MedidaSalidaListar() throws Exception {
        return dao.readAll();
    }

    public MedidaSalida MedidaSalidaBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }

    public String MedidaSalidaProcesar(MedidaSalida medS, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(medS);
                msg = "Medida de salida registrado con exito";
                break;
            case Constante.UPD:
                dao.update(medS);
                msg = "Medida de salida actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(medS);
                msg = "Medida de salida eliminado con exito";
                break;
        }
        return msg;
    }
 
}
