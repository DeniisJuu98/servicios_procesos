/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T3_ApiRest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.WindowConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Vespertino
 */
public class Ejercicio2 extends JFrame {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String numSelect = "";
    private List tf;
    private JComboBox combo;

    public void empezar() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://www.aemet.es/xml/municipios_h/localidad_h_26089.xml"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String XMLCompleto = response.body();
        //System.out.println(XMLCompleto);
        filtrarEstadoDelCielo(XMLCompleto);

    }

    public Ejercicio2() {
        tf = new List();
        
        // Creacion del JComboBox y añadir los items.
        combo = new JComboBox();
        combo.addItem("08");
        combo.addItem("09");
        combo.addItem("10");
        combo.addItem("11");
        combo.addItem("12");
        combo.addItem("13");
        combo.addItem("14");
        combo.addItem("15");
        combo.addItem("16");
        combo.addItem("17");
        combo.addItem("18");
        combo.addItem("19");
        combo.addItem("20");
        combo.addItem("21");
        combo.addItem("22");
        combo.addItem("23");

        // Accion a realizar cuando el JComboBox cambia de item seleccionado.
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numSelect = String.valueOf(combo.getSelectedItem());
                tf.clear();
                try {
                    empezar();
                } catch (IOException ex) {
                    Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Creacion de la ventana con los componentes
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(combo, BorderLayout.NORTH);
        getContentPane().add(tf, BorderLayout.CENTER);
        setSize(400, 250);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void filtrarEstadoDelCielo(String XMLCompleto) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Convertir String en InputStream
            InputStream stream = new ByteArrayInputStream(XMLCompleto.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("dia"); // Cojo los nodos cuyo tag es dia
            //System.out.println("Nodos dia: " + nList.getLength());

            extraeEstadoCielo(nList.item(1)); // Sólo me interesa el DIA DE HOY

        } catch (SAXException ex) {
            Logger.getLogger(HttpClientSynchronous.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpClientSynchronous.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(HttpClientSynchronous.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void extraeEstadoCielo(Node item) {
        if (item.getNodeType() == Node.ELEMENT_NODE) {
            NodeList dia = item.getChildNodes(); // Cojo la lista de nodos hijos dentro de día
            //System.out.println("Hijos dentro de dia: " + dia.getLength());
            for (int i = 0; i < dia.getLength(); i++) {
                Node aux = dia.item(i); // Cojo el primer nodo                               
                if (aux.getNodeName().equals("estado_cielo")) { // Muestro el estado del cielo durante todo el día
                    System.out.println("\nESTADO DEL CIELO");
                    Element e = (Element) aux;
                    if (e.getAttribute("periodo").equals(numSelect)) {
                        tf.add("A las " + e.getAttribute("periodo") + " - " + e.getAttribute("descripcion") + "\n");
                    }
                }
                if (aux.getNodeName().equals("temperatura")) {
                    System.out.println("\nTEMPERATURA");
                    Element e = (Element) aux;
                    if (e.getAttribute("periodo").equals(numSelect)) {
                        tf.add("A las " + e.getAttribute("periodo") + " - " + "Con temperatura: " + e.getTextContent() + " ºC" + "\n");
                    }
                }
                if (aux.getNodeName().equals("viento")) {
                    System.out.println("\nVIENTO");
                    Element e = (Element) aux;
                    if (e.getAttribute("periodo").equals(numSelect)) {
                        tf.add("A las " + e.getAttribute("periodo") + " - " + "Con direccion: " + e.getElementsByTagName("direccion").item(0).getTextContent() + " Y velocidad: " + e.getElementsByTagName("velocidad").item(0).getTextContent() + "\n");
                    }
                }
                if (aux.getNodeName().equals("prob_precipitacion")) {
                    System.out.println("\nPROB.PRECIPITACION");
                    Element e = (Element) aux;
                    if (e.getAttribute("periodo").equals(numSelect)) {
                        tf.add("A las " + e.getAttribute("periodo") + " - " + e.getTextContent() + "\n");
                    }
                }
            }
        }
    }
}
