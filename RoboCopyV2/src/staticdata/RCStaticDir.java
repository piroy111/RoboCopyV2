package staticdata;

public class RCStaticDir {

	
	private static String MAIN = "ZZ_ROBOCOPY_SOURCE/";
	private static String TARGET = "ZZ_ROBOCOPY_TARGET/";
	private static String CONF = "ZZ_ROBOCOPY_TARGET/00_Conf/";
	private static String BACKUPS = "ZZ_ROBOCOPY_TARGET/01_Backups/";
	
	/*
	 * Getters & Setters
	 */
	public static final String getMAIN() {
		return MAIN;
	}
	public static final String getTARGET() {
		return TARGET;
	}
	public static final String getCONF() {
		return CONF;
	}
	public static final String getBACKUPS() {
		return BACKUPS;
	}
	
}
