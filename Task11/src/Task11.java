import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Task11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		try 
		{
			File f = new File("GroupXML.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			DOMSource dom = null;
			StreamResult os = new StreamResult("CorrectedGroupXML.xml");
			NodeList lNode = doc.getElementsByTagName("student");
			double average = 0;
			for (int n = 0; n< lNode.getLength(); n++)
			{
				Node node = lNode.item(n);
				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element)node;
					System.out.print("Студент "+element.getAttribute("lastname"));
					System.out.println(" "+element.getAttribute("firstname"));
					System.out.println("Средняя оценка "+element.getElementsByTagName("average").item(0).getTextContent());
					average = (	Double.parseDouble(element.getElementsByTagName("subject").item(0).getAttributes().item(0).getTextContent())
							+	Double.parseDouble(element.getElementsByTagName("subject").item(1).getAttributes().item(0).getTextContent())
							+	Double.parseDouble(element.getElementsByTagName("subject").item(2).getAttributes().item(0).getTextContent())
							+	Double.parseDouble(element.getElementsByTagName("subject").item(3).getAttributes().item(0).getTextContent()) ) / 4;
					if ( average != Double.parseDouble(element.getElementsByTagName("average").item(0).getTextContent()) )
					{
						element.getElementsByTagName("average").item(0).setTextContent(String.valueOf(average));
						System.out.println("Исправленная средняя оценка "+element.getElementsByTagName("average").item(0).getTextContent());
					}
				}
				System.out.println();
			}
			dom = new DOMSource(doc);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doc.getDoctype().getSystemId());
			transformer.transform(dom, os);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
