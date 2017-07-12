package com.xwarner.html;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.xwarner.html.element.Element;
import com.xwarner.html.element.tags.ButtonElement;
import com.xwarner.html.element.tags.DivElement;
import com.xwarner.html.element.tags.FormElement;
import com.xwarner.html.element.tags.H1Element;
import com.xwarner.html.element.tags.H2Element;
import com.xwarner.html.element.tags.H3Element;
import com.xwarner.html.element.tags.H4Element;
import com.xwarner.html.element.tags.H5Element;
import com.xwarner.html.element.tags.H6Element;
import com.xwarner.html.element.tags.InputElement;
import com.xwarner.html.element.tags.PElement;
import com.xwarner.html.element.tags.TextAreaElement;
import com.xwarner.html.nav.Nav;
import com.xwarner.html.style.CSSParser;

/**
 * 
 * @author Max Warner
 *
 */
public class HTMLParser {

	private ArrayList<String> styles; // only parse one file at a time
	private Node nav;

	public Document parse(File file) throws SAXException, IOException, ParserConfigurationException {
		styles = new ArrayList<String>();
		org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		document.getDocumentElement().normalize();
		Document parsedDoc = new Document();

		/** Parse the document **/
		NodeList children = document.getElementsByTagName("body").item(0).getChildNodes(); // the body
		for (int i = 0; i < children.getLength(); i++) {
			if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element e = parseElement(children.item(i));
				if (e != null)
					parsedDoc.children.add(e);
			}
		}

		/** Parse the head **/
		NodeList head = document.getElementsByTagName("head").item(0).getChildNodes(); // the head
		for (int i = 0; i < head.getLength(); i++) {
			Node n = head.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				switch (n.getNodeName()) {
				case "title":
					parsedDoc.title = n.getTextContent();
					break;
				case "meta":
					parsedDoc.attributes.put(n.getAttributes().getNamedItem("name").getTextContent(),
							n.getAttributes().getNamedItem("content").getTextContent());
					break;
				case "style":
					styles.add(n.getTextContent()); // add new style sheet
					break;
				case "link":
					if (n.getAttributes().getNamedItem("rel").getTextContent().equalsIgnoreCase("stylesheet")) {
						String path = n.getAttributes().getNamedItem("href").getTextContent();
						File f = null;
						if (path.startsWith(File.separator))
							// absolute path
							f = new File(path);
						else
							// relative path to html document
							f = new File(file.getParent() + File.separator + path);
						styles.add(readFile(f.getAbsolutePath()));
					}
					break;

				case "nav":
					nav = n;
					break;
				}
			}
		}
		parsedDoc.width = Integer.parseInt(parsedDoc.attributes.get("width"));
		parsedDoc.height = Integer.parseInt(parsedDoc.attributes.get("height"));
		parsedDoc.resizable = Boolean.parseBoolean(parsedDoc.attributes.get("resizable"));
		parsedDoc.styles = new CSSParser().parseCSS(styles);
		parsedDoc.styles.apply(parsedDoc);
		parsedDoc.nav = new Nav(nav);
		parsedDoc.nav.parse(); // parse the menu
		return parsedDoc;
	}

	private Element parseElement(Node node) {
		Element element;
		switch (node.getNodeName()) {
		case "p":
			element = new PElement();
			break;
		case "h1":
			element = new H1Element();
			break;
		case "h2":
			element = new H2Element();
			break;
		case "h3":
			element = new H3Element();
			break;
		case "h4":
			element = new H4Element();
			break;
		case "h5":
			element = new H5Element();
			break;
		case "h6":
			element = new H6Element();
			break;
		case "div":
			element = new DivElement();
			break;
		case "form":
			element = new FormElement();
			break;
		case "input":
			element = new InputElement();
			break;
		case "textarea":
			element = new TextAreaElement();
			break;
		case "button":
			element = new ButtonElement();
			break;

		case "style":
			styles.add(node.getTextContent()); // add new style sheet
			return null;

		case "nav":
			nav = node;
			return null;

		default:
			element = new Element();
			break;
		// nav tags and nested ul --> menu bar
		}
		element.type = node.getNodeName();
		element.contents = node.getTextContent();

		NamedNodeMap attributes = node.getAttributes();
		for (int i = 0; i < attributes.getLength(); i++) {
			Node n = attributes.item(i);
			element.attributes.put(n.getNodeName(), n.getTextContent());
		}
		if (attributes.getNamedItem("id") != null)
			element.id = attributes.getNamedItem("id").getTextContent();
		if (attributes.getNamedItem("class") != null)
			element.cl = attributes.getNamedItem("class").getTextContent();

		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
			if (children.item(i).getNodeType() == Node.ELEMENT_NODE)
				element.children.add(parseElement(children.item(i)));

		return element;
	}

	// https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
	private String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.defaultCharset());
	}

}