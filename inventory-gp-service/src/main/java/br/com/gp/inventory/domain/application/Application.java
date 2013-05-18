package br.com.gp.inventory.domain.application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.embracon.j4e.email.EmailConfigurationException;
import br.com.embracon.j4e.email.EmailException;
import br.com.embracon.j4e.exceptions.ConfigurationException;
import br.com.embracon.j4e.i18n.Messages;
import br.com.embracon.j4e.io.vfs.DiskFileSystem;
import br.com.embracon.j4e.io.vfs.VirtualFile;

public final class Application {

	public static final String LOGGING_APPLICATION_ = "system";
	private static DiskFileSystem fileSystem;
	public static String contentPath;
	
	private static Properties emailProperties; 

	public static VirtualFile retrive(String fileFolderName) {
		return fileSystem.retrive(fileFolderName);
	}

	public static void config() throws Exception {
		config(true);
	}

	public static void config(Boolean fullConfiguration) throws Exception {

		fileSystem = DiskFileSystem.virtualize(new File(Application.contentPath));

		configEmail();   

		VirtualFile downloadPath = fileSystem.retrive(Messages.getMessage("download.folderPath"));
		if (!downloadPath.exists()) {
			downloadPath.createFolder();
		}
	}

	public static Properties configEmail() {

		VirtualFile emailConfiFile = retrive("email.properties");

		if (!emailConfiFile.exists()) {
			throw new ConfigurationException("File email.properties does not found");
		} else {
			setProperties(emailConfiFile);
		}
		
		return emailProperties;
	}

	private static void setProperties(VirtualFile file) {

		emailProperties = new Properties();

		try {

			InputStream in = file.getContent().getInputStream();

			if (in == null) {
				throw new EmailConfigurationException("Arquivo de configuração de email não encontrado");
			}

			emailProperties.load(in);

		} catch (IOException e) {
			throw new EmailException(e);
		}
	}

}
