package buildings.dwelling;

import java.io.Serializable;

import buildings.IFloor;
import buildings.ISpace;


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
}
