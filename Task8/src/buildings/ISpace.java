package buildings;
public interface ISpace extends Cloneable {

	public int getCountRoom();

	public void setCountRoom(int count);

	public int getSpace();

	public void setSpace(int space);
	
	public Object clone();

}