package com.xwarner.html.element.tags;

import java.awt.Font;

import com.xwarner.html.element.TextElement;

/**
 * 
 * @author Max Warner
 *
 */
public class H1Element extends TextElement {

	public void update() {
		defaultFont = new Font("Arial", Font.BOLD, 24);
		super.update();
	}

}
