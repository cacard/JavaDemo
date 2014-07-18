/**
 * ������
 * 
 * 	- ���ڽӿڴ�����ͨ��ʵ�ֽӿ��Դ��������ࣩ
 * 	- ���ڸ��ഴ����ͨ����д���������������ࣩ
 * 
 * �������������޹��캯����
 */

package com.cacard.javalang;

public class AnonymousClass {

	public static void main(String[] args) {
		testAnonymousClassUsingClass();
	}

	static void testAnonymousClassUsingClass() {
		ClassForAnonymous c = new ClassForAnonymous() {
			@Override
			public void sayHello() {
				System.out.println("ClassForAnonymous.[anonymous].hello" + i);
			}
		};
		c.sayHello();
	}
}

interface InterfaceSome {
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
