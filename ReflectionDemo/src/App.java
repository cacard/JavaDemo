import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.cacard.pojo.*;

public class App {

	public static void main(String[] args) {
		getClassByFullName();
	}

	/**
	 * object -> Class Full Name
	 * 
	 * @param p
	 * @return
	 */
	public static String getClassFullPathByClassInstance(Person p) {
		return p.getClass().getName();
	}

	public static void getClassByFullName() {
		String fullName = "com.cacard.pojo.Person";

		Class<?> c = null;
		try {
			c = Class.forName(fullName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		
		// 
		try {
			c.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// list of ctor
		Constructor<?> ctors[] = c.getConstructors();
		for(Constructor ctor : ctors)
		{
			System.out.println(ctor.getName());
		}
		
		Object obj = null;
		
		// ->ctor()
		try {
			obj = ctors[0].newInstance();
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
		
		// -> ctor(x,y);
		try {
			obj = ctors[1].newInstance("cacard",1);
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
		
		// method list
		System.out.println("method list:");
		Method[] methodList = c.getDeclaredMethods();
		for(Method m : methodList)
		{
			System.out.println(m.getName());
		}
		
		// invoke method by name
		try {
			Method im = c.getDeclaredMethod("staticMethodWithParams",new Class[]{String.class,int.class});
			try {
				im.invoke(obj,"cacard",2);
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
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// invoke method by method index
		try {
			methodList[2].invoke(obj);
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
