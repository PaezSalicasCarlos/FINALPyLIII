/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.grillas;

import ar.edu.unlar.FinalPyLIII.objetos.Alumno;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DjNiico
 */
public class AlumnoGrilla extends AbstractTableModel{
private static final String columnaNombre [] ={"id","nombre","apellido","matricula"};
  ArrayList <Alumno> alumnos = new ArrayList<>();

    public AlumnoGrilla() {
    }
    public AlumnoGrilla(ArrayList <Alumno> alumnos) {
     this.alumnos=alumnos;
    }
    
    
    @Override
    public int getRowCount() {
    return  alumnos.size(); 
    }

    @Override
    public int getColumnCount() {
    return columnaNombre.length;     
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
     Alumno a=  alumnos.get (rowIndex);
            switch(columnIndex){
           case 0:
           return a.getIdAlumno();
          case 1:
           return a.getNombre();
            case 2:
           return a.getApellido();
           case 3:
           return a.getMatricula();          
           default : 
           return "";
          }
    
    }
     @Override
    public String getColumnName(int column) {
    return columnaNombre[column];
    }
    
    public void borrarFilas(int indice){
        alumnos.remove(indice);
        this.fireTableRowsDeleted(indice, indice);
        
    
    }
}
