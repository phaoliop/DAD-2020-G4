/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ContactoProveedorDao;
import entity.ContactoProveedor;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import util.Constante;

/**
 *
 * @author ARCRODINPC-02
 */
public class ContactoProveedorController {
    
    ContactoProveedorDao dao;

    public ContactoProveedorController() {
        
        dao=new ContactoProveedorDao();
    }
    
    public ContactoProveedorController(ContactoProveedorDao dao) {
        this.dao = dao;
    }

    
    
    public List<ContactoProveedor> ContactoProveedorListar() throws Exception {
        return dao.readAll();
    }
      
     public List<ContactoProveedor> ContactoProveedorListar1(Object id) throws Exception{
       return dao.readAll1(id);
     }
     
    
     public ContactoProveedor ContactoProveedorBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
     
    public ContactoProveedor ContactoProveedorBuscar(Object nombre, Object id) throws Exception {
        return dao.findForId(nombre, id);
    }
    
    public List<ContactoProveedor> ContactoProveedorFiltrar(String valor,String nombre) throws Exception {
        return dao.findForLike(valor,nombre);
    }
    
    public DefaultComboBoxModel ListarCombodeContacto(String nombre, int i) throws Exception {
        return dao.CargarContactosCombo(nombre,i);
    }
   
    public String ContactoProveedorProcesar(ContactoProveedor contPro, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(contPro);
                msg = "Contacto registrado con exito";
                break;
            case Constante.UPD:
                dao.update(contPro);
                msg = "Contacto actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(contPro);
                msg = "Contacto eliminado con exito";
                break;
        }
        return msg;
    }
    
}
