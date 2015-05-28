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
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.ListOffice;
import buildings.office.ListOfficeFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.office.OfficeNode;
import buildings.threads.Cleaner;
import buildings.threads.Repairer;
import buildings.threads.Semaphor;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;
import buildings.threads.SwitchingSemaphor;

public class Task6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IFloor floor = new DwellingFloor(100);
		SwitchingSemaphor sem = new SwitchingSemaphor();
		SequentalRepairer sr = new SequentalRepairer(sem, floor);
		SequentalCleaner sc = new SequentalCleaner(sem, floor);
		Thread t1 = new Thread(sr);
		Thread t2 = new Thread(sc);
		t2.start();
		t1.start();
		
		SynchronizedFloor sFloor =  (SynchronizedFloor)Buildings.synchronizedFloor(floor); 
		
	}
}
