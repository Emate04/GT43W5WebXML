package domgt43w51029;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWrite {

    public static void main(String[] args) {
        try {
            File inputFile = new File("GT43W5hallgato.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------------------------");

            printElement(doc.getDocumentElement(), 0);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("hallgato1gt43w5.xml"));
            transformer.transform(source, result);

            System.out.println("\nAz XML sikeresen kiírva a hallgato1gt43w5.xml fájlba.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printElement(Node node, int indent) {
        String indentStr = " ".repeat(indent * 2);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indentStr + "<" + node.getNodeName() + ">");
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                printElement(child, indent + 1);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String text = child.getTextContent().trim();
                if (!text.isEmpty()) {
                    System.out.println(indentStr + "  " + text);
                }
            }
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(indentStr + "</" + node.getNodeName() + ">");
        }
    }
}
