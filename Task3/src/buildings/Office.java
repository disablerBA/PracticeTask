package buildings;

public class Office implements ISpace {

	final static private int DEFAULT_SPACE = 250;
	final static private int DEFAULT_COUNT_ROOM = 1;
	private int space;
	private int countRoom;
	
	public Office()
	{
		space = DEFAULT_SPACE;
		countRoom = DEFAULT_COUNT_ROOM;
	}
	
	public Office(int space)
	{
		if ( space < 1)
		{
			throw new InvalidSpaceAreaException(space);
		}
		
		countRoom = DEFAULT_COUNT_ROOM;
		this.space = space;
	}
	
	public Office(int countRoom, int space)
	{
		if ( countRoom < 1)
		{
			throw new InvalidRoomsCountException(countRoom);
		}
		
		if ( space < 1)
		{
			throw new InvalidSpaceAreaException(space);
		}
		
		this.countRoom = countRoom;
		this.space = space;
	}
	
	/* (non-Javadoc)
	 * @see Space#getCountRoom()
	 */
	@Override
	public int getCountRoom()
	{
		return this.countRoom;
	}
	
	/* (non-Javadoc)
	 * @see Space#setCountRoom(int)
	 */
	@Override
	public void setCountRoom(int count)
	{
		if(count < 1)
		{
			throw new InvalidRoomsCountException(count);
		}
		this.countRoom = count;
	}
	
	/* (non-Javadoc)
	 * @see Space#getSpace()
	 */
	@Override
	public int getSpace()
	{
		return this.space;
	}
	
	/* (non-Javadoc)
	 * @see Space#setSpace(int)
	 */
	@Override
	public void setSpace(int space)
	{
		if(space < 1)
		{
			throw new InvalidSpaceAreaException(space);
		}
		this.space = space;
	}
	
	private class InvalidSpaceAreaException extends IllegalArgumentException
	{
		private int space;
		public InvalidSpaceAreaException(int space)
		{
			this.space = space;
		}
		
		public String toString()
		{
			return "InvalidSpaceAreaException: "+this.space+" некорретная площадь офиса";
		}
	}
	
	private class InvalidRoomsCountException extends IllegalArgumentException
	{
		private int countRoom;
		public InvalidRoomsCountException(int count)
		{
			countRoom = count;
		}
		
		public String toString()
		{
			return "InvalidRoomsCountException: "+this.countRoom+" некорретное кол-во помещений";
		}
	}
}
