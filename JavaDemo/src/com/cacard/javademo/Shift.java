/**
 * ÒÆÎ»²Ù×÷£¬ Shift Operator
 * 
 * http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.19
 */


package com.cacard.javademo;

public class Shift {

	public static void main(String[] args)
	{
		int i=0;
		int b=~0;
		System.out.println(Integer.toHexString(b)); // ffffffff
		System.out.println(Integer.toBinaryString(b)); // 32bit 
		
		
		int a = 0x0000003;
		a=a>>1;
		System.out.println(a);
		System.out.println(Integer.toBinaryString(a));
		
		int size = 11;
		int newSize = size + (size >> 1);
		System.out.println(newSize);
		
	}
	
}
