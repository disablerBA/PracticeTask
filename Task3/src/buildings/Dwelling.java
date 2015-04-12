package buildings;


public class Dwelling implements IBuilding {

	private IFloor[] dwellingFloors;
	
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
	
	public int getCountPlacement()
	{
		int count = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			count += dwellingFloors[i].getCountPlacement();
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
	
	public int getTotalRoom()
	{
		int count = 0;
		ISpace[] flats;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			flats = dwellingFloors[i].getArrayPlacement();
			for ( int j = 0; j < flats.length; j++)
			{
				count += flats[j].getCountRoom();
			}
		}
		return count;
	}
	
	public IFloor[] getArrayFloor()
	{
		return dwellingFloors;
	}
	
	public IFloor getFloor(int number)
	{
		return dwellingFloors[number];
	}
	
	public void changeFloor(int number, IFloor floor)
	{
		dwellingFloors[number] = floor;
	}
	
	public ISpace getPlacement(int number)
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountPlacement();
			if ( number < countFlat )
			{
				return dwellingFloors[i].getPlacement(number - (countFlat - dwellingFloors[i].getCountPlacement()));
			} else continue;
		}
		return null;
	} 
	
	public void changePlacement(int number, ISpace newFlat)
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountPlacement();
			if ( number < countFlat )
			{
				dwellingFloors[i].changePlacement(number - (countFlat - dwellingFloors[i].getCountPlacement()), newFlat);
				return;
			} else continue;
		}
	}
	
	public void addPlacement( int number, ISpace newFlat)
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountPlacement();
			if ( number < countFlat || i == dwellingFloors.length-1 )
			{
				dwellingFloors[i].addPlacement(number - (countFlat - dwellingFloors[i].getCountPlacement()), newFlat);
				return;
			} else continue;
			
		}
	}
	
	public void remove( int number )
	{
		int countFlat = 0;
		for ( int i = 0; i < dwellingFloors.length; i++)
		{
			countFlat += dwellingFloors[i].getCountPlacement();
			if ( number < countFlat )
			{
				dwellingFloors[i].remove(number - (countFlat - dwellingFloors[i].getCountPlacement()) );
				return;
			} else continue;
		}
	}
	
	public ISpace getBestSpace()
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
	
	public ISpace[] sortPlacementDescendingSpace()
	{
		int count = 0;
		ISpace sortFlat;
		ISpace[] flats = new ISpace[this.getCountPlacement()];
		for ( int i = 0; i < dwellingFloors.length; i++ )
		{
			for ( int j = 0; j < dwellingFloors[i].getCountPlacement(); j++ )
			{
				flats[count++] = dwellingFloors[i].getPlacement(j);
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
