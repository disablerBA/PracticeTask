package buildings.threads;
import buildings.IFloor;

public class SequentalCleaner implements Runnable {

	private IFloor m_floor = null;
	private SwitchingSemaphor m_semaphor;

	public SequentalCleaner()
	{
		
	}
	
	public SequentalCleaner(SwitchingSemaphor sem, IFloor floor)
	{
		m_floor = floor;
		m_semaphor = sem;
	}
	
	@Override
	public void run()
	{
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int f = 0; f < m_floor.getCountPlacement(); f++)
		{
			m_semaphor.startClean();
			System.out.println("Cleaning room number "+f+" with total area "+m_floor.getPlacement(f).getSpace()+" square meters");
			m_semaphor.endClean();
		}
		//m_semaphor.release();
	}
	
	public void setFloor(IFloor floor)
	{
		m_floor = floor;
	}
}


