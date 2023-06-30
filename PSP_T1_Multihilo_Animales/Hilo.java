/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T1_Multihilo_Animales;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vespertino
 */
//le pasamos la vista "v", para poder llamar al metodo setMensaje
public class Hilo implements Runnable{
    private Carrera v;
    private String animal;
    private int tiempo;
    //constructor del hilo
    public Hilo(Carrera v,String animal,int tiempo) {
        this.v = v;
        this.tiempo = tiempo;
        this.animal = animal;
    }
    
    //lo que ocurre al iniciarlo
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                //tiempo de espera de cada animal
                Thread.sleep(tiempo);
                //setea el mensaje 
                v.setMensaje(animal,i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    
}
