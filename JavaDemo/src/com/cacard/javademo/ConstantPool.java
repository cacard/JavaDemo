/**
 * 测试一下runtime constant pool
 * 
 * 常量池（JVM称为Run-Time Constant Pool）可以看做一个符号表，包含了几种类型的constant：
 - Integer 4byte
 - Long 8byte
 - Float 4byte
 - Double 8byte
 - String 指向Utf8项
 - Utf8 存储字符串的真正位置
 - Class 指向一个Utf8项，表示类路径
 - Methodref 格式为#xx.#yy 。xx指向Class项，yy指向一个NameAndType（）
 - NameAndType 格式为#xx:#yy。xx指向一个Utf8项，表示方法名/字段名；yy指向一个Utf8项，表示参数列表（例如()Ljava/lang/String; 或者 (II)Ljava/lang/String;）。
 */

package com.cacard.javademo;

public class ConstantPool {

	public static void main(String[] args)
	{
		String shortString="hello";
		String longString="hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.hello.";
		
		String str1="good";
		String str2="day";
		String str3=str1+str2;
		
		String str4 = str3.toString(); // no params
		String str5 = str3.substring(0, 1); // 2 params
		
		int i=0;
		float f=1;
		double d=2.12222277222222222222555555555555555556666666666666666665d;
		
		System.out.println(d);// 2.1222227722222224
	}
	
	public static void someMethod()
	{
		
	}
	
	public static void someMethodWithParams(int x,String y)
	{
		
	}
	
}
