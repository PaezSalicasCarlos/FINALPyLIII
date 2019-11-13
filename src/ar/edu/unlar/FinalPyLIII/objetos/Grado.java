/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.objetos;

import java.util.ArrayList;

/**
 *
 * @author DjNiico
 */
public class Grado {
    private Integer id;
    private Integer numero;
    private String seccion;
    private Turno turno;
//    private ArrayList<Alumno> alumno= new ArrayList<>();
     ArrayList<Docente> docentes= new ArrayList<>();

   

    public Grado() {
    }

    public Grado(Integer id, Integer numero, String seccion, Turno turno) {
        this.id = id;
        this.numero = numero;
        this.seccion = seccion;
        this.turno = turno;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

   


    public ArrayList<Docente> getDocente() {
        return docentes;
    }

    public void setDocente(ArrayList<Docente> docentes) {
        this.docentes = docentes;
    }


   
    
     @Override
    public String toString() {
        return numero + " " + seccion ;
    }

   
 
    
    
}
