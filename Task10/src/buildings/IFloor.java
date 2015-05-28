package buildings;
public interface IFloor extends Cloneable, Iterable<ISpace> {

	public int getCountPlacement();

	public int getTotalSpace();

	public int getTotalRoom();

	public ISpace[] getArrayPlacement();

	public ISpace getPlacement(int index);

	public void changePlacement(int index, ISpace space);

	public ISpace getBestSpace();

	public void addPlacement(int index, ISpace office);

	public void remove(int index);

	public Object clone();
	
	public int getPlacementNumberBySpace(ISpace placement);
}