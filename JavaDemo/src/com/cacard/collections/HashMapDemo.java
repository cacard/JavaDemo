package com.cacard.collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

	public static void main(String[] args){
		
	}
	
	 // ���������С��޸�Ԫ�ء����ǽṹ���޸ģ���û������
	static void testHashMapEditElement()
	{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<10;i++)
		{
			map.put(i, i);
		}
		
		int i=0;
		for(Map.Entry<Integer, Integer> entry : map.entrySet())
		{
			if(i==5)
			{
				map.put(9, 10); // �޸�
			}
			System.out.println(entry.toString());
			i++;
		}
	}
	
}
