package com.cacard.concurrentcollections;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

	public static void main(String[] args){
		
	}
	
	static void testIterator()
	{
		ConcurrentHashMap<String,String> cHashMap = new ConcurrentHashMap<>();
		
		for(int i=0;i<10;i++){
			cHashMap.put(String.valueOf(i), String.valueOf(i));
		}
		
		for(Iterator<Entry<String,String>> iter = cHashMap.entrySet().iterator();iter.hasNext();)
		{
			
		}
	}
	
}
