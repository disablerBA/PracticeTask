package buildings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;
import java.lang.reflect.*;
import java.util.Formatter;
import java.util.Scanner;

import buildings.dwelling.DwellingFactory;
import buildings.dwelling.hotel.HotelFactory;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.OfficeFactory;


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
	
	public static <B, F, S> IBuilding inputBuilding ( InputStream in
													, Class<B> buildingClass
													, Class<F> floorClass
													, Class<S> spaceClass ) throws	IOException
																				,	NoSuchMethodException
																				,	SecurityException
																				,	InstantiationException
																				,	IllegalAccessException
																				,	IllegalArgumentException
																				,	InvocationTargetException
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
			if ( floorClass == HotelFloor.class )
			{
				stars = in.read();
				System.out.println("Кол-во звезд = "+stars);
				countPlacement = in.read();
				System.out.println("Кол-во помещений на этаже = "+countPlacement);
				floors[f] = Buildings.createFloor(floorClass, countPlacement);
				((HotelFloor)floors[f]).setStars(stars);
			} else
			{
				countPlacement = in.read();
				System.out.println("Кол-во помещений на этаже = "+countPlacement);
				floors[f] = Buildings.createFloor(floorClass, countPlacement);
			}

			for ( int p = 0; p < countPlacement; p++)
			{
				countRoom = in.read();
				System.out.println("Кол-во комнат в помещении = "+countRoom);
				floors[f].getPlacement(p).setCountRoom(countRoom);
				byte[] byteArray = {(byte)in.read(),(byte)in.read(),(byte)in.read(),(byte)in.read()};
				System.out.println("Площадь помещения = "+byteArrayToInt(byteArray));
				floors[f].changePlacement(p, Buildings.createSpace(countRoom, byteArrayToInt(byteArray), spaceClass ));
			}
		}

		return Buildings.createBuilding(buildingClass, floors);
	}
	
	public static void writeSheet(Writer out) throws IOException
	{
		Formatter fmt = new Formatter(out);
		fmt.format("%d %d %d %s", 1, 2, 3, "ypa!");
		fmt.format("%s   %d", "new ypa!", 22);
		fmt.flush();
		fmt.close();
	}
	
	public static void writeBuilding (IBuilding building, Writer out) throws IOException
	{
		Formatter fmt = new Formatter(out);
		switch (building.getClass().getName())
		{
			case "buildings.dwelling.Dwelling":
				fmt.format("%d ", DWELLING);
				break;
				
			case "buildings.office.OfficeBuilding":
				fmt.format("%d ", OFFICE_BUILDING);
				break;
				
			case "buildings.dwelling.hotel.Hotel":
				fmt.format("%d ", HOTEL);
				break;
		}
		
		fmt.format("%d ", building.getCountFloor());
		for ( int f = 0; f < building.getCountFloor(); f++)
		{
			switch (building.getFloor(f).getClass().getName())
			{
				case "buildings.dwelling.DwellingFloor":
					fmt.format("%d ", DWELLING);
					break;
					
				case "buildings.office.OfficeFloor":
					fmt.format("%d ", OFFICE_BUILDING);
					break;
					
				case "buildings.dwelling.hotel.HotelFloor":
					fmt.format("%d ", HOTEL);
					fmt.format("%d ", ((HotelFloor)building.getFloor(f)).getStars());
					break;
			}

			fmt.format("%d ", building.getFloor(f).getCountPlacement());
			for ( int p = 0; p < building.getFloor(f).getCountPlacement(); p++)
			{
				fmt.format("%d ", building.getFloor(f).getPlacement(p).getCountRoom() );
				fmt.format("%d ", building.getFloor(f).getPlacement(p).getSpace() );
			}
		}
		
		fmt.format("%n");
		fmt.flush();
		//fmt.close();
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
	
	public static IBuilding readBuilding ( Scanner scanner) throws IOException
	{
		int countPlacement
			,typeFloor = 0
			,stars = 0;
		
		if ( !scanner.hasNext())
		{
			return null;
		}
		
		int typeBuilding = scanner.nextInt();
		IFloor[] floors = new IFloor[scanner.nextInt()];
		System.out.println("Кол-во этажей = "+floors.length);
		
		for ( int f = 0; f < floors.length; f++ )
		{
			typeFloor = scanner.nextInt();
			if ( typeFloor == HOTEL )
			{
				stars = scanner.nextInt();
			}
			
			countPlacement = scanner.nextInt();
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
				floors[f].getPlacement(p).setCountRoom(scanner.nextInt());
				System.out.println("Кол-во комнат в помещении = "+floors[f].getPlacement(p).getCountRoom());
				floors[f].getPlacement(p).setSpace(scanner.nextInt());
				System.out.println("Площадь помещения = "+floors[f].getPlacement(p).getSpace());
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
	
	public static <B, F, S> IBuilding readBuilding ( 	Reader in
													, 	Class<B> buildingClass
													, 	Class<F> floorClass
													,	Class<S> spaceClass ) throws	IOException,
																						NoSuchMethodException,
																						SecurityException,
																						InstantiationException,
																						IllegalAccessException,
																						IllegalArgumentException,
																						InvocationTargetException
	{
		int countPlacement;
		StreamTokenizer sT = new StreamTokenizer(in);
		if ( sT.nextToken() == StreamTokenizer.TT_EOF)
		{
			return null;
		}
		
		sT.nextToken();
		IFloor[] floors = new IFloor[(int)sT.nval];
		System.out.println("Кол-во этажей = "+(int)sT.nval);
		
		for ( int f = 0; f < floors.length; f++ )
		{
			sT.nextToken();
			if ( floorClass == HotelFloor.class )
			{
				sT.nextToken();
				sT.nextToken();
				countPlacement = (int)sT.nval;
				System.out.println("Кол-во помещений на этаже = "+countPlacement);
				floors[f] = Buildings.createFloor(HotelFloor.class, countPlacement);
			} else
			{
				sT.nextToken();
				countPlacement = (int)sT.nval;
				System.out.println("Кол-во помещений на этаже = "+countPlacement);
				floors[f] = Buildings.createFloor(floorClass, countPlacement);
			}
			
			int space = 0;
			int countRoom = 0;
			for ( int p = 0; p < countPlacement; p++)
			{
				sT.nextToken();
				System.out.println("Кол-во комнат в помещении = "+sT.nval);
				countRoom = ((int)sT.nval);
				sT.nextToken();
				System.out.println("Площадь помещения = "+sT.nval);
				space = (int)sT.nval;
				floors[f].changePlacement(p, Buildings.createSpace(countRoom, space, spaceClass));
			}
		}
		
		// ^???
		return Buildings.createBuilding(buildingClass, floors );
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
	
	public static <S> ISpace createSpace(int area, Class<S> spaceClass) throws NoSuchMethodException
																	,	SecurityException
																	,	InstantiationException
																	,	IllegalAccessException
																	,	IllegalArgumentException
																	,	InvocationTargetException
	{
		ISpace res = null;
		Constructor<S> constr = spaceClass.getConstructor(Integer.TYPE);
		res = (ISpace)constr.newInstance(area);
		return res;
	}
	
	public static <S> ISpace createSpace(int roomsCount, int area, Class<S> spaceClass) throws	NoSuchMethodException
																					,	SecurityException
																					,	InstantiationException
																					,	IllegalAccessException
																					,	IllegalArgumentException
																					,	InvocationTargetException
	{
		ISpace res = null;
		Constructor<S> constr = spaceClass.getConstructor(Integer.TYPE, Integer.TYPE);
		res = (ISpace)constr.newInstance(roomsCount, area );
		return res;
		
	}
	
	public static IFloor createFloor(int spacesCount)
	{
		return buildingFactory.createFloor(spacesCount);
	}
	
	public static IFloor createFloor(ISpace... spaces)
	{
		return buildingFactory.createFloor(spaces);
	}
	
	public static <F> IFloor createFloor(Class<F> floorClass, int spacesCount) throws 	NoSuchMethodException
																			,	SecurityException
																			,	InstantiationException
																			,	IllegalAccessException
																			,	IllegalArgumentException
																			,	InvocationTargetException
	{
		IFloor res = null;
		Constructor<F> constr = floorClass.getConstructor(Integer.TYPE);
		res = (IFloor)constr.newInstance(spacesCount);
		return res;
	}
	
	public static <F> IFloor createFloor(Class<F> floorClass, ISpace... spaces) throws 	NoSuchMethodException
																					,	SecurityException
																					,	InstantiationException
																					,	IllegalAccessException
																					,	IllegalArgumentException
																					,	InvocationTargetException
	{
		IFloor res = null;
		Constructor<F> constr = floorClass.getConstructor(ISpace[].class);
		res = (IFloor)constr.newInstance((Object)spaces);
		return res;
	}
	
	public static IBuilding createBuilding(int floorsCount, int... spacesCounts)
	{
		return buildingFactory.createBuilding(floorsCount, spacesCounts);
	}
	
	public static IBuilding createBuilding(IFloor... floors)
	{
		return buildingFactory.createBuilding(floors);
	}
	
	public static <B> IBuilding createBuilding(Class<B> buildingClass, int floorsCount, int... spacesCounts) throws	NoSuchMethodException
																										,	SecurityException
																										,	InstantiationException
																										,	IllegalAccessException
																										,	IllegalArgumentException
																										,	InvocationTargetException
	{
		IBuilding res = null;
		Constructor<B> constr = buildingClass.getConstructor(Integer.TYPE, int[].class);
		res = (IBuilding)constr.newInstance(floorsCount, spacesCounts);
		return res;
	}
	
	public static <B> IBuilding createBuilding(Class<B> buildingClass, IFloor... floors) throws	NoSuchMethodException
																							,	SecurityException
																							,	InstantiationException
																							,	IllegalAccessException
																							,	IllegalArgumentException
																							,	InvocationTargetException
	{
		IBuilding res = null;
		Constructor<B> constr = buildingClass.getConstructor(IFloor[].class);
		res = (IBuilding)constr.newInstance((Object)floors);
		return res;
	}
}
