package buildings;

public class Task3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-----------Тест списка----------");
		ListOffice l = new ListOffice();
		ISpace o1 = new Office(2, 200);	
		ISpace o2 = new Office(3, 300);	
		ISpace o3 = new Office(4, 400);	
		l.add(o1);
		l.add(o2);
		l.add(o3);
		System.out.println(l.size());
		l.add(0, new Office(1, 100));
		l.add(1, new Office(1, 100));
		System.out.println(l.size());
		
		l.add(5, new Office(1, 100));
		
		System.out.println(l.getOffice(0).getSpace());
		System.out.println(l.getOffice(1).getSpace());
		System.out.println(l.getOffice(2).getSpace());
		System.out.println(l.getOffice(3).getSpace());
		System.out.println(l.getOffice(4).getSpace());
		System.out.println(l.getOffice(5).getSpace());
		System.out.println();
		
		l.remove(0);
		l.remove(1);
		l.remove(3);
		
		System.out.println(l.getOffice(0).getSpace());
		System.out.println(l.getOffice(1).getSpace());
		System.out.println(l.getOffice(2).getSpace());
		//System.out.println(l.getOffice(3).getSpace());
		System.out.println("-----------Тест списка----------");
		
		
		int[] a = {4,3,5};
		System.out.println(a[2]);
		IBuilding building = new OfficeBuilding( 3, a );
		IFloor changeFloor = new OfficeFloor(1);	// сохраним чтобы потом использовать
		System.out.println(	"Количество этажей в здании: "+building.getCountFloor()
						+	"\nКоличество офисов в здании: "+building.getCountPlacement()
						+	"\nКоличество комнат в здании: "+building.getTotalRoom()
						+	"\nСамая большая площадь офиса в здании: "+building.getBestSpace().getSpace()+"\n"); 
		
		for ( int i = 0; i < building.getCountFloor(); i++)
		{
			System.out.println("Кол-во офисов на "+(i+1)+" этаже: "+building.getFloor(i).getCountPlacement());
			System.out.println("Наибольшая площадь офиса на "+(i+1)+" этаже: "+building.getFloor(i).getBestSpace().getSpace());
			System.out.println("Кол-во офисов на "+(i+1)+" этаже: "+building.getFloor(i).getTotalRoom());
			System.out.println("Общая площадь всех офисов на "+(i+1)+" этаже: "+building.getFloor(i).getTotalSpace());
			System.out.println();
		}
		
		// теперь изменим здание
		building.addPlacement(12, new Office(3, 260));
		building.addPlacement(2, new Office(4, 280));
		building.addPlacement(5, new Office(3, 270));
		ISpace newOffice = new Office(3, 270);
		building.changePlacement(3, newOffice);
		building.remove(0);
		System.out.println("_____________Изменили_этажи_______________________________\n");
		
		System.out.println(	"Количество этажей в здании: "+building.getCountFloor()
				+	"\nКоличество офисов в здании: "+building.getCountPlacement()
				+	"\nКоличество комнат в здании: "+building.getTotalRoom()
				+	"\nСамая большая площадь офиса в здании: "+building.getBestSpace().getSpace()+"\n");
		
		IFloor[] officeFloor = building.getArrayFloor();
		for ( int i = 0; i < officeFloor.length; i++ )
		{
			System.out.println("Кол-во офисов на "+(i+1)+" этаже: "+officeFloor[i].getCountPlacement());
			System.out.println("Наибольшая площадь офиса на "+(i+1)+" этаже: "+officeFloor[i].getBestSpace().getSpace());
			System.out.println("Кол-во комнат на "+(i+1)+" этаже: "+officeFloor[i].getTotalRoom());
			System.out.println("Общая площадь всех офисов на "+(i+1)+" этаже: "+officeFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("_____________3_этаж_теперь_дефолтный_с_1_офисом_\n");
		building.changeFloor(2, changeFloor);
		System.out.println(	"Количество этажей в здании: "+building.getCountFloor()
				+	"\nКоличество офисов в здании: "+building.getCountPlacement()
				+	"\nКоличество комнат в здании: "+building.getTotalRoom()
				+	"\nСамая большая площадь офиса в здании: "+building.getBestSpace().getSpace()+"\n");
		
		for ( int i = 0; i < officeFloor.length; i++ )
		{
			System.out.println("Кол-во офисов на "+(i+1)+" этаже: "+officeFloor[i].getCountPlacement());
			System.out.println("Наибольшая площадь офиса на "+(i+1)+" этаже: "+officeFloor[i].getBestSpace().getSpace());
			System.out.println("Кол-во комнат на "+(i+1)+" этаже: "+officeFloor[i].getTotalRoom());
			System.out.println("Общая площадь всех офисов на "+(i+1)+" этаже: "+officeFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("Отсортированный по убыванию список офисов: ");
		ISpace[] sortingOffice = building.sortPlacementDescendingSpace();
		for ( int i = 0; i < sortingOffice.length; i++ )
		{
			System.out.println(sortingOffice[i]+ ": "+sortingOffice[i].getSpace());
		}
		
		//building.getOfficeFloor(3);
		//building.getOffice(100);
		//building.getOffice(1).setCountRoom(0);
		//building.getOffice(1).setSpace(0);
		
	}
}
