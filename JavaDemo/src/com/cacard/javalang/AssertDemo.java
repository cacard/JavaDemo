/**
 * Assert
 * 
 * java -ea xxx Ê¹assertÉúĞ§
 * 
 */

package com.cacard.javalang;

public class AssertDemo {
	
	public static void main(String[] args){
		method(null,0);
	}

	public static void method(String str,int b){
		assert str!=null;
		assert b!=0;
		
		System.out.println("end");
	}
	
}
