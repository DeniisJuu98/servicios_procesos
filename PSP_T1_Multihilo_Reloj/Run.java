/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T1_Multihilo_Reloj;

/**
 *
 * @author Vespertino
 */
public class Run {
    public static void main(String[] args) {
        Vista v = new Vista();
        
        Hilo segundo = new Hilo(1, 60, v);
        Hilo minuto = new Hilo(60, 60, v);
        Hilo hora = new Hilo(3600, 24, v);
        
        Thread hiloHora = new Thread(hora);
        Thread hiloMinuto = new Thread(minuto);
        Thread hiloSegundo = new Thread(segundo);
        
        hiloSegundo.start();
        hiloMinuto.start();
        hiloHora.start();
    }
}
