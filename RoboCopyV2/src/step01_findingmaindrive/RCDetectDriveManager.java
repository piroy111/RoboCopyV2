package step01_findingmaindrive;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import basicmethods.BasicFichiers;
import basicmethods.BasicFichiersNio;
import basicmethods.BasicFichiersNioRaw;
import basicmethods.BasicPrintMsg;
import staticdata.RCStaticDir;
import step08_launchme.RCManager;

public class RCDetectDriveManager {

	
	public RCDetectDriveManager(RCManager _sRCManager) {
		pRCManager = _sRCManager;
	}
	
	/*
	 * Data
	 */
	private RCManager pRCManager;
	private List<String> pListDriveSource;
	private List<String> pListDriveTarget;
	
	/**
	 * 
	 */
	public final void run() {
		BasicPrintMsg.displaySuperTitle(this, "Detect drives");
		/*
		 * Detect, identify all existing drives
		 */
		BasicPrintMsg.displayTitle(this, "Simple detection of all existing drives");
		Map<String, String> lMapDriveToType = BasicFichiers.getDrivesValid();
		for (String lDrive : lMapDriveToType.keySet()) {
			BasicPrintMsg.display(this, "Drive= '" + lDrive + "'"
					+ "; Type= '" + lMapDriveToType.get(lDrive) + "'");
		}
		/*
		 * Detect the sources
		 */
		BasicPrintMsg.displayTitle(this, "Detection of the source drives (from which we will copy)");
		pListDriveSource = new ArrayList<>();
		for (String lDrive : lMapDriveToType.keySet()) {
			String lTypeStr = lMapDriveToType.get(lDrive);
			if (lTypeStr.equals("Local Disk") || lTypeStr.equals("USB Drive")) {
				String lFolderSource = lDrive + RCStaticDir.getMAIN();
				if (BasicFichiersNioRaw.getIsAlreadyExist(Paths.get(lFolderSource))) {
					BasicPrintMsg.display(this, "Drive source detected= '" + lDrive + "'"
							+ " because the folder exists '" + lFolderSource + "'");
					pListDriveSource.add(lFolderSource);
				} 
				/*
				 * Special case for google drive --> we must detect the directory at the second level
				 */
				else {
					List<String> lListSubDir = BasicFichiersNio.getListFilesAndDirectoriesInDirectory(lDrive);
					for (String lSubDir : lListSubDir) {
						lFolderSource = BasicFichiers.getDirectoryNameCorrect(lDrive + lSubDir)
								+ RCStaticDir.getMAIN();
						if (BasicFichiersNioRaw.getIsAlreadyExist(Paths.get(lFolderSource))) {
							BasicPrintMsg.display(this, "Drive source detected= '" + lDrive + "'"
									+ " because the folder exists '" + lFolderSource + "'");
							pListDriveSource.add(lFolderSource);
						}
					}
				}
			}
		}
		/*
		 * Detect the targets (where we will copy)
		 */
		BasicPrintMsg.displayTitle(this, "Detection of the target drives (to which we will copy)");
		pListDriveTarget = new ArrayList<>();
		for (String lDrive : lMapDriveToType.keySet()) {
			String lTypeStr = lMapDriveToType.get(lDrive);
			if (lTypeStr.equals("Local Disk") || lTypeStr.equals("USB Drive")) {
				String lFolderTarget = lDrive + RCStaticDir.getTARGET();
				if (BasicFichiersNioRaw.getIsAlreadyExist(Paths.get(lFolderTarget))) {
					BasicPrintMsg.display(this, "Drive target detected= '" + lDrive + "'"
							+ " because the folder exists '" + lFolderTarget + "'");
					pListDriveTarget.add(lFolderTarget);
				} 
			}
		}
	}

	/*
	 * Getters & Setters
	 */
	public final RCManager getpRCManager() {
		return pRCManager;
	}
	
}
