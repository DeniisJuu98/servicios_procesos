package PSP_T1_Multihilo_Roca;
/**
 * @author Víctor Burón
 */
public class Roca {
    private int posX, posY, dirX, dirY, radio=10, margenX, margenY, numRoca;
    
    //constructor de roca
    public Roca(int numRoca, int margenX, int margenY) {
        this.numRoca = numRoca;
        this.margenX = margenX-45;
        this.margenY = margenY-45;
        posX = (int) (Math.random()*600+80);
        posY = (int) (Math.random()*400+80);
        dirX = posX%10 + generarDireccion();
        dirY = posY%10 + generarDireccion();
    }

    public int getNumRoca() {
        return numRoca;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
    
    //numero aleatorio sea -1 o 1
    
    //genera la direccion en el que va a ir la roca, si hacia delante o hacia atras
    private int generarDireccion() {
        byte num = (byte) (Math.random()*256-128);
        if (num>0){
            return 1;
        } else {
            return -1;
        }
    }
    
    //mueve la roca
    public void moverRoca() {
        posX += dirX;
        posY += dirY;
        
        if ((posX - radio) <-1) {
            dirX*=-1;
        } else if ((posX+radio) >= margenX)  {
            dirX*=-1;
        }
        
        if ((posY - radio) <-1) {
            dirY*=-1;
        } else if ((posY+radio) >= margenY)  {
            dirY*=-1;
        }
    }
}