package com.xwarner.html.element.tags;

import java.util.HashMap;

import com.xwarner.html.element.ContainerElement;
import com.xwarner.html.element.Element;
import com.xwarner.html.element.util.ButtonListener;
import com.xwarner.html.element.util.FormListener;

/**
 * 
 * @author Max Warner
 *
 */
public class FormElement extends ContainerElement {

	public FormListener listener;

	public void update() {
		super.update();
		for (Element e : children) {
			if (e.type.equalsIgnoreCase("input") && e.attributes.get("type").equalsIgnoreCase("submit")) {
				((InputElement) e).listener = new ButtonListener() {
					public void click() {
						submit();
					}
				};
			} else if (e.type.equalsIgnoreCase("button")) {
				((ButtonElement) e).listener = new ButtonListener() {
					public void click() {
						submit();
					}
				};
			}
		}
	}

	public void submit() {
		if (listener != null) {
			HashMap<String, String> data = new HashMap<String, String>();
			for (Element e : children) {
				if (e.type.equalsIgnoreCase("input"))
					if (!e.attributes.get("type").equalsIgnoreCase("submit"))
						data.put(e.attributes.get("name"), ((InputElement) e).getValue());
			}
			listener.submit(data);
		}
	}

}
