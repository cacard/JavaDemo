/*
 * volatile
 * 只保证，对volatile的一次写操作对其他线程可见，即每次写操作不依赖于volatile变量本身，则可以看做是线程安全的。
 * 
 * volatile语义
 * 	- 旧JMM:对volatile的写保证其他线程在读时可见
 *  - 新JMM:增加，volatile变量与其他费volatile变量不发生reorder
 */

package com.cacard.threads;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {

	private static volatile int flag = 0;
	private static AtomicInteger atomic = new AtomicInteger(0);

	public static void main(String[] args) {
		Demo1();
	}

	/**
	 * 多线程下，对volatile变量的赋值依赖于volatile变量（比如val=val++），则不是线程安全的操作
	 */
	static void Demo1() {
		final int threadCount = 1000;
		final CyclicBarrier cb = new CyclicBarrier(threadCount, new Runnable() {

			@Override
			public void run() {

				System.out.println("last result\nflag:" + flag + "\natomic:" + atomic.get());

			}
		});

		for (int i = 0; i < threadCount; i++) {
			final int m = i;
			new Thread(new Runnable() {

				@Override
				public void run() {

					flag++;
					atomic.getAndIncrement();

					try {
						cb.await();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}).start();
		}
	}

	/**
	 * 对volatile变量的写，且不依赖于volatile变量本身，则是线程安全的！
	 */
	static void Demo2() {
		for (int i = 0; i < 10; i++) {
			final int m = i;
			new Thread(new Runnable() {

				@Override
				public void run() {

					flag = m; // 对volatile变量进行一次写操作（注意不是先读后写：flag=falg+1），这个写操作是线程安全的
					System.out.println(flag); // 读操作。其它线程的写，对当前线程的读是可见的。

				}
			}).start();
		}
	}

}
