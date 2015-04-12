package buildings;

public class OfficeFloor implements IFloor {

	private ListOffice offices;
	
	public OfficeFloor(int countOffice)
	{
		offices = new ListOffice();
		for (int i = 0; i < countOffice; i++ )
		{
			offices.add(new Office());
		}
	}
	
	public OfficeFloor(ISpace[] offs)
	{
		for ( int i = 0; i < offs.length; i++)
		{
			offices.add(offs[i]);
		}
	}
	
	@Override
	public int getCountPlacement()
	{
		return offices.size();
	}
	
	@Override
	public int getTotalSpace()
	{
		int total = 0;
		for ( int i = 0; i < offices.size(); i++)
		{
			total += offices.getOffice(i).getSpace();
		}
		return total;
	}
	
	@Override
	public int getTotalRoom()
	{
		int count = 0;
		for ( int i = 0; i < offices.size(); i++)
		{
			count += offices.getOffice(i).getCountRoom();
		}
		return count;
	}
	
	@Override
	public ISpace[] getArrayPlacement()
	{
		ISpace[] offs = new ISpace[offices.size()];
		for (int i = 0; i < offices.size(); i++)
		{
			offs[i] = offices.getOffice(i);
		}
		return offs;
	}
	
	@Override
	public ISpace getPlacement(int index)
	{
		if( index >= offices.size() )
		{
			throw new SpaceIndexOutOfBoundsException(index, offices.size() );
		}
		
		return offices.getOffice(index);
	}
	
	@Override
	public void changePlacement(int index, ISpace off)
	{
		offices.setOffice(index, off);
	}
	
	@Override
	public ISpace getBestSpace()
	{
		int bestSpace = 0;
		int indexOffice = 0;
		
		for (int i = 0; i < offices.size(); i++ )
		{
			if ( offices.getOffice(i).getSpace() > bestSpace) 
			{
				bestSpace = offices.getOffice(i).getSpace();
			}
		}
		
		for (int i = 0; i < offices.size(); i++ )
		{
			if ( offices.getOffice(i).getSpace() == bestSpace )
			{
				indexOffice = i;
			}
		}
		return offices.getOffice(indexOffice) ;
	}
	
	@Override
	public void addPlacement(int index, ISpace office)
	{
		offices.add(index, office);
	}
	
	@Override
	public void remove(int index)
	{
		offices.remove(index);
	}
	
} 