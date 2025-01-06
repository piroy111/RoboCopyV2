package step08_launchme;

import step01_findingmaindrive.RCDetectDriveManager;

public class RCManager {

	public static void main(String[] _sArgs) {
		new RCManager().run();
	}
	
	/**
	 * 
	 */
	public RCManager() {
		pRCDetectDriveManager = new RCDetectDriveManager(this);
	}
	
	/*
	 * Data
	 */
	private RCDetectDriveManager pRCDetectDriveManager;
	
	/**
	 * 
	 */
	public final void run() {
		pRCDetectDriveManager.run();
	}

	/*
	 * Getters & Setters
	 */
	public final RCDetectDriveManager getpRCDetectDriveManager() {
		return pRCDetectDriveManager;
	}
	
	
}
