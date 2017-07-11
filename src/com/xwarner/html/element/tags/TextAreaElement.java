package com.xwarner.html.element.tags;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.xwarner.html.element.Element;

/**
 * 
 * @author Max Warner
 *
 */
public class TextAreaElement extends Element {

	public void update() {
		JTextArea textArea = new JTextArea(contents);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		component = textArea;
	}

	public String getValue() {
		return ((JTextArea) component).getText();
	}

}
