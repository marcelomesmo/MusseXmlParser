#include "CppXmlParser.h"

/*
* Created only for test purposes.
*/
int main(){
	CppXmlParser cxml;

	cxml.parseXmlFile("teste.xml");

	system("pause");

	return 0;
}

CppXmlParser::CppXmlParser()
{
}


CppXmlParser::~CppXmlParser()
{
}

void CppXmlParser::parseXmlFile(char src[])
{
	// Creates a rapidxml::File to read Files
	file<> xmlFile(src); // Default template is char

	// Parse new document using a RapidXml representation of the XML file
	doc.parse<0>(xmlFile.data());

	// Get SpriteSheet element
	xml_node<> *spritesheet = doc.first_node();

	cout << "Root element : " << spritesheet->name() << "\n";
	cout << "----------------------------" << "\n"; 

	// Get a list of Animation elements
	xml_node<> *animations = spritesheet->first_node("animation");

	// Iterate a List of Animation elements
	for (animations; animations; animations = animations->next_sibling())
	{
		cout << "Current element : " << animations->first_attribute()->value() << "\n";
		
		/*
		* This is where your Engine should read the Animation element.
		*
		* Something like:
		*
		* 		Animation a = new Animation();
		*
		*/

		// Get a NodeList of Sprite elements
		xml_node<> *sprites = animations->first_node("sprite");

		// For each Sprite
		for (sprites; sprites; sprites = sprites->next_sibling())
		{
			// Get Sprite name, position and size
			std::string name = sprites->first_attribute()->value();
			int posX = std::stoi( sprites->first_node("offset_x")->value() );
			int posY = std::stoi( sprites->first_node("offset_y")->value() );
			int w = std::stoi( sprites->first_node("width")->value() );
			int h = std::stoi( sprites->first_node("height")->value() );

			/*
			* This is where your Engine should read the Sprite elements.
			*
			* Something like:
			*
			* 		Sprite s = new Sprite(posX, posY, w, h);
			*
			* And add it to the Animation object:
			*
			* 		a.addSprite(s);
			*
			*/

			cout << "\n";
			cout << "Sprite   : " << name << "\n";
			cout << "Offset X : " << posX << "\n";
			cout << "Offset Y : " << posY << "\n";
			cout << "Width    : " << w << "\n";
			cout << "Height   : " << h << "\n";
		}
		cout << "\n";
	}
}