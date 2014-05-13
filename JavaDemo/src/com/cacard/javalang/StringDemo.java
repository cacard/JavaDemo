/**
 * String 
 * 认清几个概念：字符串常量（也是字符串对象，只是存储在常量池中），在Heap中的字符串对象，在StackFrame中的字符串对象变量（指向Heap或者指向常量池）
 * 
 */


package com.cacard.javalang;

import java.util.ArrayList;

public class StringDemo {

	public static void main(String[] args)
	{
		testAppend();
	}
	
	/**
	 * 驻留字符串
	 * 字符串常量
	 */
	static void internString()
	{
		// "abc" 属于字符串常量，编译期间放到了 string pool 中
	}
	
	/**
	 * 栈字符串变量
	 * a是个statckFrame中的本地变量，其值为"abc"
	 *  0: ldc       #16 // String abc
 	 *	2: astore_1     	// 本地变量 String a
	 */
	static void stackString()
	{
		String a="abc";
	}
	
	/**
	 * Heap中的字符串对象
	 *  0: new           #25                 // class java/lang/String
	 *	3: dup
	 *	4: ldc           #16                 // String abc
	 *	6: invokespecial #27                 // Method java/lang/String."<init>":(Ljava/lang/String;)V
	 * 	9: astore_0	// 
	 */
	static void heapString()
	{
		String a = new String("abc");
	}
	
	// --------------------------------------
	// AbstractStringBuilder/StringBuffer/StringBuilder
	

	static void testAppend()
	{
		//String a = null;
		ArrayList<String> a=null;
		
		StringBuilder sb = new StringBuilder();
		sb.append(a);
		
		System.out.println(sb.toString());
	}
	
}
