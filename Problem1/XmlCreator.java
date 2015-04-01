import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlCreator {
  public void printPerson(String firstName, String lastName, String address) {
    try {
      // Creating docBuilder to allow creation of doc
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // Creating doc to hold contents of XML file to be created
      Document doc = docBuilder.newDocument();

      // Adding main Person element to hold Person attributes
      Element personElement = doc.createElement("Person");
      doc.appendChild(personElement);

      Element firstNameElement = doc.createElement("FirstName");
      firstNameElement.appendChild(doc.createTextNode(firstName));
      personElement.appendChild(firstNameElement);

      Element lastNameElement = doc.createElement("LastName");
      lastNameElement.appendChild(doc.createTextNode(lastName));
      personElement.appendChild(lastNameElement);

      Element addressElement = doc.createElement("Address");
      addressElement.appendChild(doc.createTextNode(address));
      personElement.appendChild(addressElement);

      // Creating transformer to allow insertion of doc into file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();

      // Not sure what windows temp directory is, so creating tmp folder outside this one
      createTmpDirectory();
      File resultFile = new File(".." + File.separator + "tmp" + File.separator + "you.xml");

      // Source is the file contents from doc and result is the actual file
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(resultFile);

      // Placing contents into file
      transformer.transform(source, result);
    } catch (ParserConfigurationException exception) {
      exception.printStackTrace();
    } catch (TransformerException exception) {
      exception.printStackTrace();
    }
  }

  private void createTmpDirectory() {
    File tmp = new File(".." + File.separator + "tmp");

    if (!tmp.exists()) {
      tmp.mkdir();
    }
  }
}
