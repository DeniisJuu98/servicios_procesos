/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyon;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

/**
 *
 * @author Vespertino
 */
public class Servidor {

    public static void main(String[] args) throws InterruptedException, IOException {
        //EMPIEZAS EL HILO PARA CREAR LAS IMAGENES
        empezarHilo();
        int i = 1;
        //CREAS UN SOCKET PARA SERVIDOR Y LE PASAS EL !!! PUERTO DEL CLIENTE !!!
        ServerSocket serverSocket = new ServerSocket(2055);
        //CREAS MULTIPLES CLIENTES SERVIDORES, PARA ENVIAR TODAS LAS IMAGENES QUE SE VAN CREANDO
        while (true) {
            //acepta la conexion con el cliente
            Socket clienteSocket = serverSocket.accept();
            
            //CREA UN NUEVO HILO SERVIDOR (CREA UN SERVIDOR NUEVO QUE ENVIA UN SOLO DATO)
            //------------------------------------------
            //IMPORTANTE!!! SOCKET CLIENTE NO HARIA FALTA
            //------------------------------------------
            HiloServidor hs = new HiloServidor(i, clienteSocket, serverSocket);
            Thread t = new Thread(hs);
            t.start();
            
            Thread.sleep(2000);//SLEEP DE 2 SEGUNDOS
            i++;
        }
    }

    //------------------------------------------
    //HILO PARA CREAR IMAGENES
    //------------------------------------------
    public static void empezarHilo() {
        Hilo h = new Hilo("Captura", 1999);
        Thread t1 = new Thread(h);
        t1.start();
    }

}
