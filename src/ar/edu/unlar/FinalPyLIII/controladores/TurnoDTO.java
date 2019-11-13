/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;

import ar.edu.unlar.FinalPyLIII.objetos.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author DjNiico
 */
public class TurnoDTO extends Transaccion implements ICrud <Turno, Integer> {
 private PreparedStatement ps;
    private ResultSet rs;
    private Statement st;
    private Connection conection;
    Conexion con;

    public TurnoDTO() {
    }

    public TurnoDTO(Conexion conexion) {
        super(conexion);
    }

   
    
    
    
    @Override
    public boolean crear(Turno entidad) {

String  query= "INSERT INTO turnos (codigo,descripcion) VALUES (?,?)";
        
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
    public boolean eliminar(Turno entidad) {
        boolean elim=false;
        String  query= "DELETE FROM  turnos  WHERE id=?";
        
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
    public boolean modificar(Turno entidad) {
       String  query= "UPDATE  turnos SET codigo=?, descripcion=? WHERE id=?";
        
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
    public Turno extraer(Turno id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Turno> extraerTodo() {
        try{
            con = Conexion.saberEstado();
            Statement stmt = con.getConexion().createStatement();           
            
           String  query= "Select * FROM turnos";
            rs = stmt.executeQuery(query);
            
            ArrayList<Turno> turnos = new ArrayList();
            
            while(rs.next()){
                
                Turno turno = extraerTurnosDesdeRS(rs);
               
                turnos.add(turno);
                
            }
            //System.out.println(cont);
            return turnos;
        } catch(SQLException ex){
        }
        return null;
    }
    
     private Turno extraerTurnosDesdeRS (ResultSet rs) throws SQLException {

    Turno turno = new Turno();

    turno.setId(rs.getInt("id") );

    turno.setCodigo(rs.getInt("codigo") );

    turno.setDescripcion(rs.getString("descripcion"));
    
   
    return turno;
    
    }
    
   public Turno BusquedaTurno(Turno entidad,String a){
       Turno turno=null;
       String filtros= ""+a+"_%";
       String  query= "SELECT * FROM turnos WHERE descripcion LIKE"+"'"+filtros+"'";
        
       try {
            con = Conexion.saberEstado();
            ps = con.getConexion().prepareStatement(query);
            ps.setString(1,a);
            rs= ps.executeQuery();
             if(rs.next()){
             turno.setId(rs.getInt(1));
             turno.setCodigo(rs.getInt(2));
             turno.setDescripcion(rs.getString(3));
               return turno;
       
             }
            } catch (SQLException e) {
            
            }
       
    
            return turno;
}
}
