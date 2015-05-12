package buildings.threads;

public class Semaphor {

	private boolean taken = false;
	private int count;
	
	public Semaphor()
	{
		
	}
	
	public void init()
	{
		
	}
	
	synchronized public void take()
	{
		taken = true;
		this.notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	synchronized public void release()
	{
		while (!taken)
		{
			try 
			{
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		taken = false;
		this.notify();
	}
	
	
}
