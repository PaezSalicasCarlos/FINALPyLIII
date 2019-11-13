/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DjNiico
 */
public class Conexion {
     private static Conexion instance =null;
    private Connection conexion;

    private Conexion()  {
        
        
            try {
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/ProyectoFinal";
                conexion = DriverManager.getConnection(url, "postgres", "19735024");
                System.out.println("Conectado");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        
        
    }
    
    public synchronized static Conexion saberEstado(){
    if (instance == null) {
    instance =new Conexion();
    } 
    return instance;
}
    
    public Connection getConexion(){
    return conexion;
    
}
    
    public void cerrarConexion(){
    instance = null;
   
            try {
                conexion. close();
                System.out.println("Desconectado");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
    } 
}
