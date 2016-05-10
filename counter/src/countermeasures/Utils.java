package countermeasures;

import java.io.File;

public class Utils {
	
	private Utils() {
		
	}
	
	public static String resolvePath(String curDirectory, String relativePath){
		String[] pathSplit = relativePath.split("/");
		int prevDir = 0;
		for (int i = 0; i < pathSplit.length; i++){
			if (pathSplit[i].equals("..")){
				prevDir++;
			} else {
				break;
			}
		}
		relativePath = "";
		for (int i = prevDir; i < pathSplit.length; i++){
			relativePath += "\\" + pathSplit[i];
		}
		while(prevDir > 0){
			curDirectory = new File(curDirectory).getParent();
			prevDir--;
		}
		
		return curDirectory += relativePath;
	}

}
