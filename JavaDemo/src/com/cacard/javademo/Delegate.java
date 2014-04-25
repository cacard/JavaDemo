package com.cacard.javademo;
/**
 * author:licunqing
 * licunqing@gmail.com
 * cacard@126.com
 */



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Delegate {
	
	
	public static void main(String[] args)
	{
		Delegate d = new Delegate(
				Person2.class,
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
	
	
	
	private Class<?> c=null;
	private String method=null;
	private Class<?>[] paramsType=null;
	private Object[] params;
	
	public Delegate(Class<?> c,String method,Class<?>[] paramsType,Object[] params)
	{
		this.c=c;
		this.method=method;
		this.paramsType=paramsType;
		this.params=params;
	}
	
	public void invoke() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method m = c.getDeclaredMethod(method, paramsType);
		Object o = c.newInstance();
		m.invoke(o, params);
	}

}
