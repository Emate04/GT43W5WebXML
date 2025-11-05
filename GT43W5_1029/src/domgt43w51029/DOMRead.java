package domgt43w51029;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("GT43W5hallgato.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");

            NodeList nList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Hallgató (ID: " + eElement.getAttribute("id") + ")");
                    
                    System.out.println("  Keresztnév: " 
                        + eElement.getElementsByTagName("keresztnev").item(0).getTextContent());
                    
                    System.out.println("  Vezetéknév: " 
                        + eElement.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    
                    System.out.println("  Foglalkozás: " 
                        + eElement.getElementsByTagName("foglalkozas").item(0).getTextContent());
                        
                    System.out.println();}}}
            
         catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }}

                
    
