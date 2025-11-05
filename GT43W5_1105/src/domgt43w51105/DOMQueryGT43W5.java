package domgt43w51105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DOMQueryGT43W5 {

    public static void main(String[] args) {
        try {
            File inputFile = new File("GT43W5hallgato.xml"); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------------------------");
            
            NodeList nList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    String id = elem.getAttribute("id");
                    String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();

                    System.out.println("Aktuális elem: hallgato");
                    System.out.println("id: " + id);
                    System.out.println("vezeteknev: " + vezeteknev);
                    System.out.println("-----------------------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
