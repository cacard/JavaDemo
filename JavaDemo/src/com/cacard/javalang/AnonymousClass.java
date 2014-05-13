/**
 * 匿名类
 * 1 基于接口创建（通过实现接口以创建匿名类）
 * 2 基于父类创建（通过重写父类来创建匿名类）
 * 
 * 无类名，所以无构造函数。
 */

package com.cacard.javalang;

public class AnonymousClass {

	public static void main(String[] args) {
		usingClass();
	}

	static void usingInterface() {

	}

	static void usingClass() {
		ClassForAnonymous c = new ClassForAnonymous() {

			@Override
			public void sayHello() {

				System.out.println("ClassForAnonymous.[anonymous].hello" + i);
			}

		};

		c.sayHello();

	}

}

interface IF1 {
	void sayHello();
}

class ClassForAnonymous {
	public int i = 0;

	public ClassForAnonymous() {
		i = 1;
		System.out.println("ClassForAnonymous.ctor");
	}

	public void sayHello() {
		System.out.println("ClassForAnonymous.hello" + i);
	}
}
