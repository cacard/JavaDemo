/**
 * HashSet
 * �ڲ������� HashMap �� Key ���洢Ԫ��
 */

package com.cacard.collections;

import java.util.HashSet;

public class HashSetDemo {

	public static void main(String[] args)
	{
		HashSet<Integer> s = new HashSet<Integer>();
		
		boolean b = s.add(1);
		System.out.println(b); // true
		boolean c = s.add(1);
		System.out.println(c); // false
	}
	
	
}
