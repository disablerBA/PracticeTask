package buildings;

public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {

	private int index, countOffice;
	
	public SpaceIndexOutOfBoundsException(int index, int countOffice)
	{
		this.index = index;
		this.countOffice = countOffice;
	}
	
	public String toString()
	{
		return "SpaceIndexOutOfBoundsException: index "+this.index+" of "+this.countOffice + ", такого номера помещения не существует в здании";
	}
}
