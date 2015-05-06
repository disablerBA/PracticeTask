package buildings.office;

import buildings.ISpace;


public class ListOffice {
	
	private OfficeNode headNode;
	
	public void add(ISpace office)
	{
		if ( headNode == null)
		{
			headNode = new OfficeNode();
			headNode.setOffice(office);
			headNode.setNext(headNode);
			return;
		}
	
		OfficeNode node = headNode.getNext();
		while ( node.getNext() != headNode )
		{
			node = node.getNext();
		}
		
		node.setNext(new OfficeNode().setOffice(office));
		node.getNext().setNext(headNode);

	}
	
	public void add(int index, ISpace office)
	{
		if ( index == size())
		{
			add(office);
			return;
		}
		
		OfficeNode node = headNode;
		OfficeNode newNode = new OfficeNode(office);
		OfficeNode prevNode = null;
		
		if ( index == 0 )
		{
			prevNode = getNode(size()-1);
			prevNode.setNext(newNode);
			newNode.setNext(headNode);
			headNode = newNode;
		} else 
		{
			prevNode = getNode(index-1);
			node = getNode(index);
			prevNode.setNext(newNode);
			newNode.setNext(node);
		}
	}
	
	public int size()
	{
		int size = 0;
		OfficeNode node = headNode;
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
	
	public ISpace getOffice(int index)
	{
		return getNode(index).getOffice();
	}
	
	public void remove(int index)
	{
		OfficeNode node;
		OfficeNode prevNode;
		if ( index == 0)
		{
			prevNode = getNode(size()-1);
			prevNode.setNext(headNode.getNext());
			headNode = headNode.getNext();
			return;
		}
		node = getNode(index);
		prevNode = getNode(index-1);
		prevNode.setNext(node.getNext());
	}
	
	private OfficeNode getNode(int index)
	{
		if ( index >= size())
		{
			return null;
		}
		
		OfficeNode node = headNode;
		node = headNode;
		for ( int i = 0; i < index; i++)
		{
			node = node.getNext();
		}
	
		return node;
	}
	
	public void setOffice(int index, ISpace office)
	{
		getNode(index).setOffice(office);
	}
}
