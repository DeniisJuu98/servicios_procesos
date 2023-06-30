/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyonX8;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Vespertino
 */
public class ClienteVista extends JFrame {

    //CREAS LO QUE CONTIENE EL PANEL
    private JLabel lb[] = new JLabel[8];
    private GridLayout panel;

    
    public ClienteVista() {
        //INICIAS LOS ELEMENTOS
        lb[0] = new JLabel();
        lb[1] = new JLabel();
        lb[2] = new JLabel();
        lb[3] = new JLabel();
        lb[4] = new JLabel();
        lb[5] = new JLabel();
        lb[6] = new JLabel();
        lb[7] = new JLabel();
        
        //CREAS EL LAYOUT TABLA
        panel = new GridLayout(2, 4,5,5);
        //SETEAS EL LAYOUR
        getContentPane().setLayout(panel);
        //AÑADES LOS ELEMENTOS
        getContentPane().add(lb[0]);
        getContentPane().add(lb[1]);
        getContentPane().add(lb[2]);
        getContentPane().add(lb[3]);
        
        getContentPane().add(lb[4]);
        getContentPane().add(lb[5]);
        getContentPane().add(lb[6]);
        getContentPane().add(lb[7]);
        
        //BOTON DE CERRADO
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //SIRVE PARA REALIZAR VARIOS CLIENTES
    public void empezar() {
        String[] listaIp = {"192.34343", "12323232"};//se introducirian las ips
        for (int i = 0; i < 8; i++) {
            HiloCliente hc = new HiloCliente(this, i, listaIp[i]);
            Thread t = new Thread(hc);
            t.start();
        }

    }

    //SIRVE PARA IR CAMBIANDO CADA IMAGEN
    public void muestraImagen(int num, BufferedImage img) {
        Icon icon = new ImageIcon(img.getScaledInstance(lb[num].getWidth(), lb[num].getHeight(), img.SCALE_AREA_AVERAGING));
        //Icon icon = new ImageIcon(img);
        lb[num].setIcon(icon);
        img.flush();

    }

}
