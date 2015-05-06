package buildings.dwelling.hotel;

import buildings.IFloor;
import buildings.ISpace;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;

public class Hotel extends Dwelling {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ^???
	private HotelFloor[] hotelFloors; 
	
	public Hotel( int countFloor, int[] countPlacement )
	{
		//super(countFloor, countPlacement);
		hotelFloors = new HotelFloor[countFloor];
		for ( int i = 0; i < countFloor; i++ )
		{
			hotelFloors[i] = new HotelFloor(countPlacement[i]);
		}
		super.dwellingFloors = this.hotelFloors;
	}
	// $???
	
	public Hotel(IFloor[] floors)
	{
		super(floors);
	}
	
	public int getStars()
	{
		int maxStar = 1;
		for (int i =0; i < getCountFloor(); i++)
		{
			if ( 	getFloor(i) instanceof HotelFloor
				&& 	maxStar < hotelFloors[i].getStars() )
			{
				maxStar = hotelFloors[i].getStars(); //((HotelFloor)getFloor(i)).getStars(); 
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
				&& 	best < hotelFloors[i].getBestSpace().getSpace() * (0.5 + (hotelFloors[i].getStars() - 1) * 0.25) )
			{
				best = hotelFloors[i].getBestSpace().getSpace() * (0.5 + (hotelFloors[i].getStars() - 1) * 0.25);
			}
		}
		
		for ( int i = 0; i < hotelFloors.length; i++ )
		{
			if ( 	getFloor(i) instanceof HotelFloor 
				&&	best == hotelFloors[i].getBestSpace().getSpace() * (0.5 + (hotelFloors[i].getStars() - 1) * 0.25) )
			{
				return hotelFloors[i].getBestSpace();
			}
		}
		return null;
	}
}
