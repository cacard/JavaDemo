/**
 * ���캯����������Ż���
 */


package com.cacard.javalang;

public class ConstructorOverload {

	public static void main(String[] args)
	{
		COSub s = new COSub();
		
	}
	
}

class CO
{
	public CO()
	{
		System.out.println("->CO.ctor");
	}
}

class COSub extends CO
{
	public COSub()
	{
		this(null);	// �����������캯��������null
	}
	
	public COSub(String name)
	{
		System.out.println("->COSub.ctor2");
	}
}