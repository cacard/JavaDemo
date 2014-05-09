/**
 * 递归次数
 */


package com.cacard.javademo;

public class RecursionCount {

	public static void main(String[] args){
		recursion(0);
	}
	
	/** 1w多次后 StackOverflowError */
	static void recursion(int i){
		System.out.println(""+i);
		recursion(i+1);
	}
	
}
