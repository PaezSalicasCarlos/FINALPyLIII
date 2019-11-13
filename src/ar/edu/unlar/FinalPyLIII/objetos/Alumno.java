/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.objetos;

import java.util.Objects;

/**
 *
 * @author DjNiico
 */
public class Alumno extends Persona {
    private Integer matricula;
    private Grado grado;
    private Integer IdAlumno;

    public Integer getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(Integer IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

   
    

    public Alumno() {
    }

    public Alumno(Integer matricula, Grado grado) {
        this.matricula = matricula;
        this.grado = grado;
    }

    public Alumno(Integer matricula, Grado grado, Integer id, String nombre, String apellido, String documento, String fechaNacimiento, String sexo) {
        super(id, nombre, apellido, documento, fechaNacimiento, sexo);
        this.matricula = matricula;
        this.grado = grado;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.matricula);
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
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
    
}
