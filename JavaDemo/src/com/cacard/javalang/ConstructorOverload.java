/**
 * 构造函数重载如何优化？
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
		this(null);	// 调用其他构造函数，参数null
	}
	
	public COSub(String name)
	{
		System.out.println("->COSub.ctor2");
	}
}