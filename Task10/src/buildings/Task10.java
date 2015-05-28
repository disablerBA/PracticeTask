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

		DwellingFloor df = new DwellingFloor(3);
		DwellingFloor df2 = (DwellingFloor)df.clone();
		System.out.println("1 этаж 1 помещение "+df.getPlacement(0));
		df.getPlacement(0).setCountRoom(10);
		System.out.println("1 этаж 1 помещение "+df.getPlacement(0));
		System.out.println("2 этаж 1 помещение "+df2.getPlacement(0));
	}
}