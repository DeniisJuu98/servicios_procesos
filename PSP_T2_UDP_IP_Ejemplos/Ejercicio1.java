/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_UDP_IP_Ejemplos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Vespertino
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String message = "";
        String ip = "10.2.2.4", puerto = "2045";

        while (!message.equals("0")) {
            try {
                InetAddress reciverHost = InetAddress.getByName(ip);
                int reciverPort = Integer.parseInt(puerto);
                System.out.println("Introduce el mensaje: ");
                message = sc.nextLine();
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
