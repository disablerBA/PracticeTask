package buildings.dwelling;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import buildings.ArrayIterator;
import buildings.IFloor;
import buildings.ISpace;


public class DwellingFloor implements IFloor, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ISpace> m_flats;
	
	public DwellingFloor(int countFlat)
	{
		m_flats = new ArrayList<ISpace>(countFlat);
		for ( int i = 0; i < countFlat; i++ )
		{
			m_flats.add(new Flat());
		}
	}
	
	public DwellingFloor(ISpace... flats)
	{
		m_flats = new ArrayList<ISpace>(flats.length);
		for (int f = 0; f < flats.length; f++)
		{
			m_flats.add(flats[f]);
		}
	}
	
	public DwellingFloor(ArrayList<ISpace> flats)
	{
		m_flats = flats;
	}
	
	@Override
	public Iterator<ISpace> iterator()
	{
		return m_flats.iterator();
	}
	
	public int getCountPlacement()
	{
		return m_flats.size();
	}
	
	public int getTotalSpace()
	{
		int totalSpace = 0;
		for (int i = 0; i < m_flats.size(); i++)
		{
			totalSpace += m_flats.get(i).getSpace();
		}
		return totalSpace;
	}
	
	public int getTotalRoom()
	{
		int totalRoom = 0;
		for (int i = 0; i < m_flats.size(); i++)
		{
			totalRoom += m_flats.get(i).getCountRoom();
		}
		return totalRoom;
	}
	
	public ISpace[] getArrayPlacement()
	{
		return m_flats.toArray(new ISpace[m_flats.size()]);		
	}
	
	public ArrayList<ISpace> getArrayListPlacement()
	{
		return m_flats;
	}
	
	public ISpace getPlacement(int number)
	{
		return m_flats.get(number);
	}
	
	public void changePlacement(int number, ISpace flat)
	{
		m_flats.set(number, flat);
	}
	
	public void addPlacement(int number, ISpace flat)
	{
		m_flats.add(number, flat);
	}
	
	public void remove(int number)
	{
		m_flats.remove(number);
	}
	
	public ISpace getBestSpace()
	{
		int bestSpace = 0;
		int indexRoom = 0;
		
		for (int i = 0; i < m_flats.size(); i++ )
		{
			if ( m_flats.get(i).getSpace() > bestSpace) 
			{
				bestSpace = m_flats.get(i).getSpace();
			}
		}
		
		for (int i = 0; i < m_flats.size(); i++ )
		{
			if ( m_flats.get(i).getSpace() == bestSpace )
			{
				indexRoom = i;
			}
		}
		return m_flats.get(indexRoom);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("DwellingFloor (").append(getCountPlacement()).append(", ");
		for (int i = 0; i < m_flats.size(); i++)
		{
			sb.append(m_flats.get(i).toString()).append(", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if ( !(obj instanceof DwellingFloor) )
		{
			return false;
		}
	
		DwellingFloor df = (DwellingFloor)obj;
		
		if ( df.getCountPlacement() != m_flats.size() )
		{
			return false;
		}
			
		for ( int i =0; i < m_flats.size(); i++)
		{
			if ( !(m_flats.get(i).equals(df.getPlacement(i))) )
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode()
	{
		int res = m_flats.size();
		for ( int i= 0; i < m_flats.size(); i++)
		{
			res ^= m_flats.get(i).hashCode();
		}
		return res;
	}
	
	@Override
	public Object clone()
	{
		DwellingFloor res = null;
		try 
		{
			res = (DwellingFloor)super.clone();
			res.m_flats = (ArrayList<ISpace>)this.m_flats.clone();
			for ( int f = 0; f < m_flats.size(); f++)
			{
				res.m_flats.set(f, (ISpace)m_flats.get(f).clone());
			}
			//res.flats = this.flats.clone();
				
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	//получение номера квартиры на этаже по ссылке
	@Override
	public int getPlacementNumberBySpace(ISpace placement)
	{
		for (int i = 0; i<m_flats.size(); i++)
		{
			if ( m_flats.get(i) == placement)
			{
				return i;
			}
		}
		return -1;
	}
}
