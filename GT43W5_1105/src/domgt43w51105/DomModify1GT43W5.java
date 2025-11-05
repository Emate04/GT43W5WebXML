package domgt43w51105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModify1GT43W5 {

    public static void main(String[] args) {
        try {
            File inputFile = new File("GT43W5_orarend.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------------------");

            NodeList oraList = doc.getElementsByTagName("ora");
            if (oraList.getLength() > 0) {
                Element elsoOra = (Element) oraList.item(0);

                Element oraado = doc.createElement("oraado");
                oraado.setTextContent("Dr. Szabó Zalán");

                elsoOra.appendChild(oraado);
            }

            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String tipus = ora.getAttribute("tipus");
                if (tipus.equalsIgnoreCase("gyakorlat")) {
                    ora.setAttribute("tipus", "előadás");
                }
            }

            printElement(doc.getDocumentElement(), 0);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("orarendModify1GT43W5.xml"));
            transformer.transform(source, result);

            System.out.println("\nA módosított XML sikeresen mentve: orarendModify1GT43W5.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printElement(Node node, int indent) {
        String indentStr = " ".repeat(indent * 2);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            
            Element elem = (Element) node;
            NamedNodeMap attrMap = elem.getAttributes();
            System.out.print(indentStr + "<" + node.getNodeName());
            for (int j = 0; j < attrMap.getLength(); j++) {
                Node attr = attrMap.item(j);
                System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
            }
            System.out.println(">");
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
