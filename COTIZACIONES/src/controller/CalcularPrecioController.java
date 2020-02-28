/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CalcularPrecioDao;
import entity.CalcularPrecio;

/**
 *
 * @author ARCRODINPC-06
 */
public class CalcularPrecioController {

    CalcularPrecioDao dao;

    public CalcularPrecioController() {
        dao = new CalcularPrecioDao();
    }

    public CalcularPrecioController(CalcularPrecioDao dao) {
        this.dao = dao;
    }

    public CalcularPrecio CalcularPrecioBuscar(float longitud, Object id) throws Exception {
        return dao.findForDiaForDolares(longitud, id);
    }

    public CalcularPrecio CalcularPrecioBuscar(float longitud, String fecha, Object id) throws Exception {
        return dao.findForDiaForSoles(longitud, fecha, id);
    }

}
