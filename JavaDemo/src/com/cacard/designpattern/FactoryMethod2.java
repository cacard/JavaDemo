package com.cacard.designpattern;

/**
 * Factory Method
 * 
 * 
 * @author cunqingli
 * 
 */
public class FactoryMethod2 {
	public static void main(String[] args) {
		ClientBase cb = new MyClientNeedSomeClass1();
		cb.say();
	}
}

interface ISomeClass {
	void say();
}

class SomeClass1 implements ISomeClass {
	public void say() {
		System.out.println("SomeClass1");
	}
}

class SomeClass2 implements ISomeClass {
	public void say() {
		System.out.println("SomeClass2");
	}
}

class ClientBase {
	ISomeClass someClass;

	public ClientBase() {
		createSomeClass(); // 由子类来创建具体的对象
	}

	void createSomeClass() {
	};

	public void say() {
		someClass.say();
	}
}

class MyClientNeedSomeClass1 extends ClientBase {
	@Override
	public void createSomeClass() { // 通过Override
		someClass = new SomeClass1();
	}
}

class MyClientNeedSomeClass2 extends ClientBase {
	@Override
	public void createSomeClass() {
		someClass = new SomeClass2();
	}
}