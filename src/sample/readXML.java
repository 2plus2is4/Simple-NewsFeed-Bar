package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class readXML {
    public static ArrayList<String> read(String path) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            document.getDocumentElement().normalize();

            NodeList rows = document.getElementsByTagName("string");
            ArrayList<String> news = new ArrayList<>();
            for (int i = 0; i < rows.getLength(); i++) {
                news.add(rows.item(i).getTextContent());
            }
            return news;
        } catch (Exception e) {
            //e.printStackTrace();
        }
       return null;
    }

}
