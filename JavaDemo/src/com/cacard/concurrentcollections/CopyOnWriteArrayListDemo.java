package com.cacard.concurrentcollections;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

	public static void main(String[] args)
	{
		testIter();
	}
	
	/**
	 * ����������
	 * ��֧�� remove
	 */
	static void testIter()
	{
		CopyOnWriteArrayList<Integer> l = new CopyOnWriteArrayList<Integer>();
		for(int i=0;i<10;i++)
		{
			l.add(i);
		}
		
		int i=0;
		for(Iterator<Integer> iter=l.iterator();iter.hasNext();i++)
		{
			if(i==1)
			{
				iter.remove(); // UnsupportedOperationException ��֧��ɾ��
			}
		}
		print(l);
	}
	
	/**
	 * ��ӡ
	 * @param l
	 */
	static void print(CopyOnWriteArrayList<Integer> l)
	{
		for(Iterator<Integer> iter=l.iterator();iter.hasNext();)
		{
			System.out.println(iter.next());
		}
	}
	
}
