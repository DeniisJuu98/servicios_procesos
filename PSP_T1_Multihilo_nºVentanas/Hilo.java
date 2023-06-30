/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T1_Multihilo_nºVentanas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Vespertino
 */
public class Hilo implements Runnable {

    private Run r;
    private int tiempo;
    private JLabel lb;
    
    public Hilo(Run r, int tiempo, JLabel lb) {
        this.r = r;
        this.tiempo = tiempo;
        this.lb = lb;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(tiempo);
                lb.setText("Contador : " + i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
