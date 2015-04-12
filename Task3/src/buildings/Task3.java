package buildings;

public class Task3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-----------���� ������----------");
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
		System.out.println("-----------���� ������----------");
		
		
		int[] a = {4,3,5};
		System.out.println(a[2]);
		IBuilding building = new OfficeBuilding( 3, a );
		IFloor changeFloor = new OfficeFloor(1);	// �������� ����� ����� ������������
		System.out.println(	"���������� ������ � ������: "+building.getCountFloor()
						+	"\n���������� ������ � ������: "+building.getCountPlacement()
						+	"\n���������� ������ � ������: "+building.getTotalRoom()
						+	"\n����� ������� ������� ����� � ������: "+building.getBestSpace().getSpace()+"\n"); 
		
		for ( int i = 0; i < building.getCountFloor(); i++)
		{
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+building.getFloor(i).getCountPlacement());
			System.out.println("���������� ������� ����� �� "+(i+1)+" �����: "+building.getFloor(i).getBestSpace().getSpace());
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+building.getFloor(i).getTotalRoom());
			System.out.println("����� ������� ���� ������ �� "+(i+1)+" �����: "+building.getFloor(i).getTotalSpace());
			System.out.println();
		}
		
		// ������ ������� ������
		building.addPlacement(12, new Office(3, 260));
		building.addPlacement(2, new Office(4, 280));
		building.addPlacement(5, new Office(3, 270));
		ISpace newOffice = new Office(3, 270);
		building.changePlacement(3, newOffice);
		building.remove(0);
		System.out.println("_____________��������_�����_______________________________\n");
		
		System.out.println(	"���������� ������ � ������: "+building.getCountFloor()
				+	"\n���������� ������ � ������: "+building.getCountPlacement()
				+	"\n���������� ������ � ������: "+building.getTotalRoom()
				+	"\n����� ������� ������� ����� � ������: "+building.getBestSpace().getSpace()+"\n");
		
		IFloor[] officeFloor = building.getArrayFloor();
		for ( int i = 0; i < officeFloor.length; i++ )
		{
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+officeFloor[i].getCountPlacement());
			System.out.println("���������� ������� ����� �� "+(i+1)+" �����: "+officeFloor[i].getBestSpace().getSpace());
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+officeFloor[i].getTotalRoom());
			System.out.println("����� ������� ���� ������ �� "+(i+1)+" �����: "+officeFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("_____________3_����_������_���������_�_1_������_\n");
		building.changeFloor(2, changeFloor);
		System.out.println(	"���������� ������ � ������: "+building.getCountFloor()
				+	"\n���������� ������ � ������: "+building.getCountPlacement()
				+	"\n���������� ������ � ������: "+building.getTotalRoom()
				+	"\n����� ������� ������� ����� � ������: "+building.getBestSpace().getSpace()+"\n");
		
		for ( int i = 0; i < officeFloor.length; i++ )
		{
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+officeFloor[i].getCountPlacement());
			System.out.println("���������� ������� ����� �� "+(i+1)+" �����: "+officeFloor[i].getBestSpace().getSpace());
			System.out.println("���-�� ������ �� "+(i+1)+" �����: "+officeFloor[i].getTotalRoom());
			System.out.println("����� ������� ���� ������ �� "+(i+1)+" �����: "+officeFloor[i].getTotalSpace());
			System.out.println();
		}
		
		System.out.println("��������������� �� �������� ������ ������: ");
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
