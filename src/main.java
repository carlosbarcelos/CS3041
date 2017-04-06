import java.util.ArrayList;

import org.w3c.dom.*;

import xml.*;

public class main {

	public static void main(String[] args) {
		xmlReader reader = new xmlReader("./src/xml/manpages.xml");
		
		System.out.println("Searching for page 'topic'...");
		ArrayList <String> elements = reader.returnPage("Command Name");
		for(int i = 0; i < elements.size(); i++){
			System.out.println(elements.get(i));
		}
		
	}
}
