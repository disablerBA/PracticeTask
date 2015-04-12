package buildings;


public class OfficeNode {
	
	private ISpace office;
	private OfficeNode next;
	
	public OfficeNode()
	{
		
	}
	
	public OfficeNode(ISpace office)
	{
		this.office = office;
	}
	
	public OfficeNode getNext()
	{
		return next;
	}
	
	public ISpace getOffice()
	{
		return office;
	}
	
	public OfficeNode setOffice(ISpace of)
	{
		office = of;
		return this;
	}
	
	public OfficeNode setNext(OfficeNode node)
	{
		next = node;
		return this;
	}
}
