/**
 * 单例模式
 * <p>
 * 重点是多线程下高性能的单例<br/>
 * 1 非线程安全的单例在多线程下会多次创建实例<br/>
 * 2 通过synchronized实现的线程安全的单例，比通过double-check（双次检测单例是否创建）的方式，性能略低一些。
 * 3 采用静态字段的内联初始化实现线程安全。
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

/** 1 非线程安全的单例 */
class Singleton1 {
	private static Singleton1 s;

	private Singleton1() {
		System.out.println("Singleton1->ctor");
	}

	public static Singleton1 getInstance() {
		if (s == null) { // #1
			s = new Singleton1(); // thread1 执行到此处，thread2执行到#2处，创建两个对象
		}
		return s;
	}
}

/** 2 线程安全的单例 */
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

/** 3 double-check 的线程安全单例 */
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

/** 采用静态字段的直接初始化 */
class Singleton4 {
	private static Singleton4 s = new Singleton4();

	private Singleton4() {
		System.out.println("Singleton4->ctor");
	}

	public static Singleton4 getInstance() {
		return s;
	}
}
