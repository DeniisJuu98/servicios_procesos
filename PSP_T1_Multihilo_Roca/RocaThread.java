package PSP_T1_Multihilo_Roca;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Victor Burón
 */
public class RocaThread implements Runnable{
    //se le pasa la vista y el objeto roca
    private Vista v;
    private Roca roca;
    
    //constructor del hilo
    public RocaThread(Vista v, Roca roca) {
        this.v = v;
        this.roca = roca;
    }
    
    @Override
    public void run() {
        while (true) { 
            //mueve la roca 
            roca.moverRoca();
            //lo mueve la imagen en la posicion nueva
            v.pintarRoca(roca);
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(RocaThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
}