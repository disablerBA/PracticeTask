package buildings.net.server.sequental;

import java.io.Serializable;

public class BuildingUnderArrestException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString()
	{
		return "The building was arrested!";
	}
}
