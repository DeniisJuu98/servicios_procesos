/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T1_Multihilo_nºVentanas;

import javax.swing.JOptionPane;

/**
 *
 * @author Vespertino
 */
public class Ejecutable {
    public static void main(String[] args) {
        String aux;
        int num;
        aux = JOptionPane.showInputDialog("Dime un numero");
        System.out.println("Valor:" + aux);
        
        num = Integer.parseInt(aux);
        Run r = new Run(num);
    }
    
}
