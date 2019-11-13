/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author DjNiico
 */
public class Docente extends Persona{
    private Integer idDOcente;
    private Integer numeroLegajo;
    private Cargo tipoCargo;
    private ArrayList<Grado> grado;
    
    public Docente() {
    }

    public Integer getIdDOcente() {
        return idDOcente;
    }

    public void setIdDOcente(Integer idDOcente) {
        this.idDOcente = idDOcente;
    }

    public Docente(Integer numeroLegajo, Cargo tipoCargo, ArrayList<Grado> grado) {
        this.numeroLegajo = numeroLegajo;
        this.tipoCargo = tipoCargo;
        this.grado = grado;
    }

    public Docente(Integer numeroLegajo, Cargo tipoCargo, ArrayList<Grado> grado, Integer id, String nombre, String apellido, String documento, String fechaNacimiento, String sexo) {
        super(id, nombre, apellido, documento, fechaNacimiento, sexo);
        this.numeroLegajo = numeroLegajo;
        this.tipoCargo = tipoCargo;
        this.grado = grado;
    }

    
    
    public Integer getNumeroLegajo() {
        return numeroLegajo;
    }

    public void setNumeroLegajo(Integer numeroLegajo) {
        this.numeroLegajo = numeroLegajo;
    }

    public Cargo getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(Cargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    @Override
    public String toString() {
        return "N° Legajo: " + numeroLegajo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.numeroLegajo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Docente other = (Docente) obj;
        if (!Objects.equals(this.numeroLegajo, other.numeroLegajo)) {
            return false;
        }
        return true;
    }

   

   

    
  
    
}
