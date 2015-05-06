package buildings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;

import buildings.dwelling.Dwelling;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;

public class Task4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// --------------------------------------------------------------------
		System.out.println("Проверка символьной записи");
		FileReader r = null;
		File f = new File("symbol.txt");
		int[] i = {4,6,5};
		Dwelling d = new Dwelling(3, i);
		d.getPlacement(0).setSpace(7000);
		d.getPlacement(0).setCountRoom(70);
		d.getPlacement(5).setSpace(800);
		d.getPlacement(5).setCountRoom(80);
		d.getPlacement(14).setSpace(32768);
		d.getPlacement(14).setCountRoom(90);
		try
		{
			FileWriter fw = new FileWriter(f);
			Buildings.writeBuilding(d, fw);
			fw.flush();
			fw.close();
			r = new FileReader( f );
			Buildings.readBuilding( r );
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// --------------------------------------------------------------------
		System.out.println("\nПроверка байтовой записи");
		f = new File("byte.bin");
		try
		{
			FileOutputStream fos = new FileOutputStream(f);
			Buildings.outputBuilding(d, fos);
			fos.flush();
			fos.close();
			FileInputStream fis = new FileInputStream( f );
			Buildings.inputBuilding( fis );
			
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// --------------------------------------------------------------------
		System.out.println("\nПроверка сериализации");
		f = new File("serializable.bin");
		try
		{
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(d);
			oos.flush();
			oos.close();
			
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Dwelling deserD = (Dwelling)ois.readObject();
			
			boolean eq = true;
			for ( int pl = 0; pl < d.getCountPlacement(); pl++ )
			{
				if ( 	d.getPlacement(pl).getCountRoom() == deserD.getPlacement(pl).getCountRoom()
					&&	d.getPlacement(pl).getSpace() == deserD.getPlacement(pl).getSpace()	)
				{
					continue;
				} else
				{
					eq = false;
					break;
				}
			}
			
			if ( eq )
			{
				System.out.println("Объекты равны");
			} else
			{
				System.out.println("Объекты не равны");
			}
			
		} catch ( IOException e)
		{
			e.printStackTrace();
		} catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		
		// --------------------------------------------------------------------
		System.out.println("\nТест отеля " );
		int[] array = {5, 3};
		Hotel h = new Hotel(array.length, array);
		h.getPlacement(5).setSpace(60);
		((HotelFloor)h.getFloor(1)).setStars(5);
		System.out.println("Площадь лучшего номера: "+ h.getBestSpace().getSpace() );
		
	}
}
