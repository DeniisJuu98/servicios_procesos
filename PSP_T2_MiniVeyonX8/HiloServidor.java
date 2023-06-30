/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyonX8;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Vespertino
 */
public class HiloServidor implements Runnable {
    
    //SERVIDOR, MIRAR EJEMPLO ANTERIOR, ES IGUAL
    
    private int numero;
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
            byte[] a;
            Socket clienteSocket = s;
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("H:\\ACCESO A DATOS\\Programacion2022\\Captura" + numero + ".jpg"));
            BufferedOutputStream out = new BufferedOutputStream(clienteSocket.getOutputStream());

            int n = 0;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
//            a = in.readAllBytes();
//            out.write(a);
            //extra para saber cuando se acaba de enviar los byts de la imagen
            out.flush();
            //out.write(-1);

            //out.flush();
            in.close();
            out.close();
        } catch (IOException ex) {
        }

    }

}
