package buildings.dwelling;

import buildings.IBuildingFactory;
import buildings.IBuilding;
import buildings.IFloor;
import buildings.ISpace;

public class DwellingFactory implements IBuildingFactory {

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
		return new DwellingFloor(spacesCount);
	}
	
	@Override
	public IFloor createFloor(ISpace... spaces)
	{
		return new DwellingFloor(spaces);
	}
	
	@Override
	public IBuilding createBuilding(int floorsCount, int... spacesCounts)
	{
		return new Dwelling(floorsCount, spacesCounts);
	}
	
	@Override
	public IBuilding createBuilding(IFloor... floors)
	{
		return new Dwelling(floors);
	}
}
