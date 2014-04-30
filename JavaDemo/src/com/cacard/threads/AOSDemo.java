/**
 * AOS AbstractOwnableSynchronizer
 * �ó������װ��һ����ռ����ʵ��
 */

package com.cacard.threads;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;

public class AOSDemo {

	public static void main(String[] args) {

	}

	// ����AOS��
	static void testCustomeLock() {
		final MyCustomLock lock = new MyCustomLock();
		boolean r1 = lock.lock();
		boolean r2 = lock.lock();// �ٴμ���

		// �¿��̼߳���
		Executor exe = Executors.newFixedThreadPool(2);
		exe.execute(new Runnable() {
			@Override
			public void run() {
				boolean r3 = lock.lock();
				System.out.println("->r3:" + r3); // result:false
			}
		});

		// �ȴ� exe ִ�����
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		boolean r4 = lock.unlock();// ����
		System.out.println("->r1:" + r1 + "/r2" + r2 + ":/r4:" + r4); // result:true
	}

}

// ����AOS����һ���Զ�������ͬһ�߳̿ɶ�μ����������������ͷ�һ�μ��ɡ�
class MyCustomLock extends AbstractOwnableSynchronizer {
	private static final long serialVersionUID = 3394444822954946380L;

	// ��ȡ���������ڲ��̱߳���Ϊ��ǰ�߳�
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

	// �ͷ���
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