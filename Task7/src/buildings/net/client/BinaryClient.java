package buildings.net.client;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StreamTokenizer;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

import buildings.Buildings;
import buildings.IBuilding;

public class BinaryClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IBuilding b = null;
		File buildingsFile = new File("Buildings.txt");	// поменять на args
		File resultFile = new File("Result.txt"); // поменять на args
		FileReader fr = null;
		FileWriter fw = null;
		Socket socket = null;
		OutputStream os = null;
		InputStream is = null;
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
			fr.close();
			
			System.out.println("Кол-во зданий отправленных клиентом "+buildingsCount);
			
			socket = new Socket(InetAddress.getLocalHost(), 1234);
			os = socket.getOutputStream(); 
			os.write(buildingsCount);
			fr = new FileReader(buildingsFile);
			while ( (b = Buildings.readBuilding(fr) ) != null )
			{
				Buildings.outputBuilding(b, socket.getOutputStream());
			}
			
			byte[] receiveData = new byte[4];
			is = socket.getInputStream();
			fw = new FileWriter(resultFile);
			int result;
			while ( is.read(receiveData) != -1 )
			{
				result = ByteBuffer.wrap(receiveData).getInt();
				if ( result == -2)
				{
					System.out.println("Результат: на здание наложен арест");
					fw.write("The buildings was arrested\n");
				} else
				{
					System.out.println("Результат: "+ result);
					fw.write(String.valueOf(result)+"\n");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				is.close();
				os.close();
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}
}
