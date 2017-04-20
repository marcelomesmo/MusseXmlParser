import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/*
 * XMLParser in Java to read MuSSE's XML files.
 * 
 */
public class JavaXmlParser {

	/*
	 * Created only for test purposes.
	 */
	public static void main(String[] args) {
		
		JavaXmlParser jxml = new JavaXmlParser();
		
		jxml.runParser("src/teste.xml");
	}
	
	// XML document to be parsed
	Document doc;
	
	public void runParser(String src)
	{
		// Opens the XML file
		parseXmlFile(src);
				
		// Get each Animation element with its corresponding Sprites
		parseDocument();
	
	}
	
	private void parseXmlFile(String src)
	{
		// Creates a Factory to read Files
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		try {
			
			// Use Factory to get an instance of new document
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			// Parse new document using a DOM representation of the XML file
			doc = dBuilder.parse(src);

			// Normalize document (optional, but recommended)
			// read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			System.out.println("----------------------------");

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void parseDocument()
	{
		// Get a NodeList of Animation elements
		NodeList animations = doc.getElementsByTagName("animation");
		
		// Check if not empty
		if(animations != null && animations.getLength() > 0)
		{
			// For each Animation
			for(int i = 0; i < animations.getLength(); i++) {	
			
				// Get Animation
				// Node anim = animations.item(i);
				// Element eAnim = (Element) anim;
				
				// Get Animation at index i
				Element anim = (Element) animations.item(i);

				System.out.println("\nCurrent Element " + i + " : " + anim.getNodeName() + " " + anim.getAttribute("name"));
				System.out.println("----------------------------");
				
				/*
				 * This is where your Engine should read the Animation element.
				 * 
				 * Something like:
				 * 
				 * 		Animation a = new Animation();
				 * 
				 */
				
				// Get a NodeList of Sprite elements
				NodeList sprites = anim.getElementsByTagName("sprite");

				// For each Sprite
				for(int j = 0; j < sprites.getLength(); j++) {
					
					// Get Sprite at index j
					Element sprite = (Element) sprites.item(j);
					
					/* Could be something like:
					 * 	
					 *  String name = getTextValue(sprite, "name");
					 * 	int posX = getIntValue(sprite, "offset_x");
					 *  ...
					 *  
					 * With specific methods 'getTextValue(sprite, "tag")' and 'getIntValue(sprite, "tag")'
					 * to return 'sprite.getElementsByTagName("tag").item(0).getTextContent()' content.
					 */
					// Get a Sprite name attribute <sprite name="Label1">
					String name = sprite.getAttribute("name");
					// Get content from tags, i.e. <sprite> <width> 10 </width> </sprite>
					// Sprite element points to <sprite> node and TagName points to <width> returning its content
					int posX = Integer.parseInt( sprite.getElementsByTagName("offset_x").item(0).getTextContent() );
					int posY = Integer.parseInt( sprite.getElementsByTagName("offset_y").item(0).getTextContent() );
					int w	 = Integer.parseInt( sprite.getElementsByTagName("width").item(0).getTextContent() );
					int h 	 = Integer.parseInt( sprite.getElementsByTagName("height").item(0).getTextContent() );
					int ancX = Integer.parseInt( sprite.getElementsByTagName("anchor_x").item(0).getTextContent() );
					int ancY = Integer.parseInt( sprite.getElementsByTagName("anchor_y").item(0).getTextContent() );
					
					/*
					 * This is where your Engine should read the Sprite elements.
					 * 
					 * Something like:
					 * 
					 * 		Sprite s = new Sprite(posX, posY, w, h, ancX, ancY);
					 * 
					 * And add it to the Animation object:
					 * 
					 * 		a.addSprite(s);
					 * 
					 */

					System.out.println();
					System.out.println("Sprite 	 : " + name);
					System.out.println("Offset X : " + posX);
					System.out.println("Offset Y : " + posY);
					System.out.println("Width    : " + w);
					System.out.println("Height   : " + h);
					System.out.println("Anchor X : " + ancX);
					System.out.println("Anchor Y : " + ancY);
					
				}

			}
		}
	}
}
