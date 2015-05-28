package buildings.net.server.parallel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Random;

import buildings.Buildings;
import buildings.IBuilding;
import buildings.net.server.sequental.BuildingUnderArrestException;

public class BinaryServer extends Thread{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ??? где лучше обрабатывать исключения?
		try 
		{
			ServerSocket serverSocket = new ServerSocket(1234, 0, InetAddress.getLocalHost());
			while(true)
			{
				new BinaryServer(serverSocket);
			} 
			
		}catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	private Socket socket;
	
	public BinaryServer(ServerSocket sSocket) throws UnknownHostException, IOException
	{
		socket = sSocket.accept();
		start();
	}
	
	public void run()
	{
		IBuilding[] buildings;
		int result;
		try 
		{
			buildings = receiveBuildings();
			//result = new int[buildings.length];
			for (int b = 0; b<buildings.length;b++)
			{
				try 
				{
					result = computeValuation(buildings[b]);
					sendData(result);
				} catch (BuildingUnderArrestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result = -2;
					sendData(result);
				}
			}
			socket.getOutputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IBuilding[] receiveBuildings() throws IOException
	{
		InputStream is = socket.getInputStream();
		int count = is.read();
		IBuilding[] buildings = new IBuilding[count];
		System.out.println("Кол-во зданий, принятых сервером "+count);
		for (int b = 0; b<count; b++)
		{
			buildings[b] = Buildings.inputBuilding(is);
		}
		return buildings;
	}
	
	public void sendData(int data) throws IOException
	{
		OutputStream os = socket.getOutputStream();
		byte[] ab = ByteBuffer.allocate(4).putInt(data).array();;
		os.write(ab);
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
