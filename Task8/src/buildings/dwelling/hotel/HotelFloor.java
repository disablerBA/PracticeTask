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
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("HotelFloor (").append(stars).append(", ").append(getCountPlacement()).append(", ");
		for (int i = 0; i < super.getArrayPlacement().length; i++)
		{
			sb.append(this.getPlacement(i).toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof HotelFloor) )
		{
			return false;
		}
	
		HotelFloor hf = (HotelFloor)obj;
		
		if ( hf.getCountPlacement() != this.getCountPlacement() )
		{
			return false;
		}
		
		for ( int i =0; i < this.getCountPlacement(); i++)
		{
			if ( !(this.getPlacement(i).equals(hf.getPlacement(i))) || this.stars != hf.stars )
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int res = this.getCountPlacement() ^ stars;
		for ( int i= 0; i < this.getCountPlacement(); i++)
		{
			res ^= this.getPlacement(i).hashCode();
		}
		return res;
	}
	
	@Override
	public Object clone()
	{
		HotelFloor res = (HotelFloor) super.clone();
		return res;
	}
}
