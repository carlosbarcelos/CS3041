package xml;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;

// Help from this StackExchange post:
// http://stackoverflow.com/questions/428073/what-is-the-best-simplest-way-to-read-in-an-xml-file-in-java-application


/* This is the layout of a XML file
<manual>
	<page>
		<topic>

		</topic>
		<summary>

		</summary>
		<details>

		</details>
	</page>
</manual>
 */

public class xmlReader {

	Document parsedFile; // The parsed XML file to use

	public xmlReader(String inputFile){
		this.parsedFile = parseXML(inputFile);
	}

	// Parse the XML file for use
	private Document parseXML(String inputFile){
		try{
			File file = new File(inputFile);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			return document;
		}
		catch (Exception e){
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	// Getter for the parsedFile
	public Document getParsed(){
		return this.parsedFile;
	}

	/**
     * Returns a <code>ArrayList</code> elements within a specified page.
     * If the page can not be found, a 404 Page Not Found will return.
     * @param pName  The name of topic of the search page
     * @return An ArrayList of the <code>topic</code>, 
     * <code>summary</code> and <code>details</code> strings within the page
	 */
	public ArrayList<String> returnPage(String pName){
		ArrayList <String> elements = new ArrayList<String>();
		try{
			NodeList nList = parsedFile.getElementsByTagName("page");
			int pIndex = this.index(nList, pName);
			Node pNode = nList.item(pIndex);
			Element pElement = (Element) pNode;
			String pTopic = pElement.getElementsByTagName("topic").item(0).getTextContent();
			String pSummary = pElement.getElementsByTagName("summary").item(0).getTextContent();
			String pDetails = pElement.getElementsByTagName("details").item(0).getTextContent();
			elements.add(pTopic);
			elements.add(pSummary);
			elements.add(pDetails);
			return elements;
		} catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	// Helper function to get a specific node's location in the list
	private int index(NodeList nList, String name){
		String searchName;
		int i;
		for (i = 0; i < nList.getLength();i++) {
	        Node nNode = nList.item(i);
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element eElement = (Element) nNode;
	            searchName = eElement.getElementsByTagName("topic").item(0).getTextContent();
	            if(searchName.equals(name)){
	            	return i;
	            }
	        }
	    }
		return i-1; // Return the last element, the not-found page
	}

	// Read a manual->page->topic

	// Read a manual->page->summary

	// Read a manual->page->details

}
