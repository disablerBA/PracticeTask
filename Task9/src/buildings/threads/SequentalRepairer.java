package buildings.threads;

import buildings.IFloor;

public class SequentalRepairer implements Runnable {

	private IFloor m_floor = null; 
	private SwitchingSemaphor m_semaphor;
	
	public SequentalRepairer()
	{
		
	}
	
	public SequentalRepairer(SwitchingSemaphor sem, IFloor floor)
	{
		m_semaphor = sem;
		m_floor = floor;
	}
	
	@Override
	public void run()
	{	
		for (int f = 0; f < m_floor.getCountPlacement(); f++)
		{
			m_semaphor.take();
			System.out.println("Repairing space number "+f+" with total area "+m_floor.getPlacement(f).getSpace()+" square meters");
			m_semaphor.switchThread();
		}
		m_semaphor.release();
	}
	
	public void setFloor(IFloor floor)
	{
		m_floor = floor;
	}

}
