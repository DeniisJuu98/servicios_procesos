/*
Consulta el enlace donde aparece un ejemplo de comunicación
bidireccional en UDP, prueba a hacer un pequeño chat, con las
características siguientes:
● El programa servidor, tendrá un hilo que se encargará únicamente
de hacer receive y cuando reciba algún mensaje, mostrará por
pantalla el mensaje.
● El programa servidor, en su programa padre realizará send, a
partir de los mensajes leídos al usuario con Scanner.
● El programa cliente, tendrá igual que el servidor su propio hilo
que hará los receive y mostrará por pantalla lo que reciba.
● El programa cliente, del mismo modo en su programa padre
realizará send, a partir de los mensajes leídos al usuario con
Scanner.
Crea un IGU con Swing, con un JList (para mostrar los mensajes del
usuario1 y el usuario2), un JButton (para enviar lo que se ponga en
JTextField) y JTextField (donde el usuario pondrá el mensaje)
 */
package PSP_T2_TCP_IP.Chat.Ej1;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Vespertino
 */
public class Hilo implements Runnable {

    private List r;
    private int puerto;

    public Hilo(List r, int puerto) {
        this.r = r;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            Socket clienteSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            String inputLine = in.readLine();
            r.add("\n" + inputLine);
            String outputLine = inputLine.toUpperCase();
            r.add("\n" + outputLine);
            out.close();
            in.close();
            clienteSocket.close();
            serverSocket.close();
        } catch (IOException ex) {
        }
    }
}
