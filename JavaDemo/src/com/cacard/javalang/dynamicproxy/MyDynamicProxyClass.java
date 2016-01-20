package com.cacard.javalang.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * 
 * @author cunqingli
 *
 */
public class MyDynamicProxyClass implements InvocationHandler {

	/**
	 * 实现InvocationHandler，仅实现该方法即可。
	 * 本身的意思是，在实际执行类中调用它的方法。
	 * 
	 * @obj 实际执行类
	 * @metod 调用实际执行类的方法
	 * @args 参数
	 * 
	 */
	@Override
	public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			result = method.invoke(obj, args);
		} catch (Exception e) {
			
		}
		return result;
	}

}
