/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.grillas;

import ar.edu.unlar.FinalPyLIII.objetos.Turno;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DjNiico
 */
public class TurnoGrilla extends AbstractTableModel {
    private static final String columnaNombre [] ={"id","codigo","descripcion"};
   
   ArrayList <Turno> turnos = new ArrayList<>();

    public TurnoGrilla() {
    }
    
      public TurnoGrilla(ArrayList<Turno> turnos) {
      this.turnos= turnos; 
    }
    
    
    
    @Override
    public int getRowCount() {
     return  turnos.size(); 
    }

    @Override
    public int getColumnCount() {
        return columnaNombre.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Turno a=  turnos.get (rowIndex);
            switch(columnIndex){
           case 0: 
           return a.getId();
           case 1:
           return a.getCodigo();
           case 2:
           return a.getDescripcion();
           default : 
           return "";
           }
    }
    
     @Override
    public String getColumnName(int column) {
    return columnaNombre[column];
    }
    
    public void borrarFilas(int indice){
        turnos.remove(indice);
        this.fireTableRowsDeleted(indice, indice);
        
    
    }
}
