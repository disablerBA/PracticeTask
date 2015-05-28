package buildings.dwelling.hotel;

import buildings.IBuilding;
import buildings.IBuildingFactory;
import buildings.IFloor;
import buildings.ISpace;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

public class HotelFactory implements IBuildingFactory {

	@Override
	public ISpace createSpace(int area)
	{
		return new Flat(area);
	}
	
	@Override
	public ISpace createSpace(int roomsCount, int area)
	{
		return new Flat(roomsCount, area);
	}
	
	@Override
	public IFloor createFloor(int spacesCount)
	{
		return new HotelFloor(spacesCount);
	}
	
	@Override
	public IFloor createFloor(ISpace[] spaces)
	{
		return new HotelFloor(spaces);
	}
	
	@Override
	public IBuilding createBuilding(int floorsCount, int[] spacesCounts)
	{
		return new Hotel(floorsCount, spacesCounts);
	}
	
	@Override
	public IBuilding createBuilding(IFloor[] floors)
	{
		return new Hotel(floors);
	}
}
