/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import database.AccesoDB;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;




/**
 *
 * @author ARCRODINPC-02
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        // TODO code application logic here

        try {
            Connection cn = AccesoDB.obtener();
            System.out.println("Conexion conforme....");
        } catch (SQLException e) {
            System.out.println("error :" + e.getMessage());
        }
    }
    
}
