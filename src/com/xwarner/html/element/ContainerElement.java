package com.xwarner.html.element;

import com.esotericsoftware.tablelayout.swing.Table;

/**
 * 
 * @author Max Warner
 *
 */
public class ContainerElement extends Element {

	public void update() {
		Table table = new Table();
		table.pad(0).top().left();
		table.defaults().pad(0).left().fill().expand(true, false);
		for (Element e : children) {
			e.update(); // should always be called first
			e.render(table);
			table.row();
		}
		component = table;
	}

}
