package gentree.locales;

public interface PermMessages {
	
	// Application msgs
	public static final String _app_started = "Application started";
	public static final String _app_ending = "Application ending";
	public static final String _app_ended = "Application ended";
	public static final String _app_name = "GenTree";
	// Localization/ResourceBundle msgs
	public static final String _loc_noval = "Message with id=%s is not defined in localization file";
	// Config msgs
	public static final String _cfg_saved = "Configuration was succesfully saved";
	public static final String _cfg_err_save = "Problem occured when saving property file. Exception : %s";
	public static final String _cfg_loaded = "Configuration was succesfully loaded. All missing values are going to be set.";
	public static final String _cfg_file_miss = "Config file does not exist. New one will be created";
	public static final String _cfg_err_load = "Problem occured when loading property file. Exception : %s";
	public static final String _cfg_set = "Missing values were succesfully set.";
	// Gui msgs
	public static final String _gui_err = "Problem in initialization of GUI occured. Exception : %s";
	public static final String _gui_closing = "Graphical interface is closing.";
	public static final String _gui_starting = "Graphical interface is starting. Stage and Scenes are going to be initialized.";
	public static final String _gui_started = "Graphical interface started without any errors";
}
