/**
 * ArrayList相关测试
 * 
 * 1 ArrayList内部原理
 * 2 如何实现线程安全的ArrayList
 * 3 迭代器与线程安全，什么是fail-fast
 */

package com.cacard.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {

	/**
	 * 测试入口
	 */
	public static void main(String[] args) {
		testRef();
	}

	/**
	 * ArrayList的线程安全实现
	 */
	private static void testThreadSafe() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		// #1 手动实现
		for (int i = 0; i < 10; i++) {
			synchronized (list) {
				list.add(i);
			}
		}

		// #2 同步包装器
		List<Integer> syncList = Collections.synchronizedList(list);
		syncList.add(1);

		// #3 使用J.U.C里面的数据结构，比如CopyOnWriteArrayList
		final CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<Integer>();
		int count = 10;
		Thread[] threads = new Thread[count];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						cowList.add(j);
						System.out.println("->" + j);
					}

				}
			});
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("all joined.");

	}

	/**
	 * 迭代器测试
	 * 迭代是“非线程安全”的，内部通过fail-fast机制来检查是否在迭代过程中发生修改。无论是单线程下或者多线程下，迭代时被修改均抛出异常
	 * 单线程下，迭代时通过list.remove()修改，引发异常。可以通过iter.remove()修改
	 * 多线程下，每个线程通过iter.remove()也可能引发异常 注意：非结构性修改不会出现问题（比如修改元素值）
	 */
	private static void testIterator() {

		// 单线程迭代时候list.操作，引发异常
		final List<String> list = create(10);
		Iterator<String> iter = list.iterator();

		int j = 0;
		while (iter.hasNext()) {
			System.out.println(iter.next());
			if (j == 3) {
				// list.add("11"); // ConcurrentModificationException
				// iter.remove();
			}
			j++;
		}
		print(list);

	}

	/**
	 * 修改元素值，没有failfast问题
	 */
	private static void testRef() {
		ArrayList<Object> l = new ArrayList<>();
		l.add(new Object());
		l.add(new Object());

		for (Object o : l) {
			o = new Object();
		}

		final List<String> list = create(10);
		Iterator<String> iter = list.iterator();

		int j = 0;
		for (String s : list) {
			System.out.println(iter.next());
			if (j == 3) {
				list.add(new String("1"));
			}
			j++;
		}
		print(list);

	}

	private static void testIteratorUsingThreads() {
		
		// 多线程下，多次迭代，使用iter.remove()引发异常
		final List<String> list2 = create(100);
		int count = 10;
		Thread[] threads = new Thread[count];
		for (int i = 0; i < count; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					// 每个线程迭代，并通过iter修改
					int j = 0;
					for (Iterator<String> iter = list2.iterator(); iter.hasNext(); j++) {
						System.out.println(iter.next());
						if (j == 10) {
							iter.remove();
						}
					}

				}
			});
			threads[i].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("all joined.");
	}

	private static List create(int count) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}

	private static void print(List<String> list) {
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}

}
