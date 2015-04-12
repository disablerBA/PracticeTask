package buildings;

public class Task2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//������� ���� � ������
		int[] a = {4,3,5};
		Dwelling building = new Dwelling( 3, a );
		DwellingFloor changeFloor = new DwellingFloor(2);	// �������� ����� ����� ������������
		System.out.println(	"���������� ������ � ����: "+building.getCountFloor()
						+	"\n���������� ������� � ����: "+building.getCountFlat()
						+	"\n���������� ������ � ����: "+building.getCountRoom()
						+	"\n����� ������� ������� �������� � ����: "+building.getBestSpace().getSpace()+"\n");
		
		for ( int i = 0; i < building.getCountFloor(); i++)
		{
			System.out.println("���-�� ������� �� "+(i+1)+" �����: "+building.getDwellingFloor(i).getCountFlat());
			System.out.println("���������� ������� �������� �� "+(i+1)+" �����: "+building.getDwellingFloor(i).getBestSpace().getSpace());
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+building.getDwellingFloor(i).getTotalRoom());
			System.out.println("����� ������� ���� ������ �� "+(i+1)+" �����: "+building.getDwellingFloor(i).getTotalSpace());
			System.out.println();
		}
		
		// ������ ������� ������
		building.addFlat(12, new Flat(3, 60));
		building.addFlat(2, new Flat(4, 80));
		building.addFlat(5, new Flat(3, 70));
		Flat newFlat = new Flat(3, 70);
		building.changeFlat(3, newFlat);
		building.deleteFlat(0);
		System.out.println();
		for ( int i = 0; i < building.getCountFlat(); i ++)
		{
			System.out.println(	"������ �� ��������: "+building.getFlat(i));
		}
		System.out.println("_____________��������_�����_______________________________\n");
		
		System.out.println(	"���������� ������ � ����: "+building.getCountFloor()
				+	"\n���������� ������� � ����: "+building.getCountFlat()
				+	"\n���������� ������ � ����: "+building.getCountRoom()
				+	"\n����� ������� ������� �������� � ����: "+building.getBestSpace().getSpace()+"\n");
		
		DwellingFloor[] dwellingFloor = building.getArrayDwellingFloor();
		for ( int i = 0; i < dwellingFloor.length; i++ )
		{
			System.out.println("���-�� ������� �� "+(i+1)+" �����: "+dwellingFloor[i].getCountFlat());
			System.out.println("���������� ������� �������� �� "+(i+1)+" �����: "+dwellingFloor[i].getBestSpace().getSpace());
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+dwellingFloor[i].getTotalRoom());
			System.out.println("����� ������� ���� ������ �� "+(i+1)+" �����: "+dwellingFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("______3_����_������_���������_�_2_����������____");
		building.changeDwellingFloor(2, changeFloor);
		System.out.println(	"���������� ������ � ����: "+building.getCountFloor()
				+	"\n���������� ������� � ����: "+building.getCountFlat()
				+	"\n���������� ������ � ����: "+building.getCountRoom()
				+	"\n����� ������� ������� �������� � ����: "+building.getBestSpace().getSpace()+"\n");
		
		for ( int i = 0; i < dwellingFloor.length; i++ )
		{
			System.out.println("���-�� ������� �� "+(i+1)+" �����: "+dwellingFloor[i].getCountFlat());
			System.out.println("���������� ������� �������� �� "+(i+1)+" �����: "+dwellingFloor[i].getBestSpace().getSpace());
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+dwellingFloor[i].getTotalRoom());
			System.out.println("����� ������� ���� ������ �� "+(i+1)+" �����: "+dwellingFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("��������������� �� �������� ������ �������: ");
		Flat[] sortingFlat = building.sortFlatDescendingSpace();
		for ( int i = 0; i < sortingFlat.length; i++ )
		{
			System.out.println(sortingFlat[i]+ ": "+sortingFlat[i].getSpace());
			
		}
		
		//Dwelling dwelling = new Dwelling( 3, {1,2,3} );	??
		
	}

}
