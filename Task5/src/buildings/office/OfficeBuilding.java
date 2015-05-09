package buildings.office;

import java.io.Serializable;

import buildings.FloorIndexOutOfBoundsException;
import buildings.IBuilding;
import buildings.IFloor;
import buildings.ISpace;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.dwelling.Dwelling;

public class OfficeBuilding implements IBuilding, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListOfficeFloor floors;
	
	public OfficeBuilding( int countFloor, int[] countOffice)
	{
		floors = new ListOfficeFloor();
		for ( int i = 0; i < countFloor; i++)
		{
			floors.add(new OfficeFloor(countOffice[i]));
		}
	}
	
	public OfficeBuilding( IFloor[] arrayFloors )
	{
		floors = new ListOfficeFloor();
		for ( int i = 0; i < arrayFloors.length; i++)
		{
			floors.add(arrayFloors[i]);
		}
	}
	
	@Override
	public int getCountFloor()
	{
		return floors.size();
	}
	
	@Override
	public int getCountPlacement()
	{
		int count = 0;
		for ( int i = 0; i < floors.size(); i++)
		{
			count += floors.getOfficeFloor(i).getCountPlacement();
		}
		return count;
	}
	
	@Override
	public int getTotalSpace()
	{
		int total = 0;
		for ( int i = 0; i < floors.size(); i++)
		{
			total += floors.getOfficeFloor(i).getTotalSpace(); 
		}
		return total;
	}
	
	@Override
	public int getTotalRoom()
	{
		int count = 0;
		ISpace[] offices;
		for ( int i = 0; i < floors.size(); i++)
		{
			offices = floors.getOfficeFloor(i).getArrayPlacement();
			for ( int j = 0; j < offices.length; j++)
			{
				count += offices[j].getCountRoom();
			}
		}
		return count;
	}
	
	@Override
	public IFloor[] getArrayFloor()
	{
		IFloor[] fls = new IFloor[floors.size()];
		for (int i = 0; i < floors.size(); i++)
		{
			fls[i] = floors.getOfficeFloor(i);
		}
		return fls;
	}
	
	@Override
	public IFloor getFloor(int number)
	{
		if ( number >= floors.size() )
		{
			throw new FloorIndexOutOfBoundsException( number, floors.size() );
		}
		
		return floors.getOfficeFloor(number);
	}
	
	@Override
	public void changeFloor(int number, IFloor floor)
	{
		if ( number >= floors.size() )
		{
			throw new FloorIndexOutOfBoundsException( number, floors.size() );
		}
		
		floors.setOfficeFloor(number, floor);
	}
	
	@Override
	public ISpace getPlacement(int number)
	{
		if ( number >= getCountPlacement() )
		{
			throw new SpaceIndexOutOfBoundsException(number, getCountPlacement());
		}
		
		ISpace[] allOffice = new ISpace[getCountPlacement()];
		int index = 0;
		for(int f = 0; f < floors.size(); f++)
		{
			for(int o = 0; o < floors.getOfficeFloor(f).getCountPlacement(); o++)
			{
				allOffice[index++] = floors.getOfficeFloor(f).getPlacement(o);
			}
		}
		
		return allOffice[number];
	
		//===========================================================
		/*
		int countOffice = 0;
		for ( int i = 0; i < floors.size(); i++)
		{
			countOffice += floors.getOfficeFloor(i).getCountOffice();
			if ( number < countOffice )
			{
				return floors.getOfficeFloor(i).getOffice(number - (countOffice - floors.getOfficeFloor(i).getCountOffice()));
			} else continue;
		}
		throw new SpaceIndexOutOfBoundsException(number, getCountOffice());
		*/
		
	} 
	
	@Override
	public void changePlacement(int number, ISpace newOffice)
	{
		if ( number >= getCountPlacement() )
		{
			throw new SpaceIndexOutOfBoundsException(number, getCountPlacement());
		}
		
		int countOffice = 0;
		for ( int i = 0; i < floors.size(); i++)
		{
			countOffice += floors.getOfficeFloor(i).getCountPlacement();
			if ( number < countOffice )
			{
				floors.getOfficeFloor(i).changePlacement(number - (countOffice - floors.getOfficeFloor(i).getCountPlacement()), newOffice);
				return;
			} else continue;
		}
	}
	
	@Override
	public void addPlacement( int number, ISpace newOffice)
	{
		if ( number > getCountPlacement() )
		{
			throw new SpaceIndexOutOfBoundsException(number, getCountPlacement());
		}
		
		int countOffice = 0;
		for ( int i = 0; i < floors.size(); i++)
		{
			countOffice += floors.getOfficeFloor(i).getCountPlacement();
			if ( number < countOffice || i == floors.size()-1 )
			{
				floors.getOfficeFloor(i).addPlacement(number - (countOffice - floors.getOfficeFloor(i).getCountPlacement()), newOffice);
				return;
			} else continue;
		}
	}
	
	@Override
	public void remove( int number )
	{
		if ( number >= getCountPlacement() )
		{
			throw new SpaceIndexOutOfBoundsException(number, getCountPlacement());
		}
		
		int countOffice = 0;
		for ( int i = 0; i < floors.size(); i++)
		{
			countOffice += getFloor(i).getCountPlacement();
			if ( number < countOffice )
			{
				floors.getOfficeFloor(i).remove(number - (countOffice - getFloor(i).getCountPlacement()) );
				return;
			} else continue;
		}
	}
	
	@Override
	public ISpace getBestSpace()
	{
		int bestSpace = 0;
		for ( int i = 0; i < floors.size(); i++ )
		{
			if ( bestSpace < floors.getOfficeFloor(i).getBestSpace().getSpace() )
			{
				bestSpace = floors.getOfficeFloor(i).getBestSpace().getSpace();
			}
		}
		
		for ( int i = 0; i < floors.size(); i++ )
		{
			if ( bestSpace == floors.getOfficeFloor(i).getBestSpace().getSpace() )
			{
				return floors.getOfficeFloor(i).getBestSpace();
			}
		}
		return null;
	}
	
	@Override
	public ISpace[] sortPlacementDescendingSpace()
	{
		int count = 0;
		ISpace sortOffice;
		ISpace[] offices = new ISpace[this.getCountPlacement()];
		for ( int i = 0; i < floors.size(); i++ )
		{
			for ( int j = 0; j < getFloor(i).getCountPlacement(); j++ )
			{
				offices[count++] = floors.getOfficeFloor(i).getPlacement(j);
			}
		}
		
		for ( int i = 0; i < offices.length-1; i ++)
		{
			for ( int j = i+1; j < offices.length; j ++)
			{
				if ( offices[i].getSpace() < offices[j].getSpace() )
				{
					sortOffice = offices[j];
					offices[j] = offices[i];
					offices[i] = sortOffice;
				}
			}
		}
		
		return offices;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("OfficeBuilding (").append(getCountFloor()).append(", ");
		for (int i = 0; i < floors.size(); i++)
		{
			sb.append(floors.getOfficeFloor(i).toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof OfficeBuilding) )
		{
			return false;
		}
	
		OfficeBuilding d = (OfficeBuilding)obj;
		for ( int i =0; i < floors.size(); i++)
		{
			if ( !(floors.getOfficeFloor(i).equals(d.floors.getOfficeFloor(i))) )
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int res = floors.size();
		for ( int i= 0; i < floors.size(); i++)
		{
			res ^= floors.getOfficeFloor(i).hashCode();
		}
		return res;
	}
	
	@Override
	public Object clone()
	{
		OfficeBuilding res = null;
		try
		{
			res = (OfficeBuilding) super.clone();
			res.floors = (ListOfficeFloor) floors.clone();
		} catch ( CloneNotSupportedException e )
		{
			e.printStackTrace();
		}
		return res;
	}
}
