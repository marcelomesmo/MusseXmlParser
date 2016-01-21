#pragma once

#include "rapidxml-1.13/rapidxml.hpp"
#include "rapidxml-1.13/rapidxml_utils.hpp"
using namespace rapidxml;

#include <iostream>
using namespace std;

/*
 *	There's plenty of parsers available to C++.
 *	I choose RapidXml 'cause of this thread:
 *	http://stackoverflow.com/questions/9387610/what-xml-parser-should-i-use-in-c
 *	(see flowchart image)
 *
 *	XMLParser in C++ to read MuSSE's XML files.
 *
 */
class CppXmlParser
{
public:
	CppXmlParser();
	~CppXmlParser();

	void parseXmlFile(char src[]);

private:
	// XML document to be parsed
	xml_document<> doc;
};

