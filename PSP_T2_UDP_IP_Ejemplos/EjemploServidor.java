/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_UDP_IP_Ejemplos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Vespertino
 */
public class EjemploServidor {

    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.out.println("Error en los argumentos");
//        } else {

        int port = 2500;
        final int MAX_LEN = 50;
        try {
                DatagramSocket mySocket = new DatagramSocket(port);
            while (true) {
                //INSTANCIAMOS
                byte[] buffer = new byte[MAX_LEN];
                DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
                System.out.println("Esperando");
                mySocket.receive(datagram);
                String message = new String(buffer);
                if (message.trim().equals("0")) {
                    mySocket.close();
                    return;
                }
                System.out.println(message);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        }
    }

}
