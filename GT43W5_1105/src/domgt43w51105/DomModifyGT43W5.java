package domgt43w51105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomModifyGT43W5 {

    public static void main(String[] args) {
        try {
            File inputFile = new File("GT43W5hallgato.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("-------------------------------------");

            NodeList hallgatoList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < hallgatoList.getLength(); i++) {
                Node node = hallgatoList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    if (elem.getAttribute("id").equals("01")) {
                      
                        elem.getElementsByTagName("keresztnev").item(0).setTextContent("László");
                        elem.getElementsByTagName("vezeteknev").item(0).setTextContent("Takács");
                    }
                }
            }

            NodeList nodeList = doc.getElementsByTagName("hallgato");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String id = elem.getAttribute("id");
                    String keresztnev = elem.getElementsByTagName("keresztnev").item(0).getTextContent();
                    String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    String foglalkozas = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();

                    System.out.println("ID: " + id);
                    System.out.println("Keresztnév: " + keresztnev);
                    System.out.println("Vezetéknév: " + vezeteknev);
                    System.out.println("Foglalkozás: " + foglalkozas);
                    System.out.println("-------------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
