package hu.domparse.GT43W5;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GT43W5DOMRead {

    public static void main(String[] args) {
        try {
            //Dokumentum építõ létrehozása
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            //XML fájl beolvasása
            File xmlFile = new File("GT43W5_XML.xml");
            Document doc = builder.parse(xmlFile);
            
            //felesleges szóközök/sortörések kezelése
            doc.getDocumentElement().normalize();
            
            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------------------");
            
            // Feldolgozás és kiírás a konzolra és a gyökérelem összes gyermekének lekérése
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                
                // elem típusú node-ok
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    // Fejléc 
                    System.out.println("\n[NODE: " + element.getNodeName() + "]");
                    
                    // Attribútumok kiírása
                    if (element.hasAttributes()) {
                        NamedNodeMap attributes = element.getAttributes();
                        System.out.print("  ATTRIBÚTUMOK: ");
                        for (int j = 0; j < attributes.getLength(); j++) {
                            Node attr = attributes.item(j);
                            System.out.print(attr.getNodeName() + "=\"" + attr.getNodeValue() + "\" ");
                        }
                        System.out.println();
                    }
                    
                    //Gyermek elemek tartalmának kiírása
                    NodeList childNodes = element.getChildNodes();
                    for (int k = 0; k < childNodes.getLength(); k++) {
                        Node child = childNodes.item(k);
                        
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            //Ellenõrzés, hogy van-e neki is gyermeke, összetett elem
                            if (child.hasChildNodes() && child.getChildNodes().getLength() > 1) {
                                System.out.println("  <" + child.getNodeName() + "> (Összetett):");
                                NodeList grandChildren = child.getChildNodes();
                                for (int m = 0; m < grandChildren.getLength(); m++) {
                                    Node grandChild = grandChildren.item(m);
                                    if (grandChild.getNodeType() == Node.ELEMENT_NODE) {
                                        System.out.println("    - <" + grandChild.getNodeName() + ">: " + grandChild.getTextContent());
                                    }
                                }
                            } else {
                                
                                System.out.println("  <" + child.getNodeName() + ">: " + child.getTextContent());
                            }
                        }
                    }
                }
            }
            
            //Mentés új fájlba 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            //Formázás beállítása
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("GT43W5_XML_Read_Output.xml"));
            
            transformer.transform(source, result);
            System.out.println("\n----------------------------------------");
            System.out.println("Sikeres mentés: GT43W5_XML_Read_Output.xml");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}