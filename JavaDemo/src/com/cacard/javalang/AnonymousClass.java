/**
 * ������
 * 1 ���ڽӿڴ�����ͨ��ʵ�ֽӿ��Դ��������ࣩ
 * 2 ���ڸ��ഴ����ͨ����д���������������ࣩ
 * 
 * �������������޹��캯����
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
