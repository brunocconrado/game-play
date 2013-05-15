package br.com.embracon.teamposition.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.embracon.j4e.email.EmailException;

public class PropertiesUtils {

	private static PropertiesUtils me;
	
	private Properties props;
	
	private PropertiesUtils() {
		load();
	}
	
	public static PropertiesUtils getInstance() {
		if(me == null) {
			me = new PropertiesUtils();
		}
		
		return me;
	}

	public Properties getProperties() {
		return props;
	}
	
	private void load() {

		try {

			props = new Properties();

			InputStream in = Thread.currentThread()
					.getContextClassLoader().getResourceAsStream("email.properties");

			if (in == null) {
				throw new FileNotFoundException("Arquivo de configuracao nao encontrado");
			}

			props.load(in);				

		} catch (IOException e) {			
			throw new EmailException(e);
		}			
	}

	public String getValue(String key) {
		return props.getProperty(key);
	}

	public String getValue(String key, String... params) {
		String value = getValue(key);
		for(int index = 0; index < params.length; index++) {
			value = value.replace("{" + index + "}", params[index]);
		}
		return value;
	}

}
