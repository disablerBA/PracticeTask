package buildings;

public class Dwelling {

	private DwellingFloor[] dwellingFloors;
	
	public Dwelling( int countFloor, int[] countFlat)
	{
		dwellingFloors = new DwellingFloor[countFloor];
		for ( int i = 0; i < countFloor; i++ )
		{
			dwellingFloors[i] = new DwellingFloor(countFlat[i]);
		}
	}
	
	public Dwelling( DwellingFloor[] dwelingFloors)
	{
		this.dwellingFloors = dwelingFloors;
	}
	
	public int getCountFloor()
	{
		return dwellingFloors.length;
	}
	
	public int getCountFlat()
	{
		int count = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			count += dwellingFloors[i].getCountFlat();
		}
		return count;
	}
	
	public int getTotalSpace()
	{
		int total = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			total += dwellingFloors[i].getTotalSpace(); 
		}
		return total;
	}
	
	public int getCountRoom()
	{
		int count = 0;
		Flat[] flats;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			flats = dwellingFloors[i].getArrayFlat();
			for ( int j = 0; j < flats.length; j++)
			{
				count += flats[j].getCountRoom();
			}
		}
		return count;
	}
	
	public DwellingFloor[] getArrayDwellingFloor()
	{
		return dwellingFloors;
	}
	
	public DwellingFloor getDwellingFloor(int number)
	{
		return dwellingFloors[number];
	}
	
	public void changeDwellingFloor(int number, DwellingFloor floor)
	{
		dwellingFloors[number] = floor;
	}
	
	public Flat getFlat(int number)
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountFlat();
			if ( number < countFlat )
			{
				return dwellingFloors[i].getFlat(number - (countFlat - dwellingFloors[i].getCountFlat()));
			} else continue;
		}
		return null;
	} 
	
	public void changeFlat(int number, Flat newFlat)
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountFlat();
			if ( number < countFlat )
			{
				dwellingFloors[i].changeFlat(number - (countFlat - dwellingFloors[i].getCountFlat()), newFlat);
				return;
			} else continue;
		}
	}
	
	public void addFlat( int number, Flat newFlat)
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountFlat();
			if ( number < countFlat || i == dwellingFloors.length-1 )
			{
				dwellingFloors[i].addFlat(number - (countFlat - dwellingFloors[i].getCountFlat()), newFlat);
				return;
			} else continue;
			
		}
	}
	
	public void deleteFlat( int number )
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountFlat();
			if ( number < countFlat )
			{
				dwellingFloors[i].deleteFlat(number - (countFlat - dwellingFloors[i].getCountFlat()) );
				return;
			} else continue;
		}
	}
	
	public Flat getBestSpace()
	{
		int bestSpace = 0;
		for ( int i = 0; i < dwellingFloors.length; i++ )
		{
			if ( bestSpace < dwellingFloors[i].getBestSpace().getSpace() )
			{
				bestSpace = dwellingFloors[i].getBestSpace().getSpace();
			}
		}
		
		for ( int i = 0; i < dwellingFloors.length; i++ )
		{
			if ( bestSpace == dwellingFloors[i].getBestSpace().getSpace() )
			{
				return dwellingFloors[i].getBestSpace();
			}
		}
		return null;
	}
	
	public Flat[] sortFlatDescendingSpace()
	{
		int count = 0;
		Flat sortFlat;
		Flat[] flats = new Flat[this.getCountFlat()];
		for ( int i = 0; i < dwellingFloors.length; i++ )
		{
			for ( int j = 0; j < dwellingFloors[i].getCountFlat(); j++ )
			{
				flats[count++] = dwellingFloors[i].getFlat(j);
			}
		}
		
		for ( int i = 0; i < flats.length-1; i ++)
		{
			for ( int j = i+1; j < flats.length; j ++)
			{
				if ( flats[i].getSpace() < flats[j].getSpace() )
				{
					sortFlat = flats[j];
					flats[j] = flats[i];
					flats[i] = sortFlat;
				}
			}
		}
		
		return flats;
	}
}
