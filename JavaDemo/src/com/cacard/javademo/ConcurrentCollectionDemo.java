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
	 * ���̰߳�ȫ��ArrayList����ʲô���⣿
	 */
	static void testArrayListProblems()
	{
		final ArrayList<String> list = new ArrayList<String>();	// ���̹߳����ArrayList
		
		// ����߳�ͬʱ����д����
		// ���� java.lang.ArrayIndexOutOfBoundsException
		// ԭ���ǣ����߳�1�ж�capacity�������Բ������ݺ󣬵���������ʱ֮ǰ���ѱ�����Thread����Ԫ�ء������������Χ�����쳣��
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
	 * ���̰߳�ȫHashMap������
	 */
	static void testHashMapProblems()
	{
		// ͬʱ�޸�
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<10;i++)
		{
			map.put(i, i);
		}
		
		// 
		
		
		
		
		// ����������ĳ����޸�
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
