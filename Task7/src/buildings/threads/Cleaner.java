package buildings.threads;

import buildings.IFloor;

public class Cleaner extends Thread {

	private IFloor m_floor = null;

	public Cleaner()
	{
		
	}
	
	public Cleaner(IFloor floor)
	{
		m_floor = floor;
	}
	
	@Override
	public void run()
	{
		
			
			
		for (int f = 0; f < m_floor.getCountPlacement(); f++)
		{
			
				
				System.out.println("Cleaning room number "+f+" with total area "+m_floor.getPlacement(f).getSpace()+" square meters");
				/*try {
					sleep(3);
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
