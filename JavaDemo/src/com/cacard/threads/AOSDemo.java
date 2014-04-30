/**
 * AOS AbstractOwnableSynchronizer
 * 该抽象类封装了一个独占锁的实现
 */

package com.cacard.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;

public class AOSDemo {

	public static void main(String[] args) {

	}

	// 测试AOS锁
	static void testCustomeLock() {
		final MyCustomLock lock = new MyCustomLock();
		boolean r1 = lock.lock();
		boolean r2 = lock.lock();// 再次加锁

		// 新开线程加锁
		Executor exe = Executors.newFixedThreadPool(2);
		exe.execute(new Runnable() {
			@Override
			public void run() {
				boolean r3 = lock.lock();
				System.out.println("->r3:" + r3); // result:false
			}
		});

		// 等待 exe 执行完毕
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean r4 = lock.unlock();// 解锁
		System.out.println("->r1:" + r1 + "/r2" + r2 + ":/r4:" + r4); // result:true
	}

}

// 根据AOS创建一个自定义锁。同一线程可多次加锁，但不计数，释放一次即可。
class MyCustomLock extends AbstractOwnableSynchronizer {
	private static final long serialVersionUID = 3394444822954946380L;

	// 获取锁：设置内部线程变量为当前线程
	public boolean lock() {
		if (this.getExclusiveOwnerThread() == null) {
			this.setExclusiveOwnerThread(Thread.currentThread());
			return true;
		}

		if (this.getExclusiveOwnerThread() == Thread.currentThread()) {
			return true;
		}

		return false;
	}

	// 释放锁
	public boolean unlock() {
		Thread t = this.getExclusiveOwnerThread();
		if (t == null || t != Thread.currentThread()) {
			return false;
		} else {
			this.setExclusiveOwnerThread(null);
			return true;
		}

	}
}