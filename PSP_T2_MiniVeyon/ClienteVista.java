/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyon;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Vespertino
 */
public class ClienteVista extends JFrame {

    //DECLARAS LOS ELEMENTOS DE LA VISTA
    private JLabel lb;
    private GridLayout panel;

    //INICIAS LA VISTA
    public ClienteVista() {
        //INICIAS LAS COSAS
        lb = new JLabel();
        panel = new GridLayout(1,1);//1X1 ES UNA TABLA
        
        //SETEAS EL LAYOUT CON EL NUEVOC READO
        getContentPane().setLayout(panel);
        //AÑADES EL LABEL
        getContentPane().add(lb);
        //PARA CERRAR LA VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    //MAIN DEL METODO
    public static void main(String[] args){
        //INICIAS LA VISTA
        ClienteVista cv = new ClienteVista();
        cv.setVisible(true);
        
        //HACES EL CLIENTE
        String ip = "10.2.2.7";
        int puerto = 2055;
        
        //HACES QUE FUNCIONE TODO EL RATO YA QUE QUIERES LAS IMAGENES CONTINUAMENTE
        while (true) {
            try {
                //ABRES EL SOCKET
                Socket clienteSocket = new Socket(ip, puerto);
                //COMO SOLO RECIBE SOLO HACES EL "IN"
                BufferedInputStream in = new BufferedInputStream(clienteSocket.getInputStream());
                //CREAR UNA IMAGEN
                BufferedImage someImage;
                //METES LOS DATOS DEL IN EN LA IMAGEN
                someImage = ImageIO.read(in);
                
                //REESCALAR LA IMAGEN
                //Icon icon = new ImageIcon(someImage.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), someImage.SCALE_AREA_AVERAGING));
                
                //CREAS UN ICONO QUE METERAS EL "SOMEIMAGEN"
                Icon icon = new ImageIcon(someImage);
                //SETEAS EL LABEL CON EL ICONO
                cv.lb.setIcon(icon);
                someImage.flush();//CONFIRMAS LOS CAMBIOS

                //!! NO CIERRAS CONEXIONES
//                in.close();
//                clienteSocket.close();
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }
        }
        
        
    }

}
