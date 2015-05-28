package buildings.threads;

public class SwitchingSemaphor {

	private volatile boolean repaired = false;
	
	public SwitchingSemaphor()
	{
		
	}
		
	synchronized public void startRepair()
	{
		while ( repaired )
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	synchronized public void startClean()
	{
		while ( !repaired )
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	synchronized public void endRepair()
	{
		repaired = true;
		notify();
	}
	
	synchronized public void endClean()
	{
		repaired = false;
		notify();
	}
}
