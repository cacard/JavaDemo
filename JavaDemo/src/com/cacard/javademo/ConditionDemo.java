/**
 * 条件变量 Condition
 * 应用场景：在锁的内部，需要等待条件，那么，先释放锁，阻塞等待，等满足条件后，获得锁并继续执行
 * 其它方法参考 object 的 wait() / notify() 方法
 */

package com.cacard.javademo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

	public static int count = 0;// 需要同步
	public static Lock lock = new ReentrantLock();//锁
	public static Condition condition = lock.newCondition();//锁对应的条件

	public static void main(String[] args) {
		new Thread(new ConditionWaiter()).start();
		new Thread(new ConditionMaker()).start();
	}

}

// 条件制造者
class ConditionMaker implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 11; i++) {
			ConditionDemo.lock.lock();
			ConditionDemo.count++;
			System.out.println("Maker ++ ,="+ConditionDemo.count);
			if (ConditionDemo.count >= 10) {
				System.out.println("Maker notify");
				ConditionDemo.condition.signalAll();//通知其它线程，条件满足。释放当前锁
				return;
			}
			ConditionDemo.lock.unlock();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

// 条件等待方
class ConditionWaiter implements Runnable {

	@Override
	public void run() {
		System.out.println("->Waiter run()");
		ConditionDemo.lock.lock();// 获取锁后，才可操作其Condition
		while (ConditionDemo.count < 10) {
			try {
				System.out.println("->Waiter await.)");
				ConditionDemo.condition.await();// 条件不满足，释放当前锁，等待信号（一旦获取信号，重新获得锁，并接着向下执行）
			
				System.out.println("->Waiter's condition ok.and count="+ConditionDemo.count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("->Waiter wait over.)");
		}
		ConditionDemo.lock.unlock();

	}

}