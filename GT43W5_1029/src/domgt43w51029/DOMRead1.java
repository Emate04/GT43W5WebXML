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

public class DOMRead1 {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("GT43W5_orarend.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");

            NodeList nList = doc.getElementsByTagName("ora");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Óra (ID: " + eElement.getAttribute("id") 
                                     + ", Típus: " + eElement.getAttribute("tipus") + ")");
                    
                    System.out.println("  Tárgy: " 
                        + eElement.getElementsByTagName("targy").item(0).getTextContent());
                    
                    System.out.println("  Időpont: ");
                    System.out.println("    Nap: " 
                        + eElement.getElementsByTagName("nap").item(0).getTextContent());
                    System.out.println("    Tól: " 
                        + eElement.getElementsByTagName("tol").item(0).getTextContent());
                    System.out.println("    Ig: " 
                        + eElement.getElementsByTagName("ig").item(0).getTextContent());
                    
                    System.out.println("  Helyszín: " 
                        + eElement.getElementsByTagName("helyszin").item(0).getTextContent());

                    System.out.println("  Oktató: " 
                        + eElement.getElementsByTagName("oktato").item(0).getTextContent());
                    
                    System.out.println("  Szak: " 
                        + eElement.getElementsByTagName("szak").item(0).getTextContent());
                        
                    System.out.println(); 
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}