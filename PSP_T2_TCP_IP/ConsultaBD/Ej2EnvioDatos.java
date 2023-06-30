/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_TCP_IP.ConsultaBD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Vespertino
 */
public class Ej2EnvioDatos {

    //CLIENTE
    public static void main(String[] args) {
        //DECLARAMOS LA IP Y EL PUERTO
        String ip = "10.2.2.7";
        int puerto = 2045;
        try {
            //INICIAMOS UN SOCKET DONDE LE PASAMOS LA IP Y EL PUERTO
            Socket clienteSocket = new Socket(ip, puerto);
            //DECLARAMOS UN PUERTO DE SALIDA
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            //DECLARAMOS UN PUERTO DE ENTRADA
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            //LE ENVIAMOS UNA ID, PARA LA SELECT EN LA PASE DE DATOS
            System.out.println("Enviando : 1");
            //LA MANDAMOS CON EL OUT
            out.println("1");

            //RECIBIMOS EL MENSAJE DEL SERVIDOR CON EL "IN"!!!!
            System.out.println("Recibiendo : " + in.readLine());

            //CERRAMOS CONEXIONES
            out.close();
            in.close();
            clienteSocket.close();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }

}
