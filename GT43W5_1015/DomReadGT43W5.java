package domgt43w51015;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class DomReadGT43W5 {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendGT43W5.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            printNode(doc.getDocumentElement(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) sb.append("  "); 

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            System.out.print(sb.toString() + "<" + element.getNodeName());

            NamedNodeMap attrMap = element.getAttributes();
            for (int i = 0; i < attrMap.getLength(); i++) {
                Node attr = attrMap.item(i);
                System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
            }
            System.out.println(">");

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    printNode(child, indent + 1);
                } else if (child.getNodeType() == Node.TEXT_NODE) {
                    String text = child.getTextContent().trim();
                    if (!text.isEmpty()) {
                        System.out.println(sb.toString() + "  " + text);
                    }
                }
            }

            System.out.println(sb.toString() + "</" + element.getNodeName() + ">");
        }
    }
}

