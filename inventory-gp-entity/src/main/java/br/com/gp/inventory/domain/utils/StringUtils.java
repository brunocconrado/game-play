package br.com.gp.inventory.domain.utils;

public class StringUtils {
	
	public static final String CODE = "0000000000";
	
	
	public static String htmlText(String title, String description) {
		return new StringBuilder()
			.append("<h2>\n\t")
			.append(title == null ? "" : title)
			.append("\n</h2>")
			.append("\n<p>\n\t<h2>")
			.append(description == null ? "" : description)
			.append("\n\t</h2>\n</p>\n")
			.toString();
	}

}
