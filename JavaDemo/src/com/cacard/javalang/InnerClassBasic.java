/**
 * 
 * Inner可以访问外围类的私有成员。
 * 		- 直接调用
 * 		- 使用 OuterClass.this.xxx调用
 * Inner与外围类有相同的成员时，就近原则，调用的是Inner自己定义的成员。
 * 
 * 
 */

package com.cacard.javalang;

public class InnerClassBasic {

	// 外部类的一个private字段
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
