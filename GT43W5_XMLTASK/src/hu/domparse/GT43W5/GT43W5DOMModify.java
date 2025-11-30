package hu.domparse.GT43W5;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GT43W5DOMModify {

    public static void main(String[] args) {
        try {
            // XML Fájl beolvasása 
            File xmlFile = new File("GT43W5_XML.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            
            // Dokumentum normalizálása
            doc.getDocumentElement().normalize();

            System.out.println("--- ADATMÓDOSÍTÁS KEZDÉSE ---");

            // 1. Feladat: A 30-as ID-jú sonkás pizza ára 3500-ra nõ.
            System.out.println("1. módosítás: A 30-as azonosítójú pizza árának módosítása 3500-ra.");
            
            NodeList pizzaList = doc.getElementsByTagName("Pizza");
            for (int i = 0; i < pizzaList.getLength(); i++) {
                Node node = pizzaList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element pizza = (Element) node;
                    // A megfelelõ ID megkeresése
                    if (pizza.getAttribute("Pizza_ID").equals("30")) {
                        // <Ar> elem tartalmának átírasa
                        Node arNode = pizza.getElementsByTagName("Ar").item(0);
                        arNode.setTextContent("3500");
                    }
                }
            }

            
            // 2. feladat: A 10-es beszállító nevének átírása 'Bio-Farm Kft.'-re.
            System.out.println("2. módosítás: A 10-es beszállító nevének átírása 'Bio-Farm Kft.'-re.");
            
            NodeList beszallitoList = doc.getElementsByTagName("Beszallito");
            for (int i = 0; i < beszallitoList.getLength(); i++) {
                Node node = beszallitoList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element besz = (Element) node;
                    if (besz.getAttribute("B_ID").equals("10")) {
                        Node nevNode = besz.getElementsByTagName("Nev").item(0);
                        nevNode.setTextContent("Bio-Farm Kft.");
                    }
                }
            }

            
            // 3. Feladat: A 20-as futár jármûvének cseréje Autó-ra.
            System.out.println("3. módosítás: A 20-as futár jármûvének cseréje 'Autó'-ra.");
            
            NodeList futarList = doc.getElementsByTagName("Futar");
            for (int i = 0; i < futarList.getLength(); i++) {
                Node node = futarList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element futar = (Element) node;
                    if (futar.getAttribute("F_ID").equals("20")) {
                        Node jarmuNode = futar.getElementsByTagName("Jarmu").item(0);
                        jarmuNode.setTextContent("Autó");
                    }
                }
            }
            
            
            // 4. feladat: A Surgos elem hozzáadása az 500-as rendeléshez.
            System.out.println("4. módosítás: 'Surgos' elem hozzáadása az 500-as rendeléshez.");
            
            NodeList rendelesList = doc.getElementsByTagName("Rendeles");
            for (int i = 0; i < rendelesList.getLength(); i++) {
                Node node = rendelesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element rendeles = (Element) node;
                    if (rendeles.getAttribute("R_ID").equals("500")) {
                        // Új elem létrehozása
                        Element ujElem = doc.createElement("Surgos");
                        ujElem.setTextContent("IGEN");
                        // A rendelés gyermekeihez való hozzáfûzés
                        rendeles.appendChild(ujElem);
                    }
                }
            }

            //A módosított dokumentum kiírása fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            //Formázás beállítása
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            // Új fájlba mentés
            StreamResult result = new StreamResult(new File("GT43W5_XML_Modify_Output.xml"));
            
            transformer.transform(source, result);
            
            System.out.println("\n--- SIKERES MENTÉS ---");
            System.out.println("A módosított fájl neve: GT43W5_XML_Modify_Output.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}