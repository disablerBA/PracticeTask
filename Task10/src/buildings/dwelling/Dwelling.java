package buildings.dwelling;

import java.io.Serializable;
import java.util.Iterator;

import buildings.ArrayIterator;
import buildings.IBuilding;
import buildings.IFloor;
import buildings.ISpace;

public class Dwelling implements IBuilding, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected IFloor[] dwellingFloors;
	private int index = 0;
	
	public Dwelling()
	{
		
	}
	
	public Dwelling( int countFloor, int... countFlat)
	{
		dwellingFloors = new DwellingFloor[countFloor];
		for ( int i = 0; i < countFloor; i++ )
		{
			dwellingFloors[i] = new DwellingFloor(countFlat[i]);
		}
	}
	
	public Dwelling( IFloor... dwelingFloors)
	{
		this.dwellingFloors = dwelingFloors;
	}
	
	@Override
	public Iterator<IFloor> iterator()
	{
		return new ArrayIterator<>(this.dwellingFloors);
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
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Dwelling (").append(getCountFloor()).append(", ");
		for (int i = 0; i < dwellingFloors.length; i++)
		{
			sb.append(dwellingFloors[i].toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof Dwelling) )
		{
			return false;
		}
	
		Dwelling d = (Dwelling)obj;
		
		if ( dwellingFloors.length != d.dwellingFloors.length  )
		{
			return false;
		}
		
		for ( int i =0; i < dwellingFloors.length; i++)
		{
			if ( !(dwellingFloors[i].equals(d.dwellingFloors[i])) )
			{
				return false;
			}
		}
		return true;
	}
	
	/*@Override
	public int hashCode()
	{
		int res = dwellingFloors.length;
		for ( int i= 0; i < dwellingFloors.length; i++)
		{
			res ^= dwellingFloors[i].hashCode();
		}
		return res;
	}*/
	
	@Override
	public Object clone()
	{
		Dwelling res = null;
		try
		{
			res = (Dwelling)super.clone();
			res.dwellingFloors = dwellingFloors.clone();
			for ( int i = 0; i < dwellingFloors.length; i++ )
			{
				res.dwellingFloors[i] = (IFloor)dwellingFloors[i].clone();
			}
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public String getType()
	{
		return "Dwelling";
	}
	
	@Override
	public int getFloorNumberByFloor(IFloor floor)
	{
		for (int i = 0; i<dwellingFloors.length; i++)
		{
			if ( dwellingFloors[i] == floor)
			{
				return i;
			}
		}
		return -1;
	}
	
	//получение номера квартиры в здании по ссылке на квартиру
	@Override
	public int getPlacementNumberBySpace(ISpace placement)
	{
		int n = 0;
		for (int f = 0; f < dwellingFloors.length; f++)
		{
			for ( int p = 0; p < dwellingFloors[f].getCountPlacement(); p++)
			{
				if (dwellingFloors[f].getPlacement(p) == placement)
				{
					return n;
				}
				n++;
			}
		}
		return -1;
	}
}