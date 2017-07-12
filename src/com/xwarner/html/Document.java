package com.xwarner.html;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.esotericsoftware.tablelayout.swing.Table;
import com.xwarner.html.element.Element;
import com.xwarner.html.nav.Nav;
import com.xwarner.html.style.Styles;

/**
 * 
 * @author Max Warner
 *
 */
public class Document extends Element {

	public String title;
	public int width;
	public int height;
	public boolean resizable;

	public Styles styles;
	public Nav nav;

	/** Creates the window **/
	public JFrame createFrame() {
		JFrame frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setResizable(resizable);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (nav != null)
			frame.setJMenuBar(nav.render());
		Table table = new Table();
		table.pad(10).top().left();
		table.defaults().pad(5).left().fill().expand(true, false);
		for (Element e : children) {
			e.update(); // should always be called first
			e.render(table);
			table.row();
		}
		// add elements
		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scroll);
		frame.setVisible(true);
		return frame;
	}

	public Element getElementById(String id) {
		for (Element e : children)
			if (id.equalsIgnoreCase(e.id))
				return e;

		return null;
	}

}
