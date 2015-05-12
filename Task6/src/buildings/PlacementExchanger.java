package buildings;

import java.util.concurrent.Exchanger;

public class PlacementExchanger {
	
	public static boolean isEqualsPlacement(ISpace space1, ISpace space2)
	{
		if ( 	space1.getCountRoom() == space2.getCountRoom() 
			&&	space1.getSpace() == space2.getSpace() )
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	public static boolean isEqualsFloor(IFloor floor1, IFloor floor2)
	{
		if ( 	floor1.getCountPlacement() == floor2.getCountPlacement() 
			&&	floor1.getTotalSpace() == floor2.getTotalSpace() )
		{
			return true;
		} else
		{
			return false;
		}
	}
	
	// по заданию exchangeFloorRooms 
	public static void exchangeFloorPlacement(IFloor floor1, int index1, IFloor floor2, int index2) throws InexchangeableSpacesException
	{
		if ( index1 >= floor1.getCountPlacement() )
		{
			throw new SpaceIndexOutOfBoundsException(index1, floor1.getCountPlacement());
		}
		
		if ( index2 >= floor2.getCountPlacement() )
		{
			throw new SpaceIndexOutOfBoundsException(index2, floor2.getCountPlacement());
		}
		
		if ( isEqualsPlacement(floor1.getPlacement(index1), floor2.getPlacement(index2)) )
		{
			IFloor tempFloor = floor2;
			floor2.changePlacement(index2, floor1.getPlacement(index1));
			floor1.changePlacement(index1, tempFloor.getPlacement(index2));
		} else
		{
			throw new InexchangeableSpacesException();
		}
	}
	
	public static void exchangeBuildingFloors(IBuilding building1, int index1, IBuilding building2, int index2) throws InexchangeableFloorException
	{
		if ( index1 >= building1.getCountFloor() )
		{
			throw new FloorIndexOutOfBoundsException(index1, building1.getCountFloor());
		}
		
		if ( index2 >= building2.getCountFloor() )
		{
			throw new FloorIndexOutOfBoundsException(index2, building2.getCountFloor());
		}
		
		if ( isEqualsFloor(building1.getFloor(index1), building2.getFloor(index2)) )
		{
			IBuilding tempBuilding = building2;
			building2.changeFloor(index2, building1.getFloor(index1));
			building1.changeFloor(index1, tempBuilding.getFloor(index2));
		} else
		{
			throw new InexchangeableFloorException();
		}
	}
}
