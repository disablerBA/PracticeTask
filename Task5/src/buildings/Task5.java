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

public class Task5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Тест OfficeBuilding
		/*int[] ar = {2, 3};
		OfficeBuilding ob1 = new OfficeBuilding(ar.length, ar);
		OfficeBuilding ob2 = (OfficeBuilding)ob1.clone();
		System.out.println("ob1.equals(ob2): "+ob1.equals(ob2));
		
		System.out.println("Здание 1 "+ob1.getCountFloor());
		for (int i = 0; i<ob1.getCountFloor(); i++)
		{
			System.out.println("	"+ob1.getFloor(i));
		}
		System.out.println();
		
		System.out.println("Здание 2 "+ob2.getCountFloor());
		for (int i = 0; i<ob2.getCountFloor(); i++)
		{
			System.out.println("	"+ob2.getFloor(i));
		}
		System.out.println();
		
		ob2.addPlacement(0, new Office());
		System.out.println("ob1.equals(ob2): "+ob1.equals(ob2));
		
		System.out.println("Здание 1 "+ob1.getCountFloor());
		for (int i = 0; i<ob1.getCountFloor(); i++)
		{
			System.out.println("	"+ob1.getFloor(i));
		}
		System.out.println();
		
		System.out.println("Здание 2 "+ob2.getCountFloor());
		for (int i = 0; i<ob2.getCountFloor(); i++)
		{
			System.out.println("	"+ob2.getFloor(i));
		}
		System.out.println();*/
		
		
		//Тест Dwelling
		/*System.out.println("\n\n");
		int[] ar = {2, 3};
		Dwelling d1 = new Dwelling(ar.length, ar);
		Dwelling d2 = (Dwelling)d1.clone();
		System.out.println("d1: "+d1+"\n"+"d1: "+d2);
		System.out.println(d1 == d2?"Равны":"Не равны");
		System.out.println("d1.equals(d2): "+d1.equals(d2));
		
		System.out.println("Здание 1 "+d1.getCountFloor()+"\n"+d1.getArrayFloor());
		for (int i = 0; i<d1.getCountFloor(); i++)
		{
			System.out.println("	"+d1.getFloor(i));
		}
		System.out.println();
		
		System.out.println("Здание 2 "+d2.getCountFloor()+"\n"+d2.getArrayFloor());
		for (int i = 0; i<d2.getCountFloor(); i++)
		{
			System.out.println("	"+d2.getFloor(i));
		}
		System.out.println();
		
		//d2.addPlacement(0, new Flat());
		d2.getPlacement(0).setSpace(500);
		System.out.println("d1.equals(d2): "+d1.equals(d2));
		
		System.out.println("Здание 1 "+d1.getCountFloor()+"\n"+d1.getArrayFloor());
		for (int i = 0; i<d1.getCountFloor(); i++)
		{
			System.out.println("	"+d1.getFloor(i));
		}
		System.out.println();
		
		System.out.println("Здание 2 "+d2.getCountFloor()+"\n"+d2.getArrayFloor());
		for (int i = 0; i<d2.getCountFloor(); i++)
		{
			System.out.println("	"+d2.getFloor(i));
		}
		System.out.println();*/
		
		
		// Тест Hotel
		System.out.println("\n\n");
		int[] ar = {2, 3};
		Hotel h1 = new Hotel(ar.length, ar);
		Hotel h2 = (Hotel)h1.clone();
		System.out.println("h1: "+h1+"\n"+"h2: "+h2);
		System.out.println(h1 == h2?"Равны":"Не равны");
		System.out.println("h1.equals(h2): "+h1.equals(h2));
		
		System.out.println("Здание 1 "+h1.getCountFloor()+"\n"+h1.getArrayFloor());
		
		for (int i = 0; i<h1.getCountFloor(); i++)
		{
			System.out.println("	"+h1.getFloor(i));
		}
		System.out.println();
		
		System.out.println("Здание 2 "+h2.getCountFloor()+"\n"+h2.getArrayFloor());
		for (int i = 0; i<h2.getCountFloor(); i++)
		{
			System.out.println("	"+h2.getFloor(i));
		}
		System.out.println();
		
		h2.getPlacement(0).setSpace(500);
		System.out.println("h1.equals(h2): "+h1.equals(h2));
		
		System.out.println("Здание 1 "+h1.getCountFloor()+"\n"+h1.getArrayFloor());
		for (int i = 0; i<h1.getCountFloor(); i++)
		{
			System.out.println("	"+h1.getFloor(i));
		}
		System.out.println();
		
		System.out.println("Здание 2 "+h2.getCountFloor()+"\n"+h2.getArrayFloor());
		for (int i = 0; i<h2.getCountFloor(); i++)
		{
			System.out.println("	"+h2.getFloor(i));
		}
		System.out.println();
	}
}
