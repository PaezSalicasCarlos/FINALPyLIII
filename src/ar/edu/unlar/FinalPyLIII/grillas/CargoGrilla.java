/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.grillas;

import ar.edu.unlar.FinalPyLIII.objetos.Cargo;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DjNiico
 */
public class CargoGrilla extends AbstractTableModel  {
private static final String columnaNombre [] ={"id","codigo","descripcion"};
   
   ArrayList <Cargo> cargos = new ArrayList<>();

    public CargoGrilla() {
    }
    public CargoGrilla(ArrayList <Cargo> cargos) {
     this.cargos=cargos;
    }
    
    
  
    @Override
    public int getRowCount() {
    return  cargos.size(); 
    }

    @Override
    public int getColumnCount() {
    return columnaNombre.length;   
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Cargo a=  cargos.get (rowIndex);
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
        cargos.remove(indice);
        this.fireTableRowsDeleted(indice, indice);
        
    
    }
}
