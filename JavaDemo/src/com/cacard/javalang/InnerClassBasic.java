/**
 * 
 * Inner���Է�����Χ���˽�г�Ա��
 * 		- ֱ�ӵ���
 * 		- ʹ�� OuterClass.this.xxx����
 * Inner����Χ������ͬ�ĳ�Աʱ���ͽ�ԭ�򣬵��õ���Inner�Լ�����ĳ�Ա��
 * 
 * 
 */

package com.cacard.javalang;

public class InnerClassBasic {

	// �ⲿ���һ��private�ֶ�
	private int a=1;
	
	private void hello(){
		System.out.println("outer say hello.");
	}
	
	private void staticMethod(){
		System.out.println("outer static method say hello.");
	}
	
	private class Inner
	{
		private int a_=2;
		
		private void innerMethod()
		{
			System.out.println(a);
			hello();
			staticMethod();
		}
		
		private void hello_(){
			System.out.println("inner say hello.");
		}
	}
	
	
	public static void main(String[] args)
	{
		InnerClassBasic outer = new InnerClassBasic();
		InnerClassBasic.Inner inner = outer.new Inner();
		inner.innerMethod();
	}
}
