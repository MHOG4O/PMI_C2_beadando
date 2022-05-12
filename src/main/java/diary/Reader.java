package diary;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<Car> readUsersFromXml(String filepath) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();

            NodeList childNodeList = rootElement.getChildNodes();
            Node node;
            for (int i = 0; i < childNodeList.getLength(); i++) {
                node = childNodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String plate="",brand ="",type="",productionYear="",color="",price="";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        Node childNodeOfUserTag = childNodesOfUserTag.item(j);
                        if (childNodeOfUserTag.getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodeOfUserTag.getNodeName()) {
                                case "plate" -> plate = childNodeOfUserTag.getTextContent();
                                case "brand" -> brand = childNodeOfUserTag.getTextContent();
                                case "type"->type=childNodeOfUserTag.getTextContent();
                                case "productionYear" -> productionYear = childNodeOfUserTag.getTextContent();
                                case "color" -> color = childNodeOfUserTag.getTextContent();
                                case "price" -> price = childNodeOfUserTag.getTextContent();
                            }
                        }
                    }
                    cars.add(new Car(plate,Brand.valueOf(brand),type,Integer.parseInt(productionYear),Color.valueOf(color),Integer.parseInt(price)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }
}
