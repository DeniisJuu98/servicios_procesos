/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyonX8;

/**
 *
 * @author Vespertino
 */
public class Run {
    public static void main(String[] args) {
        //INICIA LA VISTA
        ClienteVista cv = new ClienteVista();
        cv.setVisible(true);
        //INICIA EL HILO DE LA VISTA 
        //SIRVE PARA HACER VARIOS CLIENTES
        cv.empezar();
    }
    
}
