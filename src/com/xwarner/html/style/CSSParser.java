package com.xwarner.html.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Max Warner
 *
 */

public class CSSParser {

	public Styles parseCSS(ArrayList<String> raw) {
		Styles styles = new Styles();
		for (String s : raw) {
			parse(styles, s);
		}
		return styles;
	}

	private void parse(Styles styles, String data) {
		Pattern pattern = Pattern.compile("([^{}]*)\\{([^{}]*)\\}"); // anything without {} [selector], {, anything
																		// without {} [styles], }
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			String selector = matcher.group(1).trim();
			String style = matcher.group(2).trim();
			Pattern pattern2 = Pattern.compile("([^;:]*):([^;:]*);"); // anything without ;:, :, anything without ;:, ;
			Matcher matcher2 = pattern2.matcher(style);
			HashMap<String, String> s = new HashMap<String, String>();
			while (matcher2.find()) {
				s.put(matcher2.group(1).trim(), matcher2.group(2).trim());
			}
			styles.map.put(selector, s);
		}
	}
}
