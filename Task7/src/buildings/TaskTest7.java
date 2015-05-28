package buildings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFactory;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFactory;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFactory;
import buildings.office.OfficeFloor;

public class TaskTest7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File f = new File("File1.txt");
		FileWriter fw;
		FileReader fr;
		try {
			fw = new FileWriter(f);
			fr = new FileReader(f);
			int[] ad = {1};
			int[] ao = {1,2};
			int[] ah = {1,2,3};
			IBuildingFactory df = new DwellingFactory();
			IBuildingFactory of = new OfficeFactory();
			IBuildingFactory hf = new HotelFactory();
			IBuilding d = df.createBuilding(ad.length, ad);
			IBuilding o = of.createBuilding(ao.length, ao);
			IBuilding h = hf.createBuilding(ah.length,ah);
			Buildings.writeBuilding(d, fw);
			Buildings.writeBuilding(o, fw);
			Buildings.writeBuilding(h, fw);
			fw.close();
			
			IBuilding d1 = Buildings.readBuilding(fr);
			IBuilding o1 = Buildings.readBuilding(fr);
			IBuilding h1 = Buildings.readBuilding(fr);
			System.out.println(d.equals(d1));
			System.out.println(o.equals(o1));
			System.out.println(h.equals(h1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
