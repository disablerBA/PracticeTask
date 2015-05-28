package buildings;

public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {

	private int index, countFloor;
	
	public FloorIndexOutOfBoundsException(int index, int countFloor)
	{
		this.index = index;
		this.countFloor = countFloor;
	}
	
	public String toString()
	{
		return "FloorIndexOutOfBoundsException: index "+this.index+" of "+this.countFloor + ", такого этажа не существует в здании";
	}
}
