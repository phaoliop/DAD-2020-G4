/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContactoClienteDao;
import entity.ContactoCliente;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class ContactoClienteController {
    ContactoClienteDao dao;

    public ContactoClienteController() {
        dao=new ContactoClienteDao();
    }

    public ContactoClienteController(ContactoClienteDao dao) {
        this.dao = dao;
    }
    
    
     public List<ContactoCliente> ContactoClienteListar() throws Exception {
        return dao.readAll();
    }
      
     public List<ContactoCliente> ContactoClienteListar1(Object id) throws Exception{
       return dao.readAll1(id);
     }
     
            
     public ContactoCliente ContactoClienteBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
     
     public ContactoCliente ContactoClienteBuscar(Object nombre,Object id ) throws Exception {
        return dao.findForId(nombre,id);
    }
     
       public ContactoCliente ContactoClienteBuscar1(Object id, int i) throws Exception {
        return dao.findForId1(id, i);
    }
     public ContactoCliente ContactoClienteBuscarDni(Object t, Object x) throws Exception {
        return dao.findForIdDni(t, x);
    }
     
     public DefaultComboBoxModel ContactoClienteListarCombo(String nombre, int id) throws Exception {
        return dao.getLista(nombre, id);
    }
       
    public ContactoCliente ContactoClienteBuscar2(Object id) throws Exception {
        return dao.findForId2(id);
    }
    
    public List<ContactoCliente> ContactoClienteFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }
    
    public DefaultComboBoxModel ListarCombodeContacto(String nombre, int i) throws Exception {
        return dao.CargarContactosCombo(nombre,i);
    }
     
    public String ContactoClienteProcesar(ContactoCliente contCli, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(contCli);
                msg = "Contacto registrado con exito";
                break;
            case Constante.UPD:
                dao.update(contCli);
                msg = "Contacto actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(contCli);
                msg = "Contacto eliminado con exito";
                break;
        }
        return msg;
    }
    
    
}
