/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyon;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vespertino
 */
public class HiloServidor implements Runnable {

    //LE PASAMOS UN NUMERO PARA QUE SEA EL NUMERO DE LA IMAGEN QUE QUIERA ENVIAR
    private int numero;
    //LE PASAMOS EL SOCKET CLIENTE PARA LA CONFIRMACION Y EL SERVER SOCKET
    
    //------------------------------------------
    //IMPORTANTE!!! SOCKET CLIENTE NO HARIA FALTA
    //------------------------------------------
    private Socket s;
    private ServerSocket ss;

    public HiloServidor(int numero, Socket s, ServerSocket ss) {
        this.numero = numero;
        this.s = s;
        this.ss = ss;
    }

    @Override
    public void run() {
        try {
            //byte[] a;
            //------------------------------------------
            //IMPORTANTE!!! SOCKET CLIENTE NO HARIA FALTA
            //------------------------------------------
            Socket clienteSocket = s;//<----------------
            //------------------------------------------
            //INICIAMOS EL IN PARA COGER DATOS DE LA IMAGEN
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("H:\\ACCESO A DATOS\\Programacion2022\\Captura" + numero + ".jpg"));
            //INICIAMOS EL OUT PARA ENVIAR LOS DATOS
            BufferedOutputStream out = new BufferedOutputStream(clienteSocket.getOutputStream());

            //LEE BYTE A BYTE HASTA LLEGAR A -1
            int n = 0;
            while ((n = in.read()) != -1) {
                out.write(n);//MANDA LOS DATOS CON EL "OUT"
            }
//            a = in.readAllBytes();
//            out.write(a);

            //extra para saber cuando se acaba de enviar los byts de la imagen
            out.flush();
            //out.write(-1);

            //out.flush();
            //CIERRA CONEXIONES
            in.close();
            out.close();
        } catch (IOException ex) {
        }

    }

}
