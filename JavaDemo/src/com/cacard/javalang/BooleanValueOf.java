package com.cacard.javalang;

public class BooleanValueOf {

	public static void main(String[] args){
		boolean b = Boolean.valueOf("true").booleanValue();
		boolean c = Boolean.valueOf("false");//.booleanValue();
		boolean d = Boolean.valueOf("_").booleanValue();
		
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
	
}
