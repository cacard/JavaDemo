package com.cacard.javademo;

import java.io.Serializable;

public class GenericType {

	public static void main(String[] args)
	{
		TestExtendsFromInterface(SomeClass.class);
		TestExtendsFromSuperClass(SomeThread.class);
		
		// RawType
		boolean b0 = new GenericClass1() instanceof Object;	// true
		boolean b1 = new GenericClass1() instanceof GenericClass1; //true, ԭ�͵�instance��ԭ�����instance
		boolean b2 = new GenericClass1<SomeClass>() instanceof GenericClass1; // true
		boolean b3 = new GenericClass1<SomeClass>() instanceof GenericClass1<?>; // true
		//boolean b4 = new GenericClass1<SomeClass>() instanceof GenericClass1<SomeClass>; // instanceof �Ҳ಻������巺�ͣ���Ϊ�����ڲ���
		
	}
	
	/**
	 * extends�ؼ��ʲ������������̳��ԡ���Ҳ�ɴ���ʵ���ˡ����ӿڣ�
	 * @param c
	 */
	static void TestExtendsFromInterface(Class<? extends Serializable> c)
	{
		
	}
	static void TestExtendsFromSuperClass(Class<? extends SomeThread> c)
	{ 
		
	}
	
}

class SomeClass implements Serializable
{
	
}

class SomeThread extends Thread
{
	
}

class GenericClass1<T extends Serializable>
{
	
}


