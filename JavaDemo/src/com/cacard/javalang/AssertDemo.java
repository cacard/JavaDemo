/**
 * Assert
 * 		from java1.4
 * 	
 * 		why?
 * 			����ʱ���ڼ�����
 * 
 * 		how using?
 * 			assert bool-exp;
 * 			assert bool-exp:"xxx"
 * 			���ɻ�����Ҫʹassert��Ч����Ĭ�������Ҳ������Ч��
 * 
 * 		note
 * 			using "java -ea xxx" ʹassert��Ч
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
