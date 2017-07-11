package com.xwarner.html.element;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

import com.esotericsoftware.tablelayout.Cell;
import com.esotericsoftware.tablelayout.swing.Table;

/**
 * 
 * @author Max Warner
 *
 */
public class Element {

	public Element parent;
	public ArrayList<Element> children;
	public HashMap<String, String> attributes;
	public HashMap<String, String> styles;

	public String type;
	public String contents;

	public String id;
	public String cl; // class

	protected JComponent component;

	public Element() {
		children = new ArrayList<Element>();
		attributes = new HashMap<String, String>();
		styles = new HashMap<String, String>();
	}

	public void render(Table table) {
		Cell<?> cell = table.addCell(component);
		if (styles.containsKey("width")) {
			cell.width(Float.parseFloat(styles.get("width").replace("px", "")));
		}
		if (styles.containsKey("height")) {
			cell.height(Float.parseFloat(styles.get("height").replace("px", "")));
		}
		if (styles.containsKey("margin")) {
			cell.pad(Float.parseFloat(styles.get("margin").replace("px", "")));
		}
		if (styles.containsKey("margin-left")) {
			cell.padLeft(Float.parseFloat(styles.get("margin-left").replace("px", "")));
		}
		if (styles.containsKey("margin-right")) {
			cell.padRight(Float.parseFloat(styles.get("margin-right").replace("px", "")));
		}
		if (styles.containsKey("margin-top")) {
			cell.padTop(Float.parseFloat(styles.get("margin-top").replace("px", "")));
		}
		if (styles.containsKey("margin-bottom")) {
			cell.padBottom(Float.parseFloat(styles.get("margin-bottom").replace("px", "")));
		}
	}

	/** Update the style and content of the element **/
	public void update() {

	}

}
