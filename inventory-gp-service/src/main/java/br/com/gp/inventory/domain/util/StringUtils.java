package br.com.gp.inventory.domain.util;

public class StringUtils {
	
	public static String formatString(Long value, int length, String complement) {
		
		StringBuilder result = new StringBuilder(complement);
		
		String valueString = String.valueOf(value);
		while((valueString.length() + result.length()) < length) {
			result.append("0");
		}
		
		result.append(valueString);
		return result.toString();
	}

}
