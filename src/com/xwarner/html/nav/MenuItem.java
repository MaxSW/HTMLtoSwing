package com.xwarner.html.nav;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.w3c.dom.Node;

/**
 * 
 * @author Max Warner
 *
 */

public class MenuItem {

	public String id;
	public String name;

	public MenuItemListener listener;

	public MenuItem(Node node) {
		if (node.getAttributes().getNamedItem("id") != null)
			id = node.getAttributes().getNamedItem("id").getTextContent();
		name = node.getTextContent().trim();
	}

	public JMenuItem render() {
		JMenuItem item = new JMenuItem(name);
		MenuItem mi = this;
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.click();
					listener.click(mi);
				}
			}
		});
		return item;
	}

}
