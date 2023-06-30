/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_MiniVeyonX8;


import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Vespertino
 */
public class Hilo implements Runnable {
    //HILO PARA CREAR LAS IMAGENES
    private String nombre;
    private int tiempo;

    public Hilo(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    
    @Override
    public void run() {
        int i = 1;
        while (true) {
            try {
                Thread.sleep(tiempo);
                BufferedImage captura = null;
                try {
                    captura = new Robot().createScreenCapture(
                            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                } catch (AWTException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Guardar Como JPEG
                File file = new File(nombre + i + ".jpg");
                ImageIO.write(captura, "jpg", file);
            } catch (InterruptedException ex) {
                Logger.getLogger(PSP_T1_Multihilo_Animales.Hilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }

    }

}
