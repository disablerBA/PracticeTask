package buildings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFactory;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFactory;
import buildings.office.OfficeFloor;

public class Buildings {

	static IBuildingFactory buildingFactory = new DwellingFactory();
	static final int DWELLING = 0, OFFICE_BUILDING = 1, HOTEL = 2;
	
	private static byte[] intToByteArray(int arg)
	{
		byte[] array = new byte[4];
		array[0] = (byte)(arg & 0xFF);
		array[1] = (byte)((arg & 0xFF00) >>> 8);
		array[2] = (byte)((arg & 0xFF0000) >>> 16);
		array[3] = (byte)((arg & 0xFF000000) >> 24);
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
				out.write(DWELLING);
				break;
				
			case "buildings.office.OfficeBuilding": 
				out.write(OFFICE_BUILDING);
				break;
			
			case "buildings.dwelling.hotel.Hotel":
				out.write(HOTEL);
				break;
		}
		
		out.write(building.getCountFloor());
		for ( int f = 0; f < building.getCountFloor(); f++)
		{
			switch (building.getFloor(f).getClass().getName())
			{
				case "buildings.dwelling.DwellingFloor":
					out.write(DWELLING);
					break;
					
				case "buildings.office.OfficeFloor":
					out.write(OFFICE_BUILDING);
					break;
					
				case "buildings.dwelling.hotel.HotelFloor":
					out.write(HOTEL);
					out.write( ((HotelFloor)building.getFloor(f)).getStars() );
					break;
			}
			
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
		int typeBuilding = in.read();
		int typeFloor = 0;
		if ( typeBuilding == -1)
		{
			System.out.println("Конец файла ");
			return null;
		}
		int countFloor = in.read();
		int stars = 0 
			,countPlacement
			,countRoom;
		IFloor[] floors = new IFloor[countFloor];
		System.out.println("Кол-во этажей = "+countFloor);
		for ( int f = 0; f < floors.length; f++ )
		{
			typeFloor = in.read();
			System.out.println("Тип этажа = "+typeFloor);
			if ( typeFloor == HOTEL )
			{
				stars = in.read();
				System.out.println("Кол-во звезд = "+stars);
			}
			
			countPlacement = in.read();
			System.out.println("Кол-во помещений на этаже = "+countPlacement);
			switch ( typeFloor )
			{
				case DWELLING:
					buildingFactory = new DwellingFactory();
					floors[f] = buildingFactory.createFloor(countPlacement);
					break;
					
				case OFFICE_BUILDING:
					buildingFactory = new OfficeFactory();
					floors[f] = buildingFactory.createFloor(countPlacement);
					break;
					
				case HOTEL:
					buildingFactory = new HotelFactory();
					floors[f] = buildingFactory.createFloor(countPlacement);
					((HotelFloor)floors[f]).setStars(stars);
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
			case DWELLING: 
				buildingFactory = new DwellingFactory();
				break;
				
			case OFFICE_BUILDING:
				buildingFactory = new OfficeFactory();
				break;
			
			case HOTEL:
				buildingFactory = new HotelFactory();
				break;
		}
		
		return buildingFactory.createBuilding(floors);
	}
	
	public static void writeBuilding (IBuilding building, Writer out) throws IOException
	{
		switch (building.getClass().getName())
		{
			case "buildings.dwelling.Dwelling":
				out.write(DWELLING+" ");
				break;
				
			case "buildings.office.OfficeBuilding":
				out.write(OFFICE_BUILDING+" ");
				break;
				
			case "buildings.dwelling.hotel.Hotel":
				out.write(HOTEL+" ");
				break;
		}
		
		out.write(building.getCountFloor()+" ");
		for ( int f = 0; f < building.getCountFloor(); f++)
		{
			switch (building.getFloor(f).getClass().getName())
			{
				case "buildings.dwelling.DwellingFloor":
					out.write(DWELLING+" ");
					break;
					
				case "buildings.office.OfficeFloor":
					out.write(OFFICE_BUILDING+" ");
					break;
					
				case "buildings.dwelling.hotel.HotelFloor":
					out.write(HOTEL+" ");
					out.write( ((HotelFloor)building.getFloor(f)).getStars()+" " );
					break;
			}

			out.write(building.getFloor(f).getCountPlacement()+" ");
			for ( int p = 0; p < building.getFloor(f).getCountPlacement(); p++)
			{
				out.write(building.getFloor(f).getPlacement(p).getCountRoom()+" ");
				out.write( building.getFloor(f).getPlacement(p).getSpace()+" ");
			}
		}
		
		out.write("\n");
	}
	
	public static IBuilding readBuilding (Reader in) throws IOException
	{
		int countPlacement
			,typeFloor = 0
			,stars = 0;
		StreamTokenizer sT = new StreamTokenizer(in);
		if ( sT.nextToken() == StreamTokenizer.TT_EOF)
		{
			return null;
		}
		
		int typeBuilding = (int)sT.nval;
		sT.nextToken();
		IFloor[] floors = new IFloor[(int)sT.nval];
		System.out.println("Кол-во этажей = "+(int)sT.nval);
		
		for ( int f = 0; f < floors.length; f++ )
		{
			sT.nextToken();
			typeFloor = (int)sT.nval;
			if ( typeFloor == HOTEL )
			{
				sT.nextToken();
				stars = (int)sT.nval;
			}
			
			sT.nextToken();
			countPlacement = (int)sT.nval;
			System.out.println("Кол-во помещений на этаже = "+countPlacement);
			
			switch ( typeFloor )
			{
				case DWELLING:
					buildingFactory = new DwellingFactory();
					floors[f] = buildingFactory.createFloor(countPlacement);
					break;
					
				case OFFICE_BUILDING:
					buildingFactory = new OfficeFactory();
					floors[f] = buildingFactory.createFloor(countPlacement);
					break;
					
				case HOTEL:
					buildingFactory = new HotelFactory();
					floors[f] = buildingFactory.createFloor(countPlacement);
					((HotelFloor)floors[f]).setStars(stars);
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
			case DWELLING: 
				buildingFactory = new DwellingFactory();
				break;
				
			case OFFICE_BUILDING:
				buildingFactory = new OfficeFactory();
				break;
			
			case HOTEL:
				buildingFactory = new HotelFactory();
				break;
		}
		
		return buildingFactory.createBuilding(floors);
		// $???
	}
	
	public static IFloor synchronizedFloor(IFloor floor)
	{
		return new SynchronizedFloor(floor);
	}
	
	public static void setBuildingFactory(IBuildingFactory bf)
	{
		buildingFactory = bf;
	}
	
	public static ISpace createSpace(int area)
	{
		return buildingFactory.createSpace(area);
	}
	
	public static ISpace createSpace(int roomsCount, int area)
	{
		return buildingFactory.createSpace(roomsCount, area);
	}
	
	public static IFloor createFloor(int spacesCount)
	{
		return buildingFactory.createFloor(spacesCount);
	}
	
	public static IFloor createFloor(ISpace[] spaces)
	{
		return buildingFactory.createFloor(spaces);
	}
	
	public static IBuilding createBuilding(int floorsCount, int[] spacesCounts)
	{
		return buildingFactory.createBuilding(floorsCount, spacesCounts);
	}
	
	public static IBuilding createBuilding(IFloor[] floors)
	{
		return buildingFactory.createBuilding(floors);
	}
}
