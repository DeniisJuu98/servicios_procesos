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
public class EjemploUsuario {

    public static void main(String[] args) {
        
        if (args.length != 3) {
            System.out.println("Error en los argumentos");
        } else {
            try {
                InetAddress reciverHost = InetAddress.getByName(args[0]);
                int reciverPort = Integer.parseInt(args[1]);
                String message = args[2];

                //instanciamos los socket
                DatagramSocket mySocket = new DatagramSocket();
                byte[] buffer = message.getBytes();
                DatagramPacket datagram
                        = new DatagramPacket(buffer, buffer.length, reciverHost, reciverPort);
                System.out.println("Enviando mensaje");
                mySocket.send(datagram);
                mySocket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
