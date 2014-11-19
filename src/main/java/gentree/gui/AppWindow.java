package gentree.gui;

import gentree.GenTree;
import gentree.annotations.Debug;
import gentree.configurations.Config;
import gentree.configurations.PathManager;
import gentree.gui.comp.SwitchButton;
import gentree.locales.PermMessages;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppWindow extends Application{

	private static final Logger LOG = Logger.getLogger(AppWindow.class.getName());
	
	/**
	 * Primarny frame pre aplikaciu v ktorej sa budu nachadzat Sceny atd... a tvorit nam jedno
	 * konzistentne GUI.
	 */
	private Stage primaryStage;	
	private BorderPane rootLayout;
	
	public static void launchFromMainMethod(String[] args) {
		launch(args);
	}
	
	/**
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		LOG.info(PermMessages._gui_starting);
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(PermMessages._app_name);
		try {
										
			primaryStage.setWidth(Config.getInstance().getWidth());
			primaryStage.setHeight(Config.getInstance().getHeight());			
			primaryStage.centerOnScreen();
			
			// Inicializacia root elementu typu BorderPane. V tomto containery bude ostatok GUI-cka
			// Root element v sebe uchovava menu bar, aby boli pristupne zakladne funkcie.
			rootInit();
			// testInit();
			
			// Scena <=> hlavna aplikacia, ktora bude zobrazovana v Stage.
			Scene scene = new Scene(rootLayout);
			
			// Do stage pridame ikonu, scenu a zobrazime aplikaciu			
			primaryStage.getIcons().add(new Image(PathManager.getInstance().getRootPath().toURI() + "icon.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
			
			LOG.info(PermMessages._gui_started);
			// NOT USED FOR NOW? primaryStage.setFullScreen(Config.getInstance().isFullscreen());
			
		} catch (Exception e) {
			LOG.severe(String.format(PermMessages._gui_err,e.toString()));
			primaryStage.close();
			// Dodatocne nullovanie.
			rootLayout = null;
			primaryStage = null;			
			exit();
		}
		
	}
	
	/**
	 * Debug metoda pre testovanie rozneho gui. Volana by mala byt pri inicializovani ostatnych 
	 * layoutov.
	 */
	@Debug
	private void testInit() {
		SwitchButton swtBtn = new SwitchButton(false);
		rootLayout.setCenter(swtBtn);
	}
	
	public void rootInit() throws Exception {
		FXMLLoader loader = new FXMLLoader(
				AppWindow.class.getResource("view/mt_gentree_root.fxml"));

		// Setting root container
		rootLayout = (BorderPane) loader.load();	
		
		
	}
	
	public void exit() {
		LOG.info(PermMessages._gui_closing);
		GenTree.exit();
	}
	
}
