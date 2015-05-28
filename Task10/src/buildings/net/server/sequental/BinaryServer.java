package buildings.net.server.sequental;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Random;

import buildings.Buildings;
import buildings.IBuilding;

public class BinaryServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket server;
		Socket socket;
		BinaryServer bs = new BinaryServer();
		int result;
		try 
		{
			server = new ServerSocket(1234, 0, InetAddress.getLocalHost());
			int i = 0;
			while ( true )
			{
				socket = server.accept();
				System.out.println(++i);
				InputStream is = socket.getInputStream(); 
				OutputStream os = socket.getOutputStream();
				int count = is.read();
				byte[] ab;
				System.out.println("Кол-во зданий, принятых сервером "+count);
				for (int b = 0; b<count; b++)
				{
					try {
						result = bs.computeValuation(Buildings.inputBuilding(is));
						ab = ByteBuffer.allocate(4).putInt(result).array();
						os.write(ab);	
						
					} catch (BuildingUnderArrestException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result = -2;
						ab = ByteBuffer.allocate(4).putInt(result).array();
						os.write(ab);	
					}
					System.out.println("Принял и посчитал "+result);
				}
				os.close();
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BinaryServer()
	{
		
	}
	
	public int computeValuation(IBuilding building) throws BuildingUnderArrestException
	{
		if ( isArrest())
		{
			throw new BuildingUnderArrestException();
		}
		int result = 0;
		for (int p = 0; p< building.getCountPlacement(); p++)
		{
			result += building.getPlacement(p).getSpace();
		}
		
		switch ( building.getClass().getName() )
		{
			case "buildings.dwelling.Dwelling": result *= 1000;
			break;
			
			case "buildings.dwelling.hotel.Hotel": result *= 2000;
			break;
			
			case "buildings.office.OfficeBuilding": result *= 1500;
			break;
		}
		return result;
	}
	
	public boolean isArrest()
	{
		Random r = new Random();
		int i = r.nextInt(101);
		System.out.println("Рандом выдал "+i);
		if (i < 11)
		{
			return true;
		} else 
		{
			return false;
		}
	}
}
