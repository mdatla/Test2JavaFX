package backend;

import java.io.File;

public class SetupHelper {

	public static String[] getAllClasses(){
		File f = new File(".");
		String[] result = new String[1000];
		int i=0;

		File[] allFiles = f.listFiles();
		for(File currentFile : allFiles){
			String currFileName = currentFile.getName();
			if(currFileName.contains(".csv")&&currFileName.contains("Roster~")){
				String[] stringArray = currFileName.split("~");
				stringArray[1]=stringArray[1].substring(0, (stringArray[1].length()-4));
				result[i]=stringArray[1];
				i++;
			}
		}

		String[] result2 = new String[i];
		for(int j=0;j<result2.length;j++){
			result2[j]=result[j];
		}
		return result2;
	}
	
	public static boolean validateClass(String s){
		if(Globals.classList.contains(s)){
			return true;
		} else {
			return false;
		}
		
		
		
		
	}
	
}
