package com.xwarner.html.style;

import java.util.HashMap;

import com.xwarner.html.Document;
import com.xwarner.html.element.Element;

/**
 * 
 * @author Max Warner
 *
 */
public class Styles {

	public HashMap<String, HashMap<String, String>> map; // oh no

	public Styles() {
		map = new HashMap<String, HashMap<String, String>>();
	}

	/** takes the styles and applies them to the respective elements in the document **/
	public void apply(Document document) {
		parse(document);
	}

	private void parse(Element element) {
		for (Element e : element.children) {
			applyStyle(e);
			parse(e);
		}
	}

	private void applyStyle(Element e) {
		for (String key : map.keySet()) {
			if (!key.contains(" ") && !key.contains(",")) { // don't deal with multi-selectors for the moment
				key.trim(); // just in case
				switch (key.charAt(0)) {
				case '#':
					if (key.substring(1, key.length()).equalsIgnoreCase(e.id))
						e.styles.putAll(map.get(key));
					break;
				case '.':
					if (key.substring(1, key.length()).equalsIgnoreCase(e.cl))
						e.styles.putAll(map.get(key));
					break;
				case '*':
					e.styles.putAll(map.get(key));
				default: // assume element selector
					if (key.equalsIgnoreCase(e.type))
						e.styles.putAll(map.get(key));
					break;
				}
			}
		}
	}

}
