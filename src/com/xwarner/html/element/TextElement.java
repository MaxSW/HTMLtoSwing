package com.xwarner.html.element;

import java.awt.Font;

import javax.swing.JTextArea;

/**
 * 
 * @author Max Warner
 *
 */
public class TextElement extends Element {

	protected Font defaultFont;

	public void update() {
		JTextArea label = new JTextArea(contents);
		label.setLineWrap(true);
		label.setEditable(false);
		Font font = defaultFont;
		if (font == null) {
			font = label.getFont();
		}
		if (styles.containsKey("font-size"))
			font = font.deriveFont(Float.parseFloat(styles.get("font-size").replace("px", "").replace("pt", "")));
		label.setFont(font);
		component = label;
	}

}
