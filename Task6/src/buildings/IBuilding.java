package buildings;
public interface IBuilding extends Cloneable {

	public int getCountFloor();

	public int getCountPlacement();

	public int getTotalSpace();

	public int getTotalRoom();

	public IFloor[] getArrayFloor();

	public IFloor getFloor(int number);

	public void changeFloor(int number, IFloor floor);

	public ISpace getPlacement(int number);

	public void changePlacement(int number, ISpace newOffice);

	public void addPlacement(int number, ISpace newOffice);

	public void remove(int number);

	public ISpace getBestSpace();

	public ISpace[] sortPlacementDescendingSpace();
	
	public Object clone();

}