package buildings;

public class ListOfficeFloor {
	
	private OfficeFloorNode headNode;
	
	public void add(IFloor officeFloor)
	{
		if ( headNode == null)
		{
			headNode = new OfficeFloorNode();
			headNode.setOfficeFloor(officeFloor);
			headNode.setNext(headNode);
			headNode.setPrevious(headNode);
			return;
		}
	
		OfficeFloorNode node = headNode.getNext();
		while ( node.getNext() != headNode )
		{
			node = node.getNext();
		}
		
		OfficeFloorNode newNode = new OfficeFloorNode();
		node.setNext(newNode.setOfficeFloor(officeFloor));
		newNode.setPrevious(node);
		newNode.setNext(headNode);
		headNode.setPrevious(newNode);
	}
	
	public void add(int index, IFloor floor)
	{
		if ( index == size())
		{
			add(floor);
			return;
		}
		
		OfficeFloorNode node = headNode;
		OfficeFloorNode newNode = new OfficeFloorNode(floor);
		OfficeFloorNode prevNode = null;
		
		if ( index == 0 )
		{
			prevNode = headNode.getPrevious();
			prevNode.setNext(newNode);
			newNode.setPrevious(prevNode);
			newNode.setNext(headNode);
			headNode.setPrevious(newNode);
			headNode = newNode;
		} else 
		{
			node = getNode(index);
			prevNode = node.getPrevious();
			prevNode.setNext(newNode);
			newNode.setPrevious(prevNode);
			newNode.setNext(node);
			node.setPrevious(newNode);
		}
	}
	
	public int size()
	{
		int size = 0;
		OfficeFloorNode node = headNode;
		if ( node != null)
		{
			size++;
			while ( (node = node.getNext()) != headNode  )
			{
				size++;
			}
		}
		
		return size;
	}
	
	public IFloor getOfficeFloor(int index)
	{
		return getNode(index).getOfficeFloor();
	}
	
	public void remove(int index)
	{
		OfficeFloorNode node = getNode(index);
		OfficeFloorNode prevNode = node.getPrevious();
		if ( index == 0)
		{
			prevNode.setNext(headNode.getNext());
			headNode.getNext().setPrevious(prevNode);
			headNode = headNode.getNext();
			return;
		}
		prevNode.setNext(node.getNext());
		node.getNext().setPrevious(prevNode);
	}
	
	private OfficeFloorNode getNode(int index)
	{
		if ( index >= size())
		{
			return null;
		}
		
		OfficeFloorNode node = headNode;
		node = headNode;
		for ( int i = 0; i < index; i++)
		{
			node = node.getNext();
		}
	
		return node;
	}
	
	public void setOfficeFloor(int index, IFloor floor)
	{
		getNode(index).setOfficeFloor(floor);
	}
}
