/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;

import ar.edu.unlar.FinalPyLIII.objetos.Docente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DjNiico
 */
public class DocenteDTO  extends Transaccion implements ICrud <Docente,Integer>{

   private PreparedStatement ps;
   private ResultSet rs;
   private Statement st;
   Conexion conn;

    public DocenteDTO() {
    }
   public DocenteDTO(Conexion conexion) {
        super(conexion);
    }
   
    @Override
    public boolean crear(Docente entidad) {
    
        conn = Conexion.saberEstado(); 
        ps=null;
       try {
//            conn.getConexion().setAutoCommit(false);
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
             query="INSERT INTO docentes(id_persona,id_cargo,numero_legajo) VALUES (?,?,?)";
             ps = conn.getConexion().prepareStatement(query);
             ps.setInt(1,entidad.getId());
             ps.setInt(2,entidad.getTipoCargo().getId());
             ps.setInt(3,entidad.getNumeroLegajo());
               
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
    public boolean eliminar(Docente entidad) {
     boolean elim=false;
        String  query= "DELETE FROM  docentes  WHERE id=?";
        
            try {
                 conn = Conexion.saberEstado();
                ps = conn.getConexion().prepareStatement(query);

                 ps.setInt(1,entidad.getIdDOcente());

              ps.executeUpdate(); 
             ps.executeQuery();
             elim= true;



             } catch (SQLException e) {

             }
          
             return elim;  
    }

    @Override
    public boolean modificar(Docente entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Docente extraer(Docente id) {
   Docente docente = null;
    conn = Conexion.saberEstado(); 

    ps=null;
    rs=null;    
     try{
         String  query= "SELECT d.id, p.nombre,p.apellido,p.documento,p.fecha_nacimiento,p.sexo,d.numero_legajo "
                   + "     FROM personas p "
                   + "     INNER JOIN docentes d "
                   + "     ON p.id=d.id_persona  AND id=?";
         ps = conn.getConexion().prepareStatement(query);
//         ps.setInt(1,(int)id);
          rs= ps.executeQuery();
          if(rs.next()){
            
           // docente.setIdDOcente(rs.getInt(1));
            docente.setNombre(rs.getString(2));
            docente.setApellido(rs.getString(3));
            docente.setDocumento(rs.getString(4));
            docente.setFechaNacimiento(rs.getString(5));
            docente.setSexo(rs.getString(6));
            docente.setNumeroLegajo(rs.getInt(7));
            return docente;
          }
                      
     } catch(SQLException ex){
        }
        return docente; 
    
    }

    @Override
    public ArrayList<Docente> extraerTodo() {
    try{
            conn = Conexion.saberEstado();
            Statement stmt = conn.getConexion().createStatement();           
            

            String  query= "SELECT d.id,p.id,p.nombre,p.apellido,p.documento,p.fecha_nacimiento,p.sexo,d.numero_legajo,d.id,d.id_persona,d.id_cargo  FROM docentes d INNER JOIN personas p  ON d.id_persona=p.id ";
            
            rs = stmt.executeQuery(query);
            
            ArrayList<Docente> docentes = new ArrayList();
            
            while(rs.next()){
                
                Docente docente = extraerDocentesDesdeRS(rs);
               
                docentes.add(docente);
                
            }
         
            return docentes;
        } catch(SQLException ex){
        }
        return null;    
    
    
    }
    
     private Docente extraerDocentesDesdeRS (ResultSet rs) throws SQLException {

    Docente docente = new Docente();
    docente.setIdDOcente(rs.getInt("id"));
    docente.setNombre(rs.getString("nombre"));
   docente.setApellido(rs.getString("apellido"));
    docente.setNumeroLegajo(rs.getInt("numero_legajo"));
    
   
    return docente;
    
    }
}
