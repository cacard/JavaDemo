/**
 * ThreadLocal Demo
 * 1 ThreadLocal变量本身在新线程中不会是null，其wrap的对象需要初始化。可指定initialValue函数来为每个线程做初始化。
 * 2 [TODO]在没有阅读ThreadLocal源码的情况下，如何自己实现一个ThreadLocal类？
 * 3 不同Thread之间的ThreadLocal的交互
 */

package com.cacard.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalDemo {

	public static void main(String[] args) {
		// test1();
		testRef();
	}

	/**
	 * ThreadLocal的初始化（声明时的初始化，以及对其wrap的对象的初始化）
	 */
	static void test1() {
		final XX c = new XX();

		// main thread
		System.out.println(c.tl.get().i);

		// new thread
		new Thread(new Runnable() {

			@Override
			public void run() {

				System.out.println(c.tl.get().i);

			}
		}).start();
	}

	/**
	 * 线程本地变量也可以通过引用来交互
	 */
	static void testRef() {
		final XX c = new XX();

		List<Callable<Integer>> tasks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			tasks.add(new Callable<Integer>() {
				@Override
				public Integer call() {
					return c.tl.get().i;
				}
			});
		}

		try {
			List<Future<Integer>> l = Executors.newFixedThreadPool(2).invokeAll(tasks);
			for (int i = 0; i < 10; i++) {
				System.out.println(l.get(i).get());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(c.refList);
		}

	}

}

/**
 * 包含ThreadLocal字段的类
 */
class XX {
	public int j; // 非线程本地变量
	public ArrayList<YY> refList = new ArrayList<YY>(); // 保存不同线程之间ThreadLocal<YY>的引用
	public ThreadLocal<YY> tl; // 线程本地变量
	public ThreadLocal<Integer> tl2;

	public XX() {
		// 只有主线程初始化
		// tl=new ThreadLocal<ForThreadLocalGeneric>();
		// tl.set(new ForThreadLocalGeneric());

		// 每个线程均如此初始化
		tl = new ThreadLocal<YY>() {
			@Override
			protected YY initialValue() {
				YY f = new YY();

				refList.add(f); // 把当前线程的本地变量包裹的对象添加到refList

				f.i = 100;
				return f;
			}
		};

		tl2 = new ThreadLocal<Integer>();
	}

	public void SomeMethod() {
		System.out.println("[SomeMethod][threadid:" + Thread.currentThread().getId() + "]" + tl.get().i);
	}

}

/**
 * ThreadLocal包裹的类
 */
class YY {
	public int i;

	public YY() {
		System.out.println("[ForThreadLocalGeneric ctor][threadid:" + Thread.currentThread().getId() + "]");
	}
}
