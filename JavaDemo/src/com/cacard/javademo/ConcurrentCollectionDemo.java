/**
 * Concurrent DataStructure Demo
 */

package com.cacard.javademo;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollectionDemo {

	public static void main(String[] args)
	{
		testConcurrentHashMap();
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
