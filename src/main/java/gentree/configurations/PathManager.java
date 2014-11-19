package gentree.configurations;
import gentree.GenTree;

import java.io.File;
import java.net.URL;
import java.util.logging.Logger;


public class PathManager {
	private static final Logger LOG = Logger.getLogger(PathManager.class.getName());
	
	private static PathManager instance;
	
	/**
	 * Defaultny korenovy adresar 
	 */
	private File rootPath;
	/**
	 * Defaultny plugin adresar
	 */
	private File pluginPath;
	/**
	 * Jedina cesta ktoru je mozne zmenit v ramci aplikacie
	 */
	private File userPath;	
	/**
	 * Cesta k dodatocnym local suborom.
	 */
	private File localePath;
	
	/**
	 * Metoda ktora navrati instanciu PathManager. Pre unikatnost tejto instancie pre celu aplikaciu
	 * dodrziavame principy Singleton navrhoveho vzoru.
	 * @return Singleton instanciu PathManageru
	 */
	public static PathManager getInstance() {
		if (instance == null) {			
			instance = new PathManager();
			instance.init();
		}
		return instance;
	}
	
	/**
	 * Inicializacia PathManageru s defaultnymi cestami. 
	 * Root zlozka je miesto kde sa nachadza aplikacia.
	 * Plugin zlozka je v root zlozke pod nazvom plugins.
	 * User zlozka je pocas behu aplikacie menitelna no defaultne je rovnaka ako root.
	 */
	public void init() {
		/*
		 *  Ziskame lokaciu triedy. Pri spustani v IDE vrati zlozku pri spustani z jar
		 *  zase cestu k jaru 
		 */
		
		URL url = GenTree.class.getProtectionDomain().getCodeSource().getLocation();		
		// 
		File _pRoot = new File(url.getFile());		
		if (_pRoot.isDirectory()) {
			rootPath = _pRoot;
		} else {
			rootPath = new File(_pRoot.getParent());
		}		
		
		// Ostatne cesty nastavime priamo z root
		pluginPath = new File(rootPath,"plugins");
		localePath = new File(rootPath,"locales");
		userPath = rootPath;
	}

	/**
	 * Getter pre root zlozku. Root zlozka je pociatkom alebo tiez miestom tejto aplikacie.
	 * Ostatne zlozky cerpaju priamo z nej
	 * @see #getPluginPath() 
	 * @return Root/korenova cesta/subor. 
	 */
	public File getRootPath() {
		return rootPath;
	}

	/**
	 * Getter pre zlozku s pluginmi
	 * @return Cesta k pluginom
	 */
	public File getPluginPath() {
		return pluginPath;
	}
	
	/**
	 * Getter pre aktualnu uzivatelsku cestu. Pri praci so subormi nam bude zlozka s touto 
	 * cestou otvorena ako prva
	 * @return Ulozena uzivatelska cesta
	 */
	public File getUserPath() {
		return userPath;
	}
	
	public File getLocalePath() {
		return localePath;
	}
	
	/**
	 * Setter pre aktualnu uzivatelsku cestu. Tato metoda nam uklada aktualny stav uzivatela 
	 * pri prehladavani adresoveho priestoru. Je na programatorovi aby bola tato cesta nastavena ako zlozka.
	 * @param userPath Aktualna uzivatelska cesta na ktoru sme sa dostali.
	 */
	public void setUserPath(File userPath) {		
		this.userPath = userPath;
		LOG.info("User path was set to " + userPath.toString());
	}
	
	
	
}
