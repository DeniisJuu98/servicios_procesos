/*
Realizar un programa que muestre al abrir un Jframe muestre una ventana de diálogo pidiendo
introducir un número N. Posteriormente muestre N contadores de 1 a 99 (Jlabels) distribuidos de
forma organizada en la ventana (con un gridLayout). Cada contador será un hilo diferente con
tiempo aleatorio entre 500-1000 milisegundos.
 */
package PSP_T1_Multihilo_nºVentanas;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Run extends JFrame {

    private FlowLayout a;

    public Run(int num) {
        a = new FlowLayout(FlowLayout.LEFT, 5, 5);
        getContentPane().setLayout(a);

        for (int i = 0; i < num; i++) {
            JLabel lb = new JLabel();
            getContentPane().add(lb);
            Hilo primero = new Hilo(this, 45, lb);
            Thread t1 = new Thread(primero);
            t1.start();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
        setVisible(true);
    }

}
