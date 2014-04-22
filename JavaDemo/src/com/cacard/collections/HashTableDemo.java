/**
 * Hashtable ����
 */
package com.cacard.collections;

import java.util.Hashtable;

public class HashTableDemo {

	public static void main(String[] args)
	{
		testReHash();
	}
	
	/**
	 * ����һ��forѭ��
	 * for(i=10;i-->0;)
	 */
	static void testForLoop()
	{
		// ��Hashtable��������һ��ѭ������Ȼ�е�ˣ���е���˼
		for(int i=10;i-->0;)
		{
			System.out.println(i); // ���ʲô? 
			// -> i=10,10>0,i--,println(9)
			// -> i=9,9>0,i--,print(8)
			// ...
			// -> i=1,1>0,i--,print(0)
		}
		
		// ��Hashmap�еĶ�Ӧʵ���У�������Ȼʹ���˴�Ҷ���������forѭ��
	}
	
	/**
	 * ģ��rehash����
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
		
		// ����ֱ�Ӹ�ֵrehash
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


