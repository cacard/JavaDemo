/**
 * 反射
 */

package com.cacard.javalang;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class Reflect {

	public static void main(String[] args) {
		//getClassByFullName();
		testGetFieldNameAndValue();
	}
	
	public static void testGetFieldNameAndValue() {
		Pojo1 pojo = new Pojo1();
		pojo.name = "name";
		pojo.addr = "addr";
		Reflect.<Pojo1>getMethodNameAndValue(pojo);
	}

	public static String getClassFullPathByClassInstance(Person p) {
		return p.getClass().getName();
	}

	/**
	 * 根据类路径创建类实例
	 */
	public static void getClassByFullName() {

		String fullName = "com.cacard.javademo.Person2";

		Class<?> c = null;
		try {
			c = Class.forName(fullName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 列出所有构造函数
		Constructor<?> ctors[] = c.getConstructors();
		for (Constructor<?> ctor : ctors) {
			System.out.println(ctor.getName());
		}

		Object obj = null;

		// 通过第1个构造函数创建实例
		try {
			obj = ctors[0].newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 通过第2个构造函数创建实例
		try {
			obj = ctors[1].newInstance("cacard", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 显示所有方法
		System.out.println("method list:");
		Method[] methodList = c.getDeclaredMethods();
		for (Method m : methodList) {
			System.out.println(m.getName());
		}

		// 调用方法
		try {
			Method im = c.getDeclaredMethod("staticMethodWithParams", new Class[] { String.class, int.class });

			im.invoke(obj, "cacard", 2);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 通过索引调用方法
		try {
			methodList[2].invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将一个pojo转化成方法名和值的集合
	 * 
	 * @param obj
	 * @param clazz
	 * @param <T>
	 * @return
	 * @author cunqingli
	 */
	public static <T extends Serializable> void getMethodNameAndValue(T obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			for (Field f : fields) {
				f.setAccessible(true);
				String value = "";
				try {
					value = (String) f.get(obj);
				} catch (Exception e) {
					// pass
				}
				System.out.println("k:" + f.getName() + "/v:" + value);
			}
		}
	}
	
	public static class Pojo1 implements Serializable {
		public String name;
		public String addr;
	}
}
