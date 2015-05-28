package buildings.net.server.sequental;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import buildings.IBuilding;

public class SerialServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try 
		{
			ServerSocket serverSocket = new ServerSocket(1234, 0, InetAddress.getLocalHost());
			SerialServer server;
			while(true)
			{
				server = new SerialServer(serverSocket);
				server.work();
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
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public SerialServer(ServerSocket sSocket) throws UnknownHostException, IOException
	{
		socket = sSocket.accept();
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}
	
	public void work()
	{
		IBuilding[] buildings;
		Object[] result;
		try 
		{
			buildings = receiveBuildings();
			result = new Object[buildings.length];
			System.out.println("Кол-во принятых зданий "+buildings.length);
			for (int b = 0; b<buildings.length;b++)
			{
				try 
				{
					result[b] = computeValuation(buildings[b]);
				} catch (BuildingUnderArrestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result[b] = e;
				}
			}
			sendData(result);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IBuilding[] receiveBuildings() throws IOException, ClassNotFoundException
	{
		return (IBuilding[])ois.readObject();
	}
	
	public void sendData(Object data) throws IOException
	{
		oos.writeObject(data);
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
