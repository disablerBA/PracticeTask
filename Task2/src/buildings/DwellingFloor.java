package buildings;

public class DwellingFloor {

	Flat[] flats;
	
	public DwellingFloor(int countFlat)
	{
		flats = new Flat[countFlat];
		for ( int i = 0; i < countFlat; i++ )
		{
			flats[i] = new Flat();
		}
	}
	
	public DwellingFloor(Flat[] flat)
	{
		flats = flat;
	}
	
	public int getCountFlat()
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
	
	public Flat[] getArrayFlat()
	{
		return flats;
	}
	
	public Flat getFlat(int number)
	{
		return flats[number];
	}
	
	public void changeFlat(int number, Flat flat)
	{
		flats[number] = flat;
	}
	
	public void addFlat(int number, Flat flat)
	{
		Flat[] newDwellingFloor = new Flat[flats.length+1];
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
	
	public void deleteFlat(int number)
	{
		Flat[] newDwellingFloor = new Flat[flats.length-1];
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
	
	public Flat getBestSpace()
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
