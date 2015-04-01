import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {
  public String firstName;
  public String lastName;
  public String address;

  public XmlReader() {
    firstName = new String();
    lastName = new String();
    address = new String();
  }

  public static void main(String[] args) {
    XmlReader xmlReader = new XmlReader();
    xmlReader.readAttributes();
    xmlReader.printAttributes();
  }

  private void readAttributes() {
    try {
      // Creating docBuilder to allow creation of doc
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // Getting contents of file
      File personFile = new File(".." + File.separator + "tmp" + File.separator + "you.xml");
      Document doc = docBuilder.parse(personFile);
      doc.getDocumentElement().normalize();

      // Getting and traversing NodeList for Person
      NodeList nodeList = doc.getElementsByTagName("Person");
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);

        // If the current node is an element
        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element element = (Element) node;

          // Pulling text content out of the element with the respective tag name
          firstName = element.getElementsByTagName("FirstName").item(0).getTextContent();
          lastName = element.getElementsByTagName("LastName").item(0).getTextContent();
          address = element.getElementsByTagName("Address").item(0).getTextContent();
        }
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  private void printAttributes() {
    System.out.println("1. First Name: " + firstName);
    System.out.println("2. Last Name: " + lastName);
    System.out.println("3. Address: " + address);
  }
}
