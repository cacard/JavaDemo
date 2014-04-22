package com.cacard.collections;

import java.util.Iterator;
import java.util.TreeMap;

public class TreeMapDemo {

	public static void main(String[] args)
	{
		testSort();
	}
	
	/**
	 * °´ÕÕkeyÉýÐò´æ´¢
	 * ¼´keySet()ÊÇÓÐÐòµÄ
	 */
	static void testSort()
	{
		TreeMap<String,Integer> map = new TreeMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("0", 100);
		map.put("3", 3);
		
		print(map);
	}
	
	
	static void print(TreeMap<String,Integer> map)
	{
		System.out.println("loop using keySet:");
		for(String key : map.keySet())
		{
			System.out.println(key+","+map.get(key));
		}
		
		System.out.println("loop using values iterator:");
		for(Iterator<?> iter = map.values().iterator();iter.hasNext();)
		{
			System.out.println(iter.next());
		}
		
	}
}
