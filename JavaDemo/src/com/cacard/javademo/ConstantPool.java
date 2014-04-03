/**
 * ����һ��runtime constant pool
 * 
 * �����أ�JVM��ΪRun-Time Constant Pool�����Կ���һ�����ű������˼������͵�constant��
 - Integer 4byte
 - Long 8byte
 - Float 4byte
 - Double 8byte
 - String ָ��Utf8��
 - Utf8 �洢�ַ���������λ��
 - Class ָ��һ��Utf8���ʾ��·��
 - Methodref ��ʽΪ#xx.#yy ��xxָ��Class�yyָ��һ��NameAndType����
 - NameAndType ��ʽΪ#xx:#yy��xxָ��һ��Utf8���ʾ������/�ֶ�����yyָ��һ��Utf8���ʾ�����б�����()Ljava/lang/String; ���� (II)Ljava/lang/String;����
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
