package com.xwarner.html;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.xwarner.html.element.tags.FormElement;
import com.xwarner.html.element.util.FormListener;

/**
 * 
 * @author Max Warner
 *
 */
public class Test {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		try {
			Document document = new HTMLParser().parse(new File("test/test.html"));
			document.createFrame().setVisible(true);
			((FormElement) document.getElementById("form")).listener = new FormListener() {
				public void submit(HashMap<String, String> data) {
					System.out.println("Form submitted");
					for (String key : data.keySet()) {
						System.out.println(key + ": " + data.get(key));
					}
				}
			};
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
