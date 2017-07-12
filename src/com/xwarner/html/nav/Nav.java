package com.xwarner.html.nav;

import java.util.ArrayList;

import javax.swing.JMenuBar;

import org.w3c.dom.Node;

/**
 * Nav represents a Menu Bar in Swing. This is created by a nested ul in a nav element (see test.html)
 * 
 * @author Max Warner
 *
 */

public class Nav {

	private Node node;
	public ArrayList<Menu> menus;

	public NavListener listener;

	public Nav(Node node) {
		this.node = node;
		menus = new ArrayList<Menu>();
	}

	public void parse() {
		Node list = getFirstElementChild(node);
		if (list.getNodeName().equalsIgnoreCase("ul")) {
			for (int i = 0; i < list.getChildNodes().getLength(); i++) {
				Node n = list.getChildNodes().item(i);
				if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equalsIgnoreCase("li"))
					menus.add(new Menu(n));
			}
		}
	}

	public JMenuBar render() {
		JMenuBar bar = new JMenuBar();
		for (Menu m : menus) {
			m.listener = new MenuListener() {
				public void click(String name, String id, Menu menu) {
					if (listener != null)
						listener.click(menu.name, menu.id, name, id);
				}
			};
			bar.add(m.render());
		}
		return bar;
	}

	private Node getFirstElementChild(Node node) {
		for (int i = 0; i < node.getChildNodes().getLength(); i++)
			if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE)
				return node.getChildNodes().item(i);
		return null;
	}

}
