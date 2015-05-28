package buildings.office;

import buildings.IBuilding;
import buildings.IBuildingFactory;
import buildings.IFloor;
import buildings.ISpace;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

public class OfficeFactory implements IBuildingFactory {
	
	@Override
	public ISpace createSpace(int area)
	{
		return new Office(area);
	}
	
	@Override
	public ISpace createSpace(int roomsCount, int area)
	{
		return new Office(roomsCount, area);
	}
	
	@Override
	public IFloor createFloor(int spacesCount)
	{
		return new OfficeFloor(spacesCount);
	}
	
	@Override
	public IFloor createFloor(ISpace... spaces)
	{
		return new OfficeFloor(spaces);
	}
	
	@Override
	public IBuilding createBuilding(int floorsCount, int... spacesCounts)
	{
		return new OfficeBuilding(floorsCount, spacesCounts);
	}
	
	@Override
	public IBuilding createBuilding(IFloor... floors)
	{
		return new OfficeBuilding(floors);
	}
}
