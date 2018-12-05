import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 01 December, 2018
 */
public class ReadLevel {
    public ReadLevel() {}

    public Board readLevelFromXML(int l) {
        Map<String, Integer> zombieScope = new HashMap<String, Integer>();
        // TODO Make as a method of the Game class with score and current level input
        try {
            File fXmlFile = new File("SouceCode/src/LevelBuilder.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("level");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if(eElement.getAttribute("id").equals(Integer.toString(l))){
                        int score = Integer.valueOf(eElement.getAttribute("score"));
                        int money = Integer.valueOf(eElement.getAttribute("money"));

                        NodeList zombies = eElement.getElementsByTagName("zombie");

                        for(int j = 0; j < zombies.getLength(); j++){
                            String name = String.valueOf(((Element) zombies.item(j)).getAttribute("name"));
                            int nts = Integer.valueOf(((Element) zombies.item(j)).getAttribute("numberToSpawn"));
                            zombieScope.put(name, nts);
                        }
                        return new Board(zombieScope, score, money);
                    }
                }
            }

        } catch(Exception e) {
            System.out.println("Exception message: "+e.getLocalizedMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        new ReadLevel().readLevelFromXML(0);
    }
}
