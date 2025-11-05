package domgt43w51105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomQuery1GT43W5 {

    public static void main(String[] args) {
        try {
            File inputFile = new File("GT43W5_orarend.xml"); 
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("---------------------------------------------");

            List<String> kurzusok = new ArrayList<>();
            NodeList oraList = doc.getElementsByTagName("ora");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                kurzusok.add(targy);
            }

            System.out.println("Kurzusnév lista: " + kurzusok);
            System.out.println("---------------------------------------------");

            if (oraList.getLength() > 0) {
                Element elsoOra = (Element) oraList.item(0);
                System.out.println("Első kurzus strukturált formában:\n");
                printElement(elsoOra, 0);

                Document newDoc = builder.newDocument();
                Node imported = newDoc.importNode(elsoOra, true);
                newDoc.appendChild(imported);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(new DOMSource(newDoc), new StreamResult(new File("orarend_first_ora.xml")));

                System.out.println("\nElső kurzus elmentve: orarend_first_ora.xml");
            }
            System.out.println("---------------------------------------------");

            List<String> oktatok = new ArrayList<>();
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();
                oktatok.add(oktato);
            }

            System.out.println("Oktatók listája: " + oktatok);
            System.out.println("---------------------------------------------");

            System.out.println("Összetett lekérdezés – Hétfői előadások:");
            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                String tipus = ora.getAttribute("tipus");
                String nap = ora.getElementsByTagName("nap").item(0).getTextContent();
                if (tipus.equalsIgnoreCase("előadás") && nap.equalsIgnoreCase("Hétfő")) {
                    String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                    String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();
                    System.out.println("- " + targy + " (" + oktato + ")");
                }
            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printElement(Node node, int indent) {
        String indentStr = " ".repeat(indent * 2);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) node;
            System.out.print(indentStr + "<" + elem.getNodeName());
            NamedNodeMap attributes = elem.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
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
