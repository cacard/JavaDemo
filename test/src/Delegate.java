/**
 * author:licunqing
 * licunqing@gmail.com
 * cacard@126.com
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Delegate {
	
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
