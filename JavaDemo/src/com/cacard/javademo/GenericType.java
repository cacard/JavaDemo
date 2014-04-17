package com.cacard.javademo;

import java.io.Serializable;

public class GenericType {

	public static void main(String[] args)
	{
		TestExtendsFromInterface(SomeClass.class);
		TestExtendsFromSuperClass(SomeThread.class);
		
		// RawType
		boolean b0 = new GenericClass1() instanceof Object;	// true
		boolean b1 = new GenericClass1() instanceof GenericClass1; //true, 原型的instance是原型类的instance
		boolean b2 = new GenericClass1<SomeClass>() instanceof GenericClass1; // true
		boolean b3 = new GenericClass1<SomeClass>() instanceof GenericClass1<?>; // true
		//boolean b4 = new GenericClass1<SomeClass>() instanceof GenericClass1<SomeClass>; // instanceof 右侧不允许具体泛型，因为运行期擦除
		
	}
	
	/**
	 * extends关键词并不仅仅代表“继承自”，也可代表“实现了”（接口）
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


