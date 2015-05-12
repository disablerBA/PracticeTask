package buildings.threads;

import buildings.IFloor;

public class Repairer extends Thread {

	private IFloor m_floor = null; 
	
	public Repairer()
	{
		
	}
	
	public Repairer(IFloor floor)
	{
		m_floor = floor;
	}
	
	@Override
	public void run()
	{

		for (int f = 0; f < m_floor.getCountPlacement(); f++)
		{
			if (!isInterrupted())
			{
				System.out.println("Repairing space number "+f+" with total area "+m_floor.getPlacement(f).getSpace()+" square meters");
				/*try {
					sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				if (f == 50)
				{
					interrupt();
				}
			}
		}
	}
	
	public void setFloor(IFloor floor)
	{
		m_floor = floor;
	}
}
