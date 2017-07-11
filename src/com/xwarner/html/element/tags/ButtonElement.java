package com.xwarner.html.element.tags;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.xwarner.html.element.Element;
import com.xwarner.html.element.util.ButtonListener;

public class ButtonElement extends Element {

	public ButtonListener listener;

	public void update() {
		JButton button = new JButton(contents);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener != null)
					listener.click();
			}
		});
		component = button;
	}
}
