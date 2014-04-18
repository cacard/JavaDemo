/**
 * ArrayList相关测试
 * 
 * 1 ArrayList内部原理
 * 2 如何实现线程安全的ArrayList
 * 3 迭代器与线程安全，什么是fail-fast
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
	 * ArrayList的线程安全实现
	 */
	private static void testThreadSafe()
	{
		// #1 手动实现
		
		
		// #2 同步包装器
		
		// #3 使用并发库里面的数据结构（J.U.C，Java.Util.Concurrent）
		
		
	}
	
	/**
	 * 迭代器测试
	 * 迭代是“非线程安全”的，若其他线程修改集合，引发异常。
	 */
	private static 
	void testIterator()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++)
		{
			list.add(String.valueOf(i));
		}
		
		// 迭代时候add/remove
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
	 * 打印
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
