/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;


import ar.edu.unlar.FinalPyLIII.objetos.Grado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author DjNiico
 */
public class GradoDTO extends Transaccion implements ICrud <Grado, Integer> {
private PreparedStatement ps;
    private ResultSet rs;
    private Statement st;
     Conexion con;
     
    public GradoDTO() {
    }
    
    public GradoDTO(Conexion conexion) {
        super(conexion);
    }
    
    
    
    @Override
    public boolean crear(Grado entidad) {
        con = Conexion.saberEstado(); 
        ps=null;
       try {
//           con.getConexion().setAutoCommit(false);
           String  query= "INSERT INTO grados (id_turno,numero,seccion) VALUES (?,?,?)";
            ps = con.getConexion().prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1,entidad.getTurno().getId());
            ps.setInt(2,entidad.getNumero());
            ps.setString(3,entidad.getSeccion());
            int count =0;
            
             if(ps.executeUpdate()>0){
             ResultSet res= ps.getGeneratedKeys();
            
                 if(res.next()){
                     entidad.setId(res.getInt(1));
                     for(int i =0; i<entidad.getDocente().size();i++){
                        query="INSERT INTO docentes_grados (id_docente,id_grado) VALUES (?,?)";
                        ps = con.getConexion().prepareStatement(query);
                        ps.setInt(1,entidad.getDocente().get(i).getIdDOcente());
                        ps.setInt(2,entidad.getId());
             
                        if(ps.executeUpdate()>0){
                                  count ++;
                                 //System.out.println(count);
                         }
                    }
                    if(count==entidad.getDocente().size()){
                      con.getConexion().commit();
                     return true;             
                     }else{
                     con.getConexion().rollback();
                      return false;
                    }
             
                 }
        
                    return true;
             }
        } catch (SQLException e) {
            
        }
         
            return false;

    }

    @Override
    public boolean eliminar(Grado entidad) {
    boolean elim=false;
        String  query= "DELETE FROM  grados  WHERE id=?";
        
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
    public boolean modificar(Grado entidad) {
     
     String  query= "UPDATE  grados SET numero=?, seccion=?, id_turno=? WHERE id=?";
        
       try {
            con = Conexion.saberEstado();
            ps = con.getConexion().prepareStatement(query);

            ps.setInt(1,entidad.getNumero());
            ps.setString(2,entidad.getSeccion());
            ps.setInt(3, entidad.getTurno().getId());
            ps.setInt(4,entidad.getId());
            
            ps.executeUpdate(); 
            
           
            } catch (SQLException e) {
            
            }
       
    
            return false;
    }

    @Override
    public Grado extraer(Grado id) {
     Grado grado;
     grado=new Grado();
    con = Conexion.saberEstado(); 

    ps=null;
    rs=null;    
     try{
         String  query= "SELECT id FROM grados where id=?";
         ps = con.getConexion().prepareStatement(query);
//      ps.setInt(1,(int)id);
          rs= ps.executeQuery();
          if(rs.next()){
             grado.setId(rs.getInt(1));
            
            
            return grado;
          }
                      
     } catch(SQLException ex){
        }
        return grado; 
    
    }

    @Override
    public ArrayList<Grado> extraerTodo() {
       
    try{
            con = Conexion.saberEstado();
            Statement stmt = con.getConexion().createStatement();           
            
           String  query= "Select * FROM grados";
            rs = stmt.executeQuery(query);
            
            ArrayList<Grado> grados = new ArrayList();
            
            while(rs.next()){
                
                Grado grado = extraerGradosDesdeRS(rs);
               
                grados.add(grado);
                
            }
            //System.out.println(cont);
            return grados;
        } catch(SQLException ex){
        }
        return null;   
    }
    
    private Grado extraerGradosDesdeRS (ResultSet rs) throws SQLException {

    Grado grado = new Grado();
    grado.setId(rs.getInt("id"));
    grado.setNumero(rs.getInt("numero") );
    grado.setSeccion(rs.getString("seccion"));
    
   
    return grado;
    
    }
    
// public ArrayList<Docente> extraerDocente (Grado grado){
//    ArrayList<Docente> listado = new ArrayList<>();
//     con = Conexion.saberEstado(); 
//
//    ps=null;
//    rs=null;    
//     try{
//         String  query= "SELECT id FROM docentes where id=?";
//         ps = con.getConexion().prepareStatement(query);
//               ps.setInt(1,grado.getId());
//          rs= ps.executeQuery();
//          if(rs.next()){
//            Docente docente;
//             DocenteDTO doc =new DocenteDTO();
//            listado=doc.extraerTodo();
//            docente= doc.extraerTodo();
//             
//            listado.add(lis)
//              grado.setId(rs.getInt(1));
//            
//            
//          
//          }
//             return listado;           
//     } catch(SQLException ex){
//        }
//        return listado; 
//
//}
}