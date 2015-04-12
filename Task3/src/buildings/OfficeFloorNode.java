package buildings;

public class OfficeFloorNode {

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
}
