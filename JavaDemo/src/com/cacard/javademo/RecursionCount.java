/**
 * �ݹ����
 */


package com.cacard.javademo;

public class RecursionCount {

	public static void main(String[] args){
		recursion(0);
	}
	
	/** 1w��κ� StackOverflowError */
	static void recursion(int i){
		System.out.println(""+i);
		recursion(i+1);
	}
	
}
