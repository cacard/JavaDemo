/**
 * ArrayDeque
 * ����ʵ�ֵ�˫�˶��У�������ջ������
 */


package com.cacard.collections;

import java.util.ArrayDeque;

public class ArrayDequeDemo {

	public static void main(String[] args){
		
		ArrayDeque ad = new ArrayDeque(8);
		
		findMinPowTwo(9);
	}
	
	// Ѱ�ұ�ָ���������С��2��ƽ����
	// 1000 => 10000
	static void findMinPowTwo(int numElements) {
		
		 int initialCapacity = 8 ;// MIN_INITIAL_CAPACITY;
	        // Find the best power of two to hold elements.
	        // Tests "<=" because arrays aren't kept full.
	        if (numElements >= initialCapacity) {
	            initialCapacity = numElements;
	            initialCapacity |= (initialCapacity >>>  1);
	            initialCapacity |= (initialCapacity >>>  2);
	            initialCapacity |= (initialCapacity >>>  4);
	            initialCapacity |= (initialCapacity >>>  8);
	            initialCapacity |= (initialCapacity >>> 16);
	            initialCapacity++;
	         
	            System.out.println(Integer.toBinaryString(initialCapacity));
	            
	        }
		
	}
	
}
