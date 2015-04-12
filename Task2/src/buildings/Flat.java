package buildings;

public class Flat {

	final static private int DEFAULT_SPACE = 50;
	final static private int DEFAULT_COUNT_ROOM = 2;
	private int space;
	private int countRoom;
	
	public Flat()
	{
		space = DEFAULT_SPACE;
		countRoom = DEFAULT_COUNT_ROOM;
	}
	
	public Flat(int space)
	{
		countRoom = DEFAULT_COUNT_ROOM;
		this.space = space;
	}
	
	public Flat(int countRoom, int space)
	{
		this.countRoom = countRoom;
		this.space = space;
	}
	
	public int getCountRoom()
	{
		return this.countRoom;
	}
	
	public void setCountRoom(int count)
	{
		this.countRoom = count;
	}
	
	public int getSpace()
	{
		return this.space;
	}
	
	public void setSpace(int space)
	{
		this.space = space;
	}
}
