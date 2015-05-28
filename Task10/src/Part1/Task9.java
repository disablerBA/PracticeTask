package Part1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Task9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(args.length);
		System.out.println(args[0]);
		System.out.println(args[1]);
		System.out.println(args[2]);
		try {
			Class c = Class.forName(args[0]);
			Method m = c.getMethod(args[1], new Class[] {Double.TYPE});
			Object res = m.invoke(null, new Object[]{Double.valueOf(args[2])});
			System.out.println(res.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Класс не найден");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			System.out.println("Метод не найден");
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println("Ошибка доступа");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("Метод недоступен");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("Неверный аргумент метода");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			System.out.println("При вызове возникло исключение");
			e.printStackTrace();
		}
	}
}
