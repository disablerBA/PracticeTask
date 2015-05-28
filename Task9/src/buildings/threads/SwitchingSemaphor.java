package buildings.threads;

public class SwitchingSemaphor {

	private volatile boolean taken = false;
	private int count;
	
	public SwitchingSemaphor()
	{
		
	}
	
	/** «адает число итераций. 
	 * Ёто число необходимо дл€ того, 
	 * чтобы разбудить ожидающие потоки **/
	public void init(int num)
	{
		count = num;
	}
	
	synchronized public void take()
	{
		//count--;
		/*while ( taken )
		{
			try 
			{
				this.notify();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		taken = true;
		/*if (count <= 0)
		{
			this.notify();
		}*/
		
		while ( taken )
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		taken = true;
	}
	
	synchronized public void switchThread()
	{
		taken = false;
		this.notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*while (!taken)
		{
			try 
			{	
				this.notify();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		taken = false;
		/*if ( count <= 0)
		{
			this.notify();
		}*/
	}
	
	synchronized public void release()
	{
		taken = false;
		this.notify();
	}
}
