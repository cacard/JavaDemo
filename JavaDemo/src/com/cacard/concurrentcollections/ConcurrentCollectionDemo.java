/**
 * Concurrent DataStructure Demo
 */

package com.cacard.concurrentcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollectionDemo {

	public static void main(String[] args)
	{
	
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
