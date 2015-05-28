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

import javax.swing.JFrame;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFactory;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFactory;
import buildings.office.OfficeFloor;

public class Task9 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*IBuilding bd1 = null;
		IBuilding bd2 = null;
		IFloor fd1 = null;
		IFloor fd2 = null;
		ISpace sd = null;
		
		IFloor[] af = new IFloor[2];
		af[0] = new HotelFloor(5);
		af[1] = new HotelFloor(6);
		
		ISpace[] as = new ISpace[2];
		as[0] = new Flat(6, 60);
		as[1] = new Flat(7, 70);
		try {
			int[] ab = {1,2,3,4};
			bd1 = Buildings.createBuilding( ab.length, ab, Hotel.class);
			bd2 = Buildings.createBuilding( af, Hotel.class);
			fd1 = Buildings.createFloor( 10, HotelFloor.class);
			fd2 = Buildings.createFloor( as, HotelFloor.class);
			sd = Buildings.createSpace(5,100, Flat.class);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dwelling 1 конструктор " +bd1.getCountFloor() +" "+ bd1.getCountPlacement());
		System.out.println("Dwelling 2 конструктор " +bd2.getCountFloor() +" "+ bd2.getCountPlacement());
		System.out.println("DwellingFloor 1 конструктор " +fd1.getCountPlacement());
		System.out.println("DwellingFloor 2 конструктор " +fd2.getCountPlacement());
		System.out.println("Flat 1 конструктор " +sd.getCountRoom() +" "+ sd.getSpace());*/
		
		File fileDwellingBin = new File("Dwelling.bin");
		File fileOfficeBin = new File("Office.bin");
		File fileHotelBin = new File("Hotel.bin");
		File fileDwellingTxt = new File("Dwelling.txt");
		File fileOfficeTxt = new File("Office.txt");
		File fileHotelTxt = new File("Hotel.txt");
		int[] ad = {1,2,3,4};
		int[] ao = {1,2,3,4,5};
		int[] ah = {1,2,3,4,5,6};
		IBuilding dwelling = new Dwelling(ad.length, ad);
		IBuilding officeBuilding = new OfficeBuilding(ao.length, ao);
		IBuilding hotel = new Hotel(ah.length, ah);
		OutputStream os = null;
		FileWriter fw = null;
		try 
		{
			os = new FileOutputStream(fileDwellingBin);
			Buildings.outputBuilding(dwelling, os);
			os.close();
			os = new FileOutputStream(fileOfficeBin);
			Buildings.outputBuilding(officeBuilding, os);
			os.close();
			os = new FileOutputStream(fileHotelBin);
			Buildings.outputBuilding(hotel, os);
			os.close();
			
			fw = new FileWriter(fileDwellingTxt);
			Buildings.writeBuilding(dwelling, fw);
			fw.close();
			fw = new FileWriter(fileOfficeTxt);
			Buildings.writeBuilding(officeBuilding, fw);
			fw.close();
			fw = new FileWriter(fileHotelTxt);
			Buildings.writeBuilding(hotel, fw);
			fw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally
		{
			try {
				os.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		InputStream is = null;
		FileReader fR = null;
		IBuilding b = null;
		try 
		{
			is = new FileInputStream(fileDwellingBin);
			b = Buildings.inputBuilding(is, Dwelling.class, DwellingFloor.class, Flat.class);
			System.out.println(b);
			is = new FileInputStream(fileOfficeBin);
			b = Buildings.inputBuilding(is, OfficeBuilding.class, OfficeFloor.class, Office.class);
			System.out.println(b);
			is = new FileInputStream(fileHotelBin);
			b = Buildings.inputBuilding(is, Hotel.class, HotelFloor.class, Flat.class);
			System.out.println(b);
			
			
			fR = new FileReader(fileDwellingTxt);
			b = Buildings.readBuilding(fR, Dwelling.class, DwellingFloor.class, Flat.class);
			System.out.println(b);
			fR = new FileReader(fileOfficeTxt);
			b = Buildings.readBuilding(fR, OfficeBuilding.class, OfficeFloor.class, Office.class);
			System.out.println(b);
			fR = new FileReader(fileHotelTxt);
			b = Buildings.readBuilding(fR, Hotel.class, HotelFloor.class, Flat.class);
			System.out.println(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				is.close();
				fR.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
