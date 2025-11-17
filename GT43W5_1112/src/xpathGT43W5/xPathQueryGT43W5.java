package xpathGT43W5;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class xPathQueryGT43W5 {

    public static void main(String[] args) {
        try {
        	String neptunkod = "GT43W5";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("studentGT43W5.xml");

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            /*XPathExpression expr1 = xpath.compile("/class/student/keresztnev");
            NodeList keresztnevek = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);

            System.out.println("=== Keresztnevek ===");
            for (int i = 0; i < keresztnevek.getLength(); i++) {
                System.out.println(keresztnevek.item(i).getTextContent());
            }
            XPathExpression expr2 = xpath.compile("/class/student/becenev");
            NodeList becenevek = (NodeList) expr2.evaluate(doc, XPathConstants.NODESET);

            System.out.println("\n=== Becenevek ===");
            for (int i = 0; i < becenevek.getLength(); i++) {
                System.out.println(becenevek.item(i).getTextContent());
            }

            XPathExpression expr3 = xpath.compile("/class/student[kor > 22]/keresztnev");
            NodeList idosebbek = (NodeList) expr3.evaluate(doc, XPathConstants.NODESET);

            System.out.println("\n=== 22 év felettiek keresztnevei ===");
            for (int i = 0; i < idosebbek.getLength(); i++) {
                System.out.println(idosebbek.item(i).getTextContent());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
