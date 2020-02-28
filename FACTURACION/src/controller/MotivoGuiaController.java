/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MotivoGuiaDao;
import entity.MotivoGuia;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ARCRODINPC-05
 */
public class MotivoGuiaController {
    
    MotivoGuiaDao dao;

    public MotivoGuiaController() {
        dao= new MotivoGuiaDao();
    }

    public MotivoGuiaController(MotivoGuiaDao dao) {
        this.dao = dao;
    }
    
    public MotivoGuia MotivoGuiaBuscarId(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public MotivoGuia MotivoGuiaBuscarNombre(Object id) throws Exception {
        return dao.findForNombre(id);
    }
    
    public MotivoGuia MotivoGuiaBuscarCombox(Object id) throws Exception {
        return dao.findForCombox(id);
    }
    
    public DefaultComboBoxModel ListarComboMotivoGuia() throws Exception {
        return dao.MotivoGuiaCombo();
    } 
    
}
