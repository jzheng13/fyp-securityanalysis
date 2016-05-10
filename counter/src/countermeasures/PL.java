package countermeasures;

import org.jpl7.*;
import countermeasures.Utils;

public class PL extends Atom{
	
	private String location;

	public PL(String location) {
		super(location);
		this.location = location;
	}
	
	public String toString() {
		String curDir = System.getProperty("user.dir");
		String absolutePath = Utils.resolvePath(curDir, location);
		return absolutePath;
	}

}
