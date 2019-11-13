/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unlar.FinalPyLIII.controladores;

import java.util.ArrayList;

/**
 *
 * @author WinUser
 * @param <T>
 * @param <I>
 */
public interface ICrud <T,I>{

public boolean crear (T entidad);
public boolean eliminar (T entidad);
public boolean modificar (T entidad);
public T extraer (T id);
public ArrayList <T> extraerTodo();
}
