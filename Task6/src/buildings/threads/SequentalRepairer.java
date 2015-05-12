package buildings.threads;

import buildings.IFloor;

public class SequentalRepairer implements Runnable {

	private IFloor m_floor = null; 
	private Semaphor m_semaphor;
	
	public SequentalRepairer()
	{
		
	}
	
	public SequentalRepairer(Semaphor sem, IFloor floor)
	{
		m_semaphor = sem;
		m_floor = floor;
	}
	
	@Override
	public void run()
	{	
		for (int f = 0; f < m_floor.getCountPlacement(); f++)
		{
			System.out.println("Repairing space number "+f+" with total area "+m_floor.getPlacement(f).getSpace()+" square meters");
			m_semaphor.take();
			/*try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	
	public void setFloor(IFloor floor)
	{
		m_floor = floor;
	}

}
