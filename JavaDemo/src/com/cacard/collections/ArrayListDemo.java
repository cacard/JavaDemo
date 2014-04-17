package com.cacard.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {

	public static void main(String[] args)
	{
		testIterator();
	}
	
	private static 
	void testIterator()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++)
		{
			list.add(String.valueOf(i));
		}
		
		// µü´úÊ±ºòadd/remove
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
	
	private static
	void print(ArrayList<String> list)
	{
		for(Iterator<String> iter=list.iterator();iter.hasNext();)
		{
			System.out.println(iter.next());
		}
	}
	
}
