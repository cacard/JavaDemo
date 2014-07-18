/**
 * Assert
 * 		from java1.4
 * 	
 * 		why?
 * 			开发时用于检查程序。
 * 
 * 		how using?
 * 			assert bool-exp;
 * 			assert bool-exp:"xxx"
 * 			生成环境不要使assert生效。（默认情况下也不会生效）
 * 
 * 		note
 * 			using "java -ea xxx" 使assert生效
 * 
 */

package com.cacard.javalang;

public class AssertDemo {
	
	public static void main(String[] args){
		testAssert(null,0);
	}

	public static void testAssert(String str,int b){
		assert str!=null;
		assert b!=0;
		assert 1==2:"hello";
		
		System.out.println("end");
	}
	
}
