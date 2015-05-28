package buildings;

import java.util.Iterator;

public class SynchronizedFloor implements IFloor {
	
	private IFloor m_floor;
	
	public SynchronizedFloor(IFloor floor)
	{
		m_floor = floor;
	}
	
	synchronized public int getCountPlacement()
	{
		return m_floor.getCountPlacement();
	}

	synchronized public int getTotalSpace()
	{
		return m_floor.getTotalSpace();
	}

	synchronized public int getTotalRoom()
	{
		return m_floor.getTotalRoom();
	}

	synchronized public ISpace[] getArrayPlacement()
	{
		return m_floor.getArrayPlacement();
	}

	synchronized public ISpace getPlacement(int index)
	{
		return m_floor.getPlacement(index);
	}

	synchronized public void changePlacement(int index, ISpace off)
	{
		m_floor.changePlacement(index, off);
	}

	synchronized public ISpace getBestSpace()
	{
		return m_floor.getBestSpace();
	}

	synchronized public void addPlacement(int index, ISpace office)
	{
		m_floor.addPlacement(index, office);
	}

	synchronized public void remove(int index)
	{
		m_floor.remove(index);
	}

	synchronized public Object clone()
	{
		return m_floor.clone();
	}
	
	synchronized public int getPlacementNumberBySpace(ISpace placement)
	{
		return m_floor.getPlacementNumberBySpace(placement);
	}
	
	@Override
	public Iterator<ISpace> iterator()
	{
		
		
		return null;
	}
}
