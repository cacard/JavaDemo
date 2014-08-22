/**
 * 		如何知道当前线程在某个对象上是否已经上锁？
 * 			- Thread.holdsLock(obj);
 * 
 */

package com.cacard.threads;

public class HoldsLock {

	private Object obj = new Object();

	public void method() {

		synchronized (obj) {
			System.out.println(Thread.holdsLock(obj));
		}

		{
			System.out.println(Thread.holdsLock(obj));
		}

	}

	public static void main(String[] args) {
		new HoldsLock().method();
	}

}
