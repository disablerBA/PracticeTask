package buildings.net.client;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamTokenizer;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import buildings.Buildings;
import buildings.IBuilding;

public class SerialClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File buildingsFile = new File(args[0]/*"Buildings.txt"*/);	
		File resultFile = new File(args[1]/*"ResultSerializable.txt"*/); 
		FileReader fr = null;
		FileWriter fw = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket socket = null;
		StreamTokenizer st;
		try 
		{
			fr = new FileReader(buildingsFile);
			st = new StreamTokenizer(fr);
			st.eolIsSignificant(true);
			int buildingsCount = 0;
			
			while ( st.nextToken() != StreamTokenizer.TT_EOF )
			{
				if (st.ttype == StreamTokenizer.TT_EOL)
				{
					buildingsCount++;
				}
			}
			IBuilding[] buildings = new IBuilding[buildingsCount];
			fr.close();
			
			socket = new Socket(InetAddress.getLocalHost(), 1234); 
			oos = new ObjectOutputStream(socket.getOutputStream());
			fr = new FileReader(buildingsFile);
			
			for ( int b = 0; b<buildingsCount; b++ )
			{
				buildings[b] = Buildings.readBuilding(fr);
			}

			oos.writeObject(buildings);
			System.out.println("Кол-во зданий отправленных клиентом "+buildingsCount);
			ois = new ObjectInputStream(socket.getInputStream());
			fw = new FileWriter(resultFile);
			Object[] result;
			result = (Object[])ois.readObject();
			for ( int i = 0; i < result.length; i++)
			{
				fw.write(result[i].toString()+"\n");	
			}
			
		} catch (ClassCastException | IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				ois.close();
				oos.close();
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
