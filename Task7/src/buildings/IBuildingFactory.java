package buildings;

public interface IBuildingFactory {

	public ISpace createSpace(int area);
	public ISpace createSpace(int roomsCount, int area);
	public IFloor createFloor(int spacesCount);
	public IFloor createFloor(ISpace[] spaces);
	public IBuilding createBuilding(int floorsCount, int[] spacesCounts);
	public IBuilding createBuilding(IFloor[] floors);

}
