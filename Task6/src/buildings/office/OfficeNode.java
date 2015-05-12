package buildings.office;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import buildings.ISpace;


public class OfficeNode implements Cloneable {
	
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
