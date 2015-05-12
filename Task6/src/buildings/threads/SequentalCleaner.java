package buildings.threads;
import buildings.IFloor;

public class SequentalCleaner implements Runnable {

	private IFloor m_floor = null;
	private Semaphor m_semaphor;

	public SequentalCleaner()
	{
		
	}
	
	public SequentalCleaner(Semaphor sem, IFloor floor)
	{
		m_floor = floor;
		m_semaphor = sem;
	}
	
	@Override
	public void run()
	{
		for (int f = 0; f < m_floor.getCountPlacement(); f++)
		{
			m_semaphor.release();
			System.out.println("Cleaning room number "+f+" with total area "+m_floor.getPlacement(f).getSpace()+" square meters");
		}
	}
	
	public void setFloor(IFloor floor)
	{
		m_floor = floor;
	}
}


