package gentree;
import gentree.configurations.Config;
import gentree.configurations.PathManager;
import gentree.gui.AppWindow;
import gentree.locales.PermMessages;

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class GenTree {

	/**
	 * Logger pre celu aplikaciu a aj pre triedu, sluzi ako root logger
	 * pre ostatne loggery. Nastaveny je v statickom bloku.
	 */
	public static final Logger LOG = Logger.getLogger(GenTree.class.getPackage().getName());	

	/**
	 * Staticky blok na vykonanie prikazov este pred zacatim aplikacie. V bloku vykoname inicializaciu
	 * defaultneho Loggeru. Nastavime aby Logger vypisoval do konzole iba potrebne spravy (Level.SEVERE). Do suboroveho
	 * logu GenTree.log vypiseme vsetky. 
	 */
	static {				
		/*
		 *  Ked pustame aplikaciu s property -Djava.util.logging.config.file tak blok preskakujeme
		 *  Inak nastavime root logger na default nastavenia.
		 */
		if (System.getProperty("java.util.logging.config.file")==null) {
			try {					
				ConsoleHandler ch = new ConsoleHandler();
				// Je zvykom nevypisovat do konzole kazdu info spravu, ale iba tie dolezite.
				ch.setLevel(Level.SEVERE);
				ch.setFormatter(new SimpleFormatter());
				LOG.addHandler(ch);				
				// Log subor nachadzajuci sa v root zlozke.
				FileHandler fh = new FileHandler(new File(PathManager.getInstance().getRootPath(),"GenTree.log").getAbsolutePath(),false);
				fh.setLevel(Level.ALL);
				fh.setFormatter(new SimpleFormatter());
				LOG.addHandler(fh);
				LOG.setUseParentHandlers(false);                                    
			} catch (Exception e) {
				LOG.log(Level.SEVERE, e.toString());
			}			
		}

		// Inicializacia
		reconfig();
	}

	/**
	 * Hlavna main metoda, ktora ma zaulohu rozparsovat argumenty a zavolat nasledujuce funkcie, ktore
	 * od aplikacie pozadujeme. Blizsie informacie o argumentoch su popisane v manuali.
	 * @param args Argumenty predane do main metody z prikazovej riadky
	 */
	public static void main(String[] args) {		

		AppWindow.launchFromMainMethod(args);
		exit();

	}	


	/**
	 * Reconfiguracia zakladnych tried a udajov. Pri neocakavanom pristupe by mohlo dojst k vymazaniu 
	 * konfiguracii. Tato metoda sluzi na obnovenie tychto udajov. Pri strate suborovych konfiguracii vytvori 
	 */
	public static void reconfig() {
		// Essential konfiguracia
		PathManager pm = PathManager.getInstance();
		Config c = Config.getInstance();		
		c.init();
		// Vyuzitie config suboru na donastavenie ostatku udajov
		pm.setUserPath(c.getUserPath());		
	}


	/**
	 * Ukoncenie aplikacie s vypisanim do logu. Metody ktore ho volaju by si mali za sebou upratat
	 * pretoze metoda nevykonava ziadne cistenie okrem ukladania konfiguracie.
	 */
	public static void exit() {
		LOG.info(PermMessages._app_ending);		
		Config.getInstance().saveProperties();		
		LOG.info(PermMessages._app_ended);
		System.exit(0);
	}

	// <editor-fold default-state="collapsed" desc="Objektove metody"/>

	//TODO



	// </editor-fold>

}

