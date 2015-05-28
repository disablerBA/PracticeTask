package buildings.office;

import java.io.Serializable;

import buildings.IFloor;
import buildings.ISpace;
import buildings.SpaceIndexOutOfBoundsException;
import buildings.dwelling.DwellingFloor;

public class OfficeFloor implements IFloor, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListOffice offices;
	
	public OfficeFloor(int countOffice)
	{
		offices = new ListOffice();
		for (int i = 0; i < countOffice; i++ )
		{
			offices.add(new Office());
		}
	}
	
	public OfficeFloor(ISpace[] offs)
	{
		for ( int i = 0; i < offs.length; i++)
		{
			offices.add(offs[i]);
		}
	}
	
	@Override
	public int getCountPlacement()
	{
		return offices.size();
	}
	
	@Override
	public int getTotalSpace()
	{
		int total = 0;
		for ( int i = 0; i < offices.size(); i++)
		{
			total += offices.getOffice(i).getSpace();
		}
		return total;
	}
	
	@Override
	public int getTotalRoom()
	{
		int count = 0;
		for ( int i = 0; i < offices.size(); i++)
		{
			count += offices.getOffice(i).getCountRoom();
		}
		return count;
	}
	
	@Override
	public ISpace[] getArrayPlacement()
	{
		ISpace[] offs = new ISpace[offices.size()];
		for (int i = 0; i < offices.size(); i++)
		{
			offs[i] = offices.getOffice(i);
		}
		return offs;
	}
	
	@Override
	public ISpace getPlacement(int index)
	{
		if( index >= offices.size() )
		{
			throw new SpaceIndexOutOfBoundsException(index, offices.size() );
		}
		
		return offices.getOffice(index);
	}
	
	@Override
	public void changePlacement(int index, ISpace off)
	{
		offices.setOffice(index, off);
	}
	
	@Override
	public ISpace getBestSpace()
	{
		int bestSpace = 0;
		int indexOffice = 0;
		
		for (int i = 0; i < offices.size(); i++ )
		{
			if ( offices.getOffice(i).getSpace() > bestSpace) 
			{
				bestSpace = offices.getOffice(i).getSpace();
			}
		}
		
		for (int i = 0; i < offices.size(); i++ )
		{
			if ( offices.getOffice(i).getSpace() == bestSpace )
			{
				indexOffice = i;
			}
		}
		return offices.getOffice(indexOffice) ;
	}
	
	@Override
	public void addPlacement(int index, ISpace office)
	{
		offices.add(index, office);
	}
	
	@Override
	public void remove(int index)
	{
		offices.remove(index);
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("OfficeFloor (").append(getCountPlacement()).append(", ");
		for (int i = 0; i < offices.size(); i++)
		{
			sb.append(offices.getOffice(i).toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof OfficeFloor) )
		{
			System.out.println("Объекты разного типа");
			return false;
		}
	
		OfficeFloor of = (OfficeFloor)obj;
		
		if ( of.getCountPlacement() != offices.size() )
		{
			return false;
		}
		
		for ( int i =0; i < offices.size(); i++)
		{
			if ( !(offices.getOffice(i).equals(of.getPlacement(i))) )
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int res = offices.size();
		for ( int i= 0; i < offices.size(); i++)
		{
			res ^= offices.getOffice(i).hashCode();
		}
		return res;
	}
	
	@Override
	public Object clone()
	{
		OfficeFloor res = null;
		try 
		{
			res = (OfficeFloor)super.clone();
			res.offices = (ListOffice) offices.clone();
				
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int getPlacementNumberBySpace(ISpace placement)
	{
		for (int i = 0; i<offices.size(); i++)
		{
			if ( offices.getOffice(i) == placement)
			{
				return i;
			}
		}
		return -1;
	}
	
	private class ListOffice implements Cloneable, Serializable {
		
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
		
		
		private class OfficeNode implements Cloneable, Serializable {
			
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 */
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
			
			@Override
			public Object clone()
			{

				OfficeNode res = null;
				try {
					res = (OfficeNode)super.clone();
					res.office = (ISpace) office.clone();

					
				} catch ( CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res;
			}
		}
	}
	
	
} 

