/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;

import ar.edu.unlar.FinalPyLIII.objetos.Alumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DjNiico
 */
public class AlumnoDTO extends Transaccion implements ICrud <Alumno,Integer> {

   private PreparedStatement ps;
   private ResultSet rs;
   private Statement st;
   Conexion conn;
   
    public AlumnoDTO() {
    }
    
    public AlumnoDTO(Conexion conexion) {
        super(conexion);
    }
    
    
    
    
    @Override
    public boolean crear(Alumno entidad) {
        
      
        conn = Conexion.saberEstado(); 
        ps=null;
       try {
//           conn.getConexion().setAutoCommit(false);
           String  query= "INSERT INTO personas (nombre,apellido,documento,fecha_nacimiento,sexo) VALUES (?,?,?,?,?)";
        ps = conn.getConexion().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);

          
            ps.setString(1,entidad.getNombre());
            ps.setString(2,entidad.getApellido());
            ps.setString(3,entidad.getDocumento());
            ps.setDate(4,java.sql.Date.valueOf(entidad.getFechaNacimiento())); 
            ps.setString(5,entidad.getSexo());
           
         
             
             if(ps.executeUpdate()>0){
             ResultSet res= ps.getGeneratedKeys();
            
             if(res.next()){
             entidad.setId(res.getInt(1));
             query="INSERT INTO alumnos (id_persona,id_grado,matricula) VALUES (?,?,?)";
             ps = conn.getConexion().prepareStatement(query);
             ps.setInt(1,entidad.getId());
             ps.setInt(2,entidad.getGrado().getId());
             ps.setInt(3,entidad.getMatricula());
             ps.executeQuery();
             ps.executeUpdate();
             }
            
             if(ps.executeUpdate()>0){
             conn.getConexion().commit();
                     return true;
             }else{
             conn.getConexion().rollback();
             }
             }

            
        } catch (SQLException e) {
            
        }
       
    
            return false;

    }

    @Override
    public boolean eliminar(Alumno entidad) {
     boolean elim=false;
        String  query= "DELETE FROM  alumnos  WHERE id=?";
        
            try {
                 conn = Conexion.saberEstado();
                ps = conn.getConexion().prepareStatement(query);

                 ps.setInt(1,entidad.getIdAlumno());

              ps.executeUpdate(); 
             ps.executeQuery();
             elim= true;



             } catch (SQLException e) {

             }
          
             return elim; 
    }

    @Override
    public boolean modificar(Alumno entidad) {
    conn = Conexion.saberEstado(); 
        ps=null;
       try {
          String query="UPDATE  alumnos SET id_persona,id_grado=?,matricula=? WHERE id=?";
             ps = conn.getConexion().prepareStatement(query);
             ps.setInt(1,entidad.getId());
             ps.setInt(2,entidad.getGrado().getId());
             ps.setInt(3,entidad.getMatricula());
             ps.setInt(4,entidad.getIdAlumno());
         
           
              if(ps.executeUpdate()>0){
                  
                    
              String  query1= "UPDATE  personas SET nombre=?,apellido=?,documento=?,fecha_nacimiento=?,sexo=? WHERE id=?";
        
            ps = conn.getConexion().prepareStatement(query1);
            ps.setString(1,entidad.getNombre());
            ps.setString(2,entidad.getApellido());
            ps.setString(3,entidad.getDocumento());
            ps.setDate(4,java.sql.Date.valueOf(entidad.getFechaNacimiento())); 
            ps.setString(5,entidad.getSexo());
             ps.setInt(6,entidad.getId());
           
                  }
              return true;

            
        } catch (SQLException e) {
            
        }
       
    
            return false;
    
    
    }

    @Override
    public Alumno extraer(Alumno id) {
    Alumno alumno = null;
    conn = Conexion.saberEstado(); 

    ps=null;
    rs=null;    
     try{
         String  query= "SELECT a.id, p.nombre,p.apellido,p.documento,p.fecha_nacimiento,p.sexo,a.matricula "
                   + "     FROM personas p "
                   + "     INNER JOIN alumnos a "
                   + "     ON p.id=a.id_persona  AND id=?";
         ps = conn.getConexion().prepareStatement(query);
     // ps.setInt(1, (int)id) ;
          rs= ps.executeQuery();
          if(rs.next()){
//            alumno.setIdAlumno(rs.getInt(1));
            alumno.setNombre(rs.getString(2));
            alumno.setApellido(rs.getString(3));
            alumno.setDocumento(rs.getString(4));
            alumno.setFechaNacimiento(rs.getString(5));
            alumno.setSexo(rs.getString(6));
            alumno.setMatricula(rs.getInt(7));
            return alumno;
          }
                      
     } catch(SQLException ex){
        }
        return alumno; 
    }


    @Override
    public ArrayList<Alumno> extraerTodo() {
     try{
            conn = Conexion.saberEstado();
            Statement stmt = conn.getConexion().createStatement();           
            

            String  query= "SELECT a.id,p.nombre,p.apellido,a.matricula  FROM alumnos a INNER JOIN personas p  ON a.id_persona=p.id ";
            
            rs = stmt.executeQuery(query);
            
            ArrayList<Alumno> alumnos = new ArrayList();
            
            while(rs.next()){
                
                Alumno alumno = extraerAlumnosDesdeRS(rs);
               
                alumnos.add(alumno);
                
            }
            //System.out.println(cont);
            return alumnos;
        } catch(SQLException ex){
        }
        return null;    
    
    }
    
     private Alumno extraerAlumnosDesdeRS (ResultSet rs) throws SQLException {

    Alumno alumno = new Alumno();
    alumno.setIdAlumno(rs.getInt("id"));
    alumno.setNombre(rs.getString("nombre"));
    alumno.setApellido(rs.getString("apellido"));
    alumno.setMatricula(rs.getInt("matricula"));
   
   
    
   
    return alumno;
    
    }

}