package diary;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Writer {
    private static void createChildElement(Document document, Element parent,
                                           String tagName, String text) {
        Element element = document.createElement(tagName);
        element.setTextContent(text);
        parent.appendChild(element);
    }
    public static void saveUsersToXml(ArrayList<Car> cars, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("cars");
            document.appendChild(rootElement);

            for (Car car : cars) {
                Element userElement = document.createElement("car");
                rootElement.appendChild(userElement);
                createChildElement(document, userElement, "plate", car.getPlate());
                createChildElement(document, userElement, "brand", car.getBrand().toString());
                createChildElement(document, userElement, "type", car.getType());
                createChildElement(document, userElement, "productionYear", String.valueOf(car.getProductionYear()));
                createChildElement(document, userElement, "color", car.getColor().toString());
                createChildElement(document, userElement, "price", String.valueOf(car.getPrice()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}