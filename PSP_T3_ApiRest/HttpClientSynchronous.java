/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSP_T3_ApiRest;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HttpClientSynchronous {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://www.aemet.es/xml/municipios_h/localidad_h_26089.xml"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String XMLCompleto = response.body();
        //System.out.println(XMLCompleto);
        filtrarEstadoDelCielo(XMLCompleto);

    }

    private static void filtrarEstadoDelCielo(String XMLCompleto) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Convertir String en InputStream
            InputStream stream = new ByteArrayInputStream(XMLCompleto.getBytes(StandardCharsets.UTF_8));
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("dia"); // Cojo los nodos cuyo tag es dia
            //System.out.println("Nodos dia: " + nList.getLength());

            extraeEstadoCielo(nList.item(0)); // Sólo me interesa el DIA DE HOY

        } catch (SAXException ex) {
            Logger.getLogger(HttpClientSynchronous.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpClientSynchronous.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(HttpClientSynchronous.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void extraeEstadoCielo(Node item) {
        if (item.getNodeType() == Node.ELEMENT_NODE) {
            NodeList dia = item.getChildNodes(); // Cojo la lista de nodos hijos dentro de día
            //System.out.println("Hijos dentro de dia: " + dia.getLength());
            for (int i = 0; i < dia.getLength(); i++) {
                Node aux = dia.item(i); // Cojo el primer nodo                               
                if (aux.getNodeName().equals("estado_cielo")) { // Muestro el estado del cielo durante todo el día
                    System.out.println("\nESTADO DEL CIELO");
                    Element e = (Element) aux;
                    System.out.println("A las " + e.getAttribute("periodo") + " - " + e.getAttribute("descripcion"));
                }
                if (aux.getNodeName().equals("temperatura")) {
                    System.out.println("\nTEMPERATURA");
                    Element e = (Element) aux;
                    System.out.println("A las " + e.getAttribute("periodo") + " - " + "Con temperatura: " + e.getTextContent() + " ºC");
                }
                if (aux.getNodeName().equals("viento")) {
                    System.out.println("\nVIENTO");
                    Element e = (Element) aux;
                    System.out.println("A las " + e.getAttribute("periodo") + " - " + "Con direccion: " + e.getElementsByTagName("direccion").item(0).getTextContent() + " Y velocidad: " + e.getElementsByTagName("velocidad").item(0).getTextContent());
                }
                if (aux.getNodeName().equals("prob_precipitacion")) { 
                    System.out.println("\nPROB.PRECIPITACION");
                    Element e = (Element) aux;
                    System.out.println("A las " + e.getAttribute("periodo") + " - " + e.getTextContent());
                }
            }
        }
    }

}
