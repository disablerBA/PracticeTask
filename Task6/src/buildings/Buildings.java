package buildings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.hotel.Hotel;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class Buildings {

	private static byte[] intToByteArray(int arg)
	{
		byte[] array = new byte[4];
		array[0] = (byte)(arg & 0xFF);
		array[1] = (byte)((arg & 0xFF00) >>> 8);
		array[2] = (byte)((arg & 0xFF0000) >>> 16);
		array[3] = (byte)((arg & 0xFF000000) >> 24);
		//System.out.println(array[3]+" "+array[2]+" "+array[1]+" "+array[0]);
		return array;
	}
	
	private static int byteArrayToInt(byte[] arg)
	{
		int p = 0,
			pr = 0;;
		for ( int i = 0; i < arg.length; i++ )
		{
			p = arg[i] << (i*8);
			if ( p < 0 )
			{
				p  = ( p << (24 - i*8 ) ) >>> (24 - i*8);
			}
			pr |= p;
		}
		return pr;
	}
	
	public static void outputBuilding (IBuilding building, OutputStream out) throws IOException
	{
		switch (building.getClass().getName())
		{
			case "buildings.dwelling.Dwelling": 
				out.write(0);
				break;
				
			case "buildings.office.OfficeBuilding": 
				out.write(1);
				break;
			
			case "buildings.dwelling.hotel.Hotel":
				out.write(2);
				break;
		}
		
		
		out.write(building.getCountFloor());
		for ( int f = 0; f < building.getCountFloor(); f++)
		{
			out.write(building.getFloor(f).getCountPlacement());
			for ( int p = 0; p < building.getFloor(f).getCountPlacement(); p++)
			{
				out.write(building.getFloor(f).getPlacement(p).getCountRoom());
				out.write( intToByteArray(building.getFloor(f).getPlacement(p).getSpace()) );
			}
		}
	}
	
	public static IBuilding inputBuilding (InputStream in) throws IOException
	{
		IBuilding building = null;
		int typeBuilding = in.read();
		int countFloor = in.read();
		int countPlacement;
		int countRoom;
		IFloor[] floors = new IFloor[countFloor];
		System.out.println("Кол-во этажей = "+countFloor);
		for ( int f = 0; f < floors.length; f++ )
		{
			countPlacement = in.read();
			System.out.println("Кол-во помещений на этаже = "+countPlacement);
			switch ( typeBuilding )
			{
				case 0: 
					floors[f] = new DwellingFloor(countPlacement);
					break;
				case 1:
					floors[f] = new OfficeFloor(countPlacement);
					break;
			}

			for ( int p = 0; p < countPlacement; p++)
			{
				countRoom = in.read();
				System.out.println("Кол-во комнат в помещении = "+countRoom);
				floors[f].getPlacement(p).setCountRoom(countRoom);
				byte[] byteArray = {(byte)in.read(),(byte)in.read(),(byte)in.read(),(byte)in.read()};
				System.out.println("Площадь помещения = "+byteArrayToInt(byteArray));
				floors[f].getPlacement(p).setSpace(byteArrayToInt(byteArray));
			}
		}
		
		switch ( typeBuilding )
		{
			case 0: 
				building = new Dwelling(floors);
				break;
				
			case 1:
				building = new OfficeBuilding(floors);
				break;
			
			case 2:
				building = new Hotel(floors);
				break;
		}
		
		return building;
	}
	
	public static void writeBuilding (IBuilding building, Writer out) throws IOException
	{
		switch (building.getClass().getName())
		{
			case "buildings.dwelling.Dwelling": 
				out.write(0+" ");
				break;
				
			case "buildings.office.OfficeBuilding": 
				out.write(1+" ");
				break;
				
			case "buildings.dwelling.hotel.Hotel":
				out.write(2+" ");
				break;
		}
		
		out.write(building.getCountFloor()+" ");
		for ( int f = 0; f < building.getCountFloor(); f++)
		{
			out.write(building.getFloor(f).getCountPlacement()+" ");
			for ( int p = 0; p < building.getFloor(f).getCountPlacement(); p++)
			{
				out.write(building.getFloor(f).getPlacement(p).getCountRoom()+" ");
				out.write( building.getFloor(f).getPlacement(p).getSpace()+" ");
			}
		}
	}
	
	public static IBuilding readBuilding (Reader in) throws IOException
	{
		IBuilding building = null;
		int countPlacement;
		StreamTokenizer sT = new StreamTokenizer(in);
		sT.nextToken();
		int typeBuilding = (int)sT.nval;
		sT.nextToken();
		System.out.println("Кол-во этажей = "+(int)sT.nval);
		IFloor[] floors = new IFloor[(int)sT.nval];
		
		for ( int f = 0; f < floors.length; f++ )
		{
			sT.nextToken();
			countPlacement = (int)sT.nval;
			System.out.println("Кол-во помещений на этаже = "+countPlacement);
			
			switch ( typeBuilding )
			{
				case 0: 
					floors[f] = new DwellingFloor(countPlacement);
					break;
				case 1:
					floors[f] = new OfficeFloor(countPlacement);
					break;
			}
			
			for ( int p = 0; p < countPlacement; p++)
			{
				sT.nextToken();
				System.out.println("Кол-во комнат в помещении = "+sT.nval);
				floors[f].getPlacement(p).setCountRoom((int)sT.nval);
				sT.nextToken();
				System.out.println("Площадь помещения = "+sT.nval);
				floors[f].getPlacement(p).setSpace((int)sT.nval);
			}
		}
		
		// ^???
		switch ( typeBuilding )
		{
			case 0: 
				building = new Dwelling(floors);
				break;
				
			case 1:
				building = new OfficeBuilding(floors);
				break;
				
			case 2:
				building = new Hotel(floors);
				break;
		}
		
		return building;
		// $???
	}
	
	public static IFloor synchronizedFloor(IFloor floor)
	{
		return new SynchronizedFloor(floor);
	}
}
