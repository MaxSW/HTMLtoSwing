package com.xwarner.html.element.tags;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.xwarner.html.element.Element;
import com.xwarner.html.element.util.ButtonListener;

/**
 * 
 * @author Max Warner
 *
 */
public class InputElement extends Element {

	public ButtonListener listener;

	public void update() {
		String type = attributes.get("type");
		if (type.equalsIgnoreCase("text")) {
			component = new JTextField(attributes.get("value"));
		} else if (type.equalsIgnoreCase("submit")) {
			JButton button = new JButton(attributes.get("value"));
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listener.click();
				}
			});
			component = button;
		}
	}

	public String getValue() {
		String type = attributes.get("type");
		if (type.equalsIgnoreCase("text")) {
			return ((JTextField) component).getText();
		}
		return "";
	}

}
