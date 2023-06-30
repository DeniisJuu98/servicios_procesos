/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T2_UDP_IP.Chat;

import java.awt.List;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JTextField;

/**
 *
 * @author Vespertino
 */
public class Vista1 extends javax.swing.JFrame {

    /**
     * Creates new form Vista1
     */
    public Vista1() {
        initComponents();
        empezarHilo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        listaChat = new java.awt.List();
        Enviar = new javax.swing.JButton();
        jText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Enviar.setText("Enviar");
        Enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EnviarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EnviarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jText, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(Enviar))
                    .addComponent(listaChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listaChat, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Enviar)
                    .addComponent(jText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //IMPORTANTE ESTE ES EL || USUARIO ||
    private void EnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnviarMouseClicked
        //PONEMOS NUESTRA IP Y EL PUERTO QUE QUEREMOS
        String ip = "localhost", puerto = "2045";
        try {
            //PASAMOS LOS DATOS DE LAS CONEXIONES
            InetAddress reciverHost = InetAddress.getByName(ip);
            int reciverPort = Integer.parseInt(puerto);
            //COGEMOS EL MENSAJE DEL TXT
            String message = jText.getText();
            //instanciamos los socket
            //INICIAMOS UN SOCKET
            DatagramSocket mySocket = new DatagramSocket();
            //METEMOS EN UN ARRAY DE BYTES EL MENSAJE QUE HEMOS COGIDO ANTERIORMENTE
            byte[] buffer = message.getBytes();
            //CREAMOS EL PAQUETE PARA EL ENVIO
            DatagramPacket datagram
                    = new DatagramPacket(buffer, buffer.length, reciverHost, reciverPort);
            //ENVIAMOS POR EL SOCKET EL PAQUETE CON LOS DATOS
            mySocket.send(datagram);
            //CERRAMOS EL SOCKET
            mySocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_EnviarMouseClicked

    private void EnviarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EnviarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnviarMousePressed

    public void empezarHilo() {
        Hilo h = new Hilo(listaChat, 2045);
        Thread t1 = new Thread(h);
        t1.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enviar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jText;
    private java.awt.List listaChat;
    // End of variables declaration//GEN-END:variables

}
