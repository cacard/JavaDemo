/**
 * String 
 * ���弸������ַ���������Ҳ���ַ�������ֻ�Ǵ洢�ڳ������У�����Heap�е��ַ���������StackFrame�е��ַ������������ָ��Heap����ָ�����أ�
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
	 * פ���ַ���
	 * �ַ�������
	 */
	static void internString()
	{
		// "abc" �����ַ��������������ڼ�ŵ��� string pool ��
	}
	
	/**
	 * ջ�ַ�������
	 * a�Ǹ�statckFrame�еı��ر�������ֵΪ"abc"
	 *  0: ldc       #16 // String abc
 	 *	2: astore_1     	// ���ر��� String a
	 */
	static void stackString()
	{
		String a="abc";
	}
	
	/**
	 * Heap�е��ַ�������
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
