/**
 * 
 * 内部类对象的初始化过程
 * 
 * 		内部类不能包含static成员，所以也不能（也没有必要）定义静态初始化块
 * 
 * 		自然，先初始化外围对象（static block -> instance block -> ctor），再初始化内部类对象。
 * 
 */

package com.cacard.javalang;

public class InnerClassInit {

	{
		System.out.println("Outer -> instance init block"); // 外部类的实例初始化块
	}

	public InnerClassInit() {
		System.out.println("Outer -> ctor");
	}

	private class Inner // extends InnerClassInit
	{
		public Inner() {
			System.out.println("Inner -> ctor");
		}

		{
			System.out.println("Inner -> instance init block"); // 内部类的实例初始化块
		}

	}

	public static void main(String[] args) {
		InnerClassInit.Inner inner = new InnerClassInit().new Inner();

	}

}
