package com.cacard.javademo;

public class CtorInitOrder {

	public static void main(String[] args)
	{
		Sub1 s = new Sub1();
	}
	
}

class Base1
{
	static int c = getC();
	
	static
	{
		System.out.println("base static block");
	}
	
	static private int getC()
	{
		System.out.println("getC()");
		return 1;
	}
	
	public Base1()
	{
		System.out.println("Base ctor");
	}
}

class Sub1 extends Base1
{
	int a=getA();
	static int b = getB();
	
	static
	{
		System.out.println("sub static block");
	}
	
	private int getA()
	{
		System.out.println("getA()");
		return 1;
	}
	
	static private int getB()
	{
		System.out.println("getB()");
		return 1;
	}
	
	public Sub1()
	{
		System.out.println("Sub ctor");
	}
	
}