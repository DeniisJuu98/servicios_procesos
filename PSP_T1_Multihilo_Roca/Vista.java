package PSP_T1_Multihilo_Roca;
import javax.swing.*;
/**
 * @author Victor
 */

public class Vista extends JFrame {
    //declara los elementos
    private JLabel[] lblRocas;
    //tamaño de la ventana
    private int tamañoX = 800, tamañoY =600, contador;
    private Roca[] rocas;
    private Thread[] hilosRoca;
    //constructor de la vista
    public Vista(){
        propiedadesVentana();
        instanciarComponentes();
    }
    
    private void propiedadesVentana() {
        //te permite crear una ventana inicial para sacar un mensaje
        //y sacar un numero que sera el contador
        contador = Integer.parseInt(JOptionPane.showInputDialog("Introduce el numero de rocas"));
        //setea el layout a null, para poder poner cualquier comonente en cualquier sitio
        getContentPane().setLayout(null);
        //le pone el tamaño a la ventana
        setSize(tamañoX, tamañoY);
        //campos obligatorios
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    } 

    private void instanciarComponentes() {
        //inicia los componentes
        lblRocas = new JLabel[contador];
        rocas = new Roca[contador];
        hilosRoca = new Thread[contador];
        for (int i = 0; i < contador; i++) {
            lblRocas[i] = new JLabel(new ImageIcon(getClass().getResource("/Recursos/roca.png")));
            getContentPane().add(lblRocas[i]);
            rocas[i] = new Roca(i, tamañoX, tamañoY);//borde donde se choca la roca
            rocas[i].setRadio(38);//margen desde el centro de la imagen
            hilosRoca[i] = new Thread(new RocaThread(this, rocas[i]));
            hilosRoca[i].start();
        }
    }

    //mueve la imagen en la posicion nueva
    public void pintarRoca(Roca roca) {
        lblRocas[roca.getNumRoca()].setBounds(roca.getPosX(), roca.getPosY(), 75, 75);
    }
}