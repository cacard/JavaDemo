/**
 * Concurrent DataStructure Demo
 */

package com.cacard.javademo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollectionDemo {

	public static void main(String[] args)
	{
		testArrayListProblems();
	}
	
	/**
	 * 非线程安全的ArrayList会有什么问题？
	 */
	static void testArrayListProblems()
	{
		final ArrayList<String> list = new ArrayList<String>();	// 多线程共享的ArrayList
		
		// 多个线程同时进行写操作
		// 出现 java.lang.ArrayIndexOutOfBoundsException
		// 原因是，当线程1判断capacity条件可以插入数据后，到真正插入时之前，已被其他Thread插入元素。超出数组最大范围出现异常。
		int count=10;
		Thread[] threads = new Thread[count];
		for(int i=0;i<10;i++)
		{
			threads[i] = new Thread(new Runnable(){

				@Override
				public void run() {
					for(int j=0;j<1000;j++)
					{
						list.add("hello");
					}
					
				}});
			threads[i].start();
		}
		for(Thread t : threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("all joined.");
			
	}
	
	
	
	
	/**
	 * 非线程安全HashMap的问题
	 */
	static void testHashMapProblems()
	{
		// 同时修改
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<10;i++)
		{
			map.put(i, i);
		}
		
		// 
		
		
		
		
		// 迭代过程中某个项被修改
		int i=0;
		for(Map.Entry<Integer, Integer> entry : map.entrySet())
		{
			if(i==5)
			{
				map.put(9, 10);
			}
			System.out.println(entry.toString());
			i++;
		}
		
	}
	
	/**
	 * ConcurrentHashMap
	 */
	static void testConcurrentHashMap()
	{
		ConcurrentHashMap<String,String> cHashMap = new ConcurrentHashMap<String,String>();
		cHashMap.put("a", "a-string");
		String rtn = cHashMap.putIfAbsent("a", "a-string-new");
		
		if(rtn==null)
		{
			System.out.println("rtn is null");
		}
		else
		{
			System.out.println("rtn="+rtn);
		}
		
		for(String key : cHashMap.keySet())
		{
			System.out.println("key:"+key+",value:"+cHashMap.get(key));
		}
		
	}
	
}
