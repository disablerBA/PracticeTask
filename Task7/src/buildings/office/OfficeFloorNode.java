package buildings.office;

import java.io.Serializable;

import buildings.IFloor;

public class OfficeFloorNode implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OfficeFloorNode prevNode, nextNode;
	private IFloor floor;
	
	public OfficeFloorNode()
	{
		
	}
	
	public OfficeFloorNode(IFloor floor)
	{
		this.floor = floor;
	}
	
	public OfficeFloorNode setOfficeFloor(IFloor floor)
	{
		this.floor = floor;
		return this;
	}
	
	public OfficeFloorNode setNext(OfficeFloorNode next)
	{
		this.nextNode = next;
		return this;
	}
	
	public OfficeFloorNode setPrevious(OfficeFloorNode prev)
	{
		this.prevNode = prev;
		return this;
	}
	
	public IFloor getOfficeFloor()
	{
		return floor;
	}
	
	public OfficeFloorNode getPrevious()
	{
		return prevNode;
	}
	
	public OfficeFloorNode getNext()
	{
		return nextNode;
	}
	
	@Override
	public Object clone()
	{
		OfficeFloorNode res = null;
		try
		{
			res = (OfficeFloorNode) super.clone();
			res.floor = (IFloor) floor.clone(); 
		} catch ( CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return res;
	}
}
