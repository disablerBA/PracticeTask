package buildings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class Task10 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] ad = {1,2,3,4};
		Dwelling d = new Dwelling(ad.length, ad);
		d.getPlacement(9).setSpace(400);
		IBuilding b;
		
		try ( FileWriter fw = new FileWriter("TestFormatOut.txt"))
		{
			Buildings.writeBuilding(d, fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try ( FileReader fr = new FileReader("TestFormatOut.txt") )
		{
			b = Buildings.readBuilding(new Scanner(fr));
			System.out.println(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}