/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.manejo_datos;

import utils.DataManager;// Aqui adicionamos la clase Datamanager para a traves de ella acceder al fichero

/**
 *
 * @author liudmila
 * En esta clase vamos a utilizar las clases y librerias involucradas en el manejo de datos.
 */
public class Datos {
    
       public static void main(String[] args) throws Exception {
         //Primero creamos un objeto de la clase DataManager que nos permite acceder al fichero
         DataManager dato = new DataManager();
         //Obtenemos el dato que se encuentra en la primera posicion de la columna nombre.
         String datoObtenido = dato.getDato("Nombre", 0);
         //Imprimimos por consola para ver lo que hemos obtenido
         System.out.println(datoObtenido);
         //Escribimos en el excel un dato 
         dato.printDato("prueba", 1, 1);
         
    }
}
