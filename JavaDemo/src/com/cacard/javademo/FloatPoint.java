package com.cacard.javademo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FloatPoint {

	public static void main(String args[])
	{
		testBig();
	}
	
	static void testConvert()
	{
		int a = (int)1023.99999999999;
		
		System.out.println(a);
	}
	
	static void testBig()
	{
		BigDecimal b = new BigDecimal("1.29999999999999999999999999999999999");
		b=new BigDecimal(2.1);
		System.out.println(b);
		
		BigInteger bi = new BigInteger("999999999999999999999999999999999999999999999");
		System.out.println(bi);
	}
	
	
}
