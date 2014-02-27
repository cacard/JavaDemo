/**
 * author:licunqing
 * licunqing@gmail.com
 * cacard@126.com
 */

import java.lang.reflect.InvocationTargetException;

import com.cacard.pojo.*;

/**
 * Java's Delegate Using Reflection
 * @author licq
 *
 */
public class App {

	public static void main(String[] args)
	{
		Delegate d = new Delegate(
				Person.class,
				"instanceMethodWithParams",
				new Class<?>[]{String.class,int.class},
				new Object[]{"jack",22});
		
		try {
			d.invoke();
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
	}
	
}
