/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ARCRODINPC-02
 */
public class AccesoDB {
    
   public static Connection obtener() throws SQLException, ClassNotFoundException {

        Connection cn = null;
        try {
            // Parámetros de Connexión
            String driver = "com.mysql.jdbc.Driver";
            //String url = "jdbc:mysql://192.168.1.4:3306/arc";

            // String url = "jdbc:mysql://localhost:3306/prueba";
            //  String url = "jdbc:mysql://localhost:3306/arcv2";
            // String url = "jdbc:mysql://Diego_Lopez:3306/arcv2";
//            String url = "jdbc:mysql://ARCRODINPC-05:3306/arcv2";
            String url = "jdbc:mysql://localhost:3306/dbgestion";

            // String url = "jdbc:mysql://ARCRODINPC-01:3306/arc";
//            String url = "jdbc:mysql://ARCRODINPC-01:3306/arcv2"; 
            
            String user = "root";
            String pwd = "root";
            // Proceso
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de la base de datos.");
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SQLException("No se puede acceder a la base de datos.");
        }
        return cn;
    }
    
}
