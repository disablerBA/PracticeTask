package buildings;

public class Task2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//выводим инфо о здании
		int[] a = {4,3,5};
		Dwelling building = new Dwelling( 3, a );
		DwellingFloor changeFloor = new DwellingFloor(2);	// сохраним чтобы потом использовать
		System.out.println(	"Количество этажей в доме: "+building.getCountFloor()
						+	"\nКоличество квартир в доме: "+building.getCountFlat()
						+	"\nКоличество комнат в доме: "+building.getCountRoom()
						+	"\nСамая большая площадь квартиры в доме: "+building.getBestSpace().getSpace()+"\n");
		
		for ( int i = 0; i < building.getCountFloor(); i++)
		{
			System.out.println("Кол-во квартир на "+(i+1)+" этаже: "+building.getDwellingFloor(i).getCountFlat());
			System.out.println("Наибольшая площадь квартиры на "+(i+1)+" этаже: "+building.getDwellingFloor(i).getBestSpace().getSpace());
			System.out.println("Кол-во комнат на "+(i+1)+" этаже: "+building.getDwellingFloor(i).getTotalRoom());
			System.out.println("Общая площадь всех комнат на "+(i+1)+" этаже: "+building.getDwellingFloor(i).getTotalSpace());
			System.out.println();
		}
		
		// теперь изменим здание
		building.addFlat(12, new Flat(3, 60));
		building.addFlat(2, new Flat(4, 80));
		building.addFlat(5, new Flat(3, 70));
		Flat newFlat = new Flat(3, 70);
		building.changeFlat(3, newFlat);
		building.deleteFlat(0);
		System.out.println();
		for ( int i = 0; i < building.getCountFlat(); i ++)
		{
			System.out.println(	"Ссылка на квартиру: "+building.getFlat(i));
		}
		System.out.println("_____________Изменили_этажи_______________________________\n");
		
		System.out.println(	"Количество этажей в доме: "+building.getCountFloor()
				+	"\nКоличество квартир в доме: "+building.getCountFlat()
				+	"\nКоличество комнат в доме: "+building.getCountRoom()
				+	"\nСамая большая площадь квартиры в доме: "+building.getBestSpace().getSpace()+"\n");
		
		DwellingFloor[] dwellingFloor = building.getArrayDwellingFloor();
		for ( int i = 0; i < dwellingFloor.length; i++ )
		{
			System.out.println("Кол-во квартир на "+(i+1)+" этаже: "+dwellingFloor[i].getCountFlat());
			System.out.println("Наибольшая площадь квартиры на "+(i+1)+" этаже: "+dwellingFloor[i].getBestSpace().getSpace());
			System.out.println("Кол-во комнат на "+(i+1)+" этаже: "+dwellingFloor[i].getTotalRoom());
			System.out.println("Общая площадь всех комнат на "+(i+1)+" этаже: "+dwellingFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("______3_этаж_теперь_дефолтный_с_2_квартирами____");
		building.changeDwellingFloor(2, changeFloor);
		System.out.println(	"Количество этажей в доме: "+building.getCountFloor()
				+	"\nКоличество квартир в доме: "+building.getCountFlat()
				+	"\nКоличество комнат в доме: "+building.getCountRoom()
				+	"\nСамая большая площадь квартиры в доме: "+building.getBestSpace().getSpace()+"\n");
		
		for ( int i = 0; i < dwellingFloor.length; i++ )
		{
			System.out.println("Кол-во квартир на "+(i+1)+" этаже: "+dwellingFloor[i].getCountFlat());
			System.out.println("Наибольшая площадь квартиры на "+(i+1)+" этаже: "+dwellingFloor[i].getBestSpace().getSpace());
			System.out.println("Кол-во комнат на "+(i+1)+" этаже: "+dwellingFloor[i].getTotalRoom());
			System.out.println("Общая площадь всех комнат на "+(i+1)+" этаже: "+dwellingFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("Отсортированный по убыванию список квартир: ");
		Flat[] sortingFlat = building.sortFlatDescendingSpace();
		for ( int i = 0; i < sortingFlat.length; i++ )
		{
			System.out.println(sortingFlat[i]+ ": "+sortingFlat[i].getSpace());
			
		}
		
		//Dwelling dwelling = new Dwelling( 3, {1,2,3} );	??
		
	}

}
