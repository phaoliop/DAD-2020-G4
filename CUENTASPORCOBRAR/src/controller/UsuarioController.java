/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import entity.Usuario;
import java.util.List;
import util.Constante;
import util.Session;

/**
 *
 * @author ARCRODINPC-02
 */
public class UsuarioController {

    UsuarioDao dao;

    public UsuarioController() {
        dao = new UsuarioDao();
    }

    public UsuarioController(UsuarioDao dao) {
        this.dao = dao;
    }

    public List<Usuario> UsuarioListar() throws Exception {
        return dao.readAll();
    }

    public Usuario UsuarioBuscar(Object id) throws Exception {
        return dao.findForId(id);
    }
    
    public Usuario UsuarioBuscarPorId(Object id) throws Exception {
        return dao.findForIdUs(id);
    }

    public Usuario UsuarioBuscar1(Object id) throws Exception {
        return dao.findForId1(id);
    }

    public Usuario UsuarioBuscar2(Object id) throws Exception {
        return dao.findForId2(id);
    }

    public String UsuarioProcesar(Usuario us, int op) throws Exception {
        String msg = null;
        switch (op) {
            case Constante.INS:
                dao.create(us);
                msg = "Usuario registrado con exito";
                break;
            case Constante.UPD:
                dao.update(us);
                msg = "Usuario actualizado con exito";
                break;
            case Constante.DEL:
                dao.delete(us);
                msg = "Usuario eliminado con exito";
                break;
        }
        return msg;
    }

    public boolean UsuarioValidar(String usu, String pas) throws Exception {
        boolean ok = false;
        Usuario emp = dao.validar(usu, pas);
        if (emp != null) {
            Session.put("usuario", emp);
            ok = true;
        }
        return ok;
    }

}
