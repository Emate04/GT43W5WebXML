package hu.domparse.GT43W5;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GT43W5DOMQuery {

    public static void main(String[] args) {
        try {
            //XML fájl betöltése
            File xmlFile = new File("GT43W5_XML.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            
            //Dokumentum normalizálása
            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------------------------------");

            
            // 1. lekérdezés: az összes pizzázó nevének kiírása
            System.out.println("1. LEKÉRDEZÉS: Pizzázók nevei");
            
            NodeList pizzazoList = doc.getElementsByTagName("Pizzazo");
            for (int i = 0; i < pizzazoList.getLength(); i++) {
                Node node = pizzazoList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // A <Nev> tag tartalmát kérjük le
                    String nev = element.getElementsByTagName("Nev").item(0).getTextContent();
                    System.out.println(" - " + nev);
                }
            }
            

            // 2. lekérdezés: 1-es id pizzázó futárainak neve
            System.out.println("\n2. LEKÉRDEZÉS: Az 1-es pizzázóhoz tartozó futárok");
            
            NodeList futarList = doc.getElementsByTagName("Futar");
            for (int i = 0; i < futarList.getLength(); i++) {
                Node node = futarList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // A Pizzazo_FK értékének lekjérése
                    String fk = element.getElementsByTagName("Pizzazo_FK").item(0).getTextContent();
                    
                    // Ha az FK értéke "1", akkor kiírjuk a nevet
                    if (fk.equals("1")) {
                        String nev = element.getElementsByTagName("Nev").item(0).getTextContent();
                        System.out.println(" - " + nev);
                    }
                }
            }
            

            // 3. lekérdezés: Miskolcon lakó vevõk kilistázása
            System.out.println("\n3. LEKÉRDEZÉS: Miskolci vevõk listája");
            
            NodeList vevoList = doc.getElementsByTagName("Vevo");
            for (int i = 0; i < vevoList.getLength(); i++) {
                Node node = vevoList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    // A <Cim> elem lekérése
                    Element cimElement = (Element) element.getElementsByTagName("Cim").item(0);
                    //Aztán a <Cim>-en belüli <Varos>-t
                    String varos = cimElement.getElementsByTagName("Varos").item(0).getTextContent();
                    
                    if (varos.equalsIgnoreCase("Miskolc")) {
                        String nev = element.getElementsByTagName("Nev").item(0).getTextContent();
                        System.out.println(" - " + nev);
                    }
                }
            }
            
            
            //4. lekérdezés: rendelésekhez tartozó pizza nevének kikeresése
            System.out.println("\n4. LEKÉRDEZÉS: Rendelésekhez tartozó pizza nevének kikeresése"); 
            NodeList rendelesList = doc.getElementsByTagName("Rendeles");
            NodeList pizzas = doc.getElementsByTagName("Pizza");
            
            //Végigmegyünk a rendeléseken
            for (int i = 0; i < rendelesList.getLength(); i++) {
                 Node rendelesNode = rendelesList.item(i);
                 if (rendelesNode.getNodeType() == Node.ELEMENT_NODE) {
                     Element rendeles = (Element) rendelesNode;
                     String pizzaFK = rendeles.getElementsByTagName("Pizza_FK").item(0).getTextContent();
                     String rendelesID = rendeles.getAttribute("R_ID");
                     
                     // A megfelelõ pizza megkeresése a másik listában
                     String pizzaNeve = "Ismeretlen";
                     for(int j=0; j<pizzas.getLength(); j++) {
                         Node pizzaNode = pizzas.item(j);
                         if (pizzaNode.getNodeType() == Node.ELEMENT_NODE) {
                             Element pizza = (Element) pizzaNode;
                             if(pizza.getAttribute("Pizza_ID").equals(pizzaFK)) {
                                 pizzaNeve = pizza.getElementsByTagName("Nev").item(0).getTextContent();
                                 break; //kilépés a belsõ ciklusból
                             }
                         }
                     }
                     System.out.println(" - Rendelés " + rendelesID + ": " + pizzaNeve);
                 }
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}