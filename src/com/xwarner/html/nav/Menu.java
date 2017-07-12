package com.xwarner.html.nav;

import java.util.ArrayList;

import javax.swing.JMenu;

import org.w3c.dom.Node;

/**
 * 
 * @author Max Warner
 *
 */
public class Menu {

	private Node node;

	public String id;
	public String name;

	public ArrayList<MenuItem> items;

	public MenuListener listener;

	public Menu(Node node) {
		this.node = node;
		items = new ArrayList<MenuItem>();
	}

	public JMenu render() {
		if (node.getAttributes().getNamedItem("id") != null)
			id = node.getAttributes().getNamedItem("id").getTextContent();
		if (getFirstElementChild(node) == null) { // has no menu items
			name = node.getTextContent().trim();
			return new JMenu(name);
		} else {
			name = node.getFirstChild().getTextContent().trim();
			JMenu menu = new JMenu(name);
			Node l = getFirstElementChild(node);
			if (l.getNodeName().equalsIgnoreCase("ul")) {
				for (int j = 0; j < l.getChildNodes().getLength(); j++) {
					Node no = l.getChildNodes().item(j);
					if (no.getNodeType() == Node.ELEMENT_NODE && no.getNodeName().equalsIgnoreCase("li")) {
						MenuItem item = new MenuItem(no);
						Menu m = this;
						item.listener = new MenuItemListener() {
							public void click(MenuItem item) {
								if (listener != null) {
									listener.click(item.name, item.id);
									listener.click(item.name, item.id, m);
								}
							}
						};
						items.add(item);
						menu.add(item.render());
					}
				}
			}
			return menu;
		}
	}

	private Node getFirstElementChild(Node node) {
		for (int i = 0; i < node.getChildNodes().getLength(); i++)
			if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE)
				return node.getChildNodes().item(i);
		return null;
	}

}
