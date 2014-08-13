/**
 * ����ģʽ
 * <p>
 * �ص��Ƕ��߳��¸����ܵĵ���<br/>
 * 1 ���̰߳�ȫ�ĵ����ڶ��߳��»��δ���ʵ��<br/>
 * 2 ͨ��synchronizedʵ�ֵ��̰߳�ȫ�ĵ�������ͨ��double-check��˫�μ�ⵥ���Ƿ񴴽����ķ�ʽ�������Ե�һЩ��
 * 3 ���þ�̬�ֶε�������ʼ��ʵ���̰߳�ȫ��
 * </p>
 */

package com.cacard.designpattern;

import java.util.concurrent.locks.ReentrantLock;

public class SingletonPattern {

	public static void main(String[] args) {
		testSingleton4();
	}

	static void testSingleton1() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Singleton1 s = Singleton1.getInstance();
				}
			}).start();
		}
	}

	static void testSingleton2() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Singleton2 s = Singleton2.getInstance();
				}
			}).start();
		}
	}

	static void testSingleton3() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Singleton3 s = Singleton3.getInstance();
				}
			}).start();
		}
	}

	static void testSingleton4() {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Singleton4 s = Singleton4.getInstance();
				}
			}).start();
		}
	}
}

/** 1 ���̰߳�ȫ�ĵ��� */
class Singleton1 {
	private static Singleton1 s;

	private Singleton1() {
		System.out.println("Singleton1->ctor");
	}

	public static Singleton1 getInstance() {
		if (s == null) { // #1
			s = new Singleton1(); // thread1 ִ�е��˴���thread2ִ�е�#2����������������
		}
		return s;
	}
}

/** 2 �̰߳�ȫ�ĵ��� */
class Singleton2 {
	private static Singleton2 s;

	private Singleton2() {
		System.out.println("Singleton2->ctor");
	}

	public static synchronized Singleton2 getInstance() {
		if (s == null) {
			s = new Singleton2();
		}
		return s;
	}
}

/** 3 double-check ���̰߳�ȫ���� */
class Singleton3 {
	private static Singleton3 s;
	private static ReentrantLock lock = new ReentrantLock();

	private Singleton3() {
		System.out.println("Singleton3->ctor");
	}

	public static Singleton3 getInstance() {
		if (s != null) {
			return s;
		} else {
			try {
				lock.lock();
				if (s != null) {
					return s;
				} else {
					s = new Singleton3();
				}
			} finally {
				lock.unlock();
			}
		}
		return s;
	}
}

/** ���þ�̬�ֶε�ֱ�ӳ�ʼ�� */
class Singleton4 {
	private static Singleton4 s = new Singleton4();

	private Singleton4() {
		System.out.println("Singleton4->ctor");
	}

	public static Singleton4 getInstance() {
		return s;
	}
}
