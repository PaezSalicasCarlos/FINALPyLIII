/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;

import ar.edu.unlar.FinalPyLIII.objetos.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 *
 * @author WinUser
 */
public class CargoDTO extends Transaccion implements ICrud <Cargo, Integer> {
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement st;
    private Connection conection;
    Conexion con;
    
    
    @Override
    public boolean crear(Cargo entidad) {
        String  query= "INSERT INTO cargos (codigo,descripcion) VALUES (?,?)";
        
       try {
           con = Conexion.saberEstado();
            ps = con.getConexion().prepareStatement(query);
            ps.setInt(1,entidad.getCodigo());
            ps.setString(2,entidad.getDescripcion());
             ps.executeQuery();
      

            
        } catch (SQLException e) {
            
        }
       
    
            return false;
    }

    @Override
    public boolean eliminar(Cargo entidad) {
       
    boolean elim=false;
        String  query= "DELETE FROM  cargos  WHERE id=?";
        
            try {
                 con = Conexion.saberEstado();
                ps = con.getConexion().prepareStatement(query);

                 ps.setInt(1,entidad.getId());

              ps.executeUpdate(); 
             ps.executeQuery();
             elim= true;



             } catch (SQLException e) {

             }
          
             return elim;
    }

    @Override
    public boolean modificar(Cargo entidad) {
    
    String  query= "UPDATE  cargos SET codigo=?, descripcion=? WHERE id=?";
        
       try {
            con = Conexion.saberEstado();
            ps = con.getConexion().prepareStatement(query);

            ps.setInt(1,entidad.getCodigo());
            ps.setString(2,entidad.getDescripcion());
            ps.setInt(3,entidad.getId());
            
            ps.executeUpdate(); 
            
           
            } catch (SQLException e) {
            
            }
       
    
            return false;
    
      }

    @Override
    public Cargo extraer(Cargo id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cargo> extraerTodo() {
    try{
            con = Conexion.saberEstado();
            Statement stmt = con.getConexion().createStatement();           
            
           String  query= "Select * FROM cargos";
            rs = stmt.executeQuery(query);
            
            ArrayList<Cargo> cargos = new ArrayList();
            
            while(rs.next()){
                
                Cargo cargo = extraerCargosDesdeRS(rs);
               
                cargos.add(cargo);
                
            }
            //System.out.println(cont);
            return cargos;
        } catch(SQLException ex){
        }
        return null;   
    
    }
    
      private Cargo extraerCargosDesdeRS (ResultSet rs) throws SQLException {

    Cargo cargo = new Cargo();

    cargo.setId(rs.getInt("id") );

    cargo.setCodigo(rs.getInt("codigo") );

    cargo.setDescripcion(rs.getString("descripcion"));
    
   
    return cargo;
    
    }
    
}
