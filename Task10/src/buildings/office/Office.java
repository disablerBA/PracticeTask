package buildings.office;

import java.io.Serializable;

import buildings.ISpace;
import buildings.dwelling.Flat;

public class Office implements ISpace, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SPACE = 250;
	private static final int DEFAULT_COUNT_ROOM = 1;
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
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Office (").append(getCountRoom()).append(", ").append(getSpace()).append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof Office) )
		{
			return false;
		}
		
		return	((Office)obj).countRoom == this.countRoom 
			&&	((Office)obj).space == this.space;
	}

	@Override
	public int hashCode()
	{
		return ( countRoom ^ (space & 0x0000FFFF) ^ ((space & 0xFFFF0000) >> 16) );
	}
	
	@Override
	public Object clone()
	{
		Office res = null;
		try 
		{
			res = (Office)super.clone();
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
