/**
 * ArrayList��ز���
 * 
 * 1 ArrayList�ڲ�ԭ��
 * 2 ���ʵ���̰߳�ȫ��ArrayList
 * 3 ���������̰߳�ȫ��ʲô��fail-fast
 */


package com.cacard.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {

	public static void main(String[] args)
	{
		testIterator();
	}
	
	/**
	 * ArrayList���̰߳�ȫʵ��
	 */
	private static void testThreadSafe()
	{
		// #1 �ֶ�ʵ��
		
		
		// #2 ͬ����װ��
		
		// #3 ʹ�ò�������������ݽṹ��J.U.C��Java.Util.Concurrent��
		
		
	}
	
	/**
	 * ����������
	 * �����ǡ����̰߳�ȫ���ģ��������߳��޸ļ��ϣ������쳣��
	 */
	private static 
	void testIterator()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++)
		{
			list.add(String.valueOf(i));
		}
		
		// ����ʱ��add/remove
		Iterator<String> iter = list.iterator();
		
		int j=0;
		while(iter.hasNext())
		{
			System.out.println(iter.next());
			if(j==3)
			{
				list.add("11"); // ConcurrentModificationException
				//iter.remove();
			}
			j++;
		}
		System.out.println("+++");
		print(list);
		
	}
	
	/**
	 * ��ӡ
	 * @param list
	 */
	private static
	void print(ArrayList<String> list)
	{
		for(Iterator<String> iter=list.iterator();iter.hasNext();)
		{
			System.out.println(iter.next());
		}
	}
	
}
