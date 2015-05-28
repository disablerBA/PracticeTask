package buildings.office;

import java.io.Serializable;

import buildings.ISpace;


public class ListOffice implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		//System.out.println("HEADNODE "+node);
		
		if ( node != null)
		{
			size++;
			while ( (node = node.getNext()) != headNode  )
			{
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("NODE     "+node);
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
			if (size() == 1)
			{
				headNode = null;
				return;
			}
			prevNode = getNode(size()-1);
			prevNode.setNext(headNode.getNext());
			headNode = headNode.getNext();
			return;
		}
		node = getNode(index);
		prevNode = getNode(index-1);
		prevNode.setNext(node.getNext());
	}
	
	public void removeAll()
	{
		headNode = null;
	}
	
	private OfficeNode getNode(int index)
	{
		if ( index >= size())
		{
			return null;
		}
		
		OfficeNode node = headNode;
		//node = headNode;
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
	
	private void add(OfficeNode newNode)
	{
		if ( headNode == null )
		{
			headNode = newNode;
			headNode.setNext(headNode);
			return;
		}
		
		OfficeNode node = headNode.getNext();
		
		while ( node.getNext() != headNode )
		{
			node = node.getNext();
		}
		
		node.setNext(newNode);
		newNode.setNext(headNode);
	}
	
	@Override
	public Object clone()
	{
		ListOffice res = null;
		try
		{
			res = (ListOffice) super.clone();
			res.headNode = null; 
			//System.out.println(res.size());
			//System.out.println(this.size());
			
			System.out.println("this "+this);
			System.out.println("res  "+res);
			
			
			//res.headNode = (OfficeNode) headNode.clone();
			if ( this == res)
			{
				System.out.println("ссылки одинаковые");
			}
			//System.out.println(res.size());
			
			for ( int i = 0; i< this.size(); i++)
			{
				res.add( (OfficeNode)this.getNode(i).clone() );
			}
		} catch ( CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
