/**
 * Hashtable 测试
 */
package com.cacard.collections;

import java.util.Hashtable;

public class HashTableDemo {

	public static void main(String[] args)
	{
		testReHash();
	}
	
	/**
	 * 测试一个for循环
	 * for(i=10;i-->0;)
	 */
	static void testForLoop()
	{
		// 在Hashtable中有这样一个循环，显然有点耍花招的意思
		for(int i=10;i-->0;)
		{
			System.out.println(i); // 输出什么? 
			// -> i=10,10>0,i--,println(9)
			// -> i=9,9>0,i--,print(8)
			// ...
			// -> i=1,1>0,i--,print(0)
		}
		
		// 在Hashmap中的对应实现中，作者显然使用了大家都容易理解的for循环
	}
	
	/**
	 * 模拟rehash测试
	 * 
	 */
	static void testReHash()
	{
		HashTableEntry[] oldEntryArray=new HashTableEntry[10];
		HashTableEntry[] newEntryArray=new HashTableEntry[20];
		
		HashTableEntry entry0 = new HashTableEntry(0,0,
				new HashTableEntry(1,1,
						new HashTableEntry(2,2,null)));
		oldEntryArray[0]=entry0;
		
		// 采用直接赋值rehash
		int newIndex=5;
		newEntryArray[newIndex]=oldEntryArray[0];
		
		printMyEntryArray(newEntryArray,newIndex);
		
	}
	
	static void printMyEntryArray(HashTableEntry[] array,int index)
	{
		if(array==null || array.length==0)
		{
			System.out.println("null or empty");
			return;
		}
		
		HashTableEntry entry = array[index];
		while(entry!=null)
		{
			System.out.println(entry.key+","+entry.value);
			entry=entry.next;
		}
	}
	
}


