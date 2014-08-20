/**
 * 
 * 	Given an array of integers, every element appears twice except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	
	-
	
	�ɶԳ��֣���һ�����������ҵ�����һ������Ĳ��ɶԵ�Ԫ�ء�
 * 
 */

package com.cacard.alg.leetcode;

public class SingleNumber {

	public static int findSingleNumber(int[] A) {
		for (int i = 0; i < A.length - 1; i++) {
			A[i + 1] = A[i] ^ A[i + 1]; // �����ö����Ա
		}

		return A[A.length - 1];
	}

	// test
	public static void main(String[] args) {
		int[] a = new int[] { 1, 2, 3, 4, 5, 6, 1, 2, 4, 5, 6 };
		System.out.println(findSingleNumber(a));
	}

}
