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
	 * ʵ��InvocationHandler����ʵ�ָ÷������ɡ�
	 * �������˼�ǣ���ʵ��ִ�����е������ķ�����
	 * 
	 * @obj ʵ��ִ����
	 * @metod ����ʵ��ִ����ķ���
	 * @args ����
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
