/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gp.inventory.domain.bean;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.embracon.j4e.i18n.Messages;

/**
 *
 * @author bruno.conrado
 */
@Controller
@Scope(value = "globalSession")
public class MessagePropertiesBean {

	private Map<Locale, ResourceBundle> bandlesMap;
	private Locale defaultLocale;

	/** Creates a new instance of MessagePropertiesBean */
	public MessagePropertiesBean() {
		bandlesMap = new HashMap<Locale, ResourceBundle>();
		defaultLocale = new Locale(
				Messages.getMessage("locale.language.default"), 
				Messages.getMessage("locale.country.default")
				);
	}
 
	public String resolve(String key) {
		return resolveMsg(key, "");
	}

	public String resolveMsg(String key, String value) {

		Locale locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();

		boolean loadBundleDefault = false;

		ResourceBundle bundle = bandlesMap.get(locale);
		if(bundle == null) {
			bundle = ResourceBundle.getBundle(Messages.getMessage("bundle.name"), locale);
			bandlesMap.put(locale, bundle);

			loadBundleDefault = true;
		}

		if(bundle == null && loadBundleDefault) {
			bundle = ResourceBundle.getBundle(Messages.getMessage("bundle.name"), defaultLocale);
			bandlesMap.put(locale, bundle);
		}

		String result =  bundle.getString(key);
		if(value != null) {
			String found = null;
			try {
				found = bundle.getString(value);
			} catch (MissingResourceException e) {
				found = value;
			}

			result =  result.replace("{0}", found);
		}

		return result;        
	}

}
