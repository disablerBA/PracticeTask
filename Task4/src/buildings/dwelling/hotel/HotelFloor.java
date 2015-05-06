package buildings.dwelling.hotel;

import buildings.ISpace;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int DEFAULT_STAR = 1;
	private int stars = DEFAULT_STAR;

	public HotelFloor(int countPlacement)
	{
		super(countPlacement);
	}
	
	public HotelFloor(ISpace[] space)
	{
		super(space);
	}
	
	public void setStars(int count)
	{
		stars = count;
	}
	
	public int getStars()
	{
		return stars;
	}
}
