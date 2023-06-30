/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyonX8;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.imageio.ImageIO;

/**
 *
 * @author Vespertino
 */
public class HiloCliente implements Runnable {

    private ClienteVista cv;
    private int num;
    private String ip;

    public HiloCliente(ClienteVista cv, int num, String ip) {
        this.cv = cv;
        this.num = num;
        this.ip = ip;
    }

    @Override
    public void run() {

        int puerto = 1234;
        while (true) {
            try {
                //CREA UN NUEVO VLIENTE
                Socket clienteSocket = new Socket(ip, puerto);
                //SOLO RECIBE LA IMAGEN DEL SERVIDOR
                BufferedInputStream in = new BufferedInputStream(clienteSocket.getInputStream());
                BufferedImage someImage;
                someImage = ImageIO.read(in);

                //LLAMA AL METODO DE LA VISTA, PARA IR CAMBIANDO LAS IMAGENES
                //QUE LE CORRESPONDE A CADA CLIENTE
                cv.muestraImagen(num, someImage);
                //CIERRA LAS CONEXIONES
                //UNA VEZ CERRADAS LAS CONEXIONES VUELVE AL PRINCIPIO PARA CAMBIAR OTRA VEZ LA IMAGEN DE ESE CLIENTE
                in.close();
                clienteSocket.close();
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }
        }

    }

}
