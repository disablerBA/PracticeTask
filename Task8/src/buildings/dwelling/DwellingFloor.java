package buildings.dwelling;

import java.io.Serializable;

import buildings.IFloor;
import buildings.ISpace;
import buildings.office.Office;
import buildings.office.OfficeFloor;


public class DwellingFloor implements IFloor, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ISpace[] flats;
	
	public DwellingFloor(int countFlat)
	{
		flats = new Flat[countFlat];
		for ( int i = 0; i < countFlat; i++ )
		{
			flats[i] = new Flat();
		}
	}
	
	public DwellingFloor(ISpace[] flat)
	{
		flats = flat;
	}
	
	public int getCountPlacement()
	{
		return flats.length;
	}
	
	public int getTotalSpace()
	{
		int totalSpace = 0;
		for (int i = 0; i < flats.length; i++)
		{
			totalSpace += flats[i].getSpace();
		}
		return totalSpace;
	}
	
	public int getTotalRoom()
	{
		int totalRoom = 0;
		for (int i = 0; i < flats.length; i++)
		{
			totalRoom += flats[i].getCountRoom();
		}
		return totalRoom;
	}
	
	public ISpace[] getArrayPlacement()
	{
		return flats;
	}
	
	public ISpace getPlacement(int number)
	{
		return flats[number];
	}
	
	public void changePlacement(int number, ISpace flat)
	{
		flats[number] = flat;
	}
	
	public void addPlacement(int number, ISpace flat)
	{
		ISpace[] newDwellingFloor = new ISpace[flats.length+1];
		newDwellingFloor[number] = flat;
		for ( int i = 0; i < number; i++ )
		{
			newDwellingFloor[i] = flats[i];
		}
		
		for ( int i = number+1; i < newDwellingFloor.length; i++ )
		{
			newDwellingFloor[i] = flats[i-1];
		}
		
		flats = newDwellingFloor;
	}
	
	public void remove(int number)
	{
		ISpace[] newDwellingFloor = new ISpace[flats.length-1];
		for ( int i = 0; i < number; i++ )
		{
			newDwellingFloor[i] = flats[i];
		}
		
		for ( int i = number+1; i < flats.length; i++ )
		{
			newDwellingFloor[i-1] = flats[i];
		}
		
		flats = newDwellingFloor;
	}
	
	public ISpace getBestSpace()
	{
		int bestSpace = 0;
		int indexRoom = 0;
		
		for (int i = 0; i < flats.length; i++ )
		{
			if ( flats[i].getSpace() > bestSpace) 
			{
				bestSpace = flats[i].getSpace();
			}
		}
		
		for (int i = 0; i < flats.length; i++ )
		{
			if ( flats[i].getSpace() == bestSpace )
			{
				indexRoom = i;
			}
		}
		return flats[indexRoom];
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("DwellingFloor (").append(getCountPlacement()).append(", ");
		for (int i = 0; i < flats.length; i++)
		{
			sb.append(flats[i].toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof DwellingFloor) )
		{
			return false;
		}
	
		DwellingFloor df = (DwellingFloor)obj;
		
		if ( df.getCountPlacement() != flats.length )
		{
			return false;
		}
			
		for ( int i =0; i < flats.length; i++)
		{
			if ( !(flats[i].equals(df.getPlacement(i))) )
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int res = flats.length;
		for ( int i= 0; i < flats.length; i++)
		{
			res ^= flats[i].hashCode();
		}
		return res;
	}
	
	@Override
	public Object clone()
	{
		DwellingFloor res = null;
		try 
		{
			res = (DwellingFloor)super.clone();
			res.flats = this.flats.clone();
			for ( int f = 0; f < flats.length; f++)
			{
				res.flats[f] = (ISpace)flats[f].clone();
			}
			//res.flats = this.flats.clone();
				
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	//получение номера квартиры на этаже по ссылке
	@Override
	public int getPlacementNumberBySpace(ISpace placement)
	{
		for (int i = 0; i<flats.length; i++)
		{
			if ( flats[i] == placement)
			{
				return i;
			}
		}
		return -1;
	}
}
