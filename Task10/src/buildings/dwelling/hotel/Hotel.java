package buildings.dwelling.hotel;

import java.util.ArrayList;

import buildings.IFloor;
import buildings.ISpace;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.office.OfficeBuilding;

public class Hotel extends Dwelling {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ^???
	private IFloor[] hotelFloors; 
	
	public Hotel()
	{
		
	}
	
	public Hotel( int countFloor, int... countPlacement )
	{
		hotelFloors = new IFloor[countFloor];
		for ( int i = 0; i < countFloor; i++ )
		{
			hotelFloors[i] = new HotelFloor(countPlacement[i]) ;
		}
		super.dwellingFloors = this.hotelFloors;
	}
	// $???
	
	public Hotel(IFloor... floors)
	{
		super(floors);
		this.hotelFloors = new IFloor[floors.length];
		for (int f = 0; f < floors.length; f++)
		{
			this.hotelFloors[f] = super.dwellingFloors[f];
		}
	}
			
	public int getStars()
	{
		int maxStar = 1;
		for (int i =0; i < getCountFloor(); i++)
		{
			if ( 	getFloor(i) instanceof HotelFloor
				&& 	maxStar < ((HotelFloor)hotelFloors[i]).getStars() )
			{
				maxStar = ((HotelFloor)hotelFloors[i]).getStars(); //((HotelFloor)getFloor(i)).getStars(); 
			}
		}
		return maxStar;
	}
	
	public ISpace getBestSpace()
	{
		double best = 0;
		for ( int i = 0; i < hotelFloors.length; i++ )
		{
			if ( 	getFloor(i) instanceof HotelFloor 
				&& 	best < hotelFloors[i].getBestSpace().getSpace() * (0.5 + ( ((HotelFloor)hotelFloors[i]).getStars() - 1) * 0.25) )
			{
				best = hotelFloors[i].getBestSpace().getSpace() * (0.5 + ( ((HotelFloor)hotelFloors[i]).getStars() - 1) * 0.25);
			}
		}
		
		for ( int i = 0; i < hotelFloors.length; i++ )
		{
			if ( 	getFloor(i) instanceof HotelFloor 
				&&	best == hotelFloors[i].getBestSpace().getSpace() * (0.5 + ( ((HotelFloor)hotelFloors[i]).getStars() - 1) * 0.25) )
			{
				return hotelFloors[i].getBestSpace();
			}
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Hotel (").append(getStars()).append(", ").append(getCountFloor()).append(", ");
		for (int i = 0; i < hotelFloors.length; i++)
		{
			sb.append(hotelFloors[i].toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof Hotel) )
		{
			return false;
		}
	
		Hotel h = (Hotel)obj;

		if ( this.hotelFloors.length != h.hotelFloors.length )
		{
			return false;
		}
		
		if ( this.getStars() != h.getStars())
		{
			return false;
		}
		
		for ( int i =0; i < hotelFloors.length; i++)
		{
			if ( !(hotelFloors[i].equals(h.hotelFloors[i])) )
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int res = hotelFloors.length ^ getStars();
		for ( int i= 0; i < hotelFloors.length; i++)
		{
			res ^= hotelFloors[i].hashCode();
		}
		return res;
	}
	
	@Override
	public Object clone()
	{
		Hotel res = (Hotel) super.clone();
		res.hotelFloors = res.dwellingFloors ;// ???
		return res;
	}	
	
	@Override
	public String getType()
	{
		return "Hotel";
	}
	
	@Override
	public int getFloorNumberByFloor(IFloor floor)
	{
		for (int i = 0; i<hotelFloors.length; i++)
		{
			if ( hotelFloors[i] == floor)
			{
				return i;
			}
		}
		return -1;
	}
	
	//получение номера квартиры в здании по ссылке
	@Override
	public int getPlacementNumberBySpace(ISpace placement)
	{
		int n = 0;
		for (int f = 0; f < hotelFloors.length; f++)
		{
			for ( int p = 0; p < hotelFloors[f].getCountPlacement(); p++)
			{
				if (hotelFloors[f].getPlacement(p) == placement)
				{
					return n;
				}
				n++;
			}
		}
		return -1;
	}
}
