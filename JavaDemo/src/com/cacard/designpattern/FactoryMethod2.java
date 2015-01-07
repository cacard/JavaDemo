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
		createSomeClass(); // ����������������Ķ���
	}

	void createSomeClass() {
	};

	public void say() {
		someClass.say();
	}
}

class MyClientNeedSomeClass1 extends ClientBase {
	@Override
	public void createSomeClass() { // ͨ��Override
		someClass = new SomeClass1();
	}
}

class MyClientNeedSomeClass2 extends ClientBase {
	@Override
	public void createSomeClass() {
		someClass = new SomeClass2();
	}
}