/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T1_Multihilo_Reloj;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author denis
 */
public class Hilo implements Runnable{
    
    int numero, temporizador, numeroMaximo;
    Vista vista;
    
    public Hilo(int temporizador, int numeroMaximo, Vista vista){
        this.numero = 0;
        this.temporizador = temporizador * 1000;
        this.numeroMaximo = numeroMaximo - 1;
        this.vista = vista;
    }
    
    @Override
    public void run() {
        if(temporizador == 1000){
            while(true){
                try {
                    vista.cambiarSegundo(numero);
                    Thread.sleep(temporizador);
                    if(numero < numeroMaximo){
                        numero++;
                    }else{
                        numero = 0;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(temporizador > 60000){
            while(true){
                try {
                    vista.cambiarHora(numero);
                    Thread.sleep(temporizador);
                    if(numero < numeroMaximo){
                        numero++;
                    }else{
                        numero = 0;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            while(true){
                try {
                    vista.cambiarMinuto(numero);
                    Thread.sleep(temporizador);
                    if(numero < numeroMaximo){
                        numero++;
                    }else{
                        numero = 0;
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
}
