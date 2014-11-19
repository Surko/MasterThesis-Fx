package gentree.configurations;
import gentree.locales.PermMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

public class Config {
	
	private static final Logger LOG = Logger.getLogger(Config.class.getName());
		
	private static Config instance;
	private String sFile;
	private Properties prop;	
		
	public static Config getInstance() {		
		if (instance == null) {
			instance = new Config();
		}
		return instance;		
	}
	
	public void init() {
		sFile = PathManager.getInstance().getRootPath() + File.separator + "config.properties";	
		loadProperties();		
	}
	
	public void loadProperties() {
		prop = new Properties();
		try {
			InputStream istream = new FileInputStream(sFile);			
			// Nacitanie noveho property objektu z ktoreho bude aplikacia citat.			
			prop.load(istream);
			LOG.info(PermMessages._cfg_loaded);
		} catch (FileNotFoundException fnfe) {
			LOG.info(PermMessages._cfg_file_miss);												
		} catch (IOException | SecurityException ex) {
			LOG.severe(String.format(PermMessages._cfg_err_load, ex.toString()));						
		}
		
		setAbsentProperties();
		LOG.info(PermMessages._cfg_set);
							
	}
	
	public void saveProperties() {
		try {			
			OutputStream ostream = new FileOutputStream(sFile);			
			// Ulozenie properties do vystupneho streamu.
			prop.store(ostream, getCurrentTime());
			LOG.info(PermMessages._cfg_saved);
		} catch (Exception e) {
			LOG.severe(String.format(PermMessages._cfg_err_save ,e.toString()));
		}
	}
	
	public void setAbsentProperties() {				
		prop.putIfAbsent("file-localization", "false");
		prop.putIfAbsent("locale", "en");		
		prop.putIfAbsent("user-path", PathManager.getInstance().getRootPath().getAbsolutePath());
		// Apps properties
		prop.putIfAbsent("app-fullscreen", "false");
		prop.putIfAbsent("app-width", "800");
		prop.putIfAbsent("app-height", "600");
		
		//TODO
	}
	
	public String getCurrentTime() {
		return DateFormat.getInstance().format(Calendar.getInstance().getTime());
	}
		
	public File getUserPath() {
		return new File(prop.getProperty("user-path"));
	}
	
	public Locale getLocale() {
		return new Locale(prop.getProperty("locale"));
	}
	
	public boolean isFileLocalized() {
		return prop.getProperty("file-localization").equals("true");
	}
	
	public int getWidth() {
		return Integer.parseInt(prop.getProperty("app-width"));
	}
	
	public int getHeight() {
		return Integer.parseInt(prop.getProperty("app-height"));
	}
	
	public boolean isFullscreen() {
		return prop.getProperty("app-fullscreen").equals("true");
	}
	
}
